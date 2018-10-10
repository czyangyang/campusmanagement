package com.czxx.campusmanagement.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import com.czxx.campusmanagement.io.Result;
import com.czxx.campusmanagement.io.websocket.SignInInput;
import com.czxx.campusmanagement.websocket.SpringWebSocketHandler;

@Controller
public class WebSocketController {

	@Bean
    public SpringWebSocketHandler infoHandler() {
 
        return new SpringWebSocketHandler();
    }
	
    @RequestMapping("/websocket/send")
    @ResponseBody
    public String send(HttpServletRequest request) {
        String username = request.getParameter("username");
        infoHandler().sendMessageToUser(username, new TextMessage("你好，测试！！！！"));
        return null;
    }
 
 
    @RequestMapping("/websocket/broad")
    @ResponseBody
    public  String broad() {
        infoHandler().sendMessageToUsers(new TextMessage("发送一条小Broad"));
        System.out.println("群发成功");
        return "broad";
    }
    
    @RequestMapping(value="/websocket/signin",method = RequestMethod.POST)
    @ResponseBody
    public Result signin(@RequestBody SignInInput input) {
    	Result result = new Result();
        String studentno = input.getStudentno();
        String studentname = input.getStudentname();
        infoHandler().sendMessageToUser("1", new TextMessage(studentname+"["+studentno+"]已签到"));
        result.setCode(1);
        result.setMessage("签到成功");
        return result;
    }
}
