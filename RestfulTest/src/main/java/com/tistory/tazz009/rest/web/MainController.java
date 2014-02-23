package com.tistory.tazz009.rest.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tistory.tazz009.rest.service.ApiResult;
import com.tistory.tazz009.rest.service.GridVO;
import com.tistory.tazz009.rest.service.MainService;

@Controller
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private MainService service ;
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main() {
		
		return "main";
	}
	
	@RequestMapping(value = "/lists/{pageNo}", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult lists(@PathVariable Integer pageNo, HttpServletRequest request, @RequestParam Map<String, String> params) {
		
		System.out.println("pageNo : " + pageNo);
		
		MapUtils.debugPrint(System.out, null, params);
		
		request.setAttribute("ajaxFlag", "true");
		
		ApiResult result = service.getList(pageNo);
		
		return result;
	}
	
	@RequestMapping(value = "/jqGrid", method = RequestMethod.GET)
	public String jqGrid() {
		
		return "jqGrid";
	}
	
	@RequestMapping(value = "/jqGrid/lists/{pageNo}", method = RequestMethod.POST)
	@ResponseBody
	public GridVO jqGridLists(@PathVariable Integer pageNo, HttpServletRequest request, @RequestParam Map<String, String> params) {
		
		System.out.println("pageNo : " + pageNo);
		
		MapUtils.debugPrint(System.out, null, params);
		
		request.setAttribute("ajaxFlag", "true");
		
		GridVO result = service.getJqtGridList(pageNo);
		
		return result;
	}

	@RequestMapping(value = "/jqGrid/save", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult jqGridSave(@RequestParam Map<String, String> params) {
		
		MapUtils.debugPrint(System.out, null, params);
		
		ApiResult result = new ApiResult();
		
		return result;
	}
}
