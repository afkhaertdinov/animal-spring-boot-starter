package Service;

import org.springframework.beans.factory.annotation.Value;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class InitAnimal {
    @Value("#{${init.cat.names}}")
    private final List<String> catNames = new ArrayList<>(); // Список кошачьих имён (в методах делать проверку значения)
    @Value("#{${init.dog.names}}")
    private final List<String> dogNames = new ArrayList<>(); // Список собачьих имён (в методах делать проверку значения)
    @Value("#{${init.wolf.names}}")
    private final List<String> wolfNames = new ArrayList<>(); // Список волчьих имён (в методах делать проверку значения)
    @Value("#{${init.shark.names}}")
    private final List<String> sharkNames = new ArrayList<>(); // Список акульих имён (в методах делать проверку значения)
    @Value("#{${init.secrets}}")
    private final List<String> secrets = new ArrayList<>(); // Список секретных слов (в методах делать проверку значения)
    @Value("#{${init.characters}}")
    private final List<String> characters = new ArrayList<>(); // Список характеров (в методах делать проверку значения)
    @Value("${init.mincount:60}")
    int minCount; // Минимальная цена животных (по умолчанию 60)
    @Value("${init.maxcount:1000}")
    int maxCount; // Максимальная цена животных (по умолчанию 1000)
    @Value("${init.startdate:}")
    String startdate; // Начальная возможная дата рождения животного (в методах делать проверку значения)
    @Value("${init.enddate:}")
    String enddate; // Конечная возможная дата рождения животного (в методах делать проверку значения)

    // Генерируем и возвращаем случайное кошачье имя из списка имён, при отсутствиии списка возвращаем "not Name"
    public String getCatName() {
        String name = "not Name";
        SecureRandom randomNum = new SecureRandom();
        if ((catNames != null) && (!catNames.isEmpty())) {
            int num = randomNum.nextInt(catNames.size());
            name = catNames.get(num);
        }
        return name;
    }

    // Генерируем и возвращаем случайное собачье имя из списка имён, при отсутствиии списка возвращаем "not Name"
    public String getDogName() {
        String name = "not Name";
        SecureRandom randomNum = new SecureRandom();
        if ((dogNames != null) && (!dogNames.isEmpty())) {
            int num = randomNum.nextInt(dogNames.size());
            name = dogNames.get(num);
        }
        return name;
    }

    // Генерируем и возвращаем случайное волчье имя из списка имён, при отсутствиии списка возвращаем "not Name"
    public String getWolfName() {
        String name = "not Name";
        SecureRandom randomNum = new SecureRandom();
        if ((wolfNames != null) && (!wolfNames.isEmpty())) {
            int num = randomNum.nextInt(wolfNames.size());
            name = wolfNames.get(num);
        }
        return name;
    }

    // Генерируем и возвращаем случайное акулье имя из списка имён, при отсутствиии списка возвращаем "not Name"
    public String getSharkName() {
        String name = "not Name";
        SecureRandom randomNum = new SecureRandom();
        if ((sharkNames != null) && (!sharkNames.isEmpty())) {
            int num = randomNum.nextInt(sharkNames.size());
            name = sharkNames.get(num);
        }
        return name;
    }

    // Генерируем и возвращаем случайное секретное слово из списка слов, при отсутствиии списка возвращаем "not Secret"
    public String getSecret() {
        String secret = "not Secret";
        SecureRandom randomNum = new SecureRandom();
        if ((secrets != null) && (!secrets.isEmpty())) {
            int num = randomNum.nextInt(secrets.size());
            secret = secrets.get(num);
        }
        return secret;
    }

    // Генерируем и возвращаем случайное секретное слово из списка слов, при отсутствиии списка возвращаем "not Characters"
    public String getCharacter() {
        String character = "not Characters";
        SecureRandom randomNum = new SecureRandom();
        if ((characters != null) && (!characters.isEmpty())) {
            int num = randomNum.nextInt(characters.size());
            character = characters.get(num);
        }
        return character;
    }

    // Генерируем и возвращаем случаную стоимость от minCount до maxCount, по умолчанию [60..1000]
    public Double getCost() {
        if (minCount < 0) minCount = 0;
        if (minCount >= maxCount) {
            minCount = 60;
            maxCount = 1000;
        }
        SecureRandom randomNum = new SecureRandom();
        return minCount + randomNum.nextDouble() * (maxCount - minCount);
    }

    // Генерируем и возвращаем случайную дату рождения в диапазоне [startdate..enddate], по умолчанию [1970-01-01..текущая дата]
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
