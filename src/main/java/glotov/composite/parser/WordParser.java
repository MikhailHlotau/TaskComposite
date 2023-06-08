package glotov.composite.parser;

import glotov.composite.entity.Component;
import glotov.composite.entity.Composite;
import glotov.composite.entity.TextType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WordParser extends DataParser {
    private static final Logger logger = LogManager.getLogger(WordParser.class);

    @Override
    protected Component handleRequest(String text) {
        // Создаем композитный компонент для слова
        Composite wordComponent = new Composite(TextType.WORD);
        // Создаем парсер букв
        DataParser letterParser = new LetterParser();
        for (char c : text.toCharArray()) {
            Component letterComponent = letterParser.parse(Character.toString(c));
            if (letterComponent != null) {
                // Добавляем компонент буквы к компоненту слова
                wordComponent.addComponent(letterComponent);
            } else {
                // Логируем предупреждение, если компонент буквы равен null
                logger.warn("The letter is null");
            }
        }
        return wordComponent;
    }
}
