package fr.lernejo.prediction;

import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class TemperatureService {

    private final Map<CaseInsensitiveString, TemperatureGenerationData> temperatureDatasByCountry;
    private final Random random = new Random();

    TemperatureService() {
        Stream<String> lines = new ClassPathFileLoader().readLines("countriesTempData.csv");

        temperatureDatasByCountry = lines
            .skip(1)
            .map(TemperatureGenerationData::parseCsv)
            .collect(Collectors.toMap(v -> new CaseInsensitiveString(v.country()), Function.identity()));
    }

    public double getTemperature(String country) throws UnknownCountryException {
        TemperatureGenerationData data = temperatureDatasByCountry.get(new CaseInsensitiveString(country));
        if (data == null) {
            throw new UnknownCountryException(country);
        }
        return generateBelievableTemperature(data);
    }

    public TemperatureByCountry getTemperatureOn2DaySliding(String country) throws UnknownCountryException {
        Temperature t1 = new Temperature();
        t1.setDate(Date.from(LocalDate.now().minus(Period.ofDays(1)).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        t1.setTemperature(this.getTemperature(country));
        Temperature t2 = new Temperature();
        t2.setDate(new Date());
        t2.setTemperature(this.getTemperature(country));
        TemperatureByCountry tbc = new TemperatureByCountry();
        tbc.setTemperatures(Arrays.asList(t1, t2));
        tbc.setCountry(country);
        return tbc;
    }

    private double generateBelievableTemperature(TemperatureGenerationData data) {
        DecimalFormat df = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.ENGLISH));
        int avg = data.avg();
        double delta = ((double) random.nextInt(data.variance() * 2 * 100)) / 100 - data.variance();
        return Double.parseDouble(df.format(avg + delta));
    }

    record TemperatureGenerationData(String country, int avg, int variance) {
        public static TemperatureGenerationData parseCsv(String csvLine) {
            String[] split = csvLine.split(";");
            return new TemperatureGenerationData(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2]));
        }
    }

    record CaseInsensitiveString(String value) {
        @Override
        public int hashCode() {
            return value == null ? 0 : Objects.hash(value.toLowerCase(Locale.ROOT));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CaseInsensitiveString that = (CaseInsensitiveString) o;
            return Objects.equals(value.toLowerCase(Locale.ROOT), that.value.toLowerCase(Locale.ROOT));
        }
    }
}
