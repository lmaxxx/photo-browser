import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class WrapperNavbarView extends JPanel {
    JLabel collectionNameLabel;
    JButton deleteCollectionButton;
    JButton importPhotosButton;
    JFileChooser fileChooser;

    WrapperNavbarView() {
        Views.wrapperNavbarView = this;

        setLayout(new BorderLayout());

        initComponents();
        render();
    }

    void initComponents() {
        this.collectionNameLabel = new JLabel(State.getActiveCollection() != null ? State.getActiveCollection().name : "All photos");
        this.collectionNameLabel.setFont(new Font(State.appFontName, Font.PLAIN, 30));
        this.collectionNameLabel.setBorder(new EmptyBorder(10,10,10,0));
        this.deleteCollectionButton = new JButton("Delete");
        this.importPhotosButton = new JButton("Import Photos");
        this.fileChooser = new JFileChooser();
        this.fileChooser.addChoosableFileFilter(new ImageFilter());
        this.fileChooser.setAcceptAllFileFilterUsed(false);
    }

    void renderActiveCollectionDetails() {
        PhotoCollection collection = State.getActiveCollection();

        String collectionNameText = collection != null ?
                collection.name + " (" + Utils.formatDate(collection.createdAt) + ")"
                : "All photos";
        this.collectionNameLabel.setText(collectionNameText);

        if(collection == null) {
            remove(this.deleteCollectionButton);
        } else {
            add(this.deleteCollectionButton, BorderLayout.EAST);
        }

        revalidate();
        repaint();
    }

    void render() {
        add(this.collectionNameLabel, BorderLayout.WEST);
        add(this.importPhotosButton, BorderLayout.SOUTH);
    }
}
