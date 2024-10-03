package src.Modes;

import java.util.List;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import src.Alphabet.Alphabet.Alphabets;
import src.GUI.Feedback;
import src.GUI.Window;
import src.Alphabet.Hiragana;
import src.Alphabet.Katakana;

public class JapaneseToSyllable extends Mode{

    private String answer;

    //! FRONT END VARS
    JLabel symbols_label = new JLabel();
    JTextField answerField = new JTextField();

    JButton button_exit = new JButton("Exit");

    Feedback feedback = new Feedback();

    public JapaneseToSyllable(){}

    public JapaneseToSyllable(int x, int y, int width, int height, 
        List<Alphabets> alphabets, Hiragana hiragana, Katakana katakana, boolean by_elimination){
        super(alphabets, hiragana, katakana, by_elimination);
        this.setBounds(x, y, width, height);
        this.setBackground(Color.GRAY);
        this.setLayout(null);
    
        //? Symbols Label
        symbols_label.setLayout(null);
        add(symbols_label);

        //? Exit Button
        button_exit.setSize(75, 40);
        button_exit.setLocation((int) (width / 1.03) - button_exit.getWidth(), 5);
        button_exit.setFocusPainted(false);
        button_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window.back_to_config();
            }
        });
        button_exit.setCursor(Window.hand_cursor);
        add(button_exit);

        //? Answer Text Field
        answerField.setBounds(0, this.getHeight() - 140, this.getWidth(), 100);
        answerField.setFont(new Font("Meiryo", Font.PLAIN, answerField.getHeight()));
        answerField.addActionListener(l -> actionListener(l));
        add(answerField);

        feedback.setBounds(0, 0, (int) (width / 2), (int) (height / 3));
        add(feedback);
        feedback.repaint();

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
            // System.out.println("Answer: " + answer + " | Correct Answer: " + string_in_syllable);
            if (answer.equals(string_in_syllable.toLowerCase())) {
                // System.out.println("Acertou!");
                Feedback.create_new_card("Q: " + string_in_japanese + " | A: " + answer, true);
                if (is_random_by_elimination) {
                    answered_right();
                }
            } else{
                // System.out.println("Errou!");
                Feedback.create_new_card("Q: " + string_in_japanese + " | A: " + answer, false);
                answered_wrong();
            }
            answerField.setText("");
            get_random_question();
            symbols_label.setText(string_in_japanese);
            adjust_symbols_label_to_japanese(string_in_japanese);
        }
    }

    @Override
    protected void adjust_panel() {
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
