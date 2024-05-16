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
        String name = JOptionPane.showInputDialog(State.frame, "Enter new collection name");

        if (name == null || name.isEmpty()) return;

        PhotoCollection newCollection = State.createCollection(name);
        State.setActiveCollection(newCollection);

        this.asideView.setCollectionButtons();
        this.asideView.collectionButtons.getLast().addActionListener(_ -> this.setActiveCollection(newCollection));


        this.asideView.renderCollectionButtons();
        State.photoListNavbarView.renderActiveCollectionDetails();
    }

    void removeActiveCollection() {
        State.setActiveCollection(null);
        State.photoListNavbarView.renderActiveCollectionDetails();
    }

    void setActiveCollection(PhotoCollection collection) {
        State.setActiveCollection(collection);
        State.photoListNavbarView.renderActiveCollectionDetails();

    }
}
