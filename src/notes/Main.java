package notes;

import notes.model.Note;
import notes.model.Notebook;
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
        
        // Показываем оригинальные теги
        System.out.println("Original tags: " + note1.getTags());
        
        // Добавляем тег
        note1.addTag("practice");
        System.out.println("After adding 'practice': " + note1.getTags());
        
        // Удаляем тег
        note1.removeTag("theory");
        System.out.println("After removing 'theory': " + note1.getTags());
        
        // Проверяем наличие тегов
        System.out.println("Has tag 'oop': " + note1.hasTag("oop"));
        System.out.println("Has tag 'database': " + note1.hasTag("database"));
        
        // Дополнительные операции с note2
        System.out.println("\nAdditional operations with note2:");
        System.out.println("Note2 tags: " + note2.getTags());
        note2.addTag("set");
        note2.addTag("map");
        System.out.println("After adding 'set' and 'map': " + note2.getTags());
        System.out.println("Removing 'collections': " + note2.removeTag("collections"));
        System.out.println("Final tags: " + note2.getTags());
    }
}