package notes.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Универсальный репозиторий для хранения объектов с целочисленным ID
 * @param <T> тип объектов, хранящихся в репозитории
 */
public class Repository<T> {
    private Map<Integer, T> items;
    
    public Repository() {
        this.items = new HashMap<>();
    }
    
    /**
     * Добавляет элемент в репозиторий
     * @param id идентификатор элемента
     * @param item элемент для добавления
     */
    public void add(int id, T item) {
        if (item == null) {
            throw new IllegalArgumentException("Элемент не может быть null");
        }
        items.put(id, item);
    }
    
    /**
     * Получает элемент по ID
     * @param id идентификатор элемента
     * @return элемент или null, если не найден
     */
    public T get(int id) {
        return items.get(id);
    }
    
    /**
     * Возвращает все элементы в виде списка
     * @return список всех элементов
     */
    public List<T> getAll() {
        return new ArrayList<>(items.values());
    }
    
    /**
     * Удаляет элемент по ID
     * @param id идентификатор элемента
     * @return true, если элемент был удален, false если не найден
     */
    public boolean remove(int id) {
        return items.remove(id) != null;
    }
    
    /**
     * Возвращает количество элементов в репозитории
     * @return количество элементов
     */
    public int size() {
        return items.size();
    }
    
    /**
     * Проверяет, пуст ли репозиторий
     * @return true, если репозиторий пуст
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }
    
    /**
     * Проверяет наличие элемента по ID
     * @param id идентификатор элемента
     * @return true, если элемент с таким ID существует
     */
    public boolean contains(int id) {
        return items.containsKey(id);
    }
    
    /**
     * Очищает репозиторий
     */
    public void clear() {
        items.clear();
    }
    
    /**
     * Возвращает все ID элементов
     * @return список ID
     */
    public List<Integer> getAllIds() {
        return new ArrayList<>(items.keySet());
    }
}