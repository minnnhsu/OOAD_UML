package UI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class ToolBar extends JToolBar {
    private JButton currentButton;
    private Color selectedColor = Color.GRAY;
    private Canvas canvas;

    public ToolBar(Canvas canvas) {
        this.canvas = canvas;
        setFloatable(false);
        setOrientation(JToolBar.VERTICAL);

        String[] buttonIcons = { "/image/select.png", "/image/association.png", "/image/generalization.png",
                "/image/composition.png", "/image/class.png", "/image/usecase.png" };

        String[] types = { "selection", "association", "generalization", "composition", "class", "usecase" };

        for (int i = 0; i < buttonIcons.length; i++) {
            add(setButton(buttonIcons[i], types[i]));
        }
    }

    public JButton setButton(String img, String type) {
        JButton button = new JButton();
        ImageIcon icon = new ImageIcon(getClass().getResource(img));
        Image scaledImage = icon.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        button.setIcon(new ImageIcon(scaledImage));
        button.setPreferredSize(new Dimension(80, 80));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton clickedButton = (JButton) e.getSource();
                if (clickedButton != currentButton) {
                    if (currentButton != null) {
                        currentButton.setBackground(null);
                    }
                    currentButton = clickedButton;
                    currentButton.setBackground(selectedColor);
                }
                canvas.setMode(type);
            }
        });

        return button;

    }

}
