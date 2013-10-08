import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class LandPlotBtn extends JButton {
    
    private ImageIcon imageIcon;
    private LandPlot myLandPlot;
    //private Border currentBorder;

    public LandPlotBtn(LandPlot landPlot, MouseListener buttonListener) {
        this.myLandPlot = landPlot;
        this.imageIcon = myLandPlot.getLandType().getStockImageIcon();
        this.addMouseListener(buttonListener);
        drawBorder();
    }
    
    /** @serial */
    private final Rectangle innerArea = new Rectangle();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        SwingUtilities.calculateInnerArea(this, innerArea);
        Image image = imageIcon.getImage();
        g.drawImage(image, innerArea.x, innerArea.y, innerArea.width, innerArea.height, this);
    }

    private void drawBorder() {
        if (myLandPlot.getOwner() == null) {
            //this.setBorder(new EmptyBorder(0,0,0,0));
            setBorderToDefault();
        } else {
            Color borderColor = myLandPlot.getBorderColor();
            this.setBorder(BorderFactory.createLineBorder(borderColor, 3));
        }
    }
    
    public LandPlot getMyLandPlot() {
        return myLandPlot;
    }
    
    public void changeImageIcon() {
        this.imageIcon = new ImageIcon("flapper.png");
    }

    /*
     * Something I will try to mess with so that we don't have the border
     * "squish" the image
     */
    public void setBorderToDefault() {
        // TODO Auto-generated method stub
        this.setBorder(BorderFactory.createEmptyBorder());
    }

}