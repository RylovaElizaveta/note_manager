package notes.model;

public class Notebook {
    private int id;
    private String name;
    private String description;
    
    public Notebook(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    
    // Геттеры и сеттеры
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public String toString() {
        return String.format("Notebook{id=%d, name='%s', description='%s'}", 
                id, name, description);
    }
}