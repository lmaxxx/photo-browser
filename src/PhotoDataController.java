import javax.swing.*;

public class PhotoDataController {
    PhotoDataView photoDataView;

    PhotoDataController(PhotoDataView photoDataView) {
        this.photoDataView = photoDataView;
        setActions();
    }

    void setActions() {
        this.photoDataView.closeButton.addActionListener(e -> this.closeImageEditor());
        this.photoDataView.deleteButton.addActionListener(e -> this.deletePhoto());
        this.photoDataView.selectCollectionsButton.addActionListener(e -> this.selectCollectionsForPhoto());
        this.photoDataView.selectTagsButton.addActionListener(e -> this.selectTagsForPhoto());
        this.photoDataView.saveButton.addActionListener(e -> this.saveInputs());
    }

    void closeImageEditor() {
        Controllers.photoEditorController.removeActivePhoto();

        if (State.showFilteredPhotos) {
            Controllers.searchBarController.search();
        } else {
            Controllers.photoGridController.updatePhotoGrid();
        }
    }

    void deletePhoto() {
        int choice = JOptionPane.showConfirmDialog(null,
                "Do you want to delete this photo?",
                "Delete Photo",
                JOptionPane.YES_NO_OPTION
        );

        if (choice != 0) return;

        State.removePhoto();
        FileManager.saveObjectsToFile(State.getPhotos(), "photos.ser");
        closeImageEditor();
    }

    void selectCollectionsForPhoto() {
        JList list = new JList(State.getCollections().stream().map(collection -> collection.name).toArray());
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        int[] selectedIndices = new int[State.getActivePhoto().collectionIds.size()];

        for (int i = 0, j = 0; i < State.getCollections().size(); i++) {
            if (State.getActivePhoto().collectionIds.contains(State.getCollections().get(i).id)) {
                selectedIndices[j] = i;
                j++;
            }
        }

        list.setSelectedIndices(selectedIndices);
        JOptionPane.showMessageDialog(Views.frame, new JScrollPane(list), "Choose collections for current photo", JOptionPane.INFORMATION_MESSAGE);
        State.addPhotoToCollections(list.getSelectedIndices());
        FileManager.saveObjectsToFile(State.getPhotos(), "photos.ser");
    }

    void selectTagsForPhoto() {
        JList list = new JList(State.getTagList());
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        int[] selectedIndices = new int[State.getActivePhoto().tags.size()];

        for (int i = 0, j = 0; i < State.getTagList().length; i++) {
            if (State.getActivePhoto().tags.contains(State.getTagList()[i])) {
                selectedIndices[j] = i;
                j++;
            }
        }

        list.setSelectedIndices(selectedIndices);
        JOptionPane.showMessageDialog(Views.frame, new JScrollPane(list), "Choose tags for current photo", JOptionPane.INFORMATION_MESSAGE);
        State.addTagsToActivePhoto(list.getSelectedIndices());
        FileManager.saveObjectsToFile(State.getPhotos(), "photos.ser");
    }

    void saveInputs() {
        Photo activePhoto = State.getActivePhoto();
        activePhoto.description = this.photoDataView.descriptionInput.getText();
        activePhoto.title = this.photoDataView.titleInput.getText();
        FileManager.saveObjectsToFile(State.getPhotos(), "photos.ser");
    }
}
