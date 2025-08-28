package common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("common")
public class PopupPagingApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PopupPagingApiApplication.class, args);
	}

}
