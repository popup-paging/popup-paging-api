package common.address.controller;

import java.util.List;
import java.util.Map;

import jakarta.annotation.Resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;
import common.address.service.AddressApiService;


@Slf4j
@RestController
@RequestMapping(value="/common/address")
public class AddressApiController {
	
	@Resource(name = "AddressApiService")
	private AddressApiService addressApiService;
	
	@ResponseBody
	@PostMapping(value="/getCityList")
	public ResponseEntity<String> getCityList(@RequestBody Map<String, Object> param) throws Throwable {
		
		String result = "";
		HttpHeaders resHeader = new HttpHeaders();
		
		try {
			List<Map<String, Object>> map = addressApiService.getCityList(param);
		    Gson gson = new Gson();
		    result = gson.toJson(map, List.class);

		}catch(Exception e) {
			log.error("getCityList: {}" + e.getMessage());
		};
		
		resHeader.add("Content-Type", "application/json;charset=UTF-8");
		
		return new ResponseEntity<String>(result, resHeader, HttpStatus.CREATED);
	}
	
}
