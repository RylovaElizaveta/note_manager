package notes.utils;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {
    
    // Метод для убирания лишних пробелов
    public static String trimAndNormalize(String text) {
        if (text == null) return "";
        // Убираем пробелы в начале и конце, затем заменяем множественные пробелы на один
        return text.trim().replaceAll("\\s+", " ");
    }
    
    // Метод для нормализации заголовка
    public static String normalizeTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Название не может быть пустым");
        }
        
        String normalized = trimAndNormalize(title);
        
        // Проверка минимальной длины
        if (normalized.length() < 3) {
            throw new IllegalArgumentException("Название должно содержать минимум 3 символа");
        }
        
        // Делаем первую букву заглавной
        if (normalized.length() > 0) {
            normalized = Character.toUpperCase(normalized.charAt(0)) + normalized.substring(1);
        }
        
        return normalized;
    }
    
    // Проверка валидности названия
    public static boolean isValidTitle(String title) {
        if (title == null) return false;
        
        // Проверка длины
        if (title.length() < 3 || title.length() > 100) {
            return false;
        }
        
        // Проверка допустимых символов: буквы, цифры, пробелы, дефисы, точки
        return title.matches("^[a-zA-Zа-яА-Я0-9\\s\\-\\.]+$");
    }
    
    // Разбор строки тегов
    public static List<String> parseTags(String tagsString) {
        List<String> tags = new ArrayList<>();
        
        if (tagsString == null || tagsString.trim().isEmpty()) {
            return tags;
        }
        
        // Разделяем по запятой, убираем пробелы и приводим к нижнему регистру
        String[] tagArray = tagsString.split(",");
        for (String tag : tagArray) {
            String normalizedTag = tag.trim().toLowerCase();
            if (!normalizedTag.isEmpty()) {
                tags.add(normalizedTag);
            }
        }
        
        return tags;
    }
    
    // Объединение тегов обратно в строку
    public static String joinTags(List<String> tags) {
        if (tags == null || tags.isEmpty()) {
            return "";
        }
        
        return String.join(",", tags);
    }
}