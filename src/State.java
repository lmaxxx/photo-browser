import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static java.util.stream.Collectors.toCollection;

final public class State {
    private static ArrayList<PhotoCollection> collections = new ArrayList<>();
    private static PhotoCollection activeCollection = null;

    private static ArrayList<Photo> photos = new ArrayList<>();

    static S30208Leshchenko frame;
    static AsideView asideView;
    static MainView mainView;
    static SearchBarView searchBarView;
    static WrapperView wrapperView;
    static WrapperNavbarView wrapperNavbarView;
    static PhotoGridView photoGridView;

    static String appFontName = "Calibri";

    static PhotoCollection createCollection(String name) {
        PhotoCollection collection = new PhotoCollection(name);
        State.collections.add(collection);
        return collection;
    }

    static ArrayList<PhotoCollection> getCollections() {
        return State.collections;
    }

    static LocalDate getActiveCollectionCreationDate() {
        if(State.activeCollection != null) {
            return State.activeCollection.createdAt.toLocalDateTime().toLocalDate();
        }
        return null;
    }

    static void removeCollection(String collectionId) {
        for (int i = 0; i < State.collections.size(); i++) {
            if(State.collections.get(i).id.equals(collectionId)) {
                State.removePhotosFromCollection(collectionId);
                State.collections.remove(i);
                break;
            }
        }
    }

    static PhotoCollection getActiveCollection() {
        return State.activeCollection;
    }

    static void setActiveCollection(PhotoCollection collection) {
        State.activeCollection = collection;
    }

    static ArrayList<Photo> getPhotos() {
        return State.photos;
    }

    static void removePhotosFromCollection(String collectionId) {
        State.photos = State.photos.stream()
                .filter(photo -> !photo.collectionIds.contains(collectionId))
                .collect(toCollection(ArrayList::new));
    }

    static ArrayList<Photo> getPhotos(String collectionId) {
        return State.photos.stream()
                .filter(photo -> photo.collectionIds.contains(collectionId))
                .collect(toCollection(ArrayList::new));
    }

    static Photo addPhoto(String id, File file) {
        try {
            BufferedImage bufferedPhoto = ImageIO.read(file);
            Dimension size = new Dimension(bufferedPhoto.getWidth(), bufferedPhoto.getHeight());
            Photo photo = new Photo(id, size, FileManager.getFileExtension(file.getName()));
            State.photos.add(photo);
            return photo;
        } catch (IOException error) {
            JOptionPane.showMessageDialog(State.frame,"Something went wrong");
        }
        return null;
    }
}
