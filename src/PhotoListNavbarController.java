import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

//        try {
//            if (response == JFileChooser.APPROVE_OPTION) {
//                File file = this.photoListNavbarView.fileChooser.getSelectedFile();
//                System.out.println(file.getName());
//                BufferedImage image = ImageIO.read(file);
//                String base64String = Utils.encodeToBase64(image, Utils.getFileExtension(file.getName()));
//                System.out.println("Base64 String: " + base64String);
//            } else {
//                System.out.println("Wrong file");
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

//        if (response == JFileChooser.APPROVE_OPTION) {
//            File fileToSave = this.photoListNavbarView.fileChooser.getSelectedFile();
//
//            // Ensure the file has the correct extension
//            if (!fileToSave.getName().toLowerCase().endsWith(".png")) {
//                fileToSave = new File(fileToSave.getAbsolutePath() + ".png");
//            }
//
//            image = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
//
//            // Write the image to the selected file
//            try {
//                ImageIO.write(image, Utils.getFileExtension(), fileToSave);
//                JOptionPane.showMessageDialog(this, "Image saved successfully!");
//            } catch (IOException ex) {
//                ex.printStackTrace();
//                JOptionPane.showMessageDialog(this, "An error occurred while saving the image.", "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        }
    }
}
