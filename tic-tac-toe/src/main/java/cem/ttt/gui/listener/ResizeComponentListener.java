package cem.ttt.gui.listener;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.function.Consumer;

public class ResizeComponentListener implements ComponentListener {

    private final Consumer<ComponentEvent> consumer;

    public ResizeComponentListener(Consumer<ComponentEvent> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        consumer.accept(e);
    }

    public void componentMoved(ComponentEvent e) {}
    public void componentShown(ComponentEvent e) {}
    public void componentHidden(ComponentEvent e) {}
}
