public class PhotoGridController {
    PhotoGridView photoGridView;

    PhotoGridController(PhotoGridView photoGridView) {
        this.photoGridView = photoGridView;
        setActions();
    }

    void setActions() {

    }

    void updatePhotoGrid() {
        Views.photoGridView.setPhotos();
        Views.photoGridView.renderPhotos();
    }

    void setActivePhoto(Photo photo) {
        State.setActivePhoto(photo);
    }

//    void
}
