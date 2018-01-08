package today.smarthealthcare.myhealth.ihealth.config;

import org.springframework.context.annotation.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.client.RestTemplate;


@Configuration
@PropertySources({
        @PropertySource(value = "conf/ihealth-default.properties")
})
@EnableOAuth2Client
@ComponentScan({"today.smarthealthcare.myhealth.ihealth"})
public class IHealthClientConfig {

    @Bean
    public RestTemplate iHealthRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        return restTemplate;
    }
}
