public class PhotoEditorController {
    PhotoEditorView photoEditorView;

    PhotoEditorController(PhotoEditorView photoEditorView) {
        this.photoEditorView = photoEditorView;
    }

    void setActivePhoto(Photo photo) {
        State.setActivePhoto(photo);
        Views.mainView.render();
        Views.photoEditorView.render();
    }

    void removeActivePhoto() {
        State.setActivePhoto(null);
        Views.mainView.render();
    }
}
