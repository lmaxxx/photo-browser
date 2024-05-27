import java.awt.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Utils {
    private static final String datePattern = "MM/dd/yyyy";
    private static final String dateTimePattern = "MM/dd/yyyy HH:mm:ss";
    private static final DateFormat dateFormat = new SimpleDateFormat(datePattern);
    private static final DateFormat dateTimeFormat = new SimpleDateFormat(dateTimePattern);

    static Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {
        int originalWidth = imgSize.width;
        int originalHeight = imgSize.height;
        int boundWidth = boundary.width;
        int boundHeight = boundary.height;
        int newWidth = originalWidth;
        int newHeight = originalHeight;

        if (originalWidth > boundWidth) {
            newWidth = boundWidth;
            newHeight = (newWidth * originalHeight) / originalWidth;
        }

        if (newHeight > boundHeight) {
            newHeight = boundHeight;
            newWidth = (newHeight * originalWidth) / originalHeight;
        }

        return new Dimension(newWidth, newHeight);
    }

    static String formatDate(Timestamp time) {
        return dateFormat.format(time);
    }

    static String formatDateTime(Timestamp time) {
        return dateTimeFormat.format(time);
    }
}
