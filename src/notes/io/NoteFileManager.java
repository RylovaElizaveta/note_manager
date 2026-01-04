package notes.io;

import notes.model.Note;
import notes.model.Notebook;
import notes.utils.StringUtils;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NoteFileManager {
    
    /**
     * Сохраняет заметки в файл
     * @param filename имя файла
     * @param notes список заметок
     * @throws IOException если произошла ошибка ввода-вывода
     */
    public void saveNotes(String filename, List<Note> notes) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Note note : notes) {
                String line = String.format("%d|%s|%s|%s|%s",
                    note.getId(),
                    escapePipe(note.getTitle()),
                    note.getCreatedAt(),
                    escapePipe(note.getContent()),
                    StringUtils.joinTags(note.getTags()));
                writer.write(line);
                writer.newLine();
            }
        }
    }
    
    /**
     * Загружает заметки из файла
     * @param filename имя файла
     * @return список заметок
     * @throws IOException если произошла ошибка ввода-вывода
     */
    public List<Note> loadNotes(String filename) throws IOException {
        List<Note> notes = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                
                String[] parts = splitByPipe(line);
                if (parts.length < 5) {
                    continue; // Пропускаем некорректные строки
                }
                
                try {
                    int id = Integer.parseInt(parts[0]);
                    String title = unescapePipe(parts[1]);
                    String createdAt = parts[2];
                    String content = unescapePipe(parts[3]);
                    List<String> tags = StringUtils.parseTags(parts[4]);
                    
                    Note note = new Note(id, title, content, createdAt, tags);
                    notes.add(note);
                } catch (NumberFormatException e) {
                    // Пропускаем строки с некорректным ID
                    continue;
                }
            }
        }
        
        return notes;
    }
    
    /**
     * Сохраняет блокноты в файл
     * @param filename имя файла
     * @param notebooks список блокнотов
     * @throws IOException если произошла ошибка ввода-вывода
     */
    public void saveNotebooks(String filename, List<Notebook> notebooks) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Notebook notebook : notebooks) {
                String line = String.format("%d|%s|%s",
                    notebook.getId(),
                    escapePipe(notebook.getName()),
                    escapePipe(notebook.getDescription()));
                writer.write(line);
                writer.newLine();
            }
        }
    }
    
    /**
     * Загружает блокноты из файла
     * @param filename имя файла
     * @return список блокнотов
     * @throws IOException если произошла ошибка ввода-вывода
     */
    public List<Notebook> loadNotebooks(String filename) throws IOException {
        List<Notebook> notebooks = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                
                String[] parts = splitByPipe(line);
                if (parts.length < 3) {
                    continue; // Пропускаем некорректные строки
                }
                
                try {
                    int id = Integer.parseInt(parts[0]);
                    String name = unescapePipe(parts[1]);
                    String description = unescapePipe(parts[2]);
                    
                    Notebook notebook = new Notebook(id, name, description);
                    notebooks.add(notebook);
                } catch (NumberFormatException e) {
                    // Пропускаем строки с некорректным ID
                    continue;
                }
            }
        }
        
        return notebooks;
    }
    
    /**
     * Экранирует символ pipe (|) в строках
     */
    private String escapePipe(String text) {
        if (text == null) return "";
        return text.replace("|", "\\|");
    }
    
    /**
     * Восстанавливает символ pipe (|) в строках
     */
    private String unescapePipe(String text) {
        if (text == null) return "";
        return text.replace("\\|", "|");
    }
    
    /**
     * Разделяет строку по символу pipe, учитывая экранирование
     */
    private String[] splitByPipe(String line) {
        List<String> parts = new ArrayList<>();
        StringBuilder currentPart = new StringBuilder();
        boolean escaped = false;
        
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            
            if (escaped) {
                currentPart.append(c);
                escaped = false;
            } else if (c == '\\') {
                escaped = true;
            } else if (c == '|') {
                parts.add(currentPart.toString());
                currentPart = new StringBuilder();
            } else {
                currentPart.append(c);
            }
        }
        
        parts.add(currentPart.toString());
        return parts.toArray(new String[0]);
    }
}