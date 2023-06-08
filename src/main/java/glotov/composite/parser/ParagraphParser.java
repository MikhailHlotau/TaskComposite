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
        Composite paragraphComponent = new Composite(TextType.PARAGRAPH); // Создаем композитный компонент для параграфа
        DataParser sentenceParser = new SentenceParser();  // Создаем парсер предложений
        String[] sentences = text.split(SENTENCE_SPLIT); // Разбиваем текст на предложения с использованием регулярного выражения
        for (String sentence : sentences) {
            sentence = sentence.trim();
            Component sentenceComponent = sentenceParser.parse(sentence); // Парсим каждое предложение
            if (!sentence.isEmpty()) {
                paragraphComponent.addComponent(sentenceComponent);  // Добавляем компонент предложения к параграфу
            } else {
                logger.warn("The sentence is empty");  // Логируем предупреждение, если предложение пустое
            }
        }
        return paragraphComponent;
    }
}
