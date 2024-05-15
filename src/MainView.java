import javax.swing.*;
import java.awt.*;

public class MainView extends JPanel {
    SearchBarView searchBar = new SearchBarView();

    MainView() {
        setPreferredSize(new Dimension(1000, 700));
        setLayout(new BorderLayout());
        add(searchBar, BorderLayout.NORTH);
    }

}
