package src.Modes;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

import src.Alphabet.Alphabet.Alphabets;
import src.GUI.Window;
import src.Alphabet.Hiragana;
import src.Alphabet.Katakana;

public abstract class Mode extends JPanel{

    private String last_string = "";

    final protected int character_size = 250;

    protected Random rng = new Random();

    //? MODE OF STUDY
    protected boolean is_completely_random;
    protected boolean is_random_by_elimination;

    //? CURRENT ALPHABETS
    protected List<Alphabets> alphabets;

    protected Hiragana hiragana;
    protected Katakana katakana;

    protected Alphabets current_alphabet;

    protected String string_in_japanese;
    protected String string_in_syllable;

    public enum Modes{
        JAPANESE_TO_SYLLABLE,
        SYLLABLE_TO_JAPANESE
    }

    protected Mode(){
    }

    protected Mode(List<Alphabets> alphabets, Hiragana hiragana, Katakana katakana, boolean by_elimination){
        this.alphabets = new ArrayList<>(alphabets);
        this.hiragana = hiragana;
        this.katakana = katakana;
        this.is_random_by_elimination = by_elimination;
    }

    protected abstract void adjust_panel();
    protected abstract void actionListener(ActionEvent l);

    protected Alphabets get_random_alphabet(){
        if (alphabets.size() == 0) {
            //! END SCREEN AT HERE
            if(this instanceof JapaneseToSyllable){
                Window.show_results(hiragana, katakana);
            } else{
                Window.back_to_config();
            }
            return null;
            // System.out.println("ACABOU OS ALFABETOS, JA RESPONDEU TUDO!");
            // System.exit(-1);
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
        if (current_alphabet == null) {
            return;
        }
        switch (current_alphabet) {
            case HIRAGANA:
                string_in_japanese = hiragana.get_random_symbol();
                string_in_syllable = hiragana.answer_of_symbol(string_in_japanese);
                // Prevent from getting the same question as before
                if(last_string.equals(string_in_japanese)){
                    // Prevent from getting stuck in a loop
                    if(alphabets.size() == 1 && hiragana.has_only_one_string()){
                        break;
                    }
                    get_random_question();
                    return;
                }
                break;
            case KATAKANA:
                string_in_japanese = katakana.get_random_symbol();
                string_in_syllable = katakana.answer_of_symbol(string_in_japanese);
                // Prevent from getting the same question as before
                if(last_string.equals(string_in_japanese)){
                    // Prevent from getting stuck in a loop
                    if(alphabets.size() == 1 && katakana.has_only_one_string()){
                        break;
                    }
                    get_random_question();
                    return;
                }
                break;
            default:
                System.out.println("algum erro aq, get_random_question");
                return;
        }
        last_string = string_in_japanese;
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

    protected void answered_wrong(){
        switch (current_alphabet) {
            case HIRAGANA:
                switch (hiragana.get_current_letter()) {
                    case A:
                        if(!hiragana.wrong_answers_A.contains(string_in_japanese)){
                            hiragana.wrong_answers_A = hiragana.wrong_answers_A.concat(string_in_japanese);
                        }
                        break;
                    case E:
                        if(!hiragana.wrong_answers_E.contains(string_in_japanese)){
                            hiragana.wrong_answers_E = hiragana.wrong_answers_E.concat(string_in_japanese);
                        }
                        break;
                    case I:
                        if(!hiragana.wrong_answers_I.contains(string_in_japanese)){
                            hiragana.wrong_answers_I = hiragana.wrong_answers_I.concat(string_in_japanese);
                        }
                        break;
                    case O:
                        if(!hiragana.wrong_answers_O.contains(string_in_japanese)){
                            hiragana.wrong_answers_O = hiragana.wrong_answers_O.concat(string_in_japanese);
                        }
                        break;
                    case U:
                        if(!hiragana.wrong_answers_U.contains(string_in_japanese)){
                            hiragana.wrong_answers_U = hiragana.wrong_answers_U.concat(string_in_japanese);
                        }
                        break;
                }
                break;
            case KATAKANA:
                switch (katakana.get_current_letter()) {
                    case A:
                        if(!katakana.wrong_answers_A.contains(string_in_japanese)){
                            katakana.wrong_answers_A = katakana.wrong_answers_A.concat(string_in_japanese);
                        }
                        break;
                    case E:
                        if(!katakana.wrong_answers_E.contains(string_in_japanese)){
                            katakana.wrong_answers_E = katakana.wrong_answers_E.concat(string_in_japanese);
                        }
                        break;
                    case I:
                        if(!katakana.wrong_answers_I.contains(string_in_japanese)){
                            katakana.wrong_answers_I = katakana.wrong_answers_I.concat(string_in_japanese);
                        }
                        break;
                    case O:
                        if(!katakana.wrong_answers_O.contains(string_in_japanese)){
                            katakana.wrong_answers_O = katakana.wrong_answers_O.concat(string_in_japanese);
                        }
                        break;
                    case U:
                        if(!katakana.wrong_answers_U.contains(string_in_japanese)){
                            katakana.wrong_answers_U = katakana.wrong_answers_U.concat(string_in_japanese);
                        }
                        break;
                }
                break;
        }
    }

    // public int get_number_of_correct_answers(Alphabets alphabet, Letter letter){
    //     switch (alphabet) {
    //         case HIRAGANA:
    //             switch (letter) {
    //                 case A:
    //                     return hiragana.symbols_A.length - hiragana.wrong_answers_A.length();
    //                 case E:
    //                     return hiragana.symbols_E.length - hiragana.wrong_answers_E.length();
    //                 case I:
    //                     return hiragana.symbols_I.length - hiragana.wrong_answers_I.length();
    //                 case O:
    //                     return hiragana.symbols_O.length - hiragana.wrong_answers_O.length();
    //                 case U:
    //                     return hiragana.symbols_U.length - hiragana.wrong_answers_U.length();
    //             }
    //             return -1;
    //         case KATAKANA:
    //             switch (letter) {
    //                 case A:
    //                     return katakana.symbols_A.length - katakana.wrong_answers_A.length();
    //                 case E:
    //                     return katakana.symbols_E.length - katakana.wrong_answers_E.length();
    //                 case I:
    //                     return katakana.symbols_I.length - katakana.wrong_answers_I.length();
    //                 case O:
    //                     return katakana.symbols_O.length - katakana.wrong_answers_O.length();
    //                 case U:
    //                     return katakana.symbols_U.length - katakana.wrong_answers_U.length();
    //             }
    //             return -1;
    //     }
    //     return -1;
    // }

    // public void show_results(){
    //     if(hiragana != null) {
    //         if(hiragana.get_letters().contains(Letter.A)){
    //             System.out.println(get_number_of_correct_answers(Alphabets.HIRAGANA, Letter.A) + "/" + hiragana.symbols_A.length);
    //         }
    //         if(hiragana.get_letters().contains(Letter.E)){
    //             System.out.println(get_number_of_correct_answers(Alphabets.HIRAGANA, Letter.E) + "/" + hiragana.symbols_E.length);
    //         }
    //         if(hiragana.get_letters().contains(Letter.I)){
    //             System.out.println(get_number_of_correct_answers(Alphabets.HIRAGANA, Letter.I) + "/" + hiragana.symbols_I.length);
    //         }
    //         if(hiragana.get_letters().contains(Letter.O)){
    //             System.out.println(get_number_of_correct_answers(Alphabets.HIRAGANA, Letter.O) + "/" + hiragana.symbols_O.length);
    //         }
    //         if(hiragana.get_letters().contains(Letter.U)){
    //             System.out.println(get_number_of_correct_answers(Alphabets.HIRAGANA, Letter.U) + "/" + hiragana.symbols_U.length);
    //         }
    //     }
    //     if(katakana != null) {
    //         if(katakana.get_letters().contains(Letter.A)){
    //             System.out.println(get_number_of_correct_answers(Alphabets.KATAKANA, Letter.A) + "/" + katakana.symbols_A.length);
    //         }
    //         if(katakana.get_letters().contains(Letter.E)){
    //             System.out.println(get_number_of_correct_answers(Alphabets.KATAKANA, Letter.E) + "/" + katakana.symbols_E.length);
    //         }
    //         if(katakana.get_letters().contains(Letter.I)){
    //             System.out.println(get_number_of_correct_answers(Alphabets.KATAKANA, Letter.I) + "/" + katakana.symbols_I.length);
    //         }
    //         if(katakana.get_letters().contains(Letter.O)){
    //             System.out.println(get_number_of_correct_answers(Alphabets.KATAKANA, Letter.O) + "/" + katakana.symbols_O.length);
    //         }
    //         if(katakana.get_letters().contains(Letter.U)){
    //             System.out.println(get_number_of_correct_answers(Alphabets.KATAKANA, Letter.U) + "/" + katakana.symbols_U.length);
    //         }
    //     }
    // }

}
