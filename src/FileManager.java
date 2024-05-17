import javax.swing.*;
import java.io.*;
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
            JOptionPane.showMessageDialog(State.frame,"Something went wrong");
            e.printStackTrace();
        }

        return uuid;
    }

    static String getFileExtension(String fileName) {
        int index = fileName.lastIndexOf('.');
        if(index > 0) {
            return fileName.substring(index + 1);
        }
        return null;
    }
}