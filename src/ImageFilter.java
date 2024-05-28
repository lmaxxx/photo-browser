import javax.swing.filechooser.FileFilter;
import java.io.File;

class ImageFilter extends FileFilter {
    public final static String JPEG = "jpeg";
    public final static String JPG = "jpg";
    public final static String GIF = "gif";
    public final static String PNG = "png";
    public final static String WEBP = "webp";

    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = getExtension(f);
        if (extension != null) {
            return extension.equals(WEBP) ||
                    extension.equals(GIF) ||
                    extension.equals(JPEG) ||
                    extension.equals(JPG) ||
                    extension.equals(PNG);
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "Image Only";
    }

    String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }
}