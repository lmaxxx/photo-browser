import javax.swing.*;

public class AsideController {
    AsideView asideView;

    AsideController(AsideView asideView) {
        this.asideView = asideView;

        setActions();
    }

    void setActions() {
        this.asideView.creteCollectionButton.addActionListener(_ -> this.createNewCollection());
        this.asideView.allPhotosButton.addActionListener(_ -> this.removeActiveCollection());
    }

    void createNewCollection() {
        String name = JOptionPane.showInputDialog(Views.frame, "Enter new collection name");

        if (name == null || name.isEmpty()) return;

        PhotoCollection newCollection = State.createCollection(name);
        State.setActiveCollection(newCollection);

        this.asideView.setCollectionButtons();
        this.asideView.collectionButtons.getLast().addActionListener(_ -> this.setActiveCollection(newCollection));


        this.asideView.renderCollectionButtons();
        Views.wrapperNavbarView.renderActiveCollectionDetails();
        Controllers.photoGridController.updatePhotoGrid();
    }

    void removeActiveCollection() {
        State.setActiveCollection(null);
        Views.wrapperNavbarView.renderActiveCollectionDetails();
        Controllers.photoGridController.updatePhotoGrid();
    }

    void setActiveCollection(PhotoCollection collection) {
        State.setActiveCollection(collection);
        Views.wrapperNavbarView.renderActiveCollectionDetails();
        Controllers.photoGridController.updatePhotoGrid();
    }
}
