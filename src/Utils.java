import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;

public class Utils {
    static String getFileExtension(String fileName) {
        int index = fileName.lastIndexOf('.');
        if(index > 0) {
            return fileName.substring(index + 1);
        }
        return null;
    }
}
