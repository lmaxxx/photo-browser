import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toCollection;

final public class State {
    private static ArrayList<PhotoCollection> collections = new ArrayList<>();
    private static PhotoCollection activeCollection = null;

    private static ArrayList<Photo> photos = new ArrayList<>();
    private static ArrayList<Photo> filteredPhotos = new ArrayList<>();
    private static Photo activePhoto = null;
    static boolean showFilteredPhotos = false;

    private static final String[] tagList = {"Nature", "Art", "Landscape", "Travel", "Sport", "Food", "Tech", "Work", "Family", "Love", "Friends", "Hobby"};

    static String appFontName = "Calibri";

    static String[] getTagList() {
        return tagList;
    }

    static void addTagsToActivePhoto(int[] tagsIndices) {
        activePhoto.tags.clear();
        for (int tagIndex : tagsIndices) {
            activePhoto.tags.add(tagList[tagIndex]);
        }
    }

    static PhotoCollection createCollection(String name) {
        PhotoCollection collection = new PhotoCollection(name);
        collections.add(collection);
        FileManager.saveObjectsToFile(collections, "collections.ser");
        return collection;
    }

    static void setCollections(ArrayList<PhotoCollection> collections) {
        State.collections = collections;
    }

    static void setPhotos(ArrayList<Photo> photos) {
        State.photos = photos;
    }

    static ArrayList<PhotoCollection> getCollections() {
        return collections;
    }

    static void removeCollection(String collectionId) {
        photos = photos.stream().map(photo -> {
            if(photo.collectionIds.contains(collectionId)) {
                photo.collectionIds.remove(photo.collectionIds.indexOf(collectionId));
            }
            return photo;
        }).collect(toCollection(ArrayList::new));

        for (int i = 0; i < collections.size(); i++) {
            if(collections.get(i).id.equals(collectionId)) {
                removePhotosFromCollection(collectionId);
                collections.remove(i);
                break;
            }
        }
        FileManager.saveObjectsToFile(collections, "collections.ser");
    }

    static void addPhotoToCollections(int[] collectionIndices) {
        activePhoto.collectionIds.clear();
        for (int collectionIndex : collectionIndices) {
            PhotoCollection collection = collections.get(collectionIndex);
            activePhoto.collectionIds.add(collection.id);
        }
    }

    static PhotoCollection getActiveCollection() {
        return activeCollection;
    }

    static void setActiveCollection(PhotoCollection collection) {
        activeCollection = collection;
    }

    static ArrayList<Photo> getPhotos() {
        return showFilteredPhotos ? filteredPhotos : photos;
    }

    static ArrayList<Photo> getPhotos(String collectionId) {
        ArrayList<Photo> possiblePhotos = showFilteredPhotos ? filteredPhotos : photos;
        return possiblePhotos.stream()
                        .filter(photo -> photo.collectionIds.contains(collectionId))
                        .collect(toCollection(ArrayList::new));
    }

    static void removePhotosFromCollection(String collectionId) {
        photos = photos.stream()
                .filter(photo -> !photo.collectionIds.contains(collectionId))
                .collect(toCollection(ArrayList::new));
    }

    static void removePhoto() {
        photos = photos.stream()
                .filter(photo -> !photo.id.equals(activePhoto.id))
                .collect(toCollection(ArrayList::new));

        File file = new File("photos/" + activePhoto.id + "." + activePhoto.extension);
        FileManager.deleteFile(file);
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

    static Photo getActivePhoto() {
        return activePhoto;
    }

    static void setActivePhoto(Photo photo) {
        activePhoto = photo;
    }

    static void filterPhotos(String filterOption, String query) {
        switch (filterOption) {
            case "title": {
                filteredPhotos = photos
                        .stream()
                        .filter(photo -> {
                            String[] words = query.trim().split("\\W+");
                            return Utils.textContains(photo.title, words);
                        })
                        .collect(toCollection(ArrayList::new));
                break;
            }
            case "description": {
                filteredPhotos = photos
                        .stream()
                        .filter(photo -> {
                            String[] words = query.trim().split("\\W+");
                            return Utils.textContains(photo.description, words);
                        })
                        .collect(toCollection(ArrayList::new));
                break;
            }
            case "tags": {
                String[] tags = query.trim().split(", ");
                filteredPhotos = photos
                        .stream()
                        .filter(photo -> photo.tags.containsAll(List.of(tags)))
                        .collect(toCollection(ArrayList::new));
                break;
            }
            case "date": {
                String dateString = query.trim();
                filteredPhotos = photos.stream()
                        .filter(photo -> Utils.formatDate(photo.createdAt).equals(dateString))
                        .collect(toCollection(ArrayList::new));
            }
        }
    }

    static void clearFilteredPhotos() {
        filteredPhotos.clear();
    }
}
