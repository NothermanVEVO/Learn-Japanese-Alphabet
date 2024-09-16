package src;
import javax.swing.JFrame;

import src.Modes.JapaneseToSyllable;
import src.Modes.SyllableToJapanese;

public class Window extends JFrame{
    // Screen screen = new Screen();
    JapaneseToSyllable japanese_to_syllable;
    SyllableToJapanese syllable_to_japanese;
    
    public Window(int width, int height){
        this.setSize(width, height);
        this.setTitle("Learn Japanese Alphabets");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // this.add(screen);
        // japanese_to_syllable = new JapaneseToSyllable(0, 0, width, height);
        // this.add(japanese_to_syllable);
        syllable_to_japanese = new SyllableToJapanese(0, 0, width, height);
        this.add(syllable_to_japanese);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false); //? new
        // screen.gui.loop();
        this.setVisible(true);
    }
}
