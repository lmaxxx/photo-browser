import javax.swing.*;
import java.awt.*;

public class PhotoListView extends JPanel {
    PhotoListNavbarView photoListNavbarView;

    PhotoListView() {
        State.photoListView = this;

        setLayout(new BorderLayout());

        initComponents();
        render();
    }

    void initComponents() {
        this.photoListNavbarView = new PhotoListNavbarView();
        new PhotoListNavbarController(this.photoListNavbarView);
    }

    void render() {
        add(this.photoListNavbarView, BorderLayout.NORTH);
    }
}
