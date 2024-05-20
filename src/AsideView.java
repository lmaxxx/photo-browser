import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class AsideView extends JPanel {
    JLabel titleLabel;
    JButton creteCollectionButton;
    JButton allPhotosButton;
    JPanel collectionListPanel;
    ArrayList<JButton> collectionButtons = new ArrayList<>();

    AsideView() {
        Views.asideView = this;
        setPreferredSize(new Dimension(200, 700));
        setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, new Color(204,204,204)));

//        this.updateScrollCollectionPane();
        this.initComponents();
        this.render();

    }

    void initComponents() {
        this.titleLabel = new JLabel("Photo browser");
        this.creteCollectionButton = new JButton("Crete new collection");
        this.allPhotosButton = new JButton("All photos");
        this.collectionListPanel = new JPanel();

        this.titleLabel.setFont(new Font(State.appFontName, Font.PLAIN, 25));
        this.titleLabel.setBorder(new EmptyBorder(10,0,10,0));
        this.titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        this.creteCollectionButton.setAlignmentX(CENTER_ALIGNMENT);
        this.allPhotosButton.setAlignmentX(CENTER_ALIGNMENT);
        this.collectionListPanel.setLayout(new BoxLayout(collectionListPanel, BoxLayout.Y_AXIS));
    }

//    void createCollectionButton(PhotoCollection collection) {
//        JButton newCollectionButton = new JButton(collection.name);
//        newCollectionButton.setAlignmentX(CENTER_ALIGNMENT);
//        this.collectionButtons.add(newCollectionButton);
//    }
//
//    void removeCollectionButton(PhotoCollection collection) {
//
//    }

    void setCollectionButtons() {
        this.collectionButtons.clear();
        for(PhotoCollection collection : State.getCollections()) {
            JButton newCollectionButton = new JButton(collection.name);
            newCollectionButton.setAlignmentX(CENTER_ALIGNMENT);
            newCollectionButton.addActionListener(_ -> Controllers.asideController.setActiveCollection(collection));
            this.collectionButtons.add(newCollectionButton);
        }
    }

    void renderCollectionButtons() {
        this.collectionListPanel.removeAll();
        for(JButton button : this.collectionButtons) {
            this.collectionListPanel.add(button);
        }
        this.collectionListPanel.revalidate();
        this.collectionListPanel.repaint();
    }

    void render() {
        add(this.titleLabel);
        add(this.allPhotosButton);
        add(this.creteCollectionButton);

        JScrollPane scrollPane = new JScrollPane(this.collectionListPanel);
        scrollPane.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, new Color(204,204,204)));
        add(scrollPane);
    }
}
