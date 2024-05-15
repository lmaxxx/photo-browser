import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class AsideView extends JPanel {
    AsideView() {
        setPreferredSize(new Dimension(200, 700));
        Border rightSideBorder = BorderFactory.createMatteBorder(0, 0, 0, 2, new Color(204,204,204));
        setBorder(rightSideBorder);

    }
}
