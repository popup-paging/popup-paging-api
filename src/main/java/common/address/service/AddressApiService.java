package common.address.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface AddressApiService {
	
	List<Map<String, Object>> getCityList(Map<String, Object> param) throws SQLException, Exception;
	
}