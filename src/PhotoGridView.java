import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Flow;

import static java.lang.Integer.MAX_VALUE;

public class PhotoGridView extends JPanel {
    JPanel grid;
    ArrayList<Photo> photos = new ArrayList<>();

    PhotoGridView() {
        Views.photoGridView = this;

//        setLayout(new FlowLayout());
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

    void renderEmptyLayout() {
        Dimension gridCellSize = new Dimension(315, 220);
        this.grid.removeAll();
        int rows = (int)Math.ceil(this.photos.size() / 3.0);
        for (int i = 0; i < 3; i++) {
            JPanel row = new JPanel( new GridLayout(1, 3, 10, 0));
            if(i == 2) {
                row.setBorder(new EmptyBorder(0,0,10,0));
            }

            for (int j = 0; j < 3; j++) {
                Photo photo = this.photos.get(i * 3 + j);
                JLabel imageLabel = new JLabel();
                imageLabel.setPreferredSize(gridCellSize);

//                row.setAlignmentY(Component.TOP_ALIGNMENT);
                row.add(imageLabel);
            }

            this.grid.add(row);
            this.grid.revalidate();
            this.grid.repaint();
        }
    }

    void initComponents() {
        this.grid = new JPanel();
//        this.grid.setMaximumSize(new Dimension(980, MAX_VALUE));
//        this.grid.setPreferredSize(new Dimension(980, 1000));
        this.grid.setLayout(new BoxLayout(this.grid, BoxLayout.Y_AXIS));
//        this.grid.setLayout(new FlowLayout());
        Controllers.photoGridController = new PhotoGridController(this);
    }

    void render() {
        JScrollPane scrollPane = new JScrollPane(grid);
        scrollPane.setBorder(new EmptyBorder(0,0,0,0));
        add(scrollPane);
//        add(grid);
    }
}
