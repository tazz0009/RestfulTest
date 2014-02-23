package com.tistory.tazz009.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class RestExceptionResolver implements HandlerExceptionResolver {

	public ModelAndView resolveException(HttpServletRequest req,
			HttpServletResponse res, Object arg2, Exception e) {

		Boolean ajaxFlag = req.getAttribute("ajaxFlag") == null? false: true;
		System.out.println("ajaxFlag : " + ajaxFlag);
		String msg = ExceptionUtils.getStackTrace(e);
		System.out.println("exception : " + msg);
		
		ModelAndView view = new ModelAndView();
		
		if (ajaxFlag) {
			view.setViewName("jsonView");
			JSONObject jsObj = new JSONObject();
			jsObj.put("status", "FAIL");
			jsObj.put("msg", msg);
			view.addObject("result", jsObj.toString());
			
		} else {
			view.setViewName("error");
			view.addObject("msg", msg);
		}
		
		return view;
	}


}
