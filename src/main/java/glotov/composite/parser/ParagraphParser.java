package glotov.composite.parser;

import glotov.composite.entity.Component;
import glotov.composite.entity.Composite;
import glotov.composite.entity.TextType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class ParagraphParser extends DataParser {
    private static final Logger logger = LogManager.getLogger(ParagraphParser.class);
    private static final String SENTENCE_SPLIT = "(?<=[.!?\\.{3}])\\s+";

    @Override
    protected Component handleRequest(String text) {
        // Создаем композитный компонент для параграфа
        Composite paragraphComponent = new Composite(TextType.PARAGRAPH);
        // Создаем парсер предложений
        DataParser sentenceParser = new SentenceParser();
        // Разбиваем текст на предложения с использованием регулярного выражения
        String[] sentences = text.split(SENTENCE_SPLIT);
        for (String sentence : sentences) {
            sentence = sentence.trim();
            // Парсим каждое предложение
            Component sentenceComponent = sentenceParser.parse(sentence);
            if (!sentence.isEmpty()) {
                // Добавляем компонент предложения к параграфу
                paragraphComponent.addComponent(sentenceComponent);
            } else {
                // Логируем предупреждение, если предложение пустое
                logger.warn("The sentence is empty");
            }
        }
        return paragraphComponent;
    }
}
