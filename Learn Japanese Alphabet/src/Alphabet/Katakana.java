package src.Alphabet;

public class Katakana extends Alphabet{

    final static public String[] symbols_A = {"ア", "カ", "サ", "タ", "ナ", "ハ", "マ", "ヤ", "ラ", "ワ", "ガ", "ザ", "ダ", "バ", "パ"};
    final static public String[] correct_answer_A = {"A", "Ka", "Sa", "Ta", "Na", "Ha", "Ma", "Ya", "Ra", "Wa", "Ga", "Za", "Da", "Ba", "Pa"};

    final static public String[] symbols_E = {"エ", "ケ", "セ", "テ", "ネ", "ヘ", "メ", "レ", "ゲ", "ゼ", "デ", "ベ", "ペ"};
    final static public String[] correct_answer_E = {"E", "Ke", "Se", "Te", "Ne", "He", "Me", "Re", "Ge", "Ze", "De", "Be", "Pe"};

    final static public String[] symbols_I = {"イ", "キ", "シ", "チ", "ニ", "ヒ", "ミ", "リ", "ギ", "ジ", "ヂ", "ビ", "ピ"};
    final static public String[] correct_answer_I = {"I", "Ki", "Shi", "Chi", "Ni", "Hi", "Mi", "Ri", "Gi", "Ji", "Ji", "Bi", "Pi"};

    final static public String[] symbols_O = {"オ", "コ", "ソ", "ト", "ノ", "ホ", "モ", "ヨ", "ロ", "ヲ", "ゴ", "ゾ", "ド", "ボ", "ポ"};
    final static public String[] correct_answer_O = {"O", "Ko", "So", "To", "No", "Ho", "Mo", "Yo", "Ro", "O", "Go", "Zo", "Do", "Bo", "Po"};

    final static public String[] symbols_U = {"ウ", "ク", "ス", "ツ", "ヌ", "フ", "ム", "ユ", "ル", "グ", "ズ", "ヅ", "ブ", "プ"};
    final static public String[] correct_answer_U = {"U", "Ku", "Su", "Tsu", "Nu", "Fu", "Mu", "Yu", "Ru", "Gu", "Zu", "Zu", "Bu", "Pu"};

    public Katakana(Letter... has_letter){
        super(has_letter);

        create_copies_symbols(symbols_A, symbols_E, symbols_I, symbols_O, symbols_U);
        create_copies_correct_answer(correct_answer_A, correct_answer_E, correct_answer_I, correct_answer_O, correct_answer_U);
    }

}
