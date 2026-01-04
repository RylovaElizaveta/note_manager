package notes.logger;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NoteLogger {
    private static final DateTimeFormatter DATE_FORMATTER = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    /**
     * Записывает информационное сообщение в лог-файл
     * @param filename имя файла лога
     * @param message сообщение
     * @throws IOException если произошла ошибка ввода-вывода
     */
    public void logInfo(String filename, String message) throws IOException {
        log(filename, "INFO", message);
    }
    
    /**
     * Записывает сообщение об ошибке в лог-файл
     * @param filename имя файла лога
     * @param message сообщение
     * @throws IOException если произошла ошибка ввода-вывода
     */
    public void logError(String filename, String message) throws IOException {
        log(filename, "ERROR", message);
    }
    
    /**
     * Записывает предупреждение в лог-файл
     * @param filename имя файла лога
     * @param message сообщение
     * @throws IOException если произошла ошибка ввода-вывода
     */
    public void logWarning(String filename, String message) throws IOException {
        log(filename, "WARNING", message);
    }
    
    /**
     * Общий метод для записи в лог
     * @param filename имя файла лога
     * @param level уровень сообщения
     * @param message сообщение
     * @throws IOException если произошла ошибка ввода-вывода
     */
    private void log(String filename, String level, String message) throws IOException {
        String timestamp = LocalDateTime.now().format(DATE_FORMATTER);
        String logEntry = String.format("[%s] [%s] %s", timestamp, level, message);
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(logEntry);
            writer.newLine();
        }
    }
    
    /**
     * Читает содержимое лог-файла
     * @param filename имя файла лога
     * @return содержимое файла
     * @throws IOException если произошла ошибка ввода-вывода
     */
    public String readLog(String filename) throws IOException {
        StringBuilder content = new StringBuilder();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        
        return content.toString();
    }
    
    /**
     * Очищает лог-файл
     * @param filename имя файла лога
     * @throws IOException если произошла ошибка ввода-вывода
     */
    public void clearLog(String filename) throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("");
        }
    }
}