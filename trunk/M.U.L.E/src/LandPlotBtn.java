import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class LandPlotBtn extends JButton {
    private Image image;

    /** @serial */
    private final Rectangle innerArea = new Rectangle();

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image != null) {
            SwingUtilities.calculateInnerArea(this, innerArea);

            g.drawImage(image,
                innerArea.x, innerArea.y, innerArea.width, innerArea.height,
                this);
        }
    }
}