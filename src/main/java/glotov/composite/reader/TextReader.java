package glotov.composite.reader;

import glotov.composite.exception.TextException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class TextReader {
    public String readFile(String filePath) throws TextException {
        // Инициализация StringBuilder для конкатенации строк
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Чтение файла построчно
            while ((line = reader.readLine()) != null) {
                // Добавление текущей строки к StringBuilder с символом новой строки
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            // Генерация исключения TextException с сообщением об ошибке
            throw new TextException("Ошибка при чтении файла: " + e.getMessage(), e);
        }
        // Преобразование StringBuilder в строку и возврат результата
        return sb.toString();
    }
}

