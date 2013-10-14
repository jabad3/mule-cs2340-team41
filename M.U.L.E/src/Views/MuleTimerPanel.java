package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class displays a timer in the Mule game.
 * A timer acts as a visual cue to the user to let them know how much time
 * is left in their current turn.
 * 
 * The MuleTimerPanel will not automatically update itself.  The decrement
 * method must be called whenever the timer needs to be adjusted.
 * 
 * A java.util.Timer or javax.swing.Timer should be set up to perform an
 * action every X number of milliseconds.  A method call to decrement can then
 * be included with the other actions that are invoked by the java.util.Timer
 * or javax.swing.Timer to get the MuleTimerPanel to update accordingly.
 * 
 * The MuleTimerPanel is expected to be displayed vertically.  As the time
 * remaining decreases, the visible bar gets shorter.
 * 
 * @author Max
 *
 */
public class MuleTimerPanel extends JPanel {

    /** The color of the bar to be displayed. */
    Color barColor;
    
    /** Duration of the timer in milliseconds. */
    int duration;
    
    /** Time remaining (in milliseconds) until the timer hits zero. */
    int remainingTime;
    
    /**
     * Creates a MuleTimerPanel with a specified duration and
     * default bar color of red.
     * 
     * @param duration Duration of the timer in milliseconds
     */
    public MuleTimerPanel(int duration) {
        this(duration, Color.red);
    }
    
    /**
     * Creates a MuleTimerPanel with a specified duration and color
     * 
     * @param duration Duration of the timer in milliseconds
     * @param barColor Color of the timer bar
     */
    public MuleTimerPanel(int duration, Color barColor) {
        this.duration = duration;
        this.barColor = barColor;
        this.remainingTime = duration;
        setPreferredSize(new Dimension(50, 400));
    }
    
    /**
     * Checks if there is any time remaining.
     * 
     * @return True if there is no time remaining
     */
    public boolean isFinished() {
        return remainingTime <= 0;
    }
    
    /**
     * Decrement the timer by a specified amount of time.
     * 
     * @param delta The number of milliseconds to decrement the timer
     */
    public void decrement(int delta) {
        remainingTime -= delta;
    }
    
    @Override
    /**
     * Paints a bar with a height that is proportional to the time remaining.
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        double fractionRemaining = ((double) remainingTime) / ((double) duration);
        int barHeight = (int) (fractionRemaining * ((double) height));
        
        g.setColor(barColor);
        g.fillRect(0, height - barHeight, width, barHeight);
    }
    
    /**
     * Main method for testing.
     * 
     * @param args
     */
    public static void main(String[] args) {
        JFrame jf = new JFrame("Timer test");
        final JPanel centerp = new JPanel();
        
        centerp.setPreferredSize(new Dimension(600, 400));
        centerp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        final MuleTimerPanel tp = new MuleTimerPanel(20000);
        
        jf.add(centerp, BorderLayout.CENTER);
        jf.add(tp, BorderLayout.EAST);
        jf.pack();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        final java.util.Timer timer = new java.util.Timer();
        timer.schedule(new java.util.TimerTask() { 
                public void run() {
                    if (!tp.isFinished()) {
                        tp.decrement(50);
                        tp.repaint();
                    }
                    else {
                        timer.cancel();
                        Graphics g = centerp.getGraphics();
                        g.setColor(Color.BLACK);
                        g.drawString("Time is up!!!!", centerp.getWidth()/3, centerp.getHeight()/2);
                        g.dispose();
                    }
                }
            }, 0, 100);
    }
}
