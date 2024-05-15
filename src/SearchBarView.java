import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SearchBarView extends JPanel {
    SearchBarView() {
        setPreferredSize(new Dimension(1000, 50));
        Border rightSideBorder = BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204,204,204));
        setBorder(rightSideBorder);
    }

}
