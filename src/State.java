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
    private static Photo activePhoto = null;

    static String appFontName = "Calibri";

    static PhotoCollection createCollection(String name) {
        PhotoCollection collection = new PhotoCollection(name);
        collections.add(collection);
        return collection;
    }

    static ArrayList<PhotoCollection> getCollections() {
        return collections;
    }

    static LocalDate getActiveCollectionCreationDate() {
        if(activeCollection != null) {
            return activeCollection.createdAt.toLocalDateTime().toLocalDate();
        }
        return null;
    }

    static void removeCollection(String collectionId) {
        for (int i = 0; i < collections.size(); i++) {
            if(collections.get(i).id.equals(collectionId)) {
                removePhotosFromCollection(collectionId);
                collections.remove(i);
                break;
            }
        }
    }

    static PhotoCollection getActiveCollection() {
        return activeCollection;
    }

    static void setActiveCollection(PhotoCollection collection) {
        activeCollection = collection;
    }

    static ArrayList<Photo> getPhotos() {
        return photos;
    }

    static void removePhotosFromCollection(String collectionId) {
        photos = photos.stream()
                .filter(photo -> !photo.collectionIds.contains(collectionId))
                .collect(toCollection(ArrayList::new));
    }

    static ArrayList<Photo> getPhotos(String collectionId) {
        return photos.stream()
                .filter(photo -> photo.collectionIds.contains(collectionId))
                .collect(toCollection(ArrayList::new));
    }

    static void addPhoto(String id, File file) {
        try {
            BufferedImage bufferedPhoto = ImageIO.read(file);
            Dimension size = new Dimension(bufferedPhoto.getWidth(), bufferedPhoto.getHeight());
            Photo photo = new Photo(id, size, FileManager.getFileExtension(file.getName()));
            photos.add(photo);
        } catch (IOException error) {
            JOptionPane.showMessageDialog(Views.frame,"Something went wrong");
        }
    }

    public static Photo getActivePhoto() {
        return activePhoto;
    }

    public static void setActivePhoto(Photo photo) {
        activePhoto = photo;
    }
}
