package src.Modes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;

import src.Alphabet.Alphabet.Alphabets;
import src.Alphabet.Alphabet.Letter;
import src.Alphabet.Hiragana;

public class JapaneseToSyllable extends Mode{

    private String answer;

    //! FRONT END VARS
    JLabel symbols_label = new JLabel();
    JTextField answerField = new JTextField();
    
    // int syllable_char_size = 250;
    // int japanese_char_size = 250;

    public JapaneseToSyllable(int x, int y, int width, int height){
        this.setBounds(x, y, width, height);
        this.setBackground(Color.GRAY);
        this.setLayout(null);
    
        //? Symbols Label
        symbols_label.setLayout(null);
        
        add(symbols_label);

        //? Answer Text Field
        answerField.setBounds(0, this.getHeight() - 140, this.getWidth(), 100);
        answerField.setFont(new Font("Meiryo", Font.PLAIN, answerField.getHeight()));
        answerField.addActionListener(l -> actionListener(l));
        add(answerField);
    
        adjust_panel();

    }

    @Override
    protected void actionListener(ActionEvent l){
        if(l.getSource().equals(answerField)){
            answer = answerField.getText().toLowerCase().trim();
            if (answer.isBlank()) {
                answerField.setText("");
                return;
            }
            System.out.println("Answer: " + answer + " | Correct Answer: " + string_in_syllable);
            if (answer.equals(string_in_syllable.toLowerCase())) {
                System.out.println("Acertou!");
                if (is_random_by_elimination) {
                    answered_right();
                }
            } else{
                System.out.println("Errou!");
            }
            answerField.setText("");
            get_random_question();
            symbols_label.setText(string_in_japanese);
            adjust_symbols_label_to_japanese(string_in_japanese);
        }
    }

    @Override
    protected void adjust_panel() {
        hiragana = new Hiragana(Letter.A);
        alphabets.add(Alphabets.HIRAGANA);
        is_random_by_elimination = true;

        get_random_question();
        symbols_label.setText(string_in_japanese);
        adjust_symbols_label_to_japanese(string_in_japanese);
    }

    // I USED SOME FORM OF DARK MAGIC THAT MADE IT WORK ALMOST AS I WANTED, BUT WORK AS INTENDED
    // IT APPLY FOR THE NEXT 2 RESPECTIVE FUNCTIONS
    private void adjust_symbols_label_to_japanese(String str){
        symbols_label.setFont(new Font("Meiryo", Font.PLAIN, character_size));
        symbols_label.setSize(character_size * str.length(), this.getHeight() / 2);
        symbols_label.setLocation((this.getWidth() - symbols_label.getWidth()) / 2, (this.getHeight() - symbols_label.getHeight()) / 2);
        repaint();
    }

}
