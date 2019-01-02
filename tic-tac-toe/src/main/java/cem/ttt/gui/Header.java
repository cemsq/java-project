package cem.ttt.gui;

import javax.swing.*;
import java.awt.*;

public class Header extends JPanel {

    public Header(int height) {
        this.setPreferredSize(new Dimension(this.getWidth(), height));
        this.setVisible(true);
    }
}
