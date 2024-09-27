package src.Modes;

import java.util.List;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import src.Alphabet.Alphabet.Alphabets;
import src.GUI.Window;
import src.Alphabet.Hiragana;
import src.Alphabet.Katakana;

public class SyllableToJapanese extends Mode{

    //! BACK END VARS
    boolean is_displaying_question = true;

    //! FRONT END VARS
    JLabel characters_label = new JLabel();
    JButton next_button = new JButton();
    JButton show_question_button = new JButton();

    JButton button_exit = new JButton("Exit");

    public SyllableToJapanese(){}

    public SyllableToJapanese(int x, int y, int width, int height, 
        List<Alphabets> alphabets, Hiragana hiragana, Katakana katakana, boolean by_elimination){
        super(alphabets, hiragana, katakana, by_elimination);
        this.setBounds(x, y, width, height);
        this.setBackground(Color.GRAY);
        this.setLayout(null);

        add(characters_label);

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

        next_button.setSize(width / 4, height / 3);
        next_button.setLocation((int) ((width - next_button.getWidth()) / 1.1), (height - next_button.getHeight()) / 2);
        next_button.setFont(new Font("Meiryo", Font.PLAIN, 40));
        next_button.setText("Answer");
        next_button.setFocusPainted(false);
        next_button.addActionListener(l -> actionListener(l));
        next_button.setCursor(Window.hand_cursor);
        add(next_button);

        show_question_button.setSize(width / 4, height / 8);
        show_question_button.setLocation((int) ((width - show_question_button.getWidth()) / 100), (height - show_question_button.getHeight()) / 100);
        show_question_button.setFont(new Font("Meiryo", Font.PLAIN, 20));
        show_question_button.setText("Show Question");
        show_question_button.setFocusPainted(false);
        show_question_button.setEnabled(false);
        show_question_button.addActionListener(l -> actionListener(l));
        show_question_button.setCursor(Window.hand_cursor);
        add(show_question_button);

        adjust_panel();

    }

    @Override
    protected void actionListener(ActionEvent l) {
        if (l.getSource() == next_button) {
            if (is_displaying_question) {
                characters_label.setText(string_in_japanese);
                adjust_characters_label(string_in_japanese, true);
                show_question_button.setEnabled(true);
                is_displaying_question = false;
                next_button.setText("Next");
            } else if(!is_displaying_question) {
                if (is_random_by_elimination) {
                    answered_right();
                }
                get_random_question();
                characters_label.setText(string_in_syllable);
                adjust_characters_label(string_in_syllable, false);
                show_question_button.setEnabled(false);
                is_displaying_question = true;
                next_button.setText("Answer");
            }
        } else if(l.getSource() == show_question_button){
            next_button.setText("Answer");
            characters_label.setText(string_in_syllable);
            adjust_characters_label(string_in_syllable, false);
            show_question_button.setEnabled(false);
            is_displaying_question = true;
        }
    }
    
    @Override
    protected void adjust_panel() {
        get_random_question();
        characters_label.setText(string_in_syllable);
        adjust_characters_label(string_in_syllable, false);
    }

    private void adjust_characters_label(String str, boolean to_japanese){
        int strange_factor = to_japanese ? 1 : 1; //? The false should be 40, but when is "Wa" it doesn't fit well 
        characters_label.setFont(new Font("Meiryo", Font.PLAIN, character_size));
        characters_label.setSize(character_size * str.length(), this.getHeight() / 2);
        characters_label.setLocation(((this.getWidth() - characters_label.getWidth()) / 2) + strange_factor * str.length(), (this.getHeight() - characters_label.getHeight()) / 2);
        repaint();
    }

}
