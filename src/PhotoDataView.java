import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PhotoDataView extends JPanel{
    JLabel titleLabel = new JLabel("Title: ");
    JLabel descriptionLabel = new JLabel("Description: ");
    JLabel extensionLabel = new JLabel();
    JLabel sizeLabel = new JLabel();
    JTextField titleInput = new JTextField();
    JTextArea descriptionInput = new JTextArea();
    JButton saveButton = new JButton("Save");
    JButton closeButton = new JButton("Close");
    JButton deleteButton = new JButton("Delete");
    JButton selectCollectionsButton = new JButton("Select collections");
    JButton selectTagsButton = new JButton("Select tags");

    PhotoDataView() {
        Views.photoDataView = this;
        Controllers.photoDataController = new PhotoDataController(this);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        this.initComponents();
        this.render();
    }

    void initComponents() {
        titleInput.setPreferredSize(new Dimension(200, 30));
        descriptionInput.setAutoscrolls(true);
        descriptionInput.setPreferredSize(new Dimension(200, 200));
        sizeLabel.setPreferredSize(new Dimension(230, 20));
        extensionLabel.setPreferredSize(new Dimension(230, 20));
        selectCollectionsButton.setPreferredSize(new Dimension(230, 20));
        selectTagsButton.setPreferredSize(new Dimension(230, 20));
    }

    void updatePhotoData() {
        extensionLabel.setText("Extension: " + State.getActivePhoto().extension);
        sizeLabel.setText("Size: " + State.getActivePhoto().size.width + "x" + State.getActivePhoto().size.height);
        revalidate();
        repaint();
    }

    void render() {
        add(titleLabel);
        add(titleInput);
        add(descriptionLabel);
        add(descriptionInput);
        add(extensionLabel);
        add(sizeLabel);
        add(selectCollectionsButton);
        add(selectTagsButton);
        add(saveButton);
        add(closeButton);
        add(deleteButton);
    }
}
