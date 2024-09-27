package src.GUI;

import java.util.List;
import java.util.ArrayList;

import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JOptionPane;

import src.Alphabet.Alphabet.Alphabets;
import src.Alphabet.Alphabet.Letter;
import src.Data.Data;
import src.Modes.Mode.Modes;
import src.Alphabet.Hiragana;
import src.Alphabet.Katakana;

public class Config extends JPanel{

    //! ALPHABETS
    private static JPanel alphabets_panel = new JPanel(null);
    private static JLabel alphabets_label = new JLabel();

    private static JCheckBox check_hiragana = new JCheckBox("Hiragana");
    private static JCheckBox letter_hir_A = new JCheckBox("A");
    private static JCheckBox letter_hir_E = new JCheckBox("E");
    private static JCheckBox letter_hir_I = new JCheckBox("I");
    private static JCheckBox letter_hir_O = new JCheckBox("O");
    private static JCheckBox letter_hir_U = new JCheckBox("U");

    private static JCheckBox check_katakana = new JCheckBox("Katakana");
    private static JCheckBox letter_kat_A = new JCheckBox("A");
    private static JCheckBox letter_kat_E = new JCheckBox("E");
    private static JCheckBox letter_kat_I = new JCheckBox("I");
    private static JCheckBox letter_kat_O = new JCheckBox("O");
    private static JCheckBox letter_kat_U = new JCheckBox("U");

    private static JCheckBox check_kanji = new JCheckBox("Kanji");
    //? JList<String> letters_kanji = new JList<>();

    //! MODE
    private static JPanel mode_panel = new JPanel(null);
    private static JLabel mode_label = new JLabel();
    private static JComboBox<String> mode_combo_box = new JComboBox<>();

    //! STUDY
    private static JPanel study_panel = new JPanel(null);
    private static JLabel study_label = new JLabel();
    private static JComboBox<String> study_combo_box = new JComboBox<>();

    //! TIME OF OUR LIVES
    private static JButton button_continue = new JButton("Continue");

    Config(int x, int y, int width, int height){
        this.setBounds(x, y, width, height);
        this.setLayout(null);

        create_alphabets_panel();
        add(alphabets_panel);

        create_mode_panel();
        add(mode_panel);

        create_study_panel();
        add(study_panel);

        button_continue.setSize(200, 50);
        button_continue.setLocation((int) (width / 1.05) - button_continue.getWidth(), 
            height - (int) (button_continue.getHeight() * 1.6) - 15);
        button_continue.setFocusPainted(false);
        button_continue.addActionListener(l -> action_listener(l));
        button_continue.setCursor(Window.hand_cursor);
        add(button_continue);
        Data.load_file();
    }

    //! NOT USED
    public void reset(){
        check_hiragana.setSelected(false);
        letter_hir_A.setSelected(false);
        letter_hir_E.setSelected(false);
        letter_hir_I.setSelected(false);
        letter_hir_O.setSelected(false);
        letter_hir_U.setSelected(false);

        check_katakana.setSelected(false);
        letter_kat_A.setSelected(false);
        letter_kat_E.setSelected(false);
        letter_kat_I.setSelected(false);
        letter_kat_O.setSelected(false);
        letter_kat_U.setSelected(false);

        mode_combo_box.setSelectedIndex(0);
        study_combo_box.setSelectedIndex(0);
    }

