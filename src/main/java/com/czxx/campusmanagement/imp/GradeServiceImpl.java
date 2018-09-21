package com.czxx.campusmanagement.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.czxx.campusmanagement.dao.GradeMapper;
import com.czxx.campusmanagement.entity.Grade;
import com.czxx.campusmanagement.entity.GradeExample;
import com.czxx.campusmanagement.in.GradeService;
import com.czxx.campusmanagement.io.Result;
import com.czxx.campusmanagement.io.grade.GetAllGradeInput;
import com.czxx.campusmanagement.util.SpringContextUtil;

@Service("gradeService")
public class GradeServiceImpl implements GradeService {

	@Resource(name = "gradeMapper")
	private GradeMapper gradeMapper;

	@Override
	public Result GetAllGrade(GetAllGradeInput input) throws Exception {
		
		GradeExample gradeExample = SpringContextUtil.getBean(GradeExample.class);
		gradeExample.setOrderByClause("id asc");
		
		List<Grade> grades = gradeMapper.selectByExample(gradeExample);

		long count = gradeMapper.countByExample(gradeExample);
		Result result = new Result();
		result.setCode(1);
		result.setMessage("数据查询成功");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", count);
		map.put("list", grades);
		result.setResult(map);
		return result;
	}

}
