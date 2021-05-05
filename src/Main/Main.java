package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;

/** This class creates a Scheduling Application.*/
public class Main extends Application {

    /** This is the start method. This method loads the first screen when the program is run.
     * @param primaryStage JavaFx stage*/
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/ViewController/Login.fxml"));
        primaryStage.setTitle("Scheduling Application");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    /** This is the main method. This is the first method that is called when the java program is run.
     * @param args String array of arguments*/
    public static void main(String[] args) {

        //Test French as default language
        //Locale.setDefault(new Locale("fr"));

        launch(args);
    }
}
