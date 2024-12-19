package common.address.service.impl;

import java.sql.SQLException;
import java.util.Map;
import java.util.List;

import jakarta.annotation.Resource;

import org.springframework.stereotype.Service;

import common.address.service.AddressApiService;
import common.address.service.mapper.AddressApiMapper;

@Service("AddressApiService")
public class AddressApiServiceImpl implements AddressApiService{
	
	@Resource
	private AddressApiMapper addressApiMapper;

	@Override
	public List<Map<String, Object>> getCityList(Map<String, Object> param) throws SQLException, Exception {		
		return addressApiMapper.getCityList(param);
	}
	
}
