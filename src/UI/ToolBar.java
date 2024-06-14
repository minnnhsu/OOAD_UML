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

        for (ButtonType type : ButtonType.values()) {
            add(setButton(type));
        }
    }

    public JButton setButton(ButtonType type) {
        JButton button = new JButton();
        ImageIcon icon = new ImageIcon(getClass().getResource(type.getIconPath()));
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
