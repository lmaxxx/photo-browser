import java.sql.Timestamp;
import java.util.UUID;

public class PhotoCollection {
    String name;
    String id;

    @Override
    public String toString() {
        return "PhotoCollection{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    Timestamp createdAt;

    PhotoCollection(String name) {
        this.name = name;
        id = UUID.randomUUID().toString();
        createdAt = new Timestamp(System.currentTimeMillis());
    }
}
