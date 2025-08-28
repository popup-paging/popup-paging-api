package common.auth.controller;

import java.time.Duration;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

import common.auth.service.AuthApiService;


@RestController
@RequestMapping("/common/auth")
public class AuthApiController {

	@Resource(name = "AuthApiService")
	private AuthApiService authApiService;
	
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody Map<String, String> loginRequest) {
    	
    	// DB 조회 (아이디+비밀번호 일치 확인)
        boolean isAuthenticated = authApiService.getAccount(loginRequest);
        
        if (isAuthenticated) {
        	
        	String username = loginRequest.get("username");
            String password = loginRequest.get("password");
        	
            String sessionId = UUID.randomUUID().toString();
            redisTemplate.opsForValue().set(sessionId, username, 30, TimeUnit.MINUTES);

            ResponseCookie sessionCookie = ResponseCookie.from("sessionId", sessionId)
                    .httpOnly(true)
                    .path("/")
                    .maxAge(Duration.ofMinutes(30))
                    .sameSite("Strict")
                    .build();

            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, sessionCookie.toString())
                    .build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/secure-endpoint")
    public ResponseEntity<String> secured(
            @CookieValue(value = "sessionId", required = false) String sessionId) {

        if (sessionId == null || redisTemplate.opsForValue().get(sessionId) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("세션 만료");
        }

        String username = redisTemplate.opsForValue().get(sessionId);
        return ResponseEntity.ok("Hello " + username);
    }
    
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@CookieValue(value = "sessionId", required = false) String sessionId) {
        if (sessionId != null) {
            redisTemplate.delete(sessionId); // Redis에서 세션 삭제
        }

        // 세션 쿠키 만료 처리 (클라이언트 측 삭제 유도)
        ResponseCookie deleteCookie = ResponseCookie.from("sessionId", "")
                .httpOnly(true)
                .path("/")
                .maxAge(0) // 즉시 만료
                .sameSite("Strict")
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, deleteCookie.toString())
                .build();
    }
    
}

