import javax.swing.*;
import java.awt.*;

public class MainView extends JPanel {
    SearchBarView searchBarView;
    PhotoListView photoListView;

    MainView() {
        setPreferredSize(new Dimension(1000, 700));
        setLayout(new BorderLayout());

        initComponents();
        render();
    }

    void initComponents() {
        this.searchBarView = new SearchBarView();
        this.photoListView = new PhotoListView();
    }

    void render() {
        add(this.searchBarView, BorderLayout.NORTH);
        add(this.photoListView, BorderLayout.CENTER);
    }

}
