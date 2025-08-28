package common.auth.service.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthApiMapper {
	
	int checkLogin(Map<String, String> param);
	
}
