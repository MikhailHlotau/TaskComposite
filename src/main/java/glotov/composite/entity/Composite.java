package glotov.composite.entity;

import java.util.ArrayList;
import java.util.List;

public class Composite implements Component {
    private TextType type;
    private List<Component> components;

    public Composite(TextType type) {
        this.type = type;
        this.components = new ArrayList<>();
    }

    @Override
    public TextType getType() {
        return type;
    }

    @Override
    public String getText() {
        StringBuilder sb = new StringBuilder();
        for (Component component : components) {
            sb.append(component.getText());
        }
        return sb.toString();
    }

    @Override
    public List<Component> getComponents() {
        return components;
    }

    public List<Component> getSentences() {
        List<Component> sentences = new ArrayList<>();
        for (Component component : components) {
            if (component.getType() == TextType.SENTENCE) {
                sentences.add(component);
            }
        }
        return sentences;
    }
    @Override
    public void removeComponent(Component component) {
        components.remove(component);
    }

    @Override
    public void addComponent(Component component) {
        components.add(component);
    }

    @Override
    public String toString() {
        return "Composite{" +
                "type=" + type +
                ", components=" + components +
                '}';
    }
}
