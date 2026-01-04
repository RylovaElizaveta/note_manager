package notes.utils;

public class TagValidator {
    
    // Проверка валидности одного тега
    public static boolean isValidTag(String tag) {
        if (tag == null) return false;
        
        // Проверка длины
        if (tag.length() < 2 || tag.length() > 20) {
            return false;
        }
        
        // Проверка символов: только буквы, цифры, дефисы
        if (!tag.matches("^[a-z0-9\\-]+$")) {
            return false;
        }
        
        return true;
    }
}