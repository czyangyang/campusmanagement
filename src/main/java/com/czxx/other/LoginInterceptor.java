package com.czxx.other;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.czxx.campusmanagement.entity.Account;


public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
        //获取session
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("ACCOUNT_SESSION");
        if (account != null) {
            return true;
        }
        request.setAttribute("msg", "您还没有登录，请先登录！");
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/login/index");
        return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
