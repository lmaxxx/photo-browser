import javax.swing.*;
import java.awt.*;

/*
PROJECT STRUCTURE
S30208Leshchenko
├── AsideView
└── MainView
    ├── SearchBarView
    ├── WrapperView
    │   ├── WrapperNavbarView
    │   └── PhotoGridView
    │       └── PhotoDataView
    └── PhotoEditorView

Each View Components that performs special operations has its own Controller.
There is a class State that stores all important data for the whole application.
Classes Views and Controllers store components respectively, in order to share them
among each other.
*/

//Main frame
public class S30208Leshchenko extends JFrame {
    AsideView asideView = new AsideView();
    AsideController asideController = new AsideController(asideView);
    MainView mainView = new MainView();

    S30208Leshchenko() {
        super("Photo Browser");

        Views.frame = this;
        Controllers.asideController = this.asideController;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        setSize(new Dimension(1200, 700));

        add(asideView, BorderLayout.WEST);
        add(mainView, BorderLayout.EAST);

        Controllers.asideController.loadCollections();
        Controllers.photoGridController.loadPhotos();

        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(S30208Leshchenko::new);
    }
}