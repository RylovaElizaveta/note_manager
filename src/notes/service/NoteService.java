package notes.service;

import java.util.ArrayList;
import java.util.List;
import notes.model.Note;
import notes.model.Notebook;
import notes.repository.Repository;

public class NoteService {
    private Repository<Note> notes;
    private Repository<Notebook> notebooks;
    
    public NoteService() {
        this.notes = new Repository<>();
        this.notebooks = new Repository<>();
    }
    
    // Метод для добавления заметки
    public void addNote(Note note) {
        if (note != null) {
            notes.add(note.getId(), note);
        }
    }
    
    // Метод для добавления блокнота
    public void addNotebook(Notebook notebook) {
        if (notebook != null) {
            notebooks.add(notebook.getId(), notebook);
        }
    }
    
    // Получить заметку по ID
    public Note getNote(int id) {
        return notes.get(id);
    }
    
    // Получить блокнот по ID
    public Notebook getNotebook(int id) {
        return notebooks.get(id);
    }
    
    // Получить все заметки
    public List<Note> getAllNotes() {
        return notes.getAll();
    }
    
    // Получить все блокноты
    public List<Notebook> getAllNotebooks() {
        return notebooks.getAll();
    }
    
    // Удалить заметку по ID
    public boolean removeNote(int id) {
        return notes.remove(id);
    }
    
    // Удалить блокнот по ID
    public boolean removeNotebook(int id) {
        return notebooks.remove(id);
    }
    
    // Поиск заметок по тегу
    public List<Note> searchByTag(String tag) {
        List<Note> result = new ArrayList<>();
        for (Note note : notes.getAll()) {
            if (note.hasTag(tag)) {
                result.add(note);
            }
        }
        return result;
    }
    
    // Поиск заметок по ключевому слову (в названии или содержании)
    public List<Note> searchByKeyword(String keyword) {
        List<Note> result = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();
        
        for (Note note : notes.getAll()) {
            if (note.getTitle().toLowerCase().contains(lowerKeyword) ||
                note.getContent().toLowerCase().contains(lowerKeyword)) {
                result.add(note);
            }
        }
        return result;
    }
    
    // Получить последние N заметок (по дате создания)
    public List<Note> getRecentNotes(int count) {
        List<Note> allNotes = notes.getAll();
        List<Note> result = new ArrayList<>();
        
        if (allNotes.isEmpty()) {
            return result;
        }
        
        // Сортируем по дате создания (новые сначала)
        allNotes.sort((n1, n2) -> n2.getCreatedAt().compareTo(n1.getCreatedAt()));
        
        // Возвращаем первые count заметок
        int endIndex = Math.min(count, allNotes.size());
        return allNotes.subList(0, endIndex);
    }
    
    // Общее количество заметок
    public int getTotalNotes() {
        return notes.size();
    }
    
    // Общее количество блокнотов
    public int getTotalNotebooks() {
        return notebooks.size();
    }
    
    // Получить репозиторий заметок (для NoteFileManager)
    public Repository<Note> getNoteRepository() {
        return notes;
    }
    
    // Получить репозиторий блокнотов (для NoteFileManager)
    public Repository<Notebook> getNotebookRepository() {
        return notebooks;
    }
}