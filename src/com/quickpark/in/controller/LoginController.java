package com.quickpark.in.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.quickpark.in.Model.Login;
import com.quickpark.in.service.LoginService;

@Controller

public class LoginController {
	@Autowired
	private LoginService loginservice;

	public void setLoginservice(LoginService loginservice) {
		this.loginservice = loginservice;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView userLogin() {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("Index");

		return mv;

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView userLogin(@RequestParam("UserName") String UserName, @RequestParam("Password") String Password,HttpSession session) {

		ModelAndView mv = new ModelAndView();

		Login log = new Login();
		log.setUserName(UserName);
		log.setPassword(Password);

		Login log1=loginservice.validateUser(log);
		
		
			if(log1!=null)
			{
		if (log1.getUserName() != null) {

			if(log1.getRole().equalsIgnoreCase("vehicleowner"))
			{
				mv.setViewName("VehicleOwnerHome");
				session.setAttribute("username", UserName);
			}
			else if(log1.getRole().equalsIgnoreCase("propertyowner"))
			{
				mv.setViewName("PropertOwnerHome");
				session.setAttribute("username", UserName);
			}
			else if(log1.getRole().equalsIgnoreCase("admin"))
			{
			mv.setViewName("AdminHome");
			session.setAttribute("username", UserName);
			}
		}
		
			}
		else {

			mv.addObject("msg", "Invalid user id or password.");
			mv.setViewName("Index");
		}
		

		return mv;

	}

}
