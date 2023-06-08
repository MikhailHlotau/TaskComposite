package glotov.composite.entity;

import java.util.List;

public class Leaf implements Component {
    private TextType type;
    private String text;
    private boolean isPunctuation; // Добавлено новое поле

    public Leaf(TextType type, String text, boolean isPunctuation) {
        this.type = type;
        this.text = text;
        this.isPunctuation = isPunctuation;
    }

    public Leaf(TextType type, String text) {
        this.type = type;
        this.text = text;
    }

    @Override
    public TextType getType() {
        return type;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public List<Component> getComponents() {
        return null;
    }

    @Override
    public void removeComponent(Component component) {
    }

    @Override
    public void addComponent(Component component) {
    }

    @Override
    public List<Component> getSentences() {
        return null;
    }

    @Override
    public String toString() {
        return "Leaf{" +
                "type=" + type +
                ", text='" + text + '\'' +
                '}';
    }

    public boolean isPunctuation() {
        String punctuationSymbols = ".,?!:;-";
        return punctuationSymbols.contains(text);
    }
}
