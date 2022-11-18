package kovo.logic;

import java.io.BufferedReader;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Utils {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private static final String LINE_DATA_DELIMITER = ",";

    private Utils() {}

    public static LocalDateTime getDateTime(String dateTimeText) {
        return LocalDateTime.parse(dateTimeText, formatter);
    }

    public static Map<String, Customer> parseCallsLog(String callsLog) {
        Map<String, Customer> result = new HashMap<>();
        new BufferedReader(new StringReader(callsLog))
                .lines()
                .map(line -> line.split(LINE_DATA_DELIMITER))
                .forEach(lineData -> {
                    Customer customer = result.getOrDefault(lineData[0], new Customer(lineData[0]));
                    customer.addCall(getDateTime(lineData[1]), getDateTime(lineData[2]));
                    result.putIfAbsent(lineData[0], customer);
                });
        return result;
    }

}
