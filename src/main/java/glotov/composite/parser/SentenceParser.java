package glotov.composite.parser;

import glotov.composite.entity.Component;
import glotov.composite.entity.Composite;
import glotov.composite.entity.TextType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SentenceParser extends DataParser {
    private static final Logger logger = LogManager.getLogger(SentenceParser.class);
    private static final String LEXEME_SPLIT = "\\s+";
    @Override
    protected Component handleRequest(String text) {
        Composite sentenceComponent = new Composite(TextType.SENTENCE); // Создаем композитный компонент для предложения
        DataParser lexemeParser = new LexemeParser();  // Создаем парсер лексем
        String[] lexems = text.trim().split(LEXEME_SPLIT); // Разбиваем предложение на лексемы с использованием разделителя
        for (String lexeme : lexems) {
            Component lexemeComponent = lexemeParser.parse(lexeme);
            if (!lexeme.isEmpty()) {
                sentenceComponent.addComponent(lexemeComponent); // Добавляем компонент лексемы к предложению
            } else {
                logger.warn("The lexeme is empty");  // Логируем предупреждение, если слово пустое
            }
        }
        return sentenceComponent;
    }
}

