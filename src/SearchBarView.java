import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SearchBarView extends JPanel {
    JLabel searchByLabel = new JLabel("Search by: ");
    JComboBox dropDownMenu = new JComboBox(new String[]{"title", "description", "tags", "date"});
    JButton searchButton = new JButton("Search");
    JButton cancelButton = new JButton("Cancel");
    JTextField input = new JTextField();


    SearchBarView() {
        Views.searchBarView = this;
        setPreferredSize(new Dimension(1000, 50));
        Border rightSideBorder = BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 204, 204));
        setBorder(rightSideBorder);

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        initComponents();
        render();
    }

    void initComponents() {
        Controllers.searchBarController = new SearchBarController(this);
        this.searchByLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
    }

    void updateSearchBar() {
        if (State.showFilteredPhotos) {
            add(cancelButton);
        } else {
            remove(cancelButton);
        }

        revalidate();
        repaint();
    }

    void render() {
        add(this.searchByLabel);
        add(this.dropDownMenu);
        add(this.input);
        add(this.searchButton);
    }

}
