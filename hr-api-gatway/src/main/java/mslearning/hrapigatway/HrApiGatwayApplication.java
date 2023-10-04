package mslearning.hrapigatway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class HrApiGatwayApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrApiGatwayApplication.class, args);
	}

}
