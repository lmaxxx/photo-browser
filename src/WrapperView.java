import javax.swing.*;
import java.awt.*;

public class WrapperView extends JPanel {
    WrapperNavbarView wrapperNavbarView = new WrapperNavbarView();
    PhotoGridView photoGridView = new PhotoGridView();

    WrapperView() {
        Views.wrapperView = this;

        setLayout(new BorderLayout());

        initComponents();
        render();
    }

    void initComponents() {
        Controllers.wrapperNavbarController = new WrapperNavbarController(this.wrapperNavbarView);
    }

    void render() {
        add(this.wrapperNavbarView, BorderLayout.NORTH);
        add(this.photoGridView, BorderLayout.CENTER);
    }
}
