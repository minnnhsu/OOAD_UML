package UI;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

public class UI extends JFrame {
    private ToolBar toolbar;
    private MenuBar menubar;
    private Canvas canvas;

    public UI() {
        setTitle("UML");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 815, 515);
        setLocationRelativeTo(null);

        canvas = Canvas.getInstance();
        canvas.setBackground(Color.WHITE);
        canvas.setBounds(95, 15, 696, 457);
        canvas.initialize();

        getContentPane().setLayout(new BorderLayout());

        toolbar = new ToolBar(canvas);
        getContentPane().add(toolbar, BorderLayout.WEST);

        menubar = new MenuBar(canvas);
        setJMenuBar(menubar);

        // Add canvas to the content pane
        getContentPane().add(canvas, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        UI ui = new UI();
        ui.setVisible(true);
    }
}
