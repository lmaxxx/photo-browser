import javax.swing.*;
import java.awt.*;

public class PhotoEditorView extends JPanel {
    JLabel imageLabel;
    PhotoDataView photoDataView;

    PhotoEditorView() {
        Views.photoEditorView = this;
        this.photoDataView = new PhotoDataView();

        setLayout(new BorderLayout());

        this.initComponents();
    }

    void initComponents() {
        Controllers.photoEditorController = new PhotoEditorController(this);
        this.imageLabel = new JLabel();
        this.imageLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, new Color(204, 204, 204)));
        this.imageLabel.setPreferredSize(new Dimension(750, 600));
    }

    void render() {
        removeAll();

        ImageIcon originalIcon = new ImageIcon(String.format("photos/%s.%s", State.getActivePhoto().id, State.getActivePhoto().extension));
        Dimension scaledDimension = Utils.getScaledDimension(new Dimension(State.getActivePhoto().size.width, State.getActivePhoto().size.height), new Dimension(750, 700));
        Image image = originalIcon.getImage().getScaledInstance(scaledDimension.width, scaledDimension.height, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(image);
        this.imageLabel.setIcon(imageIcon);
        this.imageLabel.setHorizontalAlignment(JLabel.CENTER);

        add(this.imageLabel, BorderLayout.WEST);
        add(this.photoDataView, BorderLayout.CENTER);

        revalidate();
        repaint();
    }
}
