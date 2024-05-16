import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;

public class Utils {
    static String encodeToBase64(BufferedImage image, String formatName) {
        String base64String = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, formatName, bos);
            byte[] imageBytes = bos.toByteArray();
            base64String = Base64.getEncoder().encodeToString(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return base64String;
    }

    static String getFileExtension(String fileName) {
        int index = fileName.lastIndexOf('.');
        if(index > 0) {
            return fileName.substring(index + 1);
        }
        return null;
    }
}
