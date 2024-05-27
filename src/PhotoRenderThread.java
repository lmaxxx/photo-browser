import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PhotoRenderThread extends Thread{
    JPanel grid;
    ArrayList<Photo> photos;

    PhotoRenderThread(JPanel grid, ArrayList<Photo> photos) {
        this.grid = grid;
        this.photos = photos;
    }

    @Override
    public void run() {
        Dimension gridCellSize = new Dimension(315, 220);
        this.grid.removeAll();
        int rows = (int)Math.ceil(this.photos.size() / 3.0);
        for (int i = 0; i < rows; i++) {
            JPanel row = new JPanel( new GridLayout(1, 3, 10, 0));
            row.setMaximumSize(new Dimension(980, 220));
            if(i != rows - 1) {
                row.setBorder(new EmptyBorder(0,0,10,0));
            }

            for (int j = 0; j < 3; j++) {
                // ternary operator prevents errors in console
                Photo photo = this.photos.get(i * 3 + j) == null ? null : this.photos.get(i * 3 + j);
                JLabel imageLabel = new JLabel();
                imageLabel.setPreferredSize(gridCellSize);
                imageLabel.setHorizontalAlignment(JLabel.CENTER);

                if(photo != null) {
                    ImageIcon originalIcon = new ImageIcon(String.format("photos/%s.%s", photo.id, photo.extension));
                    Dimension scaledDimension = Utils.getScaledDimension(new Dimension(photo.size.width, photo.size.height), gridCellSize);
                    Image image = originalIcon.getImage().getScaledInstance(scaledDimension.width, scaledDimension.height, Image.SCALE_SMOOTH);
                    ImageIcon imageIcon = new ImageIcon(image);
                    imageLabel.setIcon(imageIcon);
                    imageLabel.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            Controllers.photoEditorController.setActivePhoto(photo);
                        }
                    });
                    imageLabel.setOpaque(true);
                    imageLabel.setBackground(new Color(232, 233, 232));
                }

                row.add(imageLabel);
            }

            this.grid.add(row);
            this.grid.revalidate();
            this.grid.repaint();
        }
    }
}
