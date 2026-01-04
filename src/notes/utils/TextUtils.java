package notes.utils;

public class TextUtils {
    
    // Проверка валидности содержимого
    public static boolean isValidContent(String content) {
        if (content == null) return false;
        
        int length = content.length();
        return length >= 1 && length <= 5000;
    }
    
    // Обрезка текста для превью
    public static String truncateContent(String content, int maxLength) {
        if (content == null) return "";
        
        if (content.length() <= maxLength) {
            return content;
        }
        
        // Обрезаем до maxLength символов, но не разрываем последнее слово
        int lastSpaceIndex = content.lastIndexOf(' ', maxLength - 3);
        if (lastSpaceIndex > maxLength / 2) {
            return content.substring(0, lastSpaceIndex) + "...";
        } else {
            return content.substring(0, maxLength - 3) + "...";
        }
    }
    
    // Подсчет слов
    public static int countWords(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0;
        }
        
        String trimmed = text.trim();
        return trimmed.split("\\s+").length;
    }
    
    // Поиск ключевого слова без учета регистра
    public static boolean searchInText(String text, String keyword) {
        if (text == null || keyword == null) return false;
        
        return text.toLowerCase().contains(keyword.toLowerCase());
    }
}