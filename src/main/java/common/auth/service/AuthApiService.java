package common.auth.service;

import java.util.Map;

public interface AuthApiService {
	
	boolean getAccount(Map<String, String> loginRequest);
	
}