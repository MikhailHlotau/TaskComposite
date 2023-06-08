package glotov.composite.parser;

import glotov.composite.entity.Component;
import glotov.composite.entity.Leaf;
import glotov.composite.entity.TextType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SymbolParser extends DataParser {
    private static final Logger logger = LogManager.getLogger(SymbolParser.class);

    @Override
    protected Component handleRequest(String text) {
        return new Leaf(TextType.SYMBOL, text);
    }
}


