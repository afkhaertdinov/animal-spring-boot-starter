package Service;

import org.junit.jupiter.api.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertAll;


class InitAnimalNegativeTest {
    static AnnotationConfigApplicationContext context;
    static InitAnimal contextBean;

    @BeforeAll
    static void setUp() {
        context = new AnnotationConfigApplicationContext(NegativeTestConfiguration.class);
        contextBean = context.getBean(InitAnimal.class);
    }

    @AfterAll
    static void close() {
        if (context != null)
            if (context.isRunning()) context.close();
    }

    @Test
    @DisplayName("Негативный тест метода getName()")
    void getName() {
        assertAll(
                "Сгруппировали проверку для разных животных, в том числе несуществующих",
                () -> Assertions.assertEquals("not Name", contextBean.getName("Cow")),
                () -> Assertions.assertEquals("not Name", contextBean.getName("Cat")),
                () -> Assertions.assertEquals("not Name", contextBean.getName("Dog")),
                () -> Assertions.assertEquals("not Name", contextBean.getName("Wolf")),
                () -> Assertions.assertEquals("not Name", contextBean.getName("Shark"))
        );
    }

    @Test
    @DisplayName("Негативный тест метода getSecret()")
    void getSecret() {
        Assertions.assertEquals("not Secret", contextBean.getSecret());
    }

    @Test
    @DisplayName("Негативный тест метода getCharacter()")
    void getCharacter() {
        Assertions.assertEquals("not Character", contextBean.getCharacter());
    }

    @Test
    @DisplayName("Негативный тест метода getCost()")
    void getCost() {
        Double cost = contextBean.getCost();
        Assertions.assertFalse((-15 <= cost) && (0 >= cost));
    }

    @Test
    @DisplayName("Негативный тест метода getBirthDate()")
    void getBirthDate() {
        LocalDate etalon = contextBean.getBirthDate();
        Assertions.assertTrue((etalon.isAfter(LocalDate.EPOCH)) && (etalon.isBefore(LocalDate.now())));
    }
}