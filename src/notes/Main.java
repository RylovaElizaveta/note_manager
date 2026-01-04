package notes;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import notes.io.NoteFileManager;
import notes.logger.NoteLogger;
import notes.model.Note;
import notes.model.Notebook;
import notes.service.NoteService;
import notes.service.NoteStatistics;

public class Main {
    public static void main(String[] args) {
        // Создаем необходимые директории, если их нет
        java.io.File logsDir = new java.io.File("logs");
        if (!logsDir.exists()) {
            logsDir.mkdirs();
        }
        
        java.io.File dataDir = new java.io.File("data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
        
        System.out.println("=== Шаг 4: Дженерики и файловый ввод-вывод ===\n");
        
        NoteLogger logger = new NoteLogger();
        String logFile = "logs/notes.log";
        
        try {
            // Очищаем лог перед началом
            logger.clearLog(logFile);
            
            // 1. Создаем исходный NoteService с данными
            System.out.println("=== Original NoteService ===");
            NoteService originalService = createTestNoteService();
            
            // Логируем создание сервиса
            logger.logInfo(logFile, "NoteService created with initial data");
            
            NoteStatistics stats = new NoteStatistics();
            System.out.println("Total notebooks: " + stats.getTotalNotebooks(originalService));
            System.out.println("Total notes: " + stats.getTotalNotes(originalService));
            System.out.println("Most used tag: '" + stats.getMostUsedTag(originalService) + "'\n");
            
            // 2. Сохраняем данные в файлы
            System.out.println("Saving data...");
            NoteFileManager fileManager = new NoteFileManager();
            
            fileManager.saveNotebooks("data/notebooks.txt", originalService.getAllNotebooks());
            fileManager.saveNotes("data/notes.txt", originalService.getAllNotes());
            
            System.out.println("Notebooks saved: " + originalService.getAllNotebooks().size());
            System.out.println("Notes saved: " + originalService.getAllNotes().size());
            
            // Логируем сохранение
            logger.logInfo(logFile, "Data saved to files");
            
            // 3. Создаем новый NoteService и загружаем данные из файлов
            System.out.println("\nLoading data...");
            NoteService loadedService = new NoteService();
            
            List<Notebook> loadedNotebooks = fileManager.loadNotebooks("data/notebooks.txt");
            List<Note> loadedNotes = fileManager.loadNotes("data/notes.txt");
            
            for (Notebook notebook : loadedNotebooks) {
                loadedService.addNotebook(notebook);
            }
            
            for (Note note : loadedNotes) {
                loadedService.addNote(note);
            }
            
            System.out.println("Notebooks loaded: " + loadedService.getAllNotebooks().size());
            System.out.println("Notes loaded: " + loadedService.getAllNotes().size());
            
            // Логируем загрузку
            logger.logInfo(logFile, "Data loaded from files");
            
            // 4. Выводим загруженные данные
            System.out.println("\n=== Loaded NoteService ===");
            System.out.println("Loaded Notebooks:");
            for (Notebook notebook : loadedService.getAllNotebooks()) {
                System.out.println("    " + notebook);
            }
            
            System.out.println("\nLoaded Notes (first 3):");
            List<Note> firstThreeNotes = loadedService.getRecentNotes(3);
            for (Note note : firstThreeNotes) {
                System.out.println("    " + note);
            }
            
            // 5. Проверяем совпадение данных
            System.out.println("\n=== Verification ===");
            boolean verificationPassed = true;
            
            // Проверка количества блокнотов
            int originalNotebookCount = originalService.getTotalNotebooks();
            int loadedNotebookCount = loadedService.getTotalNotebooks();
            System.out.println("Original total notebooks: " + originalNotebookCount);
            System.out.println("Loaded total notebooks: " + loadedNotebookCount);
            
            if (originalNotebookCount == loadedNotebookCount) {
                System.out.println("✓ Notebooks verified");
            } else {
                System.out.println("✗ Notebooks verification failed");
                verificationPassed = false;
            }
            
            // Проверка количества заметок
            int originalNoteCount = originalService.getTotalNotes();
            int loadedNoteCount = loadedService.getTotalNotes();
            System.out.println("\nOriginal total notes: " + originalNoteCount);
            System.out.println("Loaded total notes: " + loadedNoteCount);
            
            if (originalNoteCount == loadedNoteCount) {
                System.out.println("✓ Notes verified");
            } else {
                System.out.println("✗ Notes verification failed");
                verificationPassed = false;
            }
            
            // Проверка самого популярного тега
            NoteStatistics loadedStats = new NoteStatistics();
            String originalMostUsedTag = stats.getMostUsedTag(originalService);
            String loadedMostUsedTag = loadedStats.getMostUsedTag(loadedService);
            int originalTagCount = stats.getTagOccurrences(originalService, originalMostUsedTag);
            int loadedTagCount = loadedStats.getTagOccurrences(loadedService, loadedMostUsedTag);
            
            System.out.println("\nOriginal most used tag: '" + originalMostUsedTag + 
                "' (" + originalTagCount + " occurrences)");
            System.out.println("Loaded most used tag: '" + loadedMostUsedTag + 
                "' (" + loadedTagCount + " occurrences)");
            
            if (originalMostUsedTag.equals(loadedMostUsedTag) && originalTagCount == loadedTagCount) {
                System.out.println("✓ Tags verified");
            } else {
                System.out.println("✗ Tags verification failed");
                verificationPassed = false;
            }
            
            // 6. Итог проверки
            if (verificationPassed) {
                System.out.println("\n✓ Data verification successful");
                logger.logInfo(logFile, "Data verification successful");
            } else {
                System.out.println("\n✗ Data verification failed");
                logger.logError(logFile, "Data verification failed");
            }
            
            // 7. Выводим содержимое лог-файла
            System.out.println("\nLog file contents (" + logFile + "):");
            String logContent = logger.readLog(logFile);
            System.out.println(logContent);
            
        } catch (IOException e) {
            System.err.println("Ошибка ввода-вывода: " + e.getMessage());
            try {
                logger.logError(logFile, "IO Error: " + e.getMessage());
            } catch (IOException logError) {
                System.err.println("Не удалось записать в лог: " + logError.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Неожиданная ошибка: " + e.getMessage());
            try {
                logger.logError(logFile, "Unexpected error: " + e.getMessage());
            } catch (IOException logError) {
                System.err.println("Не удалось записать в лог: " + logError.getMessage());
            }
        }
    }
    
    /**
     * Создает тестовый NoteService с данными
     */
    private static NoteService createTestNoteService() {
        NoteService service = new NoteService();
        
        // Добавляем блокноты
        service.addNotebook(new Notebook(1, "Java Learning", "Notes about Java programming"));
        service.addNotebook(new Notebook(2, "Web Development", "Frontend and backend notes"));
        
        // Добавляем заметки
        service.addNote(new Note(1, "OOP Principles", 
            "Object-Oriented Programming principles: Encapsulation, Inheritance, Polymorphism, Abstraction.",
            "2025-01-15 10:30", 
            Arrays.asList("java", "oop", "theory")));
        
        service.addNote(new Note(2, "Collections Framework", 
            "Java Collections Framework: List, Set, Map interfaces and their implementations.",
            "2025-01-15 11:00", 
            Arrays.asList("java", "collections")));
        
        service.addNote(new Note(3, "String Handling", 
            "String class, StringBuilder, StringBuffer, and string manipulation techniques.",
            "2025-01-15 12:00", 
            Arrays.asList("java", "strings", "utils")));
        
        service.addNote(new Note(4, "Exception Handling", 
            "Checked and unchecked exceptions, try-catch-finally, custom exceptions.",
            "2025-01-15 13:00", 
            Arrays.asList("java", "exceptions")));
        
        service.addNote(new Note(5, "HTML Basics", 
            "HTML tags, attributes, forms, tables, and semantic elements.",
            "2025-01-16 14:00", 
            Arrays.asList("web", "html", "frontend")));
        
        service.addNote(new Note(6, "CSS Styling", 
            "CSS selectors, box model, flexbox, grid, and responsive design.",
            "2025-01-16 15:00", 
            Arrays.asList("web", "css", "design")));
        
        service.addNote(new Note(7, "JavaScript Fundamentals", 
            "Variables, functions, objects, events, and DOM manipulation.",
            "2025-01-16 16:00", 
            Arrays.asList("web", "javascript")));
        
        service.addNote(new Note(8, "JavaScript Events", 
            "Event handling, event listeners, event propagation, and common events.",
            "2025-01-17 09:00", 
            Arrays.asList("web", "javascript", "events")));
        
        service.addNote(new Note(9, "Algorithms Introduction", 
            "What are algorithms? Complexity analysis (Big O notation).",
            "2025-01-17 10:00", 
            Arrays.asList("algorithms", "theory")));
        
        service.addNote(new Note(10, "Data Structures", 
            "Arrays, linked lists, stacks, queues, trees, and graphs.",
            "2025-01-17 11:00", 
            Arrays.asList("algorithms", "data-structures")));
        
        return service;
    }
}