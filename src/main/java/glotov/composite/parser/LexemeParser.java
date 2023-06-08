package glotov.composite.parser;

import glotov.composite.entity.Component;
import glotov.composite.entity.Composite;
import glotov.composite.entity.Leaf;
import glotov.composite.entity.TextType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LexemeParser extends DataParser {
    private static final Logger logger = LogManager.getLogger(LexemeParser.class);

    @Override
    protected Component handleRequest(String text) {
        Composite lexemeComponent = new Composite(TextType.LEXEME); // Создаем компонент типа Composite для хранения лексем
        DataParser symbolParser = new SymbolParser();  // Создаём общий парсер для обработки как букв, так и знаков препинания
        // Обрабатываем каждый символ в тексте
        for (char c : text.toCharArray()) {
            boolean isPunctuation = !Character.isLetterOrDigit(c); // Проверяем, является ли символ знаком пунктуации
            Component symbolComponent = symbolParser.parse(Character.toString(c)); // Обрабатываем символ с помощью парсера
            if (symbolComponent != null) {
                Composite component = new Composite(symbolComponent.getType()); // Создаем компонент типа Composite для хранения буквы или знака препинания
                component.addComponent(new Leaf(symbolComponent.getType(), symbolComponent.getText(), isPunctuation)); // Добавляем соответствующий Leaf с информацией о букве или знаке препинания
                lexemeComponent.addComponent(component); // Добавляем компонент в лексему
            } else {
                logger.log(Level.WARN, "The symbol is null");
            }
        }
        return lexemeComponent;
    }

}
