package glotov.composite.entity;

import java.util.List;

public interface Component {
    TextType getType();
    String getText();
    List<Component> getComponents();
    void removeComponent(Component component);
    void addComponent(Component component);
    List<Component> getSentences();
}