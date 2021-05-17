import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Overlay ol = new Overlay("WindowsLA", 460, 80);
        ol.add(new DrawCanvas());
        ol.setVisible(true);
        ol.setAlwaysOnTop(true);
        ol.startGameLoop();
        ol.setBackground(new Color(1f, 1f, 1f, 0f));

    }
}
class Overlay extends JFrame implements Runnable {
    private Thread th = null;
    public Overlay(String title, int x, int y) {
        super(title);
        JFrame.setDefaultLookAndFeelDecorated(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(x, y);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int w = screenSize.width;
        int h = screenSize.height;
        setLocation(w-x, h-y-50);
        setUndecorated(true);
        setResizable(false);
    }
    public synchronized void startGameLoop(){
        if ( th == null ) {
            th = new Thread(this);
            th.start();
        }
    }

    public synchronized void stopGameLoop(){
        if ( th != null ) {
            th = null;
        }
    }

    public void run(){
        while(th != null){
            try{
                Thread.sleep(500);
                repaint();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

class DrawCanvas extends JPanel {
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setFont(new Font("Yu Gothic UI", Font.TRUETYPE_FONT, 22));
        g.setColor(Color.LIGHT_GRAY);
        setBackground(new Color(0f, 0f, 0f, 0f));
        g.drawString("Windows のライセンス認証", 5, 24);
        g.setFont(new Font("Meiryo UI", Font.TRUETYPE_FONT, 16));
        g.drawString("設定を開き、Windows のライセンス認証を行ってください。", 5, 54);
    }
}