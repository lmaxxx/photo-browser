import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class PhotoListNavbarController {
    PhotoListNavbarView photoListNavbarView;

    PhotoListNavbarController(PhotoListNavbarView photoListNavbarView) {
        this.photoListNavbarView = photoListNavbarView;

        setActions();
    }

    void setActions() {
        this.photoListNavbarView.deleteCollectionButton.addActionListener(_ -> this.deleteCollection());
        this.photoListNavbarView.importPhotosButton.addActionListener(_ -> this.choosePhoto());
    }

    void deleteCollection() {
        int choice = JOptionPane.showConfirmDialog(null,
                "Do you want to delete '" + State.getActiveCollection().name + "' collection?",
                "Delete Collection",
                JOptionPane.YES_NO_OPTION
        );

        if (choice != 0) return;

        State.removeCollection(State.getActiveCollection().id);
        State.setActiveCollection(null);
        State.asideView.setCollectionButtons();
        State.asideView.renderCollectionButtons();
        this.photoListNavbarView.renderActiveCollectionDetails();
    }

    void choosePhoto() {
        int response = this.photoListNavbarView.fileChooser.showSaveDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            File file = this.photoListNavbarView.fileChooser.getSelectedFile();
            String newPhotoId = FileManager.uploadFile(file);
            State.addPhoto(newPhotoId, file);
            System.out.println(State.getPhotos());
        } else {
            JOptionPane.showMessageDialog(State.frame, "Something went wrong");
        }
    }
}
