import javax.swing.*;
import java.awt.*;

//Main frame
public class S30208Leshchenko extends JFrame {
    AsideView asideView = new AsideView();
    AsideController asideController = new AsideController(asideView);
    MainView mainView = new MainView();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(S30208Leshchenko::new);
    }

    S30208Leshchenko() {
        super("Photo Browser");

        State.frame = this;
        Controllers.asideController = this.asideController;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        setSize(new Dimension(1200, 700));

        add(asideView, BorderLayout.WEST);
        add(mainView, BorderLayout.EAST);

        setLocationRelativeTo(null);
        pack();

        setVisible(true);
    }
}