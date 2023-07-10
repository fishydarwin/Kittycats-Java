package me.darwj.kittycats.gui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;

public class JImagePanel extends JPanel {

    private BufferedImage image;
    private BufferedImage scaledImage;

    public JImagePanel() {
        super();
        setLayout(new FlowLayout());
        image = new BufferedImage(64, 64, BufferedImage.TYPE_INT_RGB);
        assignResizeEvent();
    }

    public JImagePanel(BufferedImage image) {
        super();
        setLayout(new FlowLayout());
        this.image = image;
        assignResizeEvent();
    }

    public BufferedImage getImage() { return image; }

    public void setImage(BufferedImage newImage) {
        image = newImage;
        try {
            int calculatedWidth = (int) (image.getWidth() * ((float) this.getHeight() / image.getHeight()));
            resizeImage(calculatedWidth, this.getHeight());
            repaint();
        } catch (Exception ex) { /* expected */}
    }

    private void assignResizeEvent() {
        this.addComponentListener(new ComponentListener() {
            public void componentResized(ComponentEvent e) {
                int calculatedWidth =
                        (int) (image.getWidth()
                                * ((float) e.getComponent().getHeight() / image.getHeight()));
                resizeImage(calculatedWidth, e.getComponent().getHeight());
            }
            public void componentHidden(ComponentEvent e) {}

            public void componentMoved(ComponentEvent e) {}

            public void componentShown(ComponentEvent e) {}
        });

    }

    private void resizeImage(int newWidth, int newHeight) {
        BufferedImage resized = new BufferedImage(newWidth, newHeight, image.getType());
        Graphics2D g = resized.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(image, 0, 0, newWidth, newHeight,
                0, 0, image.getWidth(), image.getHeight(), null);
        g.dispose();
        scaledImage = resized;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(scaledImage,
                (this.getWidth() - scaledImage.getWidth()) / 2,
                (this.getHeight() - scaledImage.getHeight()) / 2,
                this);
    }

}
