import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

public class PhotoCollection implements Serializable {
    String name;
    String id;
    Timestamp createdAt;

    @Override
    public String toString() {
        return "PhotoCollection{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }



    PhotoCollection(String name) {
        this.name = name;
        this.id = UUID.randomUUID().toString();
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }
}
