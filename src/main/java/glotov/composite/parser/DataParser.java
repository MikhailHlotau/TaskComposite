package glotov.composite.parser;

import glotov.composite.entity.Component;

public abstract class DataParser {

    private DataParser nextParser;

    public void setNextParser(DataParser nextParser) {
        this.nextParser = nextParser;
    }

    public DataParser getNextParser() {
        return nextParser;
    }

    public Component parse(String text) {
        // Обработка запроса текущим парсером
        Component component = handleRequest(text);

        // Если текущий парсер не обработал текст и есть следующий парсер, передаем запрос ему
        if (component == null && nextParser != null) {
            return nextParser.parse(text);
        }

        return component;
    }

    // Метод, который должен быть реализован в подклассах для обработки запроса
    protected abstract Component handleRequest(String text);
}
