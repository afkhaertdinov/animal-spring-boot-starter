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
    @Value("#{${init.names}}")
    private final List<String> names = new ArrayList<>(); // Список имён (в методах делать проверку значения)
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

    // Генерируем и возвращаем случайное имя из списка имён, при отсутствиии списка возвращаем "not Name"
    public String getName() {
        String name = "not Name";
        SecureRandom randomNum = new SecureRandom();
        if ((names != null) && (!names.isEmpty())) {
            int num = randomNum.nextInt(names.size());
            name = names.get(num);
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
