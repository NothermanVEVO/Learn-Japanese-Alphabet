package src;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Screen extends JPanel{

    JLabel text = new JLabel();
    GUI gui = new GUI();
    
    Screen(){
        this.setSize(800, 600);
        this.setBackground(Color.gray);
        this.setOpaque(true);
        this.add(gui);
        this.setLayout(null);
    }

}
