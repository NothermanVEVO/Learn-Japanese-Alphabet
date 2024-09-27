package src.Alphabet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class Alphabet {

    protected static Random rng = new Random();

    public enum Alphabets{
        HIRAGANA,
        KATAKANA
    }

    public enum Letter{
        A,
        E,
        I,
        O,
        U
    }

    protected List<Letter> has_letter = new ArrayList<>();
    protected List<Letter> has_letter_copy = new ArrayList<>();

    protected List<String> list_symbols_A;
    protected List<String> list_correct_answer_A;
    public String wrong_answers_A = "";

    protected List<String> list_symbols_E;
    protected List<String> list_correct_answer_E;
    public String wrong_answers_E = "";

    protected List<String> list_symbols_I;
    protected List<String> list_correct_answer_I;
    public String wrong_answers_I = "";

    protected List<String> list_symbols_O;
    protected List<String> list_correct_answer_O;
    public String wrong_answers_O = "";

    protected List<String> list_symbols_U;
    protected List<String> list_correct_answer_U;
    public String wrong_answers_U = "";

    protected Letter current_letter;

    Alphabet(Letter... has_letter){
        for (Letter letter : has_letter) {
            this.has_letter.add(letter);
            this.has_letter_copy.add(letter);
        }
    }

    protected void create_copies_symbols(String[] a, String[] e, String[] i, String[] o, String[] u){
        if(has_letter.contains(Letter.A)){
            list_symbols_A = new ArrayList<>(Arrays.asList(a));
        }

        if(has_letter.contains(Letter.E)){
            list_symbols_E = new ArrayList<>(Arrays.asList(e));
        }

        if(has_letter.contains(Letter.I)){
            list_symbols_I = new ArrayList<>(Arrays.asList(i));
        }

        if(has_letter.contains(Letter.O)){
            list_symbols_O = new ArrayList<>(Arrays.asList(o));
        }

        if(has_letter.contains(Letter.U)){
            list_symbols_U = new ArrayList<>(Arrays.asList(u));
        }
    }

    protected void create_copies_correct_answer(String[] correct_a, String[] correct_e, String[] correct_i, String[] correct_o, String[] correct_u){
        if(has_letter.contains(Letter.A)){
            list_correct_answer_A = new ArrayList<>(Arrays.asList(correct_a));
        }

        if(has_letter.contains(Letter.E)){
            list_correct_answer_E = new ArrayList<>(Arrays.asList(correct_e));
        }

        if(has_letter.contains(Letter.I)){
            list_correct_answer_I = new ArrayList<>(Arrays.asList(correct_i));
        }

        if(has_letter.contains(Letter.O)){
            list_correct_answer_O = new ArrayList<>(Arrays.asList(correct_o));
        }

        if(has_letter.contains(Letter.U)){
            list_correct_answer_U = new ArrayList<>(Arrays.asList(correct_u));
        }
    }

    public Letter belongs_to_letter(String symbol){
        if(list_symbols_A != null && !list_symbols_A.isEmpty() && list_symbols_A.contains(symbol)){
            return Letter.A;
        } else if(list_symbols_E != null && !list_symbols_E.isEmpty() && list_symbols_E.contains(symbol)){
            return Letter.E;
        } else if(list_symbols_I != null && !list_symbols_I.isEmpty() && list_symbols_I.contains(symbol)){
            return Letter.I;
        } else if(list_symbols_O != null && !list_symbols_O.isEmpty() && list_symbols_O.contains(symbol)){
            return Letter.O;
        } else if(list_symbols_U != null && !list_symbols_U.isEmpty() && list_symbols_U.contains(symbol)){
            return Letter.U;
        }
        System.out.println("algum erro aq, belongs_to_letter");
        return null;
    }

    public String answer_of_symbol(String symbol){
        switch (belongs_to_letter(symbol)) {
            case A:
                return list_correct_answer_A.get(list_symbols_A.indexOf(symbol));
            case E:
                return list_correct_answer_E.get(list_symbols_E.indexOf(symbol));
            case I:
                return list_correct_answer_I.get(list_symbols_I.indexOf(symbol));
            case O:
                return list_correct_answer_O.get(list_symbols_O.indexOf(symbol));
            case U:
                return list_correct_answer_U.get(list_symbols_U.indexOf(symbol));
            default:
                System.out.println("algum erro aq, answer_of_symbol");
                return null;
        }
    }

    public Letter get_random_letter(){
        return has_letter.get(rng.nextInt(0, has_letter.size()));
    }

    public String get_random_symbol(){
        current_letter = get_random_letter();
        switch (current_letter) {
            case A:
                return list_symbols_A.get(rng.nextInt(0, list_symbols_A.size()));
            case E:
                return list_symbols_E.get(rng.nextInt(0, list_symbols_E.size()));
            case I:
                return list_symbols_I.get(rng.nextInt(0, list_symbols_I.size()));
            case O:
                return list_symbols_O.get(rng.nextInt(0, list_symbols_O.size()));
            case U:
                return list_symbols_U.get(rng.nextInt(0, list_symbols_U.size()));
            default:
            System.out.println("algum erro aq, get_random_symbol");
                return null;
        }
    }
    
    public void answered_right_symbol(Letter letter, String symbol){
        int index;
        switch (letter) {
            case A:
                index = list_symbols_A.indexOf(symbol);
                list_symbols_A.remove(index);
                list_correct_answer_A.remove(index);

                if(list_symbols_A.isEmpty()){
                    has_letter.remove(Letter.A);
                }
                break;
            case E:
                index = list_symbols_E.indexOf(symbol);
                list_symbols_E.remove(index);
                list_correct_answer_E.remove(index);

                if(list_symbols_E.isEmpty()){
                    has_letter.remove(Letter.E);
                }
                break;
            case I:
                index = list_symbols_I.indexOf(symbol);
                list_symbols_I.remove(index);
                list_correct_answer_I.remove(index);

                if(list_symbols_I.isEmpty()){
                    has_letter.remove(Letter.I);
                }
                break;
            case O:
                index = list_symbols_O.indexOf(symbol);
                list_symbols_O.remove(index);
                list_correct_answer_O.remove(index);

                if(list_symbols_O.isEmpty()){
                    has_letter.remove(Letter.O);
                }
                break;
            case U:
                index = list_symbols_U.indexOf(symbol);
                list_symbols_U.remove(index);
                list_correct_answer_U.remove(index);

                if(list_symbols_U.isEmpty()){
                    has_letter.remove(Letter.U);
                }
                break;
            default:
                System.out.println("algum erro aq, answered_right_symbol");
                break;
        }
    }

    public Letter get_current_letter() {
        return current_letter;
    }

    public boolean has_only_one_string(){
        if (has_letter.size() != 1) {
            return false;
        } else {
            switch (has_letter.get(0)) {
            case A:
                if(list_symbols_A.size() == 1){
                    return true;
                }
                break;
            case E:
                if(list_symbols_E.size() == 1){
                    return true;
                }
                break;
            case I:
                if(list_symbols_I.size() == 1){
                    return true;
                }
                break;
            case O:
                if(list_symbols_O.size() == 1){
                    return true;
                }
                break;
            case U:
                if(list_symbols_U.size() == 1){
                    return true;
                }
                break;
            }
        }
        return false;
    }

    public List<Letter> get_letters() {
        return has_letter_copy;
    }

    public boolean is_empty(){
        return has_letter.isEmpty();
    }

    // switch (letter) {
    //     case A:
    //         break;
    //     case E:
    //         break;
    //     case I:
    //         break;
    //     case O:
    //         break;
    //     case U:
    //         break;
    //     default:
    //         break;
    // }

}
