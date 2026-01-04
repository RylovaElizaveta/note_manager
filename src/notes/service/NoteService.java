package notes.service;

import notes.model.Note;
import notes.model.Notebook;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NoteService {
    private List<Note> notes;
    private List<Notebook> notebooks;
    
    public NoteService() {
        this.notes = new ArrayList<>();
        this.notebooks = new ArrayList<>();
    }
    
    // Метод для добавления заметки
    public void addNote(Note note) {
        if (note != null) {
            notes.add(note);
        }
    }
    
    // Метод для добавления блокнота
    public void addNotebook(Notebook notebook) {
        if (notebook != null) {
            notebooks.add(notebook);
        }
    }
    
    // Получить заметку по ID
    public Note getNote(int id) {
        for (Note note : notes) {
            if (note.getId() == id) {
                return note;
            }
        }
        return null;
    }
    
    // Получить блокнот по ID
    public Notebook getNotebook(int id) {
        for (Notebook notebook : notebooks) {
            if (notebook.getId() == id) {
                return notebook;
            }
        }
        return null;
    }
    
    // Получить все заметки
    public List<Note> getAllNotes() {
        return new ArrayList<>(notes);
    }
    
    // Получить все блокноты
    public List<Notebook> getAllNotebooks() {
        return new ArrayList<>(notebooks);
    }
    
    // Удалить заметку по ID
    public boolean removeNote(int id) {
        Note noteToRemove = null;
        for (Note note : notes) {
            if (note.getId() == id) {
                noteToRemove = note;
                break;
            }
        }
        
        if (noteToRemove != null) {
            notes.remove(noteToRemove);
            return true;
        }
        return false;
    }
    
    // Удалить блокнот по ID
    public boolean removeNotebook(int id) {
        Notebook notebookToRemove = null;
        for (Notebook notebook : notebooks) {
            if (notebook.getId() == id) {
                notebookToRemove = notebook;
                break;
            }
        }
        
        if (notebookToRemove != null) {
            notebooks.remove(notebookToRemove);
            return true;
        }
        return false;
    }
    
    // Поиск заметок по тегу
    public List<Note> searchByTag(String tag) {
        List<Note> result = new ArrayList<>();
        for (Note note : notes) {
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
        
        for (Note note : notes) {
            if (note.getTitle().toLowerCase().contains(lowerKeyword) ||
                note.getContent().toLowerCase().contains(lowerKeyword)) {
                result.add(note);
            }
        }
        return result;
    }
    
    // Получить последние N заметок (по дате создания)
    public List<Note> getRecentNotes(int count) {
        List<Note> sortedNotes = new ArrayList<>(notes);
        
        // Сортируем по дате создания (новые сначала)
        sortedNotes.sort((n1, n2) -> n2.getCreatedAt().compareTo(n1.getCreatedAt()));
        
        // Возвращаем первые count заметок
        if (sortedNotes.size() <= count) {
            return sortedNotes;
        }
        return sortedNotes.subList(0, count);
    }
    
    // Общее количество заметок
    public int getTotalNotes() {
        return notes.size();
    }
    
    // Общее количество блокнотов
    public int getTotalNotebooks() {
        return notebooks.size();
    }
}