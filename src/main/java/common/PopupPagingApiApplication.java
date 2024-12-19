package common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.ComponentScan;
import org.mybatis.spring.annotation.MapperScan;

//@ComponentScan(basePackages="common.**")
@SpringBootApplication
@MapperScan("common.address.service.mapper")
public class PopupPagingApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PopupPagingApiApplication.class, args);
	}

}
