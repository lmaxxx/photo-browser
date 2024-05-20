import javax.swing.*;
import java.awt.*;

public class WrapperView extends JPanel {
    WrapperNavbarView wrapperNavbarView;
    PhotoGridView photoGridView;

    WrapperView() {
        Views.wrapperView = this;

        setLayout(new BorderLayout());

        initComponents();
        render();
    }

    void initComponents() {
        this.wrapperNavbarView = new WrapperNavbarView();
        this.photoGridView = new PhotoGridView();
        Controllers.wrapperNavbarController = new WrapperNavbarController(this.wrapperNavbarView);
    }

    void render() {
        add(this.wrapperNavbarView, BorderLayout.NORTH);
        add(this.photoGridView, BorderLayout.CENTER);
    }
}
