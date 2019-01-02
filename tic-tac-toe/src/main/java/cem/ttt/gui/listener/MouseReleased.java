package cem.ttt.gui.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.function.Consumer;

public class MouseReleased implements MouseListener {

    private final Consumer<MouseEvent> consumer;

    public MouseReleased(Consumer<MouseEvent> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        consumer.accept(e);
    }

    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
