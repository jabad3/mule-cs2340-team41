package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.Collection;
import java.util.ArrayList;

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
    
    /** The default number of milliseconds to decrement the timer with. */
    int defaultDecrementAmount;
    
    /** All mule timer listeners listening to this object. */
    Collection<MuleTimerListener> muleTimerListeners;
    
    /**
     * Creates a MuleTimerPanel with default duration of 30 seconds and
     * default  color of red.
     */
    public MuleTimerPanel() {
        this(30);
    }
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
        this.defaultDecrementAmount = 33;
        muleTimerListeners = new ArrayList<>(3);  // not many expected
        
        setPreferredSize(new Dimension(50, 400));
    }
    
    /**
     * Add a listener to this MuleTimerPanel.
     * 
     * @param listener A new MuleTimerListener to send notifications to
     */
    public void addMuleTimerListener(MuleTimerListener listener) {
        muleTimerListeners.add(listener);
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
     * Decrement the timer using the previously-set value for
     * defaultDecrementAmount.
     * 
     * If defaultDecrementAmount was not previously set to a specified value,
     * the default value of 33 milliseconds for defaultDecrementAmount will
     * be used to decrement the timer.
     */
    public void decrement() {
        decrement(defaultDecrementAmount);
    }
    
    /**
     * Decrement the timer by a specified amount of time.
     * 
     * @param delta The number of milliseconds to decrement the timer
     */
    public void decrement(int delta) {
        remainingTime -= delta;
        if (isFinished())
            sendMuleTimerFinishedNotifications();
    }
    
    private void sendMuleTimerFinishedNotifications() {
        for (MuleTimerListener mtl: muleTimerListeners)
            mtl.muleTimerFinished();
    }
    
    /**
     * Set the timer's duration to the specified amount
     * 
     * @param duration The new duration for the MuleTimer
     */
    public void setDuration(int duration) {
        if (duration <= 0)
            this.duration = 0;
        else
            this.duration = duration;
    }
   
    /**
     * Set the timer's default decrement amount.
     * 
     * @param amount A positive number of milliseconds to decrement the timer
     * each time decrement() is called
     */
    public void setDefaultDecrementAmount(int amount) {
        defaultDecrementAmount = amount;
    }
    
    /**
     * Resets the timer by setting the time remaining equal to the timer's
     * duration.
     */
    public void reset() {
        remainingTime = duration;
    }
    
    /**
     * Resets the timer by setting the time remaining equal to a new duration
     * for the timer.
     * 
     * @param newDuration The new duration to be used for the timer
     */
    public void reset(int newDuration) {
        setDuration(newDuration);
        reset();
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
     * to delete
     */
    public String toString() {
        int width = getWidth();
        int height = getHeight();
        double fractionRemaining = ((double) remainingTime) / ((double) duration);
        int barHeight = (int) (fractionRemaining * ((double) height));
        return ("duration/remaining/defaultDec = " + duration + "/" + remainingTime + "/" + defaultDecrementAmount
                + "\n" + "barheight/width/height:  " + barHeight + "/" + width + "/" + height);
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
        
        final MuleTimerPanel tp = new MuleTimerPanel(3000);
        
        jf.add(centerp, BorderLayout.CENTER);
        jf.add(tp, BorderLayout.EAST);
        jf.pack();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        final java.util.Timer timer = new java.util.Timer();
        timer.schedule(new java.util.TimerTask() { 
                public void run() {
                    if (!tp.isFinished()) {
                        tp.decrement(10);
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
            }, 0, 10);
    }
}
