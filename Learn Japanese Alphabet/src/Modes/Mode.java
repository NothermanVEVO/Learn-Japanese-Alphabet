package src.Modes;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

import src.Alphabet.Alphabet.Alphabets;
import src.Alphabet.Hiragana;
import src.Alphabet.Katakana;

public abstract class Mode extends JPanel{

    final int character_size = 250;

    protected Random rng = new Random();

    //? MODE OF STUDY
    protected boolean is_completely_random;
    protected boolean is_random_by_elimination;
    //TODO PSEUDO RANDOM, ANALYZE AND THROW A QUESTION BY PROBABILITY, THE MORE YOU MISS A QUESTION, THE MORE IT'LL APPEAR, SO DOES THE INVERSE

    //? CURRENT ALPHABETS
    protected List<Alphabets> alphabets = new ArrayList<>();

    protected Hiragana hiragana;
    protected Katakana katakana;

    protected Alphabets current_alphabet;

    protected String string_in_japanese;
    protected String string_in_syllable;

    protected abstract void adjust_panel();
    protected abstract void actionListener(ActionEvent l);

    protected Alphabets get_random_alphabet(){
        if (alphabets.size() == 0) {
            //TODO END SCREEN AT HERE
            System.out.println("ACABOU OS ALFABETOS, JA RESPONDEU TUDO!");
            System.exit(-1);
        }
        Alphabets alphabet = alphabets.get(rng.nextInt(0, alphabets.size()));
        switch (alphabet) {
            case HIRAGANA:
                if (hiragana.is_empty()) {
                    alphabets.remove(Alphabets.HIRAGANA);
                    return get_random_alphabet();
                }
                return Alphabets.HIRAGANA;
            case KATAKANA:
                if (katakana.is_empty()) {
                    alphabets.remove(Alphabets.KATAKANA);
                    return get_random_alphabet();
                }
                return Alphabets.KATAKANA;
            default:
                System.out.println("algum erro aq, get_random_alphabet");
                return null;
        }
    }

    protected void get_random_question(){
        current_alphabet = get_random_alphabet();
        switch (current_alphabet) {
            case HIRAGANA:
                string_in_japanese = hiragana.get_random_symbol();
                string_in_syllable = hiragana.answer_of_symbol(string_in_japanese);
                break;
            case KATAKANA:
                string_in_japanese = katakana.get_random_symbol();
                string_in_syllable = katakana.answer_of_symbol(string_in_japanese);
                break;
            default:
                System.out.println("algum erro aq, get_random_question");
                return;
        }
    }

    protected void answered_right(){
        switch (current_alphabet) {
            case HIRAGANA:
                hiragana.answered_right_symbol(hiragana.get_current_letter(), string_in_japanese);
                break;
            case KATAKANA:
                katakana.answered_right_symbol(katakana.get_current_letter(), string_in_japanese);
                break;
            default:
                System.out.println("algum erro aq, actionListener");
                return;
        }
    }

}
