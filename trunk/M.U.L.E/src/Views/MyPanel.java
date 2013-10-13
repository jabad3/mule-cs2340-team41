package Views;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyPanel extends JPanel implements KeyListener {
    private char temp = 't';
    public MyPanel() {
        this.setPreferredSize(new Dimension(100, 100));
        addKeyListener(this);}
    public void addNotify() {
        super.addNotify();
        requestFocus();}
    public void paintComponent(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());
        g.drawString(""+temp, 50, 50);}
    public void keyPressed(KeyEvent e) { }
    public void keyReleased(KeyEvent e) { }
    public void keyTyped(KeyEvent e) {
        System.out.print("testing");
    	temp = e.getKeyChar();
        repaint();}
    public static void main(String[] s) {
        JFrame f = new JFrame();
        f.getContentPane().add(new MyPanel());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);}}