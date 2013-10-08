import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class LandPlotBtn extends JButton {
    
    private Image image;
    private LandPlot myLandPlot;

    public LandPlotBtn(ActionListener buttonListener) {
        this.addActionListener(buttonListener);
    }
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
    
    public LandPlot getMyLandPlot() {
        return myLandPlot;
    }
}