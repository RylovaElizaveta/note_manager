package notes.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {
    
    private static final DateTimeFormatter FORMATTER = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    
    // Проверка формата даты
    public static boolean isValidDateTime(String datetime) {
        if (datetime == null) return false;
        
        try {
            LocalDateTime parsedDate = LocalDateTime.parse(datetime, FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    
    // Получение текущей даты в формате
    public static String getCurrentDateTime() {
        return LocalDateTime.now().format(FORMATTER);
    }
}