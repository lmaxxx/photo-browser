import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class PhotoGridView extends JPanel {
    JPanel grid;
    ArrayList<Photo> photos = new ArrayList<>();
    Dimension gridCellSize = new Dimension(315, 220);

    PhotoGridView() {
        Controllers.photoGridController = new PhotoGridController(this);
        State.photoGridView = this;

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
        grid.removeAll();
        int rows = (int)Math.ceil(this.photos.size() / 3.0);
        for (int i = 0; i < rows; i++) {
            JPanel row = new JPanel( new GridLayout(1, 3, 10, 0));
            if(i != rows - 1) {
                row.setBorder(new EmptyBorder(0,0,10,0));
            }

            for (int j = 0; j < 3; j++) {
                Photo photo = this.photos.get(i * 3 + j);
                JLabel imageLabel = new JLabel();
                imageLabel.setPreferredSize(this.gridCellSize);

                if(photo != null) {
                    ImageIcon originalIcon = new ImageIcon(String.format("photos/%s.%s", photo.id, photo.extension));
                    Dimension scaledDimension = Utils.getScaledDimension(new Dimension(photo.size.width, photo.size.height), this.gridCellSize);
                    Image image = originalIcon.getImage().getScaledInstance(scaledDimension.width, scaledDimension.height, Image.SCALE_SMOOTH);
                    ImageIcon imageIcon = new ImageIcon(image);
                    imageLabel.setIcon(imageIcon);
                }

                row.add(imageLabel);
            }

            grid.add(row);
            grid.revalidate();
            grid.repaint();
        }
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
