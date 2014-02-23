package com.tistory.tazz009.rest.web;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tistory.tazz009.rest.service.ApiResult;
import com.tistory.tazz009.rest.service.LoginService;
import com.tistory.tazz009.rest.service.UserVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private LoginService service;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! the client locale is "+ locale.toString());
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		UserVO user = new UserVO();
		model.addAttribute("user", user);
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/loginAction", method = RequestMethod.POST)
	@ResponseBody
	public  ApiResult loginAction(@ModelAttribute("user") UserVO vo, Locale locale, Model model, 
			HttpServletRequest request) {
		
		System.out.println("vo : " + vo.toString());
		
		request.setAttribute("ajaxFlag", "true");
		
		ApiResult result = service.loginAction(vo);
		
		System.out.println("result : " + result.toString());
		
		return result;
	}
	
}
