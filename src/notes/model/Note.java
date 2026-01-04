package notes.model;

import java.util.ArrayList;
import java.util.List;
import notes.utils.DateUtils;
import notes.utils.StringUtils;
import notes.utils.TagValidator;

public class Note {
    private int id;
    private String title;
    private String content;
    private String createdAt;
    private List<String> tags;
    
    // Конструктор без тегов
    public Note(int id, String title, String content) {
        this.id = id;
        setTitle(title);
        this.content = content;
        this.createdAt = DateUtils.getCurrentDateTime();
        this.tags = new ArrayList<>();
    }
    
    // Конструктор с тегами
    public Note(int id, String title, String content, List<String> tags) {
        this.id = id;
        setTitle(title);
        this.content = content;
        this.createdAt = DateUtils.getCurrentDateTime();
        setTags(tags);
    }
    
    // Конструктор с датой (для тестирования)
    public Note(int id, String title, String content, String createdAt, List<String> tags) {
        this.id = id;
        setTitle(title);
        this.content = content;
        setCreatedAt(createdAt);
        setTags(tags);
    }
    
    // Геттеры и сеттеры
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getTitle() { return title; }
    
    public void setTitle(String title) {
        if (!StringUtils.isValidTitle(title)) {
            throw new IllegalArgumentException("Недопустимое название: " + title);
        }
        this.title = StringUtils.normalizeTitle(title);
    }
    
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    
    public String getCreatedAt() { return createdAt; }
    
    public void setCreatedAt(String createdAt) {
        if (!DateUtils.isValidDateTime(createdAt)) {
            throw new IllegalArgumentException("Недопустимый формат даты: " + createdAt);
        }
        this.createdAt = createdAt;
    }
    
    public List<String> getTags() {
        return new ArrayList<>(tags);
    }
    
    public void setTags(List<String> tags) {
        this.tags = new ArrayList<>();
        if (tags != null) {
            for (String tag : tags) {
                addTag(tag);
            }
        }
    }
    
    // Метод для добавления тега
    public void addTag(String tag) {
        if (tag == null) return;
        
        String normalizedTag = tag.trim().toLowerCase();
        
        if (!TagValidator.isValidTag(normalizedTag)) {
            throw new IllegalArgumentException("Недопустимый тег: " + tag);
        }
        
        if (!tags.contains(normalizedTag)) {
            tags.add(normalizedTag);
        }
    }
    
    // Метод для удаления тега
    public boolean removeTag(String tag) {
        if (tag == null) return false;
        return tags.remove(tag.trim().toLowerCase());
    }
    
    // Метод для проверки наличия тега
    public boolean hasTag(String tag) {
        if (tag == null) return false;
        return tags.contains(tag.trim().toLowerCase());
    }
    
    @Override
    public String toString() {
        return String.format("Note{id=%d, title='%s', createdAt='%s', tags=%s}", 
                id, title, createdAt, tags);
    }
    
    // Новый метод для красивого вывода с отступами
    public String toFormattedString() {
        return String.format("    Note{id=%d, title='%s', createdAt='%s', tags=%s}", 
                id, title, createdAt, tags);
    }
}