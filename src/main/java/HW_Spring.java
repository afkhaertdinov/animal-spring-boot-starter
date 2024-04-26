import Service.InitAnimal;
import Service.AnimalAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HW_Spring {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AnimalAutoConfiguration.class);
        InitAnimal contextBean = context.getBean(InitAnimal.class);

        System.out.println(contextBean.getSecrets());
        System.out.println(contextBean.getNames());
        System.out.println(contextBean.getCharacters());
        System.out.println(contextBean.getSecret());
        System.out.println(contextBean.getName());
        System.out.println(contextBean.getCharacter());
        System.out.println(contextBean.getCost());
        System.out.println(contextBean.getBirthDate());

        context.close();
    }
}
