package glotov.composite.parser;

import glotov.composite.entity.Component;
import glotov.composite.entity.Composite;
import glotov.composite.entity.TextType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TextParser extends DataParser {
    private static final Logger logger = LogManager.getLogger(TextParser.class);
    private static final String PARAGRAPH_SPLIT = "(\\t|\\s{4})";
    public TextParser() {
        // Создаем экземпляры парсеров
        DataParser paragraphParser = new ParagraphParser();
        DataParser sentenceParser = new SentenceParser();
        DataParser wordParser = new LexemeParser();
        DataParser letterParser = new SymbolParser();

        // Устанавливаем связи между парсерами
        this.setNextParser(paragraphParser);
        paragraphParser.setNextParser(sentenceParser);
        sentenceParser.setNextParser(wordParser);
        wordParser.setNextParser(letterParser);
    }

    @Override
    protected Component handleRequest(String text) {
        Composite textComponent = new Composite(TextType.TEXT); // Создаем композитный компонент для текста
        DataParser paragraphParser = this.getNextParser(); // Получаем следующий парсер из текущего парсера
        String[] paragraphs = text.split(PARAGRAPH_SPLIT); // Разбиваем текст на абзацы с использованием разделителя
        for (String paragraph : paragraphs) {
            paragraph = paragraph.trim();
            Component paragraphComponent = paragraphParser.parse(paragraph);
            if (!paragraph.isEmpty()) {
                textComponent.addComponent(paragraphComponent); // Добавляем компонент абзаца к тексту
            } else {
                logger.warn("The paragraph is empty"); // Логируем предупреждение, если абзац пустой
            }
        }
        return textComponent;
    }
}


