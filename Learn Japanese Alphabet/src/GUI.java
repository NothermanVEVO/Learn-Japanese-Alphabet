package src;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI extends JPanel implements ActionListener {

    //! OPTIONS
    //? Which letter it'll have (You can choose as much and the way you want)
    boolean has_A = false;
    boolean has_E = false;
    boolean has_I = false;
    boolean has_O = false;
    boolean has_U = false;

    //? Which kind of study you want (You have to choose one, only)
    boolean isHiraganaToSyllable = false;
    boolean isSyllableToHiragana = false;

    //? Which mode you want (You have to choose one, only)
    boolean completelyRandom; // Everything random, infinite, it's set by default, no need to change that variable
    boolean randomByElimination = false; // Everything random, ends when you get everything right

    //! CONFIG

    //? SWING VARIABLES
    JLabel question = new JLabel();
    JLabel symbol = new JLabel();

    JTextField enterText = new JTextField();

    JButton showAnswer = new JButton();
    JButton nextHiragana = new JButton();

    //? OTHER VARIABLES
    boolean codeStarted;

    Random random = new Random();

    int numberOfOptions = 0;

    int lastIndex = -1;
    int index;

    int lastOption = -1;
    int option;

    //? HIRAGANA VARIABLES
    String[] symbols_A = {"あ", "か", "さ", "た", "な", "は", "ま", "や", "ら", "わ", "が", "ざ", "だ", "ば", "ぱ"};
    String[] correctAnswer_A = {"A", "Ka", "Sa", "Ta", "Na", "Ha", "Ma", "Ya", "Ra", "Wa", "Ga", "Za", "Da", "Ba", "Pa"};

    String[] symbols_E = {"え", "け", "せ", "て", "ね", "へ", "め", "れ", "げ", "ぜ", "で", "べ", "ぺ"};
    String[] correctAnswer_E = {"E", "Ke", "Se", "Te", "Ne", "He", "Me", "Re", "Ge", "Ze", "De", "Be", "Pe"};

    String[] symbols_I = {"い", "き", "し", "ち", "に", "ひ", "み", "り", "ぎ", "じ", "ぢ", "び", "ぴ"};
    String[] correctAnswer_I = {"I", "Ki", "Shi", "Chi", "Ni", "Hi", "Mi", "Ri", "Gi", "Ji", "Ji", "Bi", "Pi"};

    String[] symbols_O = {"お", "こ", "そ", "と", "の", "ほ", "も", "よ", "ろ", "を", "ご", "ぞ", "ど", "ぼ", "ぽ"};
    String[] correctAnswer_O = {"O", "Ko", "So", "To", "No", "Ho", "Mo", "Yo", "Ro", "O", "Go", "Zo", "Do", "Bo", "Po"};

    String[] symbols_U = {"う", "く", "す", "つ", "ぬ", "ふ", "む", "ゆ", "る", "ぐ", "ず", "づ", "ぶ", "ぷ"};
    String[] correctAnswer_U = {"U", "Ku", "Su", "Tsu", "Nu", "Fu", "Mu", "Yu", "Ru", "Gu", "Zu", "Zu", "Bu", "Pu"};

    String answer;

    //? ANSWERS VARIABLES
    int maxAnsweredRight_A = 0;
    String wrongAnswers_A = "";

    int maxAnsweredRight_E = 0;
    String wrongAnswers_E = "";

    int maxAnsweredRight_I = 0;
    String wrongAnswers_I = "";

    int maxAnsweredRight_O = 0;
    String wrongAnswers_O = "";

    int maxAnsweredRight_U = 0;
    String wrongAnswers_U = "";

    GUI(){
        this.setSize(800, 600);
        this.add(question);
        createQuestion();
        this.add(symbol);
        createSymbol();
        if(isHiraganaToSyllable){
            setTextToHaraganaSize();
            this.add(enterText);
            createEnterText();
        }
        if(isSyllableToHiragana){
            setTextToSyllabeSize();
            this.add(showAnswer);
            createShowAnswer();
            this.add(nextHiragana);
            createNextHiragana();
        }
        this.setOpaque(false);
        this.setLayout(null);
        checkChoices();
        checkTheLettersOptions();
        generateRandomSymbol();
        codeStarted = true;
    }

    //! START
    void createQuestion(){
        question.setForeground(Color.BLACK);
        question.setFont(new Font("Meiryo", Font.PLAIN, 50));
        question.setBounds(130, 10, 600, 50);
        question.setText("What symbol is that?");
    }

    void createSymbol(){
        symbol.setForeground(Color.BLACK);
        symbol.setFont(new Font("Meiryo", Font.PLAIN, 200));
        symbol.setBounds(300, 200, 200, 200);
        symbol.setText("");
    }

    void createEnterText(){
        enterText.addActionListener(this);
        enterText.setFont(new Font("Meiryo", Font.PLAIN, 110));
        enterText.setBounds(0, 450, 800, 150);
    }

    void createShowAnswer(){
        showAnswer.addActionListener(this);
        showAnswer.setFocusable(false);
        showAnswer.setFont(new Font("Meiryo", Font.PLAIN, 110));
        showAnswer.setText("Show Answer");
        showAnswer.setBounds(0, 450, 800, 150);
    }

    void createNextHiragana(){
        nextHiragana.addActionListener(this);
        nextHiragana.setFocusable(false);
        nextHiragana.setFont(new Font("Meiryo", Font.PLAIN, 90));
        nextHiragana.setText("Next Hiragana");
        nextHiragana.setBounds(0, 450, 800, 150);
    }

    void setTextToHaraganaSize(){
        symbol.setBounds(300, 200, 200, 200);
    }

    void setTextToSyllabeSize(){
        symbol.setBounds(200, 100, 400, 400);
    }

    //! CALL FUNCTIONS
    @Override
    public void actionPerformed(ActionEvent e) {
        if(isHiraganaToSyllable){
            if(e.getSource() == enterText){
                if (enterText.getText().isBlank()) {
                    return;
                }
                answer = enterText.getText();
                enterText.setText("");
                hiraganaToSyllable();
            }
        } else if(isSyllableToHiragana){
            if(e.getSource() == showAnswer){
                nextHiragana.setVisible(true);
                showAnswer.setVisible(false);
                setTextToHaraganaSize();
                syllableToHiragana();
            }
            if(e.getSource() == nextHiragana){
                setTextToSyllabeSize();
                showAnswer.setVisible(true);
                nextHiragana.setVisible(false);
                generateRandomSymbol();
            }
        } else{
            System.out.println("Erro! Tipo F");
            System.exit(-3);
        }
    }

    //! MAIN LOOP
    void loop(){
        // while (true) {
        // }
    }

    //! MAIN FUNCTIONS/ SELF CODE
    //? There'll be given Hiragana characters and you have to write in syllable
    void hiraganaToSyllable(){
        boolean missed = false;
        switch (option) {
            case 1:
                if (answer.toUpperCase().equals(correctAnswer_A[index].toUpperCase())) {
                    if(randomByElimination){
                        correctAnswer_A = stringRemoveAt(correctAnswer_A, index);
                        symbols_A = stringRemoveAt(symbols_A, index);
                    }
                    System.out.println("CERTO");
                } else{
                    System.out.println("ERRADO");
                    System.out.println("Voce escreveu: " + answer + " | Resposta correta: " + correctAnswer_A[index]);
                    if(!wrongAnswers_A.contains(symbols_A[index])){
                        wrongAnswers_A += symbols_A[index];
                    }
                    missed = true;
                }
                break;
            case 2:
                if (answer.toUpperCase().equals(correctAnswer_E[index].toUpperCase())) {
                    if(randomByElimination){
                        correctAnswer_E = stringRemoveAt(correctAnswer_E, index);
                        symbols_E = stringRemoveAt(symbols_E, index);
                    }
                    System.out.println("CERTO");
                } else{
                    System.out.println("ERRADO");
                    System.out.println("Voce escreveu: " + answer + " | Resposta correta: " + correctAnswer_E[index]);
                    if(!wrongAnswers_E.contains(symbols_E[index])){
                        wrongAnswers_E += symbols_E[index];
                    }
                    missed = true;
                }
                break;
            case 3:
                if (answer.toUpperCase().equals(correctAnswer_I[index].toUpperCase())) {
                    if(randomByElimination){
                        correctAnswer_I = stringRemoveAt(correctAnswer_I, index);
                        symbols_I = stringRemoveAt(symbols_I, index);
                    }
                    System.out.println("CERTO");
                } else{
                    System.out.println("ERRADO");
                    System.out.println("Voce escreveu: " + answer + " | Resposta correta: " + correctAnswer_I[index]);
                    if(!wrongAnswers_I.contains(symbols_I[index])){
                        wrongAnswers_I += symbols_I[index];
                    }
                    missed = true;
                }
                break;
            case 4:
                if (answer.toUpperCase().equals(correctAnswer_O[index].toUpperCase())) {
                    if(randomByElimination){
                        correctAnswer_O = stringRemoveAt(correctAnswer_O, index);
                        symbols_O = stringRemoveAt(symbols_O, index);
                    }
                    System.out.println("CERTO");
                } else{
                    System.out.println("ERRADO");
                    System.out.println("Voce escreveu: " + answer + " | Resposta correta: " + correctAnswer_O[index]);
                    if(!wrongAnswers_O.contains(symbols_O[index])){
                        wrongAnswers_O += symbols_O[index];
                    }
                    missed = true;
                }
                break;
            case 5:
                if (answer.toUpperCase().equals(correctAnswer_U[index].toUpperCase())) {
                    if(randomByElimination){
                        correctAnswer_U = stringRemoveAt(correctAnswer_U, index);
                        symbols_U = stringRemoveAt(symbols_U, index);
                    }
                    System.out.println("CERTO");
                } else{
                    System.out.println("ERRADO");
                    System.out.println("Voce escreveu: " + answer + " | Resposta correta: " + correctAnswer_U[index]);
                    if(!wrongAnswers_U.contains(symbols_U[index])){
                        wrongAnswers_U += symbols_U[index];
                    }
                    missed = true;
                }
                break;
            default:
                System.out.println("Erro! Tipo A");
                System.exit(-1);
                break;
        }
        if(randomByElimination && !missed){
            lastIndex = -1;
            lastOption = -1;
        }
        generateRandomSymbol();
    }

    //? There'll be given syllables and you have to write in paper
    void syllableToHiragana(){
        switch (option) {
            case 1:
                symbol.setText(symbols_A[index]);
                if(randomByElimination){ 
                    correctAnswer_A = stringRemoveAt(correctAnswer_A, index);
                    symbols_A = stringRemoveAt(symbols_A, index);
                }
                break;
            case 2:
                symbol.setText(symbols_E[index]);
                if(randomByElimination){
                    correctAnswer_E = stringRemoveAt(correctAnswer_E, index);
                    symbols_E = stringRemoveAt(symbols_E, index);
                }
                break;
            case 3:
                symbol.setText(symbols_I[index]);
                if(randomByElimination){
                    correctAnswer_I = stringRemoveAt(correctAnswer_I, index);
                    symbols_I = stringRemoveAt(symbols_I, index);
                }
                break;
            case 4:
                symbol.setText(symbols_O[index]);
                if(randomByElimination){
                    correctAnswer_O = stringRemoveAt(correctAnswer_O, index);
                    symbols_O = stringRemoveAt(symbols_O, index);
                }
                break;
            case 5:
                symbol.setText(symbols_U[index]);
                if(randomByElimination){
                    correctAnswer_U = stringRemoveAt(correctAnswer_U, index);
                    symbols_U = stringRemoveAt(symbols_U, index);
                }
                break;
            default:
                System.out.println("Erro! Tipo B");
                System.exit(-1);
                break;
        }
    }

    //! LOOP FUNCTIONS
    void checkTheLettersOptions(){
        numberOfOptions = 0;
        if(has_A){
            numberOfOptions++;
        }
        if(has_E){
            numberOfOptions++;
        }
        if(has_I){
            numberOfOptions++;
        }
        if(has_O){
            numberOfOptions++;
        }
        if(has_U){
            numberOfOptions++;
        }
        if(numberOfOptions == 0 && !codeStarted){
            System.err.println("===========================================");
            System.err.println("| You have to choose at least one option! |");
            System.err.println("===========================================");
            System.exit(-3);
        }
        if(numberOfOptions == 0 && codeStarted){
            System.err.println("==========================");
            System.err.println("| Acabaram as perguntas! |");
            System.err.println("==========================");
            if(isHiraganaToSyllable){
                printResult();
            }
            System.exit(0);
        }
    }

    int randomOption(){
        checkTheLettersOptions();
        return random.nextInt(0, numberOfOptions) + 1;
    }

    int randomSymbolIndex(int bound){
        return random.nextInt(0, bound);
    }

    int generateRandomOption(){
        int pos = 0;
        int choosedOption = randomOption();

        if (symbols_A.length == 0 && has_A) {
            // System.out.println("Acabou A");
            has_A = false;
            return -1;
        }
        if (symbols_E.length == 0 && has_E) {
            // System.out.println("Acabou E");
            has_E = false;
            return -1;
        }
        if (symbols_I.length == 0 && has_I) {
            // System.out.println("Acabou I");
            has_I = false;
            return -1;
        }
        if (symbols_O.length == 0 && has_O) {
            // System.out.println("Acabou O");
            has_O = false;
            return -1;
        }
        if (symbols_U.length == 0 && has_U) {
            // System.out.println("Acabou U");
            has_U = false;
            return -1;
        }

        if(has_A){
            pos++;
            if(pos == choosedOption){
                return 1;
            }
        }
        if(has_E){
            pos++;
            if(pos == choosedOption){
                return 2;
            }
        }
        if(has_I){
            pos++;
            if(pos == choosedOption){
                return 3;
            }
        }
        if(has_O){
            pos++;
            if(pos == choosedOption){
                return 4;
            }
        }
        if(has_U){
            pos++;
            if(pos == choosedOption){
                return 5;
            }
        }
        System.out.println("Erro! Tipo E");
        System.exit(-4);
        return 0;
    }

    void generateRandomSymbol(){
        // If is not first time, the last index and last option will really be the last
        if (lastIndex >= 0 && lastOption >= 0) {
            lastIndex = index;
            lastOption = option;
        }

        do {
            option = generateRandomOption();
        } while (option <= 0);

        if(isHiraganaToSyllable){
            switch (option) {
                case 1:
                    index = randomSymbolIndex(symbols_A.length);
                    symbol.setText(symbols_A[index]);
                    break;
                case 2:
                    index = randomSymbolIndex(symbols_E.length);
                    symbol.setText(symbols_E[index]);
                    break;
                case 3:
                    index = randomSymbolIndex(symbols_I.length);
                    symbol.setText(symbols_I[index]);
                    break;
                case 4:
                    index = randomSymbolIndex(symbols_O.length);
                    symbol.setText(symbols_O[index]);
                    break;
                case 5:
                    index = randomSymbolIndex(symbols_U.length);
                    symbol.setText(symbols_U[index]);
                    break;
                default:
                    System.out.println("Erro! Tipo C");
                    System.exit(-1);
                    break;
            }
        } else if(isSyllableToHiragana){
            switch (option) {
                case 1:
                    index = randomSymbolIndex(correctAnswer_A.length);
                    symbol.setText(correctAnswer_A[index]);
                    break;
                case 2:
                    index = randomSymbolIndex(correctAnswer_E.length);
                    symbol.setText(correctAnswer_E[index]);
                    break;
                case 3:
                    index = randomSymbolIndex(correctAnswer_I.length);
                    symbol.setText(correctAnswer_I[index]);
                    break;
                case 4:
                    index = randomSymbolIndex(correctAnswer_O.length);
                    symbol.setText(correctAnswer_O[index]);
                    break;
                case 5:
                    index = randomSymbolIndex(correctAnswer_U.length);
                    symbol.setText(correctAnswer_U[index]);
                    break;
                default:
                    System.out.println("Option: " + option);
                    System.out.println("Erro! Tipo D");
                    System.exit(-1);
                    break;
            }
        }
        if(!isHiraganaToSyllable && !isSyllableToHiragana){
            System.err.println("===============================");
            System.err.println("| You have to choose a study! |");
            System.err.println("===============================");
            System.exit(-3);
        } else if(isHiraganaToSyllable && isSyllableToHiragana){
            System.err.println("======================================");
            System.err.println("| You have to choose only ONE study! |");
            System.err.println("======================================");
            System.exit(-3);
        }

        // Generate another random symbol if same index and option
        if (lastIndex == index && lastOption == option) {
            if (numberOfOptions == 1) {
                boolean lastLetter = false;
                switch (option) {
                    case 1:
                        if(symbols_A.length == 1){
                            lastLetter = true;
                        }
                        break;
                    case 2:
                        if(symbols_E.length == 1){
                            lastLetter = true;
                        }
                        break;
                    case 3:
                        if(symbols_I.length == 1){
                            lastLetter = true;
                        }
                        break;
                    case 4:
                        if(symbols_O.length == 1){
                            lastLetter = true;
                        }
                        break;
                    case 5:
                        if(symbols_U.length == 1){
                            lastLetter = true;
                        }
                        break;
                    default:
                        System.out.println("Erro! Tipo G");
                        System.exit(-3);
                        break;
                }
                if (lastLetter) {
                    return;
                }
            }
            generateRandomSymbol();
            return;
        }

        // If is the first time the last index and last option will be the current one
        if (lastIndex < 0 && lastOption < 0) {
            lastIndex = index;
            lastOption = option;
        }
    }

    String[] stringRemoveAt(String[] string, int index){
        String[] newString = new String[string.length - 1];
        int actualPos = 0;
        for(int i = 0; i < string.length; i++){
            if(i != index){
                newString[actualPos] = string[i];
                actualPos++;
            }
        }
        return newString;
    }

    int countOccurrencesOf(int[] integer, int number){
        int occurrences = 0;
        for(int i = 0; i < integer.length; i++){
            if(integer[i] == number){
                occurrences++;
            }
        }
        return occurrences;
    }

    void checkChoices(){
        if(has_A){
            maxAnsweredRight_A = symbols_A.length;
        }
        if(has_E){
            maxAnsweredRight_E = symbols_E.length;
        }
        if(has_I){
            maxAnsweredRight_I = symbols_I.length;
        }
        if(has_O){
            maxAnsweredRight_O = symbols_O.length;
        }
        if(has_U){
            maxAnsweredRight_U = symbols_U.length;
        }
    }

    void printResult(){
        System.out.println("============================");
        if(maxAnsweredRight_A != 0){
            int acertos = maxAnsweredRight_A - wrongAnswers_A.length();
            if (acertos < 10) {
                System.out.println("| Você acertou em A: 0" + acertos + "/" + maxAnsweredRight_A + " |");
            } else{
                System.out.println("| Você acertou em A: " + acertos + "/" + maxAnsweredRight_A + " |");
            }
        }
        if(maxAnsweredRight_E != 0){
            int acertos = maxAnsweredRight_E - wrongAnswers_E.length();
            if (acertos < 10) {
                System.out.println("| Você acertou em E: 0" + acertos + "/" + maxAnsweredRight_E + " |");
            } else{
                System.out.println("| Você acertou em E: " + acertos + "/" + maxAnsweredRight_E + " |");
            }
        }
        if(maxAnsweredRight_I != 0){
            int acertos = maxAnsweredRight_I - wrongAnswers_I.length();
            if (acertos < 10) {
                System.out.println("| Você acertou em I: 0" + acertos + "/" + maxAnsweredRight_I + " |");
            } else{
                System.out.println("| Você acertou em I: " + acertos + "/" + maxAnsweredRight_I + " |");
            }
        }
        if(maxAnsweredRight_O != 0){
            int acertos = maxAnsweredRight_O - wrongAnswers_O.length();
            if (acertos < 10) {
                System.out.println("| Você acertou em O: 0" + acertos + "/" + maxAnsweredRight_O + " |");
            } else{
                System.out.println("| Você acertou em O: " + acertos + "/" + maxAnsweredRight_O + " |");
            }
        }
        if(maxAnsweredRight_U != 0){
            int acertos = maxAnsweredRight_U - wrongAnswers_U.length();
            if (acertos < 10) {
                System.out.println("| Você acertou em U: 0" + acertos + "/" + maxAnsweredRight_U + " |");
            } else{
                System.out.println("| Você acertou em U: " + acertos + "/" + maxAnsweredRight_U + " |");
            }
        }
        System.out.println("============================");

    }

}
