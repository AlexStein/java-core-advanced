package javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Chat extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("chat.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        primaryStage.setTitle("Chat");
        primaryStage.setScene(new Scene(root, 320, 800));
        primaryStage.show();
    }
}
