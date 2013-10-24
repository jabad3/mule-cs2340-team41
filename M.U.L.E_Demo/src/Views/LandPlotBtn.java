package Views;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import Models.LandPlot;

public class LandPlotBtn extends ResizableIcon {
    
    private LandPlot myLandPlot;

    public LandPlotBtn(LandPlot landPlot, MouseListener buttonListener) {
        super(landPlot.getLandType().getStockImageIcon());
        this.myLandPlot = landPlot;
        this.addMouseListener(buttonListener);
        drawBorder();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // TODO needed later for painting mule, resource
    }

    private void drawBorder() {
        if (myLandPlot.getOwner() == null) {
            setBorderToDefault();
        } else {
            Color borderColor = myLandPlot.getBorderColor();
            this.setBorder(BorderFactory.createLineBorder(borderColor, 3));
        }
    }
    
    public LandPlot getMyLandPlot() {
        return myLandPlot;
    }

    public void setBorderToDefault() {
        this.setBorder(BorderFactory.createEmptyBorder());
    }

}