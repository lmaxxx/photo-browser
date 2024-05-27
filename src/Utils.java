import java.awt.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    private static final String datePattern = "dd/MM/yyyy";
    private static final String dateTimePattern = "dd/MM/yyyy HH:mm:ss";
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

    static boolean textContains(String text, String[] words) {
        StringBuilder regexBuilder = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            regexBuilder.append("\\b").append(words[i]).append("\\b");
            if (i < words.length - 1) {
                regexBuilder.append("|");
            }
        }
        String regex = regexBuilder.toString();

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text == null ? "" : text);

        return matcher.find();
    }
}
