package glotov.composite.main;

import glotov.composite.entity.Component;
import glotov.composite.entity.TextType;
import glotov.composite.exception.TextException;
import glotov.composite.parser.*;
import glotov.composite.reader.TextReader;
import glotov.composite.util.PropertiesStreamReader;

import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Component> paragraphs = null;
        Component textComponent = null;
        LexemeParser lexemeParser = new LexemeParser();

        try {
            // Создание объекта PropertiesStreamReader
            PropertiesStreamReader ob = new PropertiesStreamReader();

            // Получение пути к файлу из ресурсов
            String filePath = String.valueOf(ob.getFileFromResource("text.txt"));

            // Создание экземпляра парсера текста
            DataParser textParser = new TextParser();

            // Создание экземпляра объекта для чтения файла
            TextReader textReader = new TextReader();
            String fileContent;

            // Чтение содержимого файла с помощью TextReader
            try {
                fileContent = textReader.readFile(filePath);
            } catch (TextException e) {
                // Обработка исключения TextException, если возникла ошибка чтения файла
                System.out.println("Error reading the file: " + e.getMessage());
                return;
            }

            System.out.println(fileContent);
            System.out.println("////////");

            // Парсинг содержимого файла с помощью textParser
            textComponent = textParser.parse(fileContent);

            // Вывод полного текста
            System.out.println("TEXT:");
            System.out.println(textComponent.getText());
            System.out.println("////////");

            // Вывод параграфов
            System.out.println("PARAGRAPHS:");
            paragraphs = textComponent.getComponents();
            for (Component paragraph : paragraphs) {
                System.out.println(paragraph.getText());
            }
            System.out.println("////////");

            // Вывод предложений
            System.out.println("SENTENCES:");
            for (Component paragraph : paragraphs) {
                List<Component> sentences = paragraph.getSentences();
                for (Component sentence : sentences) {
                    System.out.println(sentence.getText());
                }
            }
            System.out.println("////////");

            // Вывод лексем
            System.out.println("LEXEME:");
            for (Component paragraph : paragraphs) {
                List<Component> sentences = paragraph.getComponents();
                for (Component sentence : sentences) {
                    List<Component> lexems = sentence.getComponents();
                    for (Component lexeme : lexems) {
                        System.out.println(lexeme.getText());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("////////");

        // Создание компаратора для сортировки абзацев по количеству предложений
        Comparator<Component> sentenceCountComparator = Comparator.comparingInt(paragraph -> paragraph.getSentences().size());

        // Сортировка абзацев по количеству предложений
        paragraphs.sort(sentenceCountComparator);

        // Вывод отсортированных абзацев
        for (Component paragraph : paragraphs) {
            System.out.println(paragraph.getText());
            System.out.println();
        }
    }
}

