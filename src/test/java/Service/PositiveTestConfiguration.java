package Service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:positive.properties")
public class PositiveTestConfiguration {

    @SuppressWarnings("unused")
    @Bean
    @ConditionalOnMissingBean
    public InitAnimal initAnimal() {
        return new InitAnimal();
    }

}
