package glotov.composite.parser;

import glotov.composite.entity.Component;
import glotov.composite.entity.Composite;
import glotov.composite.entity.TextType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class TextParser extends DataParser {
    private static final Logger logger = LogManager.getLogger(TextParser.class);
    private static final String PARAGRAPH_SPLIT = "(\\t|\\s{4})";

    public TextParser() {
        // Создаем экземпляры парсеров
        DataParser paragraphParser = new ParagraphParser();
        DataParser sentenceParser = new SentenceParser();
        DataParser wordParser = new WordParser();
        DataParser letterParser = new LetterParser();

        // Устанавливаем связи между парсерами
        this.setNextParser(paragraphParser);
        paragraphParser.setNextParser(sentenceParser);
        sentenceParser.setNextParser(wordParser);
        wordParser.setNextParser(letterParser);
    }

    @Override
    protected Component handleRequest(String text) {
        // Создаем композитный компонент для текста
        Composite textComponent = new Composite(TextType.TEXT);
        DataParser paragraphParser = this.getNextParser(); // Получить следующий парсер из текущего парсера

        // Разбиваем текст на абзацы с использованием разделителя
        String[] paragraphs = text.split(PARAGRAPH_SPLIT);
        for (String paragraph : paragraphs) {
            paragraph = paragraph.trim();
            Component paragraphComponent = paragraphParser.parse(paragraph);
            if (!paragraph.isEmpty()) {
                // Добавляем компонент абзаца к тексту
                textComponent.addComponent(paragraphComponent);
            } else {
                // Логируем предупреждение, если абзац пустой
                logger.warn("The paragraph is empty");
            }
        }
        return textComponent;
    }
}


