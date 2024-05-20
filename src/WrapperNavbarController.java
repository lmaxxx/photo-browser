import javax.swing.*;
import java.io.*;

public class WrapperNavbarController {
    WrapperNavbarView wrapperNavbarView;

    WrapperNavbarController(WrapperNavbarView wrapperNavbarView) {
        this.wrapperNavbarView = wrapperNavbarView;
        setActions();
    }

    void setActions() {
        this.wrapperNavbarView.deleteCollectionButton.addActionListener(_ -> this.deleteCollection());
        this.wrapperNavbarView.importPhotosButton.addActionListener(_ -> this.choosePhoto());
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
        Views.asideView.setCollectionButtons();
        Views.asideView.renderCollectionButtons();
        this.wrapperNavbarView.renderActiveCollectionDetails();
    }

    void choosePhoto() {
        int response = this.wrapperNavbarView.fileChooser.showSaveDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            File file = this.wrapperNavbarView.fileChooser.getSelectedFile();
            String newPhotoId = FileManager.uploadFile(file);
            State.addPhoto(newPhotoId, file);
            Views.photoGridView.setPhotos();
            Views.photoGridView.renderPhotos();
        }
    }
}
