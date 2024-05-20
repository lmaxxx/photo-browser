import javax.swing.*;
import java.awt.*;

public class MainView extends JPanel{
    SearchBarView searchBarView;
    WrapperView wrapperView;

    MainView() {
        setPreferredSize(new Dimension(1000, 700));
        setLayout(new BorderLayout());

        initComponents();
        render();
    }

    void initComponents() {
        this.searchBarView = new SearchBarView();
        this.wrapperView = new WrapperView();
    }

    void render() {
        add(this.searchBarView, BorderLayout.NORTH);
        add(this.wrapperView, BorderLayout.CENTER);
    }

}
