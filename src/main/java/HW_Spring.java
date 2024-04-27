import Service.AnimalAutoConfiguration;
import Service.InitAnimal;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HW_Spring {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AnimalAutoConfiguration.class);
        InitAnimal contextBean = context.getBean(InitAnimal.class);
        System.out.println(contextBean.getCatName());
        System.out.println(contextBean.getDogName());
        System.out.println(contextBean.getWolfName());
        System.out.println(contextBean.getSharkName());
    }
}
