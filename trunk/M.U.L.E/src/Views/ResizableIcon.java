package Views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class allows an icon to be displayed in a JComponent.  The icon is
 * painted in such a way that it allows it to be resized whenever the component
 * it is contained in is resized.
 * 
 * This is similar to setting a JLabel icon, except that the icon fills the
 * entire component and is, therefore, resizable.
 * 
 * @author Max
 *
 */
public class ResizableIcon extends JComponent {

    /** The image to be displayed in the resizable icon. */
    Image image;
    
    /**
     * Create a ResizableIcon to display the given ImageIcon object.
     * Preferred size of the component defaults to the icon's width and height.
     * 
     * @param icon The ImageIcon to be displayed
     */
    public ResizableIcon(ImageIcon icon) {
        this(icon, new Dimension(icon.getIconWidth(), icon.getIconHeight()));
    }
    
    /**
     * Create a ResizableIcon to display a given ImageIcon object with the
     * specified preferred size.
     * 
     * @param icon The ImageIcon to be displayed
     * @param preferredSize The Dimension of the preferred size
     */
    public ResizableIcon(ImageIcon icon, Dimension preferredSize) {
        image = icon.getImage();
        setPreferredSize(preferredSize);
    }
    
    /**
     * Set the displayed icon and automatically updates the component to
     * display the new icon.
     * 
     * @param icon ImageIcon containing the icon to be displayed
     */
    public void setIcon(ImageIcon icon) {
        setImage(icon.getImage());
    }
    
    /**
     * Set the displayed image and automatically updates the component to
     * display the new icon.
     * 
     * @param image Image object containing the icon to be displayed
     */
    public void setImage(Image image) {
        this.image = image;
        repaint();
    }
    
    /**
     * Get the Image object that is being displayed inside the component.
     * 
     * @return The image object being displayed
     */
    public Image getImage() {
        return image;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
    
    public static void main(String[] args) {
        JFrame jf = new JFrame("Display a pawn");
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new PlayerPawn(new ImageIcon("Buzzite.png")), BorderLayout.CENTER);
        panel.add(new ResizableIcon(new ImageIcon("flapper.png")), BorderLayout.WEST);
        panel.add(new JLabel(new ImageIcon("bonzoid.png")), BorderLayout.EAST);
        panel.setPreferredSize(new Dimension(300, 200));
        jf.getContentPane().add(panel);
        jf.pack();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }
}
