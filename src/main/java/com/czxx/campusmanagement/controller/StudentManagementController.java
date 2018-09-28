package com.czxx.campusmanagement.controller;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.czxx.campusmanagement.entity.Student;
import com.czxx.campusmanagement.in.GradeService;
import com.czxx.campusmanagement.in.SchoolClassService;
import com.czxx.campusmanagement.in.StudentService;
import com.czxx.campusmanagement.io.Result;
import com.czxx.campusmanagement.io.grade.GetAllGradeInput;
import com.czxx.campusmanagement.io.schoolclass.GetAllSchoolClassWithOutPagedInput;
import com.czxx.campusmanagement.io.student.CreateOrEditStudentInput;
import com.czxx.campusmanagement.util.ExcelRead;
import com.czxx.campusmanagement.util.ExcelUtil;

@Controller
@RequestMapping(value = "/studentmanagement")
public class StudentManagementController {
	
	@Resource(name = "schoolClassService")
	private SchoolClassService schoolClassService;
	
	@Resource(name = "gradeService")
	private GradeService gradeService;
	
	@Resource(name = "studentService")
	private StudentService studentService;
	
	@Cacheable(value="cacheTest")
	@RequestMapping(value = "/index")
	public ModelAndView Index() throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("studentmanagement");
		
		//获取班级和年级信息
		Result schoolClassResult = schoolClassService.GetAllSchoolClassWithOutPaged(new GetAllSchoolClassWithOutPagedInput());
		modelAndView.addObject("schoolclassList", schoolClassResult.getResult());
		
		Result gradeResult = gradeService.GetAllGrade(new GetAllGradeInput());
		modelAndView.addObject("gradeList", gradeResult.getResult());
		
		return modelAndView;
	}
	
	/* 读取Excel数据到数据库
	 * @param file
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@Transactional(rollbackFor=Exception.class)
	@RequestMapping(value="/readexcel", method = RequestMethod.POST)	
	public void readExcel(@RequestParam(value="excelFile") MultipartFile file,HttpServletRequest request,HttpServletResponse response, HttpSession session) throws Exception{
//		try {
			//判断文件是否为空
			if(file == null){
				response.setContentType("text/html");
				response.getWriter().write("无法获取文件!");
			}
	               String name = file.getOriginalFilename();
			long size = file.getSize();
			if(name == null || ExcelUtil.EMPTY.equals(name) && size==0){
				response.setContentType("text/html");
				response.getWriter().write("无法获取文件!");
			}
			//读取Excel数据到List中
			List<ArrayList<String>> list = new ExcelRead().readExcel(file);
	        //list中存的就是excel中的数据，可以根据excel中每一列的值转换成你所需要的值（从0开始），如：
			Student student = null;
			List<Student> listStudent = new ArrayList<Student>();
			for(ArrayList<String> arr:list){						
				student = new Student();						
				student.setStudentname(arr.get(0).toString());//每一行的第一个单元格
				String sex = arr.get(1).toString();
				if(sex.equals("男"))
				{
					student.setSex(0);
				}else {
					student.setSex(1);
				}
				student.setAge(Integer.parseInt(arr.get(2).toString()));
				student.setClassid(Integer.parseInt(arr.get(3).toString()));
				student.setHeadimage("");
				student.setGradeid(Integer.parseInt(arr.get(5).toString()));
				listStudent.add(student);
			}
			int index = 0;
			for(Student stu: listStudent)
			{
				CreateOrEditStudentInput createOrEditStudentInput = new CreateOrEditStudentInput();
				createOrEditStudentInput.setStudentname(stu.getStudentname());
				createOrEditStudentInput.setAge(stu.getAge());
				createOrEditStudentInput.setSex(stu.getSex());
				createOrEditStudentInput.setHeadimage(stu.getHeadimage());
				if(index == 2)
				{
					throw new RuntimeException("出错了");
//					throw new Exception("");
				}
				createOrEditStudentInput.setClassid(stu.getClassid());
				createOrEditStudentInput.setGradeid(stu.getGradeid());
				studentService.CreateOrEditStudent(createOrEditStudentInput);
				
				index++;
			}
			response.sendRedirect("../studentmanagement/index");
//		}
//		catch (Exception e) {
//			System.err.println(e.getMessage());
//			response.setContentType("text/html");
//			response.getWriter().write("出错了！");
//		}
	}
	
	
	@RequestMapping(value = "/exportexcel")
    @ResponseBody
    public void exportExcel(HttpServletRequest request,HttpServletResponse response) throws Exception {
		//获取数据
	    List<Student> list = (List<Student>)studentService.GetAllStudentWithOutPaged().getResult();
	    //excel标题
	    String[] title = {"学生姓名","性别","年龄","班级","年级"};
	    //excel文件名
	    String fileName = "Stu"+System.currentTimeMillis()+".xls";
		//sheet名
		String sheetName = "学生信息表";
		String[][] content = new String[list.size()][5];
		for (int i = 0; i < list.size(); i++) {
			content[i] = new String[title.length];
			Student obj = list.get(i);
			content[i][0] = obj.getStudentname().toString();
			content[i][1] = obj.getSex().toString();
			content[i][2] = obj.getAge().toString();
			content[i][3] = obj.getClassid().toString();
			content[i][4] = obj.getGradeid().toString();
		}

		//创建HSSFWorkbook 
		HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);
		//响应到客户端
		try {
			this.setResponseHeader(response, fileName);
			OutputStream os = response.getOutputStream();
			wb.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
	
		}
	}
	
	//发送响应流方法
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
//            try {
//                fileName = new String(fileName.getBytes(),"UTF-8");
//            } catch (UnsupportedEncodingException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
	
}
