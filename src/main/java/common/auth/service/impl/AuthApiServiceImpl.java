package common.auth.service.impl;

import java.util.Map;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import common.auth.service.AuthApiService;
import common.auth.service.mapper.AuthApiMapper;

@Service("AuthApiService")
public class AuthApiServiceImpl implements AuthApiService{
	
	@Resource
	private AuthApiMapper authApiMapper;

	@Override
    public boolean getAccount(Map<String, String> loginRequest) {
		
		int count = authApiMapper.checkLogin(loginRequest);
        return count > 0;
    }
	
}
