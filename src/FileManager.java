import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.UUID;

public class FileManager {
    static String uploadFile(File file) {
        FileOutputStream out = null;
        FileInputStream in = null;
        int cursor;
        String uuid = UUID.randomUUID().toString();

        try{
            in = new FileInputStream(file);
            out = new FileOutputStream("photos/" + uuid + "." + FileManager.getFileExtension(file.getName()));
            while((cursor = in.read())!=-1){
                out.write(cursor);
            }

            in.close();
            out.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(Views.frame,"Something went wrong");
            e.printStackTrace();
        }

        return uuid;
    }

    static void deleteFile(File file) {
        file.delete();
    }

    static String getFileExtension(String fileName) {
        int index = fileName.lastIndexOf('.');
        if(index > 0) {
            return fileName.substring(index + 1);
        }
        return null;
    }

    static void saveObjectsToFile(ArrayList<? extends Serializable> objects, String fileName) {
        try {
            FileOutputStream FOP = new FileOutputStream(fileName);
            ObjectOutputStream OOS = new ObjectOutputStream(FOP);
            OOS.writeObject(objects);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static ArrayList<PhotoCollection> readCollections() {
        ArrayList<PhotoCollection> collections = null;

        if(!new File("collections.ser").exists()) {
            return new ArrayList<>();
        }

        try (
                FileInputStream fileIn = new FileInputStream("collections.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn)
        ) {
            collections = (ArrayList<PhotoCollection>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return collections;
    }

    static ArrayList<Photo> readPhotos() {
        ArrayList<Photo> photos = null;

        if(!new File("photos.ser").exists()) {
            return new ArrayList<>();
        }

        try (
                FileInputStream fileIn = new FileInputStream("photos.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn)
        ) {
            photos = (ArrayList<Photo>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return photos;
    }
}