    public void action_listener(ActionEvent l){
        if(l.getSource() == button_continue){
            Data.save_file();
            List<Alphabets> alphabets = new ArrayList<>();
            List<Letter> letters_hiragana = new ArrayList<>();
            List<Letter> letters_katakana = new ArrayList<>();
            Hiragana hiragana = null;
            Katakana katakana = null;
            if(!check_hiragana.isSelected() && !check_katakana.isSelected()){
                JOptionPane.showMessageDialog(null, 
                        "You have to choose at least ONE alphabet!", 
                        "Error: No alphabet choosed.", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (check_hiragana.isSelected()) {
                alphabets.add(Alphabets.HIRAGANA);
                if(letter_hir_A.isSelected()){
                    letters_hiragana.add(Letter.A);
                }
                if(letter_hir_E.isSelected()){
                    letters_hiragana.add(Letter.E);
                }
                if(letter_hir_I.isSelected()){
                    letters_hiragana.add(Letter.I);
                }
                if(letter_hir_O.isSelected()){
                    letters_hiragana.add(Letter.O);
                }
                if(letter_hir_U.isSelected()){
                    letters_hiragana.add(Letter.U);
                }
                if (letters_hiragana.isEmpty()) {
                    JOptionPane.showMessageDialog(null, 
                        "You have to choose at least ONE letter in Hiragana!", 
                        "Error: No letter choosed. | Hiragana", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Letter[] letters = new Letter[letters_hiragana.size()];
                for (int i = 0; i < letters.length; i++) {
                    letters[i] = letters_hiragana.get(i);
                    // System.out.println(letters[i]);
                }
                hiragana = new Hiragana(letters);
            }
            if (check_katakana.isSelected()) {
                alphabets.add(Alphabets.KATAKANA);
                if(letter_kat_A.isSelected()){
                    letters_katakana.add(Letter.A);
                }
                if(letter_kat_E.isSelected()){
                    letters_katakana.add(Letter.E);
                }
                if(letter_kat_I.isSelected()){
                    letters_katakana.add(Letter.I);
                }
                if(letter_kat_O.isSelected()){
                    letters_katakana.add(Letter.O);
                }
                if(letter_kat_U.isSelected()){
                    letters_katakana.add(Letter.U);
                }
                if (letters_katakana.isEmpty()) {
                    JOptionPane.showMessageDialog(null, 
                        "You have to choose at least ONE letter in Katakana!", 
                        "Error: No letter choosed. | Katakana", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Letter[] letters = new Letter[letters_katakana.size()];
                for (int i = 0; i < letters.length; i++) {
                    letters[i] = letters_katakana.get(i);
                }
                katakana = new Katakana(letters);
            }
            Modes mode = Modes.SYLLABLE_TO_JAPANESE;
            boolean by_elimination = false;
            if(mode_combo_box.getSelectedItem().equals("Japanese to Syllable")){
                mode = Modes.JAPANESE_TO_SYLLABLE;
            }
            if(study_combo_box.getSelectedItem().equals("Elimination")){
                by_elimination = true;
            }
            Window.create_mode(mode, alphabets, hiragana, katakana, by_elimination);
        }
    }

    public void create_alphabets_panel(){
        alphabets_panel.setBounds(0, 0, 800, 600);

        alphabets_panel.add(alphabets_label);
        alphabets_label.setBounds(5, 10, 0, 50);
        alphabets_label.setFont(new Font("Meiryo", Font.PLAIN, alphabets_label.getHeight()));
        alphabets_label.setText("Alphabets");
        alphabets_label.setSize(alphabets_label.getFont().getSize() * alphabets_label.getText().length(), 
            alphabets_label.getHeight());

        add(check_hiragana);
        check_hiragana.setBounds(25, alphabets_label.getY() + alphabets_label.getHeight() + 15, 0, 25);
        check_hiragana.setFont(new Font("Meiryo", Font.PLAIN, check_hiragana.getHeight()));
        check_hiragana.setText("Hiragana");
        check_hiragana.setSize(check_hiragana.getFont().getSize() * check_hiragana.getText().length(), 
            check_hiragana.getHeight());
        check_hiragana.setFocusPainted(false);

        //-----------------------------------------------------------------------//
        // HIRAGANA LETTERS
        add(letter_hir_A);
        letter_hir_A.setLocation(check_hiragana.getX() + check_hiragana.getWidth(), check_hiragana.getY());
        letter_hir_A.setSize(0, 25);
        letter_hir_A.setFont(new Font("Meiryo", Font.PLAIN, (int) (letter_hir_A.getHeight() / 1.5)));
        letter_hir_A.setSize(letter_hir_A.getText().length() * letter_hir_A.getFont().getSize() + 25, 25);
        letter_hir_A.setFocusPainted(false);

        add(letter_hir_E);
        letter_hir_E.setLocation(letter_hir_A.getX() + letter_hir_A.getWidth(), letter_hir_A.getY());
        letter_hir_E.setSize(0, 25);
        letter_hir_E.setFont(new Font("Meiryo", Font.PLAIN, (int) (letter_hir_E.getHeight() / 1.5)));
        letter_hir_E.setSize(letter_hir_E.getText().length() * letter_hir_E.getFont().getSize() + 25, 25);
        letter_hir_E.setFocusPainted(false);

        add(letter_hir_I);
        letter_hir_I.setLocation(letter_hir_E.getX() + letter_hir_E.getWidth(), letter_hir_E.getY());
        letter_hir_I.setSize(0, 25);
        letter_hir_I.setFont(new Font("Meiryo", Font.PLAIN, (int) (letter_hir_I.getHeight() / 1.5)));
        letter_hir_I.setSize(letter_hir_I.getText().length() * letter_hir_I.getFont().getSize() + 25, 25);
        letter_hir_I.setFocusPainted(false);

        add(letter_hir_O);
        letter_hir_O.setLocation(letter_hir_I.getX() + letter_hir_I.getWidth(), letter_hir_I.getY());
        letter_hir_O.setSize(0, 25);
        letter_hir_O.setFont(new Font("Meiryo", Font.PLAIN, (int) (letter_hir_O.getHeight() / 1.5)));
        letter_hir_O.setSize(letter_hir_O.getText().length() * letter_hir_O.getFont().getSize() + 25, 25);
        letter_hir_O.setFocusPainted(false);

        add(letter_hir_U);
        letter_hir_U.setLocation(letter_hir_O.getX() + letter_hir_O.getWidth(), letter_hir_O.getY());
        letter_hir_U.setSize(0, 25);
        letter_hir_U.setFont(new Font("Meiryo", Font.PLAIN, (int) (letter_hir_U.getHeight() / 1.5)));
        letter_hir_U.setSize(letter_hir_U.getText().length() * letter_hir_U.getFont().getSize() + 25, 25);
        letter_hir_U.setFocusPainted(false);

        //-----------------------------------------------------------------------//

        add(check_katakana);
        check_katakana.setBounds(25, check_hiragana.getY() + check_hiragana.getHeight() + 15, 0, 25);
        check_katakana.setFont(new Font("Meiryo", Font.PLAIN, check_katakana.getHeight()));
        check_katakana.setText("Katakana");
        check_katakana.setSize(check_katakana.getFont().getSize() * check_katakana.getText().length(), 
            check_katakana.getHeight());
        check_katakana.setFocusPainted(false);

        //-----------------------------------------------------------------------//
        // KATAKANA LETTERS
        add(letter_kat_A);
        letter_kat_A.setLocation(check_katakana.getX() + check_katakana.getWidth(), check_katakana.getY());
        letter_kat_A.setSize(0, 25);
        letter_kat_A.setFont(new Font("Meiryo", Font.PLAIN, (int) (letter_kat_A.getHeight() / 1.5)));
        letter_kat_A.setSize(letter_kat_A.getText().length() * letter_kat_A.getFont().getSize() + 25, 25);
        letter_kat_A.setFocusPainted(false);

        add(letter_kat_E);
        letter_kat_E.setLocation(letter_kat_A.getX() + letter_kat_A.getWidth(), letter_kat_A.getY());
        letter_kat_E.setSize(0, 25);
        letter_kat_E.setFont(new Font("Meiryo", Font.PLAIN, (int) (letter_kat_E.getHeight() / 1.5)));
        letter_kat_E.setSize(letter_kat_E.getText().length() * letter_kat_E.getFont().getSize() + 25, 25);
        letter_kat_E.setFocusPainted(false);

        add(letter_kat_I);
        letter_kat_I.setLocation(letter_kat_E.getX() + letter_kat_E.getWidth(), letter_kat_E.getY());
        letter_kat_I.setSize(0, 25);
        letter_kat_I.setFont(new Font("Meiryo", Font.PLAIN, (int) (letter_hir_I.getHeight() / 1.5)));
        letter_kat_I.setSize(letter_kat_I.getText().length() * letter_kat_I.getFont().getSize() + 25, 25);
        letter_kat_I.setFocusPainted(false);

        add(letter_kat_O);
        letter_kat_O.setLocation(letter_kat_I.getX() + letter_kat_I.getWidth(), letter_kat_I.getY());
        letter_kat_O.setSize(0, 25);
        letter_kat_O.setFont(new Font("Meiryo", Font.PLAIN, (int) (letter_kat_O.getHeight() / 1.5)));
        letter_kat_O.setSize(letter_kat_O.getText().length() * letter_kat_O.getFont().getSize() + 25, 25);
        letter_kat_O.setFocusPainted(false);

        add(letter_kat_U);
        letter_kat_U.setLocation(letter_kat_O.getX() + letter_kat_O.getWidth(), letter_kat_O.getY());
        letter_kat_U.setSize(0, 25);
        letter_kat_U.setFont(new Font("Meiryo", Font.PLAIN, (int) (letter_kat_U.getHeight() / 1.5)));
        letter_kat_U.setSize(letter_kat_U.getText().length() * letter_kat_U.getFont().getSize() + 25, 25);
        letter_kat_U.setFocusPainted(false);

        //-----------------------------------------------------------------------//

        add(check_kanji);
        check_kanji.setBounds(25, check_katakana.getY() + check_katakana.getHeight() + 15, 0, 25);
        check_kanji.setFont(new Font("Meiryo", Font.PLAIN, check_kanji.getHeight()));
        check_kanji.setText("Kanji");
        check_kanji.setSize(check_kanji.getFont().getSize() * check_kanji.getText().length(), 
            check_kanji.getHeight());
        check_kanji.setFocusPainted(false);
        check_kanji.setEnabled(false);
        check_kanji.setToolTipText("NOT IMPLEMENTED YET!");

        alphabets_panel.setSize(alphabets_panel.getWidth(), check_kanji.getY() + check_kanji.getHeight() + 16);

        JSeparator jSeparator = new JSeparator(JSeparator.HORIZONTAL);
        jSeparator.setBounds(0, check_kanji.getY() + check_kanji.getHeight() + 15, 
            alphabets_panel.getWidth(), 1);
        alphabets_panel.add(jSeparator);

    }

    public void create_mode_panel(){

        mode_panel.setBounds(0, alphabets_panel.getY() + alphabets_panel.getHeight() + 10, 800, 600);

        mode_panel.add(mode_label);
        mode_label.setBounds(5, 10, 0, 50);
        mode_label.setFont(new Font("Meiryo", Font.PLAIN, mode_label.getHeight()));
        mode_label.setText("Mode");
        mode_label.setSize(mode_label.getFont().getSize() * mode_label.getText().length(), 
            mode_label.getHeight());

        mode_panel.add(mode_combo_box);
        mode_combo_box.addItem("Japanese to Syllable");
        mode_combo_box.addItem("Syllable to Japanese");
        mode_combo_box.setBounds(25, mode_label.getY() + mode_label.getHeight() + 10, 250, 50);
        mode_combo_box.setFont(new Font("Meiryo", Font.PLAIN, 20));

        mode_panel.setSize(mode_panel.getWidth(), mode_combo_box.getY() + mode_combo_box.getHeight() + 21);

        JSeparator jSeparator = new JSeparator(JSeparator.HORIZONTAL);
        jSeparator.setBounds(0, mode_combo_box.getY() + mode_combo_box.getHeight() + 20, 
            mode_panel.getWidth(), 1);
        mode_panel.add(jSeparator);

    }

    public void create_study_panel(){

        study_panel.setBounds(0, mode_panel.getY() + mode_panel.getHeight() + 10, 800, 600);

        study_panel.add(study_label);
        study_label.setBounds(5, 10, 0, 50);
        study_label.setFont(new Font("Meiryo", Font.PLAIN, study_label.getHeight()));
        study_label.setText("Study");
        study_label.setSize(study_label.getFont().getSize() * study_label.getText().length(), 
            study_label.getHeight());

        study_panel.add(study_combo_box);
        study_combo_box.addItem("Elimination");
        study_combo_box.addItem("Infinite");
        study_combo_box.setBounds(25, study_label.getY() + study_label.getHeight() + 10, 250, 50);
        study_combo_box.setFont(new Font("Meiryo", Font.PLAIN, 20));

        study_panel.setSize(study_panel.getWidth(), study_combo_box.getY() + study_combo_box.getHeight() + 21);

        JSeparator jSeparator = new JSeparator(JSeparator.HORIZONTAL);
        jSeparator.setBounds(0, study_combo_box.getY() + study_combo_box.getHeight() + 20, 
            study_panel.getWidth(), 1);
        study_panel.add(jSeparator);

    }

    public static boolean is_hiragana_selected(){
        return check_hiragana.isSelected();
    }

    public static boolean is_hiragana_A_selected(){
        return letter_hir_A.isSelected();
    }

    public static boolean is_hiragana_E_selected(){
        return letter_hir_E.isSelected();
    }

    public static boolean is_hiragana_I_selected(){
        return letter_hir_I.isSelected();
    }

    public static boolean is_hiragana_O_selected(){
        return letter_hir_O.isSelected();
    }

    public static boolean is_hiragana_U_selected(){
        return letter_hir_U.isSelected();
    }

    public static boolean is_katakana_selected(){
        return check_katakana.isSelected();
    }

    public static boolean is_katakana_A_selected(){
        return letter_kat_A.isSelected();
    }

    public static boolean is_katakana_E_selected(){
        return letter_kat_E.isSelected();
    }

    public static boolean is_katakana_I_selected(){
        return letter_kat_I.isSelected();
    }

    public static boolean is_katakana_O_selected(){
        return letter_kat_O.isSelected();
    }

    public static boolean is_katakana_U_selected(){
        return letter_kat_U.isSelected();
    }

    public static boolean is_japanese_to_syllable_selected(){
        return mode_combo_box.getSelectedItem().equals("Japanese to Syllable");
    }

    public static boolean is_syllable_to_japanese_selected(){
        return mode_combo_box.getSelectedItem().equals("Syllable to Japanese");
    }

    public static boolean is_elimination_selected(){
        return study_combo_box.getSelectedItem().equals("Elimination");
    }

    public static boolean is_infinite_selected(){
        return study_combo_box.getSelectedItem().equals("Infinite");
    }

    public static void set_hiragana_selected(boolean hiragana_selected){
        check_hiragana.setSelected(hiragana_selected);
    }

    public static void set_hiragana_A_selected(boolean hiragana_A_selected){
        letter_hir_A.setSelected(hiragana_A_selected);
    }

    public static void set_hiragana_E_selected(boolean hiragana_E_selected){
        letter_hir_E.setSelected(hiragana_E_selected);
    }

    public static void set_hiragana_I_selected(boolean hiragana_I_selected){
        letter_hir_I.setSelected(hiragana_I_selected);
    }

    public static void set_hiragana_O_selected(boolean hiragana_O_selected){
        letter_hir_O.setSelected(hiragana_O_selected);
    }

    public static void set_hiragana_U_selected(boolean hiragana_U_selected){
        letter_hir_U.setSelected(hiragana_U_selected);
    }

    public static void set_katakana_selected(boolean katakana_selected){
        check_katakana.setSelected(katakana_selected);
    }

    public static void set_katakana_A_selected(boolean katakana_A_selected){
        letter_kat_A.setSelected(katakana_A_selected);
    }

    public static void set_katakana_E_selected(boolean katakana_E_selected){
        letter_kat_E.setSelected(katakana_E_selected);
    }

    public static void set_katakana_I_selected(boolean katakana_I_selected){
        letter_kat_I.setSelected(katakana_I_selected);
    }

    public static void set_katakana_O_selected(boolean katakana_O_selected){
        letter_kat_O.setSelected(katakana_O_selected);
    }

    public static void set_katakana_U_selected(boolean katakana_U_selected){
        letter_kat_U.setSelected(katakana_U_selected);
    }

    public static void set_japanese_to_syllable_selected(){
        mode_combo_box.setSelectedItem("Japanese to Syllable");
    }

    public static void set_syllable_to_japanese_selected(){
        mode_combo_box.setSelectedItem("Syllable to Japanese");
    }

    public static void set_elimination_selected(){
        study_combo_box.setSelectedItem("Elimination");
    }

    public static void set_infinite_selected(){
        study_combo_box.setSelectedItem("Infinite");
    }
}
