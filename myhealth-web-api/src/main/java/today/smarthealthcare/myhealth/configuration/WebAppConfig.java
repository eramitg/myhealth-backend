package today.smarthealthcare.myhealth.configuration;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostException;
import com.sparkpost.transport.RestConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class WebAppConfig {
    @Autowired
    private Client client;
    @Bean
    public RestConnection restConnection() throws SparkPostException {
        return new RestConnection(client);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return Jackson2ObjectMapperBuilder
                .json()
                .featuresToEnable(MapperFeature.DEFAULT_VIEW_INCLUSION)
                .build();
    }
}
