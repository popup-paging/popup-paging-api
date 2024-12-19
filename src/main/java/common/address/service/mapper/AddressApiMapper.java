package common.address.service.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressApiMapper {
	
	List<Map<String, Object>> getCityList(Map<String, Object> param) throws Exception, SQLException;
	
}
