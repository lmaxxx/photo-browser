public class PhotoGridController {
    PhotoGridView photoGridView;

    PhotoGridController(PhotoGridView photoGridView) {
        this.photoGridView = photoGridView;
        setActions();
    }

    void setActions() {

    }

    void updatePhotoGrid() {
        State.photoGridView.setPhotos();
        State.photoGridView.renderPhotos();
    }
}
