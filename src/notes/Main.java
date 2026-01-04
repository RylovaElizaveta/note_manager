package notes;

import notes.model.Note;
import notes.model.Notebook;
import notes.utils.StringUtils;
import notes.utils.TextUtils;
import notes.utils.DateUtils;
import notes.utils.TagValidator;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Шаг 1: Базовая модель заметок ===\n");
        
        // 1. Создаем несколько записных книжек
        Notebook notebook1 = new Notebook(1, "Java Learning", "Notes about Java programming");
        Notebook notebook2 = new Notebook(2, "Web Development", "HTML, CSS, JavaScript notes");
        
        System.out.println("Notebooks:");
        System.out.println(notebook1);
        System.out.println(notebook2);
        System.out.println();
        
        // 2. Создаем несколько заметок с разными тегами
        Note note1 = new Note(1, "OOP Principles", 
                "Object-Oriented Programming principles: Encapsulation, Inheritance, Polymorphism, Abstraction",
                Arrays.asList("java", "oop", "theory"));
        
        Note note2 = new Note(2, "Collections Framework", 
                "Java Collections Framework: List, Set, Map interfaces and their implementations",
                Arrays.asList("java", "collections", "list"));
        
        System.out.println("Notes:");
        System.out.println(note1);
        System.out.println(note2);
        System.out.println();
        
        // 3. Демонстрируем операции с тегами
        System.out.println("Tag Operations:");
        System.out.println("Original tags: " + note1.getTags());
        
        note1.addTag("practice");
        System.out.println("After adding 'practice': " + note1.getTags());
        
        note1.removeTag("theory");
        System.out.println("After removing 'theory': " + note1.getTags());
        
        System.out.println("Has tag 'oop': " + note1.hasTag("oop"));
        System.out.println("Has tag 'database': " + note1.hasTag("database"));
        
        // ================= ШАГ 2 =================
        System.out.println("\n=== Шаг 2: Работа со строками ===\n");
        
        // 1. Нормализация названий
        System.out.println("Title Normalization:");
        String rawTitle = "   my   java note   ";
        System.out.println("Raw: \"" + rawTitle + "\"");
        
        try {
            String normalizedTitle = StringUtils.normalizeTitle(rawTitle);
            System.out.println("Normalized: \"" + normalizedTitle + "\"");
            System.out.println("Valid: " + StringUtils.isValidTitle(normalizedTitle));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println();
        
        // 2. Обработка тегов
        System.out.println("Tag Processing:");
        String rawTags = "java, OOP, PRACTICE, data-structures";
        System.out.println("Raw tags string: \"" + rawTags + "\"");
        
        List<String> parsedTags = StringUtils.parseTags(rawTags);
        System.out.println("Parsed: " + parsedTags);
        
        String joinedTags = StringUtils.joinTags(parsedTags);
        System.out.println("Joined back: \"" + joinedTags + "\"");
        System.out.println();
        
        // 3. Валидация тегов
        System.out.println("Tag Validation:");
        String[] testTags = {"java", "j", "Java@123", "data-structures", "a", "verylongtagnameexceedingtwenty"};
        for (String tag : testTags) {
            System.out.printf("\"%s\" is valid: %s%n", tag, TagValidator.isValidTag(tag));
        }
        System.out.println();
        
        // 4. Превью контента
        System.out.println("Content Preview:");
        String longContent = "This is a long note about Java programming and OOP concepts including inheritance, polymorphism, and encapsulation.";
        System.out.println("Original: \"" + longContent + "\"");
        System.out.println("Preview (20 chars): \"" + TextUtils.truncateContent(longContent, 20) + "\"");
        System.out.println("Word count: " + TextUtils.countWords(longContent));
        System.out.println("Search for 'Java': " + (TextUtils.searchInText(longContent, "Java") ? "found" : "not found"));
        System.out.println("Search for 'Python': " + (TextUtils.searchInText(longContent, "Python") ? "found" : "not found"));
        System.out.println();
        
        // 5. Валидация текста
        System.out.println("Text Validation:");
        String[] testTexts = {"Hello", "", "A".repeat(5001)};
        for (String text : testTexts) {
            System.out.printf("\"%s...\" is valid: %s%n", 
                text.length() > 10 ? text.substring(0, 10) : text,
                TextUtils.isValidContent(text));
        }
        System.out.println();
        
        // 6. Валидация даты
        System.out.println("Date Validation:");
        String[] testDates = {"2025-01-15 10:30", "invalid-date", "2025/01/15 10:30", "2025-13-45 99:99"};
        for (String date : testDates) {
            System.out.printf("\"%s\" is valid: %s%n", date, DateUtils.isValidDateTime(date));
        }
        System.out.println();
        
        // 7. Демонстрация обработки ошибок
        System.out.println("Error Handling Examples:");
        
        try {
            Note invalidNote = new Note(3, "ab", "Short title test");
        } catch (IllegalArgumentException e) {
            System.out.println("Error creating note with short title: " + e.getMessage());
        }
        
        try {
            Note noteWithInvalidTag = new Note(4, "Valid Title", "Content");
            noteWithInvalidTag.addTag("@invalid");
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding invalid tag: " + e.getMessage());
        }
        
        try {
            Note noteWithInvalidDate = new Note(5, "Another Note", "Content", "invalid-date", Arrays.asList("test"));
        } catch (IllegalArgumentException e) {
            System.out.println("Error creating note with invalid date: " + e.getMessage());
        }
    }
}