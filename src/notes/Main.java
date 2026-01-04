package notes;

import java.util.Arrays;
import java.util.List;
import notes.model.Note;
import notes.model.Notebook;
import notes.service.NoteService;
import notes.service.NoteStatistics;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Шаг 3: Использование коллекций ===\n");
        
        // Создаем NoteService
        NoteService noteService = new NoteService();
        
        // Добавляем 2-3 блокнота
        Notebook notebook1 = new Notebook(1, "Java Learning", "Notes about Java programming");
        Notebook notebook2 = new Notebook(2, "Web Development", "Frontend and backend notes");
        Notebook notebook3 = new Notebook(3, "Algorithms", "Data structures and algorithms notes");
        
        noteService.addNotebook(notebook1);
        noteService.addNotebook(notebook2);
        noteService.addNotebook(notebook3);
        
        // Добавляем 8-10 заметок с разными датами для демонстрации сортировки
        noteService.addNote(new Note(1, "OOP Principles", 
            "Object-Oriented Programming principles: Encapsulation, Inheritance, Polymorphism, Abstraction. " +
            "This is a detailed note about OOP concepts in Java programming language.",
            "2025-01-15 10:30", 
            Arrays.asList("java", "oop", "theory")));
        
        noteService.addNote(new Note(2, "Collections Framework", 
            "Java Collections Framework: List, Set, Map interfaces and their implementations. " +
            "This note covers ArrayList, LinkedList, HashSet, HashMap, TreeMap and other collection classes.",
            "2025-01-15 11:00", 
            Arrays.asList("java", "collections")));
        
        noteService.addNote(new Note(3, "String Handling", 
            "String class, StringBuilder, StringBuffer, and string manipulation techniques. " +
            "Includes examples of common string operations in Java.",
            "2025-01-15 12:00", 
            Arrays.asList("java", "strings", "utils")));
        
        noteService.addNote(new Note(4, "Exception Handling", 
            "Checked and unchecked exceptions, try-catch-finally, custom exceptions. " +
            "Best practices for exception handling in Java applications.",
            "2025-01-15 13:00", 
            Arrays.asList("java", "exceptions", "error-handling")));
        
        noteService.addNote(new Note(5, "HTML Basics", 
            "HTML tags, attributes, forms, tables, and semantic elements. " +
            "Introduction to HTML5 features and web page structure.",
            "2025-01-16 15:45", 
            Arrays.asList("web", "html", "frontend")));
        
        noteService.addNote(new Note(6, "CSS Styling", 
            "CSS selectors, box model, flexbox, grid, and responsive design. " +
            "Modern CSS techniques for creating beautiful web interfaces.",
            "2025-01-16 16:00", 
            Arrays.asList("web", "css", "design")));
        
        noteService.addNote(new Note(7, "JavaScript Fundamentals", 
            "Variables, functions, objects, events, and DOM manipulation. " +
            "Core JavaScript concepts for frontend development.",
            "2025-01-16 14:00", 
            Arrays.asList("web", "javascript", "frontend")));
        
        noteService.addNote(new Note(8, "JavaScript Events", 
            "Event handling, event listeners, event propagation, and common events. " +
            "Detailed guide to JavaScript event system and practical examples.",
            "2025-01-16 14:30", 
            Arrays.asList("web", "javascript")));
        
        noteService.addNote(new Note(9, "Algorithms Introduction", 
            "What are algorithms? Complexity analysis (Big O notation). " +
            "Basic algorithmic concepts and analysis techniques for beginners.",
            "2025-01-17 09:00", 
            Arrays.asList("algorithms", "theory", "complexity")));
        
        noteService.addNote(new Note(10, "Data Structures", 
            "Arrays, linked lists, stacks, queues, trees, and graphs. " +
            "Comprehensive overview of common data structures and their applications.",
            "2025-01-17 10:00", 
            Arrays.asList("algorithms", "data-structures")));
        
        // Выводим все блокноты
        System.out.println("All Notebooks:");
        List<Notebook> allNotebooks = noteService.getAllNotebooks();
        for (Notebook notebook : allNotebooks) {
            System.out.println("    " + notebook);
        }
        System.out.println();
        
        // Выводим все заметки
        System.out.println("All Notes (" + noteService.getTotalNotes() + "):");
        List<Note> allNotes = noteService.getAllNotes();
        for (Note note : allNotes) {
            System.out.println(note.toFormattedString());
        }
        System.out.println();
        
        // Заметки с тегом 'java'
        System.out.println("Notes with tag 'java':");
        List<Note> javaNotes = noteService.searchByTag("java");
        for (Note note : javaNotes) {
            System.out.println(note.toFormattedString());
        }
        System.out.println("Total: " + javaNotes.size() + " notes\n");
        
        // Заметки, содержащие 'Framework' в названии или тексте
        System.out.println("Notes containing 'Framework':");
        List<Note> frameworkNotes = noteService.searchByKeyword("Framework");
        for (Note note : frameworkNotes) {
            System.out.println(note.toFormattedString());
        }
        System.out.println();
        
        // Последние 3 заметки
        System.out.println("Recent notes (last 3):");
        List<Note> recentNotes = noteService.getRecentNotes(3);
        for (Note note : recentNotes) {
            System.out.println(note.toFormattedString());
        }
        System.out.println();
        
        // Статистика
        NoteStatistics stats = new NoteStatistics();
        System.out.println("Statistics:");
        System.out.println("Total notes: " + stats.getTotalNotes(noteService));
        System.out.println("Total notebooks: " + stats.getTotalNotebooks(noteService));
        
        String mostUsedTag = stats.getMostUsedTag(noteService);
        System.out.println("Most used tag: '" + mostUsedTag + "' (" + 
            stats.countNotesByTag(noteService, mostUsedTag) + " occurrences)");
        
        System.out.println("All tags: " + stats.getAllTags(noteService));
        
        Note longestNote = stats.getLongestNote(noteService);
        if (longestNote != null) {
            System.out.println("Longest note: '" + longestNote.getTitle() + "' (" + 
                longestNote.getContent().length() + " chars)");
        }
        
        // Демонстрация других методов
        System.out.println("\nAdditional operations:");
        
        // Поиск по тегу 'web'
        System.out.println("Notes with tag 'web': " + 
            noteService.searchByTag("web").size() + " notes");
        
        // Удаление заметки
        System.out.println("Removing note with id=4... " + 
            (noteService.removeNote(4) ? "Success" : "Failed"));
        System.out.println("Total notes after removal: " + noteService.getTotalNotes());
        
        // Поиск по нескольким ключевым словам
        System.out.println("\nNotes containing 'Java':");
        List<Note> javaSearch = noteService.searchByKeyword("Java");
        for (Note note : javaSearch) {
            System.out.println(note.toFormattedString());
        }
        
        // Демонстрация getNote
        System.out.println("\nGetting note with id=2:");
        Note note2 = noteService.getNote(2);
        if (note2 != null) {
            System.out.println(note2.toFormattedString());
        }
    }
}