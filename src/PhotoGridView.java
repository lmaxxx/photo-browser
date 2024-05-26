import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class PhotoGridView extends JPanel {
    JPanel grid;
    ArrayList<Photo> photos = new ArrayList<>();

    PhotoGridView() {
        Views.photoGridView = this;
        setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, new Color(204,204,204)));

        initComponents();
        render();
    }

    void setPhotos() {
        this.photos.clear();
        ArrayList<Photo> currentCollectionPhotos = State.getActiveCollection() != null ? State.getPhotos(State.getActiveCollection().id) : State.getPhotos();
        this.photos.addAll(currentCollectionPhotos);

        for (int i = 0; 9 > this.photos.size(); i++) {
            this.photos.add(null);
        }

        for (int i = 0; i < this.photos.size() % 3; i++) {
            this.photos.add(null);
        }
    }

    void renderPhotos() {
        PhotoRenderThread photoRenderThread = new PhotoRenderThread(this.grid, this.photos);
        photoRenderThread.start();
    }

    void initComponents() {
        this.grid = new JPanel();
        this.grid.setLayout(new BoxLayout(this.grid, BoxLayout.Y_AXIS));
        Controllers.photoGridController = new PhotoGridController(this);
    }

    void render() {
        JScrollPane scrollPane = new JScrollPane(grid);
        scrollPane.setBorder(new EmptyBorder(0,0,0,0));
        add(scrollPane);
    }
}
