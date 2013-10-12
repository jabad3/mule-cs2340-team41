package Views;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

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
    private Image image;
    
    /**
     * Create a ResizableIcon to display the given ImageIcon object.
     * 
     * @param icon The ImageIcon to be displayed
     */
    public ResizableIcon(ImageIcon icon) {
        this(icon.getImage());
    }
    
    /**
     * Create a ResizableIcon to display the given Image object.
     * 
     * @param image The image to be displayed
     */
    public ResizableIcon(Image image) {
        this.image = image;
    }
    
    /**
     * Set the displayed icon and automatically updates the component to
     * display the new icon.
     * 
     * @param icon The new icon to be displayed
     */
    public void setIcon(ImageIcon icon) {
        this.image = icon.getImage();
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}
