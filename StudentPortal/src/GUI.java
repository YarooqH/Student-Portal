package StudentPortal.src;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class GUI extends Application {
    @Override
    public void start(Stage parentStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("./fxml/Login-Signup.fxml"));
        parentStage.setTitle("UBIT Student Portal");

        parentStage.getIcons().add(new Image("/StudentPortal/src/icons/icon.png"));
        parentStage.maximizedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue)
                parentStage.setMaximized(false);
        });

        Scene scene = new Scene(root);

        scene.getStylesheets().add("/StudentPortal/src/css/styles.css");

        parentStage.setScene(scene);
        parentStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}