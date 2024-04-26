package Service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.context.annotation.Configuration;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Configuration
@ConfigurationPropertiesScan
public class InitAnimal {
    @org.springframework.beans.factory.annotation.Value("#{${init.names}}")
    private List<String> names = new ArrayList<>(); // Список имён (в методах делать проверку значения)
    @org.springframework.beans.factory.annotation.Value("#{${init.secrets}}")
    private List<String> secrets = new ArrayList<>(); // Список секретных слов (в методах делать проверку значения)
    @org.springframework.beans.factory.annotation.Value("#{${init.characters}}")
    private List<String> characters = new ArrayList<>(); // Список характеров (в методах делать проверку значения)
    @org.springframework.beans.factory.annotation.Value("${init.mincount:60}")
    int minCount; // Минимальная цена животных (по умолчанию 60)
    @org.springframework.beans.factory.annotation.Value("${init.maxcount:1000}")
    int maxCount; // Максимальная цена животных (по умолчанию 1000)
    @org.springframework.beans.factory.annotation.Value("${init.startdate:}")
    String startdate; // Начальная возможная дата рождения животного (в методах делать проверку значения)
    @org.springframework.beans.factory.annotation.Value("${init.enddate:}")
    String enddate; // Конечная возможная дата рождения животного (в методах делать проверку значения)

    public String getName() {
        String name = "not Name";
        SecureRandom randomNum = new SecureRandom();
        if ((names != null) && (!names.isEmpty())) {
            int num = randomNum.nextInt(names.size());
            name = names.get(num);
        }
        return name;
    }

    public String getSecret() {
        String secret = "not Secret";
        SecureRandom randomNum = new SecureRandom();
        if ((secrets != null) && (!secrets.isEmpty())) {
            int num = randomNum.nextInt(secrets.size());
            secret = secrets.get(num);
        }
        return secret;
    }

    public String getCharacter() {
        String character = "not Characters";
        SecureRandom randomNum = new SecureRandom();
        if ((characters != null) && (!characters.isEmpty())) {
            int num = randomNum.nextInt(characters.size());
            character = characters.get(num);
        }
        return character;
    }

    public Double getCost() {
        SecureRandom randomNum = new SecureRandom();
        return randomNum.nextDouble() * maxCount;
    }

    public LocalDate getBirthDate() {
        long start, end;
        if ((startdate == null) || (startdate.isEmpty())) // (по умолчанию 1970-01-01)
             start = LocalDate.EPOCH.atStartOfDay(ZoneId.of("Europe/Moscow")).toInstant().getEpochSecond();
        else start = LocalDate.parse(startdate).atStartOfDay(ZoneId.of("Europe/Moscow")).toInstant().getEpochSecond();

        if ((enddate == null) || (enddate.isEmpty())) // (по умолчанию текущее число)
             end = LocalDate.now().atStartOfDay(ZoneId.of("Europe/Moscow")).toInstant().getEpochSecond();
        else end = LocalDate.parse(enddate).atStartOfDay(ZoneId.of("Europe/Moscow")).toInstant().getEpochSecond();

        if (start >= end) {
            start = LocalDate.EPOCH.atStartOfDay(ZoneId.of("Europe/Moscow")).toInstant().getEpochSecond();
            end = LocalDate.now().atStartOfDay(ZoneId.of("Europe/Moscow")).toInstant().getEpochSecond();
            System.err.println("Неверные входные параметры: <startdate=" + startdate + ">;  <enddate=" + enddate + ">");
        }

        double dblRandom = new SecureRandom().nextDouble();
        return LocalDate.ofInstant(Instant.ofEpochSecond((long) (start + (end - start) * dblRandom)),
                ZoneId.of("Europe/Moscow"));
    }

}
