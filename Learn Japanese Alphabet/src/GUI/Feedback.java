package src.GUI;

import javax.swing.JPanel;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Feedback extends JPanel{

    private static ArrayList<Cards> cards = new ArrayList<>();
    private static Feedback feedback;

    public Feedback(){
        setOpaque(false);
        setDoubleBuffered(true);
        setLayout(null);
        feedback = this;
        cards.clear();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for(Cards card : cards){
            card.draw(g2);
        }
        g2.dispose();
    }

    private static void add_to_cards(Cards card){
        cards.add(0, card);
        for(int i = 1; i < cards.size(); i++){
            cards.get(i).y = cards.get(i - 1).y + 15 + Cards.fontSize;
        }
    }

    public static void create_new_card(String text, boolean is_correct_answer){
        add_to_cards(new Cards(10, 10, text, is_correct_answer));
        feedback.repaint();
    }

    public static void remove_card(Cards card){
        cards.remove(card);
        feedback.repaint();
    }

    public static void repaint_screen(){
        feedback.repaint();
    }

    public static int get_height(){
        return feedback.getHeight();
    }

}

class Cards{

    int x;
    float y;
    float fall_speed = 0.0f;
    float width;
    String text;
    boolean is_correct_answer;

    float opacity = 1.0f;

    Cards this_card;

    final static int fontSize = 25;

    Timer wait_n_seconds = new Timer();

    Timer disapear_card = new Timer();

    Cards(int x, int y, String text, boolean is_correct_answer){
        this.x = x;
        this.y = y;
        this.text = text;
        this.is_correct_answer = is_correct_answer;
        this_card = this;

        width = ((float) (fontSize / 1.5) * text.length());

        wait_n_seconds.schedule(new TimerTask(){
            @Override
            public void run() {
                wait_n_seconds.cancel();
                disapear();
            }
        }, 12000);

    }

    void disapear(){
        disapear_card.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run() {
                fall_speed += 0.05f;
                y += fall_speed;
                opacity -= 0.0085f; // 美味しいですね
                if(y >= Feedback.get_height() || opacity < 0.0f){
                    disapear_card.cancel();
                    Feedback.remove_card(this_card);
                    // System.out.println("morri");
                }
                Feedback.repaint_screen();
            }
        }, 0, 20);
    }

    void draw(Graphics2D g2){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        g2.setColor(Color.WHITE);
        g2.fillRect(x, (int) y, (int) width + 5, fontSize + 10);
        if(is_correct_answer){
            g2.setColor(Color.GREEN);
        } else{
            g2.setColor(Color.RED);
        }
        g2.setFont(new Font("Meiryo", Font.PLAIN, fontSize));
        g2.drawString(text, x + 5, y + fontSize);
        g2.drawRect(x, (int) y, (int) width + 5, fontSize + 10);
        // g2.drawRoundRect(x, y, (int) width, fontSize, (int) (width / 4), (int) (fontSize / 4));
        // g2.fillRoundRect(x, y, (int) width, fontSize, (int) (width / 4), (int) (fontSize / 4));
    }

}