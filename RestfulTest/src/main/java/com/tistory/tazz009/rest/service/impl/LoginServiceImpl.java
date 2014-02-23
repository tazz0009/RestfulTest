package com.tistory.tazz009.rest.service.impl;

import org.springframework.stereotype.Service;

import com.tistory.tazz009.rest.service.ApiResult;
import com.tistory.tazz009.rest.service.LoginService;
import com.tistory.tazz009.rest.service.UserVO;

@Service
public class LoginServiceImpl implements LoginService {

	public ApiResult loginAction(UserVO vo) {
		ApiResult result = new ApiResult();
		if (vo.getId().equals("tazz")) {
			result.setStatus("SUCCESS");
			result.setData(vo);
			result.setMsg("한글테스트");
		} else {
			result.setStatus("FAIL");
			result.setData(vo);
			result.setMsg("한글테스트");
		}
		return result;
	}

}
