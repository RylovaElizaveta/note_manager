package notes.service;

import notes.model.Note;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NoteStatistics {
    
    // Получить общее количество заметок
    public int getTotalNotes(NoteService service) {
        return service.getTotalNotes();
    }
    
    // Получить общее количество блокнотов
    public int getTotalNotebooks(NoteService service) {
        return service.getTotalNotebooks();
    }
    
    // Получить самый часто используемый тег
    public String getMostUsedTag(NoteService service) {
        Map<String, Integer> tagCount = new HashMap<>();
        
        for (Note note : service.getAllNotes()) {
            for (String tag : note.getTags()) {
                tagCount.put(tag, tagCount.getOrDefault(tag, 0) + 1);
            }
        }
        
        String mostUsedTag = null;
        int maxCount = 0;
        
        for (Map.Entry<String, Integer> entry : tagCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostUsedTag = entry.getKey();
            }
        }
        
        return mostUsedTag;
    }
    
    // Получить все уникальные теги в системе
    public List<String> getAllTags(NoteService service) {
        Set<String> uniqueTags = new HashSet<>();
        
        for (Note note : service.getAllNotes()) {
            uniqueTags.addAll(note.getTags());
        }
        
        return new ArrayList<>(uniqueTags);
    }
    
    // Подсчитать количество заметок с определенным тегом
    public int countNotesByTag(NoteService service, String tag) {
        return service.searchByTag(tag).size();
    }
    
    // Получить самую длинную заметку (по количеству символов в содержании)
    public Note getLongestNote(NoteService service) {
        Note longestNote = null;
        int maxLength = 0;
        
        for (Note note : service.getAllNotes()) {
            int length = note.getContent().length();
            if (length > maxLength) {
                maxLength = length;
                longestNote = note;
            }
        }
        
        return longestNote;
    }
}