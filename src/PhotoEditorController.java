public class PhotoEditorController {
    PhotoEditorView photoEditorView;

    PhotoEditorController(PhotoEditorView photoEditorView) {
        this.photoEditorView = photoEditorView;
    }

    void setActivePhoto(Photo photo) {
        State.setActivePhoto(photo);
        Views.mainView.render();
        Views.photoEditorView.render();
        Views.photoDataView.render();
        Views.photoDataView.updatePhotoData();
    }

    void removeActivePhoto() {
        State.setActivePhoto(null);
        Views.mainView.render();
    }
}
