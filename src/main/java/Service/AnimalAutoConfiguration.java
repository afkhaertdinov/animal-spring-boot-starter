package Service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.*;

@Configuration
@PropertySource("classpath:init.properties")
public class AnimalAutoConfiguration {

    @SuppressWarnings("unused")
    @Bean
    @ConditionalOnMissingBean
    public InitAnimal initAnimal() {
        return new InitAnimal();
    }

}
