import java.sql.Timestamp;
import java.util.ArrayList;

public class Photo {
    String id;
    String title;
    String height;
    String width;
    Timestamp createdAt;
    String extension;

    public Photo(String id, String height, String width, String extension) {
        this.id = id;
        this.height = height;
        this.width = width;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.extension = extension;
    }
}

