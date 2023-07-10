package me.darwj.kittycats.gui.util;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class GUIWindowInit {

    public static void setDefaults(@NotNull JFrame who) {
        who.setSize(960, 640);
        who.setPreferredSize(new Dimension(960, 640));
        who.setMinimumSize(new Dimension(640, 480));
        who.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        who.setLayout(new GridBagLayout());
    }

}
