import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PhotoListNavbarView extends JPanel {
    JLabel collectionNameLabel;
    JButton deleteCollectionButton;
    JButton importPhotosButton;
    JFileChooser fileChooser;

    PhotoListNavbarView() {
        State.photoListNavbarView = this;

        setLayout(new BorderLayout());

        initComponents();
        render();
    }

    void initComponents() {
        this.collectionNameLabel = new JLabel(State.getActiveCollection() != null ? State.getActiveCollection().name : "All photos");
        this.collectionNameLabel.setFont(new Font(State.appFontName, Font.PLAIN, 30));
        this.collectionNameLabel.setBorder(new EmptyBorder(10,10,0,0));
        this.deleteCollectionButton = new JButton("Delete");
        this.importPhotosButton = new JButton("Import Photos");
        this.fileChooser = new JFileChooser();
        this.fileChooser.addChoosableFileFilter(new ImageFilter());
        this.fileChooser.setAcceptAllFileFilterUsed(false);
    }

    void renderActiveCollectionDetails() {
        String collectionNameText = State.getActiveCollection() != null ?
                State.getActiveCollection().name + " (" + State.getActiveCollectionCreationDate().toString() + ")"
                : "All photos";
        this.collectionNameLabel.setText(collectionNameText);

        if(State.getActiveCollection() == null) {
            remove(this.deleteCollectionButton);
            remove(this.importPhotosButton);
        } else {
            add(this.deleteCollectionButton, BorderLayout.EAST);
            add(this.importPhotosButton, BorderLayout.SOUTH);
        }

        revalidate();
        repaint();
    }

    void render() {
        add(this.collectionNameLabel, BorderLayout.WEST);
    }
}
