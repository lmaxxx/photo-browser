import javax.swing.*;
import java.awt.*;

//Main frame
public class S30208Leshchenko extends JFrame {
    AsideView aside = new AsideView();
    MainView main = new MainView();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(S30208Leshchenko::new);
    }

    S30208Leshchenko() {
        super("Photo Browser");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        setSize(new Dimension(1200, 700));

        add(aside, BorderLayout.WEST);
        add(main, BorderLayout.EAST);

        setLocationRelativeTo(null);
        pack();

        setVisible(true);
    }
}