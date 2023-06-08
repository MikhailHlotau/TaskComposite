package glotov.composite.parser;

import glotov.composite.entity.Component;
import glotov.composite.entity.Leaf;
import glotov.composite.entity.TextType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LetterParser extends DataParser {
    private static final Logger logger = LogManager.getLogger(LetterParser.class);

    @Override
    protected Component handleRequest(String text) {
        // Проверяем, является ли текст одиночной буквой
        if (isSingleLetter(text)) {
            return new Leaf(TextType.LETTER, text);
        } else {
            // Логируем предупреждение о недопустимом вводе
            logger.warn("Недопустимый ввод для буквы: " + text);
            return null;
        }
    }

    private boolean isSingleLetter(String text) {
        // Проверяем, является ли текст одиночной буквой
        return text.length() == 1 && Character.isLetter(text.charAt(0));
    }
}


