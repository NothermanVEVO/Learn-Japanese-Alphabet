package src.GUI;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JButton;

import src.Alphabet.Alphabet.Alphabets;
import src.Alphabet.Alphabet.Letter;
import src.Alphabet.Hiragana;
import src.Alphabet.Katakana;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Results extends JPanel {

    private Hiragana hiragana;
    private Katakana katakana;

    private ArrayList<VLabel> v_labels_hiragana = new ArrayList<>();
    private VLabel hiragana_title;
    private VLabel hiragana_answer_A;
    private VLabel hiragana_answer_E;
    private VLabel hiragana_answer_I;
    private VLabel hiragana_answer_O;
    private VLabel hiragana_answer_U;

    private ArrayList<VLabel> v_labels_katakana = new ArrayList<>();
    private VLabel katakana_title;
    private VLabel katakana_answer_A;
    private VLabel katakana_answer_E;
    private VLabel katakana_answer_I;
    private VLabel katakana_answer_O;
    private VLabel katakana_answer_U;

    // JPanel kanji = new JPanel();
    // VLabel[] right_answers_kanji;

    JButton button_continue = new JButton("Back");

    public Results(int x, int y, int width, int height){
        setBounds(x, y, width, height);
        adjust_button_continue();
        setLayout(null);
    }

    public Results(int x, int y, int width, int height, Hiragana hiragana, Katakana katakana){
        setBounds(x, y, width, height);
        set_labels(hiragana, katakana);
        adjust_button_continue();
        setLayout(null);
    }

    private void create_hiragana_answers(){
        for (Letter letter : hiragana.get_letters()) {
            VLabel last_label = v_labels_hiragana.get(v_labels_hiragana.size() - 1);
            switch (letter) {
                case A:
                    hiragana_answer_A = new VLabel("Voce acertou em A: " + get_result(Alphabets.HIRAGANA, Letter.A), false);
                    hiragana_answer_A.setLocation(0, last_label.getY() + last_label.getHeight());
                    add(hiragana_answer_A);
                    v_labels_hiragana.add(hiragana_answer_A);
                    break;
                case E:
                    hiragana_answer_E = new VLabel("Voce acertou em E: " + get_result(Alphabets.HIRAGANA, Letter.E), false);
                    hiragana_answer_E.setLocation(0, last_label.getY() + last_label.getHeight());
                    add(hiragana_answer_E);
                    v_labels_hiragana.add(hiragana_answer_E);
                    break;
                case I:
                    hiragana_answer_I = new VLabel("Voce acertou em I: " + get_result(Alphabets.HIRAGANA, Letter.I), false);
                    hiragana_answer_I.setLocation(0, last_label.getY() + last_label.getHeight());
                    add(hiragana_answer_I);
                    v_labels_hiragana.add(hiragana_answer_I);
                    break;
                case O:
                    hiragana_answer_O = new VLabel("Voce acertou em O: " + get_result(Alphabets.HIRAGANA, Letter.O), false);
                    hiragana_answer_O.setLocation(0, last_label.getY() + last_label.getHeight());
                    add(hiragana_answer_O);
                    v_labels_hiragana.add(hiragana_answer_O);
                    break;
                case U:
                    hiragana_answer_U = new VLabel("Voce acertou em U: " + get_result(Alphabets.HIRAGANA, Letter.U), false);
                    hiragana_answer_U.setLocation(0, last_label.getY() + last_label.getHeight());
                    add(hiragana_answer_U);
                    v_labels_hiragana.add(hiragana_answer_U);
                    break;
            }
        }
    }

    private void create_katakana_answers(){
        for (Letter letter : katakana.get_letters()) {
            VLabel last_label = v_labels_katakana.get(v_labels_katakana.size() - 1);
            switch (letter) {
                case A:
                    katakana_answer_A = new VLabel("Voce acertou em A: " + get_result(Alphabets.KATAKANA, Letter.A), false);
                    katakana_answer_A.setLocation(0, last_label.getY() + last_label.getHeight());
                    add(katakana_answer_A);
                    v_labels_katakana.add(katakana_answer_A);
                    break;
                case E:
                    katakana_answer_E = new VLabel("Voce acertou em E: " + get_result(Alphabets.KATAKANA, Letter.E), false);
                    katakana_answer_E.setLocation(0, last_label.getY() + last_label.getHeight());
                    add(katakana_answer_E);
                    v_labels_katakana.add(katakana_answer_E);
                    break;
                case I:
                    katakana_answer_I = new VLabel("Voce acertou em I: " + get_result(Alphabets.KATAKANA, Letter.I), false);
                    katakana_answer_I.setLocation(0, last_label.getY() + last_label.getHeight());
                    add(katakana_answer_I);
                    v_labels_katakana.add(katakana_answer_I);
                    break;
                case O:
                    katakana_answer_O = new VLabel("Voce acertou em O: " + get_result(Alphabets.KATAKANA, Letter.O), false);
                    katakana_answer_O.setLocation(0, last_label.getY() + last_label.getHeight());
                    add(katakana_answer_O);
                    v_labels_katakana.add(katakana_answer_O);
                    break;
                case U:
                    katakana_answer_U = new VLabel("Voce acertou em U: " + get_result(Alphabets.KATAKANA, Letter.U), false);
                    katakana_answer_U.setLocation(0, last_label.getY() + last_label.getHeight());
                    add(katakana_answer_U);
                    v_labels_katakana.add(katakana_answer_U);
                    break;
            }
        }
    }

    public void set_labels(Hiragana hiragana, Katakana katakana){
        this.hiragana = hiragana;
        this.katakana = katakana;

        //? Hiragana
        hiragana_title = new VLabel("Hiragana", true);
        hiragana_title.setLocation(0, 0);
        add(hiragana_title);
        v_labels_hiragana.add(hiragana_title);

        if(hiragana != null){
            create_hiragana_answers();
        }

        JSeparator jSeparator_hiragana = new JSeparator(JSeparator.HORIZONTAL);
        jSeparator_hiragana.setBounds(0, v_labels_hiragana.get(v_labels_hiragana.size() - 1).getY() + 
            v_labels_hiragana.get(v_labels_hiragana.size() - 1).getHeight() + 20, 
            getWidth(), 1);
        add(jSeparator_hiragana);

        //? Katakana
        katakana_title = new VLabel("Katakana", true);
        katakana_title.setLocation(0, jSeparator_hiragana.getY() + 
            jSeparator_hiragana.getHeight() + 0);
        add(katakana_title);
        v_labels_katakana.add(katakana_title);

        if(katakana != null){
            create_katakana_answers();
        }

        JSeparator jSeparator_katakana = new JSeparator(JSeparator.HORIZONTAL);
        jSeparator_katakana.setBounds(0, v_labels_katakana.get(v_labels_katakana.size() - 1).getY() + 
            v_labels_katakana.get(v_labels_katakana.size() - 1).getHeight() + 20, 
            getWidth(), 1);
        add(jSeparator_katakana);
    }

    private void adjust_button_continue(){
        button_continue.setSize(150, 50);
        button_continue.setLocation((int) (getWidth() / 1.05) - button_continue.getWidth(), 
            getHeight() - (int) (button_continue.getHeight() * 1.6) - 15);
        button_continue.setFocusPainted(false);
        button_continue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == button_continue){
                    Window.back_to_config();
                }
            }
        });
        button_continue.setCursor(Window.hand_cursor);
        add(button_continue);
    }

    private int get_number_of_correct_answers(Alphabets alphabet, Letter letter){
        switch (alphabet) {
            case HIRAGANA:
                switch (letter) {
                    case A:
                        return Hiragana.symbols_A.length - hiragana.wrong_answers_A.length();
                    case E:
                        return Hiragana.symbols_E.length - hiragana.wrong_answers_E.length();
                    case I:
                        return Hiragana.symbols_I.length - hiragana.wrong_answers_I.length();
                    case O:
                        return Hiragana.symbols_O.length - hiragana.wrong_answers_O.length();
                    case U:
                        return Hiragana.symbols_U.length - hiragana.wrong_answers_U.length();
                }
                return -1;
            case KATAKANA:
                switch (letter) {
                    case A:
                        return Katakana.symbols_A.length - katakana.wrong_answers_A.length();
                    case E:
                        return Katakana.symbols_E.length - katakana.wrong_answers_E.length();
                    case I:
                        return Katakana.symbols_I.length - katakana.wrong_answers_I.length();
                    case O:
                        return Katakana.symbols_O.length - katakana.wrong_answers_O.length();
                    case U:
                        return Katakana.symbols_U.length - katakana.wrong_answers_U.length();
                }
                return -1;
        }
        return -1;
    }

    private String get_result(Alphabets alphabet, Letter letter){
        switch (alphabet) {
            case HIRAGANA:
                switch (letter) {
                    case A:
                        return get_number_of_correct_answers(Alphabets.HIRAGANA, Letter.A) + "/" + Hiragana.symbols_A.length;
                    case E:
                        return get_number_of_correct_answers(Alphabets.HIRAGANA, Letter.E) + "/" + Hiragana.symbols_E.length;
                    case I:
                        return get_number_of_correct_answers(Alphabets.HIRAGANA, Letter.I) + "/" + Hiragana.symbols_I.length;
                    case O:
                        return get_number_of_correct_answers(Alphabets.HIRAGANA, Letter.O) + "/" + Hiragana.symbols_O.length;
                    case U:
                        return get_number_of_correct_answers(Alphabets.HIRAGANA, Letter.U) + "/" + Hiragana.symbols_U.length;
                }
                break;
            case KATAKANA:
                switch (letter) {
                    case A:
                        return get_number_of_correct_answers(Alphabets.KATAKANA, Letter.A) + "/" + Katakana.symbols_A.length;
                    case E:
                        return get_number_of_correct_answers(Alphabets.KATAKANA, Letter.E) + "/" + Katakana.symbols_E.length;
                    case I:
                        return get_number_of_correct_answers(Alphabets.KATAKANA, Letter.I) + "/" + Katakana.symbols_I.length;
                    case O:
                        return get_number_of_correct_answers(Alphabets.KATAKANA, Letter.O) + "/" + Katakana.symbols_O.length;
                    case U:
                        return get_number_of_correct_answers(Alphabets.KATAKANA, Letter.U) + "/" + Katakana.symbols_U.length;
                }
                break;
        }
        return null;
    }

}

class VLabel extends JLabel{

    final static int title_character = 60;
    final static int normal_character = 25;
    
    int character_size;

    VLabel(String text, boolean is_title){
        setText(text);
        if (is_title) {
            character_size = title_character;
        } else{
            character_size = normal_character;
        }
        setSize(0, character_size);
        setFont(new Font("Meiryo", Font.PLAIN, (int) (character_size / 1.5)));
        setSize(getText().length() * getFont().getSize() + 25, character_size);
        setLayout(null);
    }

}
