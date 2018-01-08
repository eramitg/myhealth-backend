package today.smarthealthcare.myhealth;

import com.sparkpost.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Bean
	public Client client() {
		return new Client("/***/");
	}
}
