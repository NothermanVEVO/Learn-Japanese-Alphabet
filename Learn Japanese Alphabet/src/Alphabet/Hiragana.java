package src.Alphabet;

public class Hiragana extends Alphabet {

    final static public String[] symbols_A = {"あ", "か", "さ", "た", "な", "は", "ま", "や", "ら", "わ", "が", "ざ", "だ", "ば", "ぱ"};
    final static public String[] correct_answer_A = {"A", "Ka", "Sa", "Ta", "Na", "Ha", "Ma", "Ya", "Ra", "Wa", "Ga", "Za", "Da", "Ba", "Pa"};

    final static public String[] symbols_E = {"え", "け", "せ", "て", "ね", "へ", "め", "れ", "げ", "ぜ", "で", "べ", "ぺ"};
    final static public String[] correct_answer_E = {"E", "Ke", "Se", "Te", "Ne", "He", "Me", "Re", "Ge", "Ze", "De", "Be", "Pe"};

    final static public String[] symbols_I = {"い", "き", "し", "ち", "に", "ひ", "み", "り", "ぎ", "じ", "ぢ", "び", "ぴ"};
    final static public String[] correct_answer_I = {"I", "Ki", "Shi", "Chi", "Ni", "Hi", "Mi", "Ri", "Gi", "Ji", "Ji", "Bi", "Pi"};

    final static public String[] symbols_O = {"お", "こ", "そ", "と", "の", "ほ", "も", "よ", "ろ", "を", "ご", "ぞ", "ど", "ぼ", "ぽ"};
    final static public String[] correct_answer_O = {"O", "Ko", "So", "To", "No", "Ho", "Mo", "Yo", "Ro", "O", "Go", "Zo", "Do", "Bo", "Po"};

    final static public String[] symbols_U = {"う", "く", "す", "つ", "ぬ", "ふ", "む", "ゆ", "る", "ぐ", "ず", "づ", "ぶ", "ぷ"};
    final static public String[] correct_answer_U = {"U", "Ku", "Su", "Tsu", "Nu", "Fu", "Mu", "Yu", "Ru", "Gu", "Zu", "Zu", "Bu", "Pu"};

    public Hiragana(Letter... has_letter){
        super(has_letter);

        create_copies_symbols(symbols_A, symbols_E, symbols_I, symbols_O, symbols_U);
        create_copies_correct_answer(correct_answer_A, correct_answer_E, correct_answer_I, correct_answer_O, correct_answer_U);
    }

}
