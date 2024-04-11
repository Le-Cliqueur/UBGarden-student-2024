package fr.ubx.poo.ugarden;

import fr.ubx.poo.ugarden.view.GameLauncherView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        GameLauncherView launcher = new GameLauncherView(stage);
        Scene scene = new Scene(launcher);
        stage.setTitle("UBGarden 2024");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }
}