package common.auth.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import common.address.service.AddressApiService;


@RestController
@RequestMapping(value="/common/auth")
public class AuthApiController {
	
	@Autowired
    private RedisTemplate<String, Object> redisTemplate;
	
	//@Resource(name = "AuthApiService")
	//private AuthApiService authApiService;
	
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> loginRequest) {
        
    	String username = loginRequest.get("username");
        String password = loginRequest.get("password");
        
        System.out.println(":: username ::");
        System.out.println(username);
        
        System.out.println(":: password ::");
        System.out.println(password);

        // 1. 사용자 인증 (DB 연동 등)
        if ("user".equals(username) && "1234".equals(password)) {
            // 2. 인증 성공 시, 세션 ID 생성
            String sessionId = UUID.randomUUID().toString();
            
            // 3. Redis에 세션 정보 저장 (세션 ID를 Key로, 사용자 정보를 Value로)
            // 30분 만료 시간 설정
            redisTemplate.opsForValue().set(sessionId, username, 30, TimeUnit.MINUTES);

            // 4. 세션 ID를 클라이언트에 반환
            Map<String, String> response = new HashMap<>();
            response.put("sessionId", sessionId);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
    
    // POST /auth/logout
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String sessionId) {
        // 1. Redis에서 세션 정보 삭제
        redisTemplate.delete(sessionId);
        
        return ResponseEntity.ok().build();
    }
	
}
