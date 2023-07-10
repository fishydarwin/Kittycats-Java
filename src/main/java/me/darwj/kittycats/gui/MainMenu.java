package me.darwj.kittycats.gui;

import me.darwj.kittycats.gui.components.JImagePanel;
import me.darwj.kittycats.gui.util.GUIWindow;
import me.darwj.kittycats.gui.util.GUIWindowInit;
import me.darwj.kittycats.gui.util.clipboard.ClipboardUtil;
import me.darwj.kittycats.imgur.ImgurBrowser;
import org.json.simple.parser.ParseException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class MainMenu extends JFrame implements GUIWindow {

    private MainMenu instance;
    public MainMenu() {
        super("Kittycats");
        this.instance = this;
        GUIWindowInit.setDefaults(this);
        init();
    }

    private JPanel browsePanelTop;
    private JLabel appTitle;
    private JButton randomCatButton;
    private JImagePanel image;

    @Override
    public void init() {

        GridBagConstraints currentConstraint = new GridBagConstraints();

        appTitle = new JLabel("🐈 Kittycats");
        appTitle.setFont(new Font("Helvetica", Font.BOLD, 24));
        currentConstraint.fill = GridBagConstraints.HORIZONTAL;
        currentConstraint.gridx = 0; currentConstraint.gridy = 0;
        currentConstraint.insets = new Insets(32, 32, 0, 0);
        this.add(appTitle, currentConstraint);

        browsePanelTop = new JPanel();
        currentConstraint.fill = GridBagConstraints.HORIZONTAL;
        currentConstraint.gridx = 0; currentConstraint.gridy = 1;
        this.add(browsePanelTop, currentConstraint);

        randomCatButton = new JButton("Random Cat");
        randomCatButton.setPreferredSize(new Dimension(128, 32));
        randomCatButton.addActionListener(e -> {
            try {
                image.setImage(ImgurBrowser.getRandomImage());
            } catch (URISyntaxException | IOException | ParseException | InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
        browsePanelTop.add(randomCatButton);

        image = new JImagePanel();
        currentConstraint.fill = GridBagConstraints.BOTH;
        currentConstraint.gridx = 0; currentConstraint.gridy = 2;
        currentConstraint.weightx = currentConstraint.weighty = 1;
        currentConstraint.insets = new Insets(0, 48, 32, 48);
        currentConstraint.anchor = GridBagConstraints.CENTER;
        this.add(image, currentConstraint);

        image.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger())
                    showImageContextPopup(e);
            }
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger())
                    showImageContextPopup(e);
            }
            private void showImageContextPopup(MouseEvent e) {
                JPopupMenu imageRightClickMenu = new JPopupMenu();

                JMenuItem copyToClipboard = new JMenuItem("Copy to Clipboard");
                JMenuItem saveImage = new JMenuItem("Save Image");

                copyToClipboard.addActionListener(ev -> {
                    ClipboardUtil.copy(image.getImage());
                });

                saveImage.addActionListener(ev -> {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Save the Cat Image");
                    fileChooser.setFileFilter(
                            new FileNameExtensionFilter("Portable Network Graphic", "png")
                    );

                    int userSelection = fileChooser.showSaveDialog(instance);

                    if (userSelection == JFileChooser.APPROVE_OPTION) {
                        File fileToSave = fileChooser.getSelectedFile();
                        if (!fileToSave.getName().endsWith(".png")) {
                            fileToSave = new File(fileToSave.getAbsolutePath() + ".png");
                        }

                        try {
                            ImageIO.write(image.getImage(), "png", fileToSave);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });

                imageRightClickMenu.add(copyToClipboard);
                imageRightClickMenu.add(saveImage);

                imageRightClickMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        });

    }

    @Override
    public void display() {
        this.setVisible(true);
    }

}
