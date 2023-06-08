package glotov.composite.parser;

import glotov.composite.entity.Component;
import glotov.composite.entity.Composite;
import glotov.composite.entity.TextType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class SentenceParser extends DataParser {
    private static final Logger logger = LogManager.getLogger(SentenceParser.class);
    private static final String WORD_SPLIT = "\\s+";

    @Override
    protected Component handleRequest(String text) {
        // Создаем композитный компонент для предложения
        Composite sentenceComponent = new Composite(TextType.SENTENCE);
        // Создаем парсер слов
        DataParser wordParser = new WordParser();
        // Разбиваем предложение на слова с использованием разделителя
        String[] words = text.trim().split(WORD_SPLIT);
        for (String word : words) {
            Component wordComponent = wordParser.parse(word);
            if (!word.isEmpty()) {
                // Добавляем компонент слова к предложению
                sentenceComponent.addComponent(wordComponent);
            } else {
                // Логируем предупреждение, если слово пустое
                logger.warn("The word is empty");
            }
        }
        return sentenceComponent;
    }
}

