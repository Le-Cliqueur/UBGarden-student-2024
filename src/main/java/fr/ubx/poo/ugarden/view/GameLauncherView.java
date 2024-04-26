package fr.ubx.poo.ugarden.view;

import fr.ubx.poo.ugarden.engine.GameEngine;
import fr.ubx.poo.ugarden.game.Configuration;
import fr.ubx.poo.ugarden.game.Game;
import fr.ubx.poo.ugarden.game.Position;
import fr.ubx.poo.ugarden.game.World;
import fr.ubx.poo.ugarden.launcher.GameLauncher;
import fr.ubx.poo.ugarden.launcher.MapEntity;
import fr.ubx.poo.ugarden.launcher.MapLevel;
import fr.ubx.poo.ugarden.launcher.MapLevelDefault;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class GameLauncherView extends BorderPane {
    private final FileChooser fileChooser = new FileChooser();

    public GameLauncherView(Stage stage) {
        // Create menu
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        MenuItem loadItem = new MenuItem("Load from file ...");
        MenuItem defaultItem = new MenuItem("Load default configuration");
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
        menuFile.getItems().addAll(
                loadItem, defaultItem, new SeparatorMenuItem(),
                exitItem);

        menuBar.getMenus().addAll(menuFile);
        this.setTop(menuBar);

        Text text = new Text("UBGarden 2024");
        text.getStyleClass().add("message");
        VBox scene = new VBox();
        scene.getChildren().add(text);
        scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
        scene.getStyleClass().add("message");
        this.setCenter(scene);

        // Load from file
        loadItem.setOnAction(e -> {
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                // TODO
                System.err.println("[TODO] Not implemented");

                Properties properties = new Properties();
                FileInputStream in = null;
                try {
                    in = new FileInputStream(file);
                    properties.load(in);
                    MapLevel[] levelTab = new MapLevel[Integer.parseInt(properties.getProperty("levels"))];

                    for (int i = 1; i <= Integer.parseInt(properties.getProperty("levels")); i++) {
                        String levelEntityCode = properties.getProperty("level" + i);

                        System.out.println("level" + i);

                        int width = 0;
                        int height = 0;

                        for (int j = 0; j < levelEntityCode.length()-1; j++) {
                            if (levelEntityCode.charAt(j) != 'x') {
                                height++;
                            } else {
                                width++;
                                height = 0;
                            }
                        }

                        MapLevel mapLevel = new MapLevel(width+1, height);

                        int onTheLine = 0;
                        int onTheCol = 0;

                        for (int j = 0; j < levelEntityCode.length(); j++) {
                            if (levelEntityCode.charAt(j) != 'x') {
                                mapLevel.set(onTheLine, onTheCol, MapEntity.fromCode(levelEntityCode.charAt(j)));
                                onTheCol++;

                            } else {
                                onTheLine++;
                                onTheCol = 0;
                            }
                        }

                        levelTab[i-1] = mapLevel;

                    }

                    GameLauncher gm = new GameLauncher(1, levelTab);
                    Game game = gm.load();
                    GameEngine engine = new GameEngine(game, stage);
                    engine.start();
                    
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        defaultItem.setOnAction(e -> {
            Game game = GameLauncher.getInstance().load();
            GameEngine engine = new GameEngine(game, stage);
            engine.start();
        });

        // Exit
        exitItem.setOnAction(e -> System.exit(0));

    }


}
