import javax.swing.*;
import java.io.*;
import java.util.UUID;

public class PhotoListNavbarController {
    PhotoListNavbarView photoListNavbarView;

    PhotoListNavbarController(PhotoListNavbarView photoListNavbarView) {
        this.photoListNavbarView = photoListNavbarView;

        setActions();
    }

    void setActions() {
        this.photoListNavbarView.deleteCollectionButton.addActionListener(_ -> this.deleteCollection());
        this.photoListNavbarView.importPhotosButton.addActionListener(_ -> this.choosePhotos());
    }

    void deleteCollection() {
        int choice = JOptionPane.showConfirmDialog(null,
                "Do you want to delete '" + State.getActiveCollection().name + "' collection?",
                "Delete Collection",
                JOptionPane.YES_NO_OPTION
        );

        if(choice != 0) return;

        State.removeCollection(State.getActiveCollection().id);
        State.setActiveCollection(null);
        State.asideView.setCollectionButtons();
        State.asideView.renderCollectionButtons();
        this.photoListNavbarView.renderActiveCollectionDetails();
    }

    void choosePhotos() {
        int response = this.photoListNavbarView.fileChooser.showSaveDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                File file = this.photoListNavbarView.fileChooser.getSelectedFile();

                FileOutputStream out = null;
                FileInputStream in = null;
                int cursor;

                String uuid = UUID.randomUUID().toString();

                try{
                    in = new FileInputStream(file);
                    out = new FileOutputStream("photos/" + uuid + Utils.getFileExtension(file.getName()));
                    while((cursor = in.read())!=-1){
                        out.write(cursor);
                    }

                    in.close();
                    out.close();


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Wrong file");
            }
    }
}
