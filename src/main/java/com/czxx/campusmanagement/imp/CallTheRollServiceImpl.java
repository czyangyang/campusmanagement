package com.czxx.campusmanagement.imp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.czxx.campusmanagement.dao.CallTheRollDetailMapper;
import com.czxx.campusmanagement.dao.CallTheRollMapper;
import com.czxx.campusmanagement.entity.CallTheRoll;
import com.czxx.campusmanagement.entity.CallTheRollDetail;
import com.czxx.campusmanagement.entity.CallTheRollExample;
import com.czxx.campusmanagement.in.CallTheRollService;
import com.czxx.campusmanagement.io.Result;
import com.czxx.campusmanagement.io.calltheroll.*;
import com.czxx.campusmanagement.util.AutoMapper;
import com.czxx.campusmanagement.util.SpringContextUtil;
import com.czxx.campusmanagement.util.SystemConfig;

@Service("callTheRollService")
public class CallTheRollServiceImpl implements CallTheRollService {

	@Resource(name = "callTheRollMapper")
	private CallTheRollMapper callTheRollMapper;
	
	@Resource(name = "callTheRollDetailMapper")
	private CallTheRollDetailMapper callTheRollDetailMapper;

	@Override
	@Transactional
	public Result CreateOrEditCallTheRoll(CreateOrEditCallTheRollInput input) throws Exception {
		
		Result result = new Result();
		if (input.getId() == 0) {
			if (input.getStudentids().size() != input.getConfirms().size())
			{
				throw new Exception("数据不正确");
			}
			//新增
			CallTheRoll callTheRoll = new CallTheRoll();
			callTheRoll.setTitle(input.getTitle());
			callTheRoll.setIsdeleted(0);
			callTheRoll.setCreatetime(new Date());
			callTheRoll.setClassid(input.getClassid());
			int returnValue = callTheRollMapper.insert(callTheRoll);
			
			int returnValue2 = 1;
			
			for (int i = 0; i < input.getStudentids().size(); i++) {
				CallTheRollDetail callTheRollDetail = new CallTheRollDetail();
				callTheRollDetail.setCalltherollid(callTheRoll.getId());
				callTheRollDetail.setStudentid(input.getStudentids().get(i));
				callTheRollDetail.setConfirm(input.getConfirms().get(i));

				if(callTheRollDetailMapper.insert(callTheRollDetail)<=0)
				{
					returnValue2 = 0;
				}
			}
			
			if (returnValue > 0 && returnValue2 > 0) {
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
			CallTheRoll callTheRoll = new CallTheRoll();
			callTheRoll.setId(input.getId());
			callTheRoll.setTitle(input.getTitle());

			int returnValue = callTheRollMapper.updateByPrimaryKeySelective(callTheRoll);
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
	public Result DeleteCallTheRollById(DeleteCallTheRollByIdInput input) throws Exception {
		
		Result result = new Result();
		int returnValue = callTheRollMapper.deleteByPrimaryKey(input.getId());
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
	public Result GetAllCallTheRoll(GetAllCallTheRollInput input) throws Exception {
		
		CallTheRollExample callTheRollExample = SpringContextUtil.getBean(CallTheRollExample.class);
		callTheRollExample.setOrderByClause("id asc");
		
		callTheRollExample.setOffset(input.getPage() * SystemConfig.getPagesize());
		callTheRollExample.setLimit(SystemConfig.getPagesize());
		List<CallTheRoll> callTheRolls = callTheRollMapper.selectByExampleWithRelation(callTheRollExample);

		long count = callTheRollMapper.countByExampleWithRelation(callTheRollExample);
		Result result = new Result();
		result.setCode(1);
		result.setMessage("数据查询成功");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", count);
		map.put("list", callTheRolls);
		map.put("pagesize", SystemConfig.getPagesize());
		result.setResult(map);
		return result;
	}


	@Override
	public Result GetCallTheRollById(GetCallTheRollByIdInput input) throws Exception {
		Result result = new Result();
		
		List<CallTheRollDetail> callTheRollDetails = callTheRollDetailMapper.selectDetailWithRelation(input.getId());
		System.out.println("-------"+callTheRollDetails.size());
		result.setCode(1);
		result.setMessage("数据查询成功");
		result.setResult(callTheRollDetails);
		return result;
	}

}
