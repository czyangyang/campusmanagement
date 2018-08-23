package com.czxx.campusmanagement.imp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.czxx.campusmanagement.dao.StudentMapper;
import com.czxx.campusmanagement.entity.Student;
import com.czxx.campusmanagement.entity.StudentExample;
import com.czxx.campusmanagement.in.StudentService;
import com.czxx.campusmanagement.io.Result;
import com.czxx.campusmanagement.io.student.CreateOrEditStudentInput;
import com.czxx.campusmanagement.io.student.DeleteStudentByIdInput;
import com.czxx.campusmanagement.io.student.GetAllStudentInput;
import com.czxx.campusmanagement.util.AutoMapper;
import com.czxx.campusmanagement.util.SpringContextUtil;
import com.czxx.campusmanagement.util.SystemConfig;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

	@Resource(name = "studentMapper")
	private StudentMapper studentMapper;

	@Override
	public Result CreateOrEditStudent(CreateOrEditStudentInput input) throws Exception {
		// TODO Auto-generated method stub
		Result result = new Result();
		if (input.getId() == 0) {
			//新增
			Student student = new Student();
			AutoMapper.mapping(input, student);
			student.setHeadimage("/static/fileUpload/headImages/defaultHeadImage.png");
			student.setIsdeleted(0);
			student.setCreatetime(new Date());
			int returnValue = studentMapper.insert(student);
			if (returnValue > 0) {
				result.setCode(1);
				result.setMessage("新增数据成功");
				result.setResult(returnValue);
			}
			else {
				throw new Exception("新增数据失败");
			}
		}
		else {
			//修改
			Student student = new Student();
			student.setId(input.getId());
			student.setStudentname(input.getStudentname());
			student.setSex(input.getSex());
			student.setAge(input.getAge());
			student.setClassid(input.getClassid());
			student.setGradeid(input.getGradeid());

			int returnValue = studentMapper.updateByPrimaryKeySelective(student);
			if (returnValue > 0) {
				result.setCode(1);
				result.setMessage("数据修改成功");
				result.setResult(returnValue);
			}
			else {
				throw new Exception("数据修改失败");
			}
		}
		return result;
	}


	@Override
	public Result DeleteStudentById(DeleteStudentByIdInput input) throws Exception {
		// TODO Auto-generated method stub
		Result result = new Result();
		int returnValue = studentMapper.deleteByPrimaryKey(input.getId());
		if (returnValue > 0)
		{
			result.setCode(1);
			result.setMessage("数据删除成功");
		}
		else {
			throw new Exception("数据删除失败或已删除");
		}
		return result;
	}


	@Override
	public Result GetAllStudent(GetAllStudentInput input) throws Exception {
		// TODO Auto-generated method stub
		StudentExample studentExample = SpringContextUtil.getBean(StudentExample.class);
		studentExample.setOrderByClause("id asc");
		
		studentExample.setOffset(input.getPage() * SystemConfig.getPagesize());
		studentExample.setLimit(SystemConfig.getPagesize());
		List<Student> students = studentMapper.selectByExampleWithRelaton(studentExample);

		long count = studentMapper.countByExample(studentExample);
		Result result = new Result();
		result.setCode(1);
		result.setMessage("数据查询成功");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", count);
		map.put("list", students);
		map.put("pagesize", SystemConfig.getPagesize());
		result.setResult(map);
		return result;
	}

}
