import javax.swing.*;

public class AsideController {
    AsideView asideView;

    AsideController(AsideView asideView) {
        this.asideView = asideView;
        setActions();
    }

    void setActions() {
        this.asideView.creteCollectionButton.addActionListener(e -> this.createNewCollection());
        this.asideView.allPhotosButton.addActionListener(e -> this.removeActiveCollection());
    }

    void createNewCollection() {
        String name = JOptionPane.showInputDialog(Views.frame, "Enter new collection name");

        if (name == null || name.isEmpty()) return;

        PhotoCollection newCollection = State.createCollection(name);
        State.setActiveCollection(newCollection);

        this.asideView.setCollectionButtons();
        this.asideView.collectionButtons.getLast().addActionListener(e -> this.setActiveCollection(newCollection));

        this.asideView.renderCollectionButtons();
        Controllers.photoEditorController.removeActivePhoto();
        Views.wrapperNavbarView.renderActiveCollectionDetails();

        Controllers.searchBarController.cancelFilter();
        Controllers.photoGridController.updatePhotoGrid();
    }

    void removeActiveCollection() {
        State.setActiveCollection(null);
        Views.wrapperNavbarView.renderActiveCollectionDetails();
        Controllers.photoEditorController.removeActivePhoto();
        Controllers.searchBarController.cancelFilter();
        Controllers.photoGridController.updatePhotoGrid();
    }

    void setActiveCollection(PhotoCollection collection) {
        State.setActiveCollection(collection);
        Controllers.photoEditorController.removeActivePhoto();
        Views.wrapperNavbarView.renderActiveCollectionDetails();
        Controllers.searchBarController.cancelFilter();
        Controllers.photoGridController.updatePhotoGrid();
    }

    void loadCollections() {
        State.setCollections(FileManager.readCollections());
        this.asideView.setCollectionButtons();
        this.asideView.renderCollectionButtons();
    }
}
