package src.GUI;

import java.util.List;

import java.awt.Cursor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import src.Alphabet.Alphabet.Alphabets;
import src.Data.Data;
import src.Alphabet.Hiragana;
import src.Alphabet.Katakana;
import src.Modes.JapaneseToSyllable;
import src.Modes.SyllableToJapanese;
import src.Modes.Mode.Modes;

public class Window extends JFrame{
    private static JapaneseToSyllable japanese_to_syllable = new JapaneseToSyllable();
    private static SyllableToJapanese syllable_to_japanese = new SyllableToJapanese();
    private static Config config;
    private static Results results;

    private static int width;
    private static int height;

    private static Modes last_mode;

    public static Window window;

    public static Cursor hand_cursor = new Cursor(Cursor.HAND_CURSOR);

    public Window(int width, int height){
        Window.width = width;
        Window.height = height;
        window = this;

        this.setSize(width, height);
        this.setTitle("Learn Japanese Alphabets");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Data.save_file();
                System.exit(0);
            }
        });
        config = new Config(0, 0, width, height);
        this.add(config);
        results = new Results(0, 0, width, height);
        results.setVisible(false);
        this.add(results);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    public static void back_to_config(){
        if(last_mode != null){
            switch (last_mode) {
                case JAPANESE_TO_SYLLABLE:
                    window.remove(japanese_to_syllable);
                    japanese_to_syllable = null;
                    break;
                case SYLLABLE_TO_JAPANESE:
                    window.remove(syllable_to_japanese);
                    syllable_to_japanese = null;
                    break;
            }
        }
        last_mode = null;
        results.setVisible(false);
        config.setVisible(true);
    }

    public static void show_results(Hiragana hiragana, Katakana katakana){
        if(last_mode != null){
            switch (last_mode) {
                case JAPANESE_TO_SYLLABLE:
                    window.remove(japanese_to_syllable);
                    japanese_to_syllable = null;
                    break;
                case SYLLABLE_TO_JAPANESE:
                    window.remove(syllable_to_japanese);
                    syllable_to_japanese = null;
                    break;
            }
        }
        last_mode = null;
        results.set_labels(hiragana, katakana);
        results.setVisible(true);
    }

    public static void create_mode(Modes mode, List<Alphabets> alphabets, 
        Hiragana hiragana, Katakana katakana, boolean by_elimination){
        last_mode = mode;
        switch (mode) {
            case JAPANESE_TO_SYLLABLE:
                // window.remove(japanese_to_syllable);
                japanese_to_syllable = new JapaneseToSyllable(0, 0, width, height, 
                    alphabets, hiragana, katakana, by_elimination);
                japanese_to_syllable.setVisible(true);
                window.add(japanese_to_syllable);
                break;
            case SYLLABLE_TO_JAPANESE:
                // window.remove(syllable_to_japanese);
                syllable_to_japanese = new SyllableToJapanese(0, 0, width, height, 
                    alphabets, hiragana, katakana, by_elimination);
                syllable_to_japanese.setVisible(true);
                window.add(syllable_to_japanese);
                break;
        }
        config.setVisible(false);
        window.repaint();
    }
}
