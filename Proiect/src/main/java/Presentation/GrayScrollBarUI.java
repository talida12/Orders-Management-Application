package Presentation;

import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class GrayScrollBarUI extends BasicScrollBarUI {

    @Override
    protected void configureScrollBarColors() {
        this.trackColor = new Color(128, 128, 128);
        this.thumbColor = new Color(138, 134, 142);
    }
}
