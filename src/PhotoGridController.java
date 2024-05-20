public class PhotoGridController {
    PhotoGridView photoGridView;

    PhotoGridController(PhotoGridView photoGridView) {
        this.photoGridView = photoGridView;
    }

    void updatePhotoGrid() {
        Views.photoGridView.setPhotos();
        Views.photoGridView.renderPhotos();
    }
}
