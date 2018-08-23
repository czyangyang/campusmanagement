package com.czxx.campusmanagement.imp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.czxx.campusmanagement.dao.AccountMapper;
import com.czxx.campusmanagement.entity.Account;
import com.czxx.campusmanagement.entity.AccountExample;
import com.czxx.campusmanagement.entity.AccountExample.Criteria;
import com.czxx.campusmanagement.in.AccountService;
import com.czxx.campusmanagement.io.Result;
import com.czxx.campusmanagement.io.account.CreateOrEditAccountInput;
import com.czxx.campusmanagement.io.account.DeleteAccountByIdInput;
import com.czxx.campusmanagement.io.account.GetAllAccountInput;
import com.czxx.campusmanagement.io.account.LoginInput;
import com.czxx.campusmanagement.util.AutoMapper;
import com.czxx.campusmanagement.util.CzxxHelper;
import com.czxx.campusmanagement.util.SpringContextUtil;
import com.czxx.campusmanagement.util.SystemConfig;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

	@Resource(name = "accountMapper")
	private AccountMapper accountMapper;
	
	@Override
	public Result Login(LoginInput input) throws Exception {
		// TODO Auto-generated method stub
		AccountExample accountExample = SpringContextUtil.getBean(AccountExample.class);
		Criteria criteria = accountExample.createCriteria();
		criteria.andUsernameEqualTo(input.getUsername());
		List<Account> accounts = accountMapper.selectByExample(accountExample);
		
		Result result = new Result();
		if (accounts.size() == 1) {
			Account account = accounts.get(0);
			if (account.getPassword().equals(input.getPassword()))
			{
				//throw new Exception("登录成功");
				result.setCode(1);
				result.setMessage("登录成功");
				result.setResult(account);
			}
			else {
				throw new Exception("密码错误");
				//result.setCode(2);
				//result.setMessage("密码错误");
			}
		}
		else if (accounts.size() > 1)
		{
			throw new Exception("数据有误，请联系管理员");
			//result.setCode(-1);
			//result.setMessage("数据有误，请联系管理员");
		}
		else {
			throw new Exception("用户不存在");
			//result.setCode(0);
			//result.setMessage("用户不存在");
		}
		return result;
	}


	@Override
	public Result Test() {
		// TODO Auto-generated method stub
		AccountExample accountExample = SpringContextUtil.getBean(AccountExample.class);
		System.out.println(accountExample.getOrderByClause().toString());
		return null;
	}


	@Override
	public Result CreateOrEditAccount(CreateOrEditAccountInput input) throws Exception {
		// TODO Auto-generated method stub
		Result result = new Result();
		if (input.getId() == 0) {
			//新增
			Account account = new Account();
			AutoMapper.mapping(input, account);
			account.setPassword("123456");
			account.setIsdeleted(0);
			account.setCreatetime(new Date());
			account.setHeadimage("/fileUpload/headImages/defaultHeadImage.png");
			account.setLastlogintime(CzxxHelper.StringToDate("1990-01-01 00:00:00"));
			int returnValue = accountMapper.insert(account);
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
			Account account = new Account();
			account.setId(input.getId());
			account.setRealname(input.getRealname());

			int returnValue = accountMapper.updateByPrimaryKeySelective(account);
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
	public Result DeleteAccountById(DeleteAccountByIdInput input) throws Exception {
		// TODO Auto-generated method stub
		Result result = new Result();
		int returnValue = accountMapper.deleteByPrimaryKey(input.getId());
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
	public Result GetAllAccount(GetAllAccountInput input) throws Exception {
		// TODO Auto-generated method stub
		AccountExample accountExample = SpringContextUtil.getBean(AccountExample.class);
		accountExample.setOrderByClause("id asc");
		
		accountExample.setOffset(input.getPage() * SystemConfig.getPagesize());
		accountExample.setLimit(SystemConfig.getPagesize());
		List<Account> accounts = accountMapper.selectByExample(accountExample);
		System.out.println(accounts.size());
		
		System.out.println(accountExample.getOffset());
		System.out.println(accountExample.getLimit());
		long count = accountMapper.countByExample(accountExample);
		Result result = new Result();
		result.setCode(1);
		result.setMessage("数据查询成功");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", count);
		map.put("list", accounts);
		map.put("pagesize", SystemConfig.getPagesize());
		result.setResult(map);
		return result;
	}

}
