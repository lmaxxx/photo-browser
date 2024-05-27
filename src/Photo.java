import java.awt.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Photo implements Serializable {
    String id;
    String title;
    String description;
    Dimension size;
    Timestamp createdAt;
    String extension;
    ArrayList<String> collectionIds = new ArrayList<>();
    ArrayList<String> tags = new ArrayList<>();

    public Photo(String id, Dimension size, String extension) {
        this.id = id;
        this.size = size;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.extension = extension;
        if(State.getActiveCollection() != null) {
            this.collectionIds.add(State.getActiveCollection().id);
        }
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", size=" + size +
                ", createdAt=" + createdAt +
                ", extension='" + extension + '\'' +
                ", collectionIds=" + collectionIds +
                '}';
    }
}

