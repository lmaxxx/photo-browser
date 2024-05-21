import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class PhotoDataController {
    PhotoDataView photoDataView;

    PhotoDataController(PhotoDataView photoDataView) {
        this.photoDataView = photoDataView;
        setActions();
    }

    void setActions() {
        this.photoDataView.closeButton.addActionListener(_ -> this.closeImageEditor());
        this.photoDataView.deleteButton.addActionListener(_ -> this.deletePhoto());
        this.photoDataView.selectCollectionsButton.addActionListener(_ -> this.selectCollectionsForPhoto());
        this.photoDataView.selectTagsButton.addActionListener(_ -> this.selectCollectionsForPhoto());
    }

    void closeImageEditor() {
        Controllers.photoEditorController.removeActivePhoto();
        Controllers.photoGridController.updatePhotoGrid();
    }

    void deletePhoto() {
        int choice = JOptionPane.showConfirmDialog(null,
                "Do you want to delete this photo?",
                "Delete Photo",
                JOptionPane.YES_NO_OPTION
        );

        if (choice != 0) return;

        State.removePhoto();
        closeImageEditor();
    }

    void selectCollectionsForPhoto() {
        JList list = new JList(State.getCollections().stream().map(collection -> collection.name).toArray());
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        int[] selectedIndices = new int[State.getActivePhoto().collectionIds.size()];

        for (int i = 0, j = 0; i < State.getCollections().size(); i++) {
            System.out.println(State.getCollections().get(i).id);
            System.out.println(State.getActivePhoto().id);
            if(State.getActivePhoto().collectionIds.contains(State.getCollections().get(i).id)) {
                selectedIndices[j] = i;
                j++;
            }
        }
        System.out.println(Arrays.toString(selectedIndices));

        list.setSelectedIndices(selectedIndices);
        JOptionPane.showMessageDialog(Views.frame, new JScrollPane(list), "Choose collections for current photo", JOptionPane.INFORMATION_MESSAGE);
        State.addPhotoToCollections(list.getSelectedIndices());
    }
}
