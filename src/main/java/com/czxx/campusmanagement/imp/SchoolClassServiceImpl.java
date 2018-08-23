package com.czxx.campusmanagement.imp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.czxx.campusmanagement.dao.SchoolClassMapper;
import com.czxx.campusmanagement.entity.SchoolClass;
import com.czxx.campusmanagement.entity.SchoolClassExample;
import com.czxx.campusmanagement.in.SchoolClassService;
import com.czxx.campusmanagement.io.Result;
import com.czxx.campusmanagement.io.schoolclass.CreateOrEditSchoolClassInput;
import com.czxx.campusmanagement.io.schoolclass.DeleteSchoolClassByIdInput;
import com.czxx.campusmanagement.io.schoolclass.GetAllSchoolClassInput;
import com.czxx.campusmanagement.io.schoolclass.GetAllSchoolClassWithOutPagedInput;
import com.czxx.campusmanagement.util.AutoMapper;
import com.czxx.campusmanagement.util.SpringContextUtil;
import com.czxx.campusmanagement.util.SystemConfig;

@Service("schoolClassService")
public class SchoolClassServiceImpl implements SchoolClassService {

	@Resource(name = "schoolClassMapper")
	private SchoolClassMapper schoolClassMapper;

	@Override
	public Result CreateOrEditSchoolClass(CreateOrEditSchoolClassInput input) throws Exception {
		// TODO Auto-generated method stub
		Result result = new Result();
		if (input.getId() == 0) {
			//新增
			SchoolClass schoolClass = new SchoolClass();
			AutoMapper.mapping(input, schoolClass);
			schoolClass.setIsdeleted(0);
			schoolClass.setCreatetime(new Date());
			int returnValue = schoolClassMapper.insert(schoolClass);
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
			SchoolClass schoolClass = new SchoolClass();
			schoolClass.setId(input.getId());
			schoolClass.setClassname(input.getClassname());
			schoolClass.setDescription(input.getDescription());

			int returnValue = schoolClassMapper.updateByPrimaryKeySelective(schoolClass);
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
	public Result DeleteSchoolClassById(DeleteSchoolClassByIdInput input) throws Exception {
		// TODO Auto-generated method stub
		Result result = new Result();
		int returnValue = schoolClassMapper.deleteByPrimaryKey(input.getId());
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
	public Result GetAllSchoolClass(GetAllSchoolClassInput input) throws Exception {
		// TODO Auto-generated method stub
		SchoolClassExample schoolClassExample = SpringContextUtil.getBean(SchoolClassExample.class);
		schoolClassExample.setOrderByClause("id asc");
		
		schoolClassExample.setOffset(input.getPage() * SystemConfig.getPagesize());
		schoolClassExample.setLimit(SystemConfig.getPagesize());
		List<SchoolClass> schoolClasss = schoolClassMapper.selectByExample(schoolClassExample);

		long count = schoolClassMapper.countByExample(schoolClassExample);
		Result result = new Result();
		result.setCode(1);
		result.setMessage("数据查询成功");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", count);
		map.put("list", schoolClasss);
		map.put("pagesize", SystemConfig.getPagesize());
		result.setResult(map);
		return result;
	}


	@Override
	public Result GetAllSchoolClassWithOutPaged(GetAllSchoolClassWithOutPagedInput input) throws Exception {
		// TODO Auto-generated method stub
		SchoolClassExample schoolClassExample = SpringContextUtil.getBean(SchoolClassExample.class);
		schoolClassExample.setOrderByClause("id asc");
		
		List<SchoolClass> schoolClasss = schoolClassMapper.selectByExample(schoolClassExample);

		long count = schoolClassMapper.countByExample(schoolClassExample);
		Result result = new Result();
		result.setCode(1);
		result.setMessage("数据查询成功");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", count);
		map.put("list", schoolClasss);
		result.setResult(map);
		return result;
	}

}
