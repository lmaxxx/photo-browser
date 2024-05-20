import javax.sound.sampled.Line;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PhotoEditorView extends JPanel {
    JLabel imageLabel;

    PhotoEditorView() {
        Views.photoEditorView = this;

        setLayout(new BorderLayout());

        this.initComponents();
    }

    void initComponents() {
        Controllers.photoEditorController = new PhotoEditorController(this);
        JLabel imageLabel = new JLabel();
        imageLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, new Color(204,204,204)));
    }

    void render() {
        removeAll();

        ImageIcon originalIcon = new ImageIcon(String.format("photos/%s.%s", State.getActivePhoto().id, State.getActivePhoto().extension));
        Dimension scaledDimension = Utils.getScaledDimension(new Dimension(State.getActivePhoto().size.width, State.getActivePhoto().size.height), new Dimension(750, 500));
        Image image = originalIcon.getImage().getScaledInstance(scaledDimension.width, scaledDimension.height, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(image);
        imageLabel.setIcon(imageIcon);

        add(imageLabel, BorderLayout.WEST);

        revalidate();
        repaint();
    }
}
