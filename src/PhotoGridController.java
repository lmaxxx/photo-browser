public class PhotoGridController {
    PhotoGridView photoGridView;

    PhotoGridController(PhotoGridView photoGridView) {
        this.photoGridView = photoGridView;
    }

    void updatePhotoGrid() {
        this.photoGridView.setPhotos();
        this.photoGridView.renderPhotos();
    }
}
