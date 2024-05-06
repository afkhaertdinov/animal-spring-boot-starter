package Service;

import org.junit.jupiter.api.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


class InitAnimalPositiveTest {
    static AnnotationConfigApplicationContext context;
    static InitAnimal contextBean;
    List<String> etalonList = new ArrayList<>();

    @BeforeAll
    static void setUp() {
        context = new  AnnotationConfigApplicationContext(PositiveTestConfiguration.class);
        contextBean = context.getBean(InitAnimal.class);
    }

    @AfterAll
    static void close() {
        context.close();
    }

    @Test
    @DisplayName("Позитивный тест метода getName()")
    void getName() {
        etalonList.clear();
        etalonList.addAll(List.of(new String[]{"CatName1", "CatName2", "CatName3"}));
        for (int i=0; i<100; i++)
            Assertions.assertTrue(etalonList.contains(contextBean.getName("Cat")));

        etalonList.clear();
        etalonList.addAll(List.of(new String[]{"DogName1", "DogName2", "DogName3"}));
        for (int i=0; i<100; i++)
            Assertions.assertTrue(etalonList.contains(contextBean.getName("Dog")));

        etalonList.clear();
        etalonList.addAll(List.of(new String[]{"WolfName1", "WolfName2", "WolfName3"}));
        for (int i=0; i<100; i++)
            Assertions.assertTrue(etalonList.contains(contextBean.getName("Wolf")));

        etalonList.clear();
        etalonList.addAll(List.of(new String[]{"SharkName1", "SharkName2", "SharkName3"}));
        for (int i=0; i<100; i++)
            Assertions.assertTrue(etalonList.contains(contextBean.getName("Shark")));
    }

    @Test
    @DisplayName("Позитивный тест метода getSecret()")
    void getSecret() {
        etalonList.clear();
        etalonList.addAll(List.of(new String[]{"Secrets1", "Secrets2", "Secrets3"}));
        for (int i=0; i<100; i++)
            Assertions.assertTrue(etalonList.contains(contextBean.getSecret()));
    }

    @Test
    @DisplayName("Позитивный тест метода getCharacter()")
    void getCharacter() {
        etalonList.clear();
        etalonList.addAll(List.of(new String[]{"Characters1", "Characters2", "Characters3"}));
        for (int i=0; i<100; i++)
            Assertions.assertTrue(etalonList.contains(contextBean.getCharacter()));
    }

    @Test
    @DisplayName("Позитивный тест метода getCost()")
    void getCost() {
        Double cost;
        for (int i=0; i<100; i++) {
            cost  = contextBean.getCost();
            Assertions.assertTrue((10<=cost)&&(15>=cost));
        }
    }

    @Test
    @DisplayName("Позитивный тест метода getBirthDate()")
    void getBirthDate() {
        LocalDate etalon;
        for (int i=0; i<100; i++) {
            etalon = contextBean.getBirthDate();
            Assertions.assertTrue(
                    (etalon.isAfter(LocalDate.parse("2003-12-31")))
                            && (etalon.isBefore(LocalDate.parse("2009-01-01"))));
        }
    }
}