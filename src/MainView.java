import javax.swing.*;
import java.awt.*;

public class MainView extends JPanel {
    SearchBarView searchBarView;
    WrapperView wrapperView;
    PhotoEditorView photoEditorView;

    MainView() {
        setPreferredSize(new Dimension(1000, 700));
        setLayout(new BorderLayout());

        initComponents();
        render();
    }

    void initComponents() {
        this.searchBarView = new SearchBarView();
        this.wrapperView = new WrapperView();
        this.photoEditorView = new PhotoEditorView();
        Views.mainView = this;
    }

    void render() {
        removeAll();
        if (State.getActivePhoto() == null) {
            add(this.wrapperView, BorderLayout.CENTER);
            add(this.searchBarView, BorderLayout.NORTH);
        } else {
            add(this.photoEditorView, BorderLayout.CENTER);
        }

        revalidate();
        repaint();
    }

}
