package notes.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Note {
    private int id;
    private String title;
    private String content;
    private String createdAt;
    private List<String> tags;
    
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    
    public Note(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now().format(FORMATTER);
        this.tags = new ArrayList<>();
    }
    
    public Note(int id, String title, String content, List<String> tags) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now().format(FORMATTER);
        this.tags = new ArrayList<>(tags);
    }
    
    // Геттеры и сеттеры
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    
    public List<String> getTags() {
        return new ArrayList<>(tags);
    }
    
    public void setTags(List<String> tags) {
        this.tags = new ArrayList<>(tags);
    }
    
    // Метод для добавления тега
    public void addTag(String tag) {
        if (tag != null && !tag.trim().isEmpty() && !tags.contains(tag)) {
            tags.add(tag);
        }
    }
    
    // Метод для удаления тега
    public boolean removeTag(String tag) {
        return tags.remove(tag);
    }
    
    // Метод для проверки наличия тега
    public boolean hasTag(String tag) {
        return tags.contains(tag);
    }
    
    @Override
    public String toString() {
        return String.format("Note[id=%d, title='%s', createdAt='%s', tags=%s]", 
                id, title, createdAt, tags);
    }
}