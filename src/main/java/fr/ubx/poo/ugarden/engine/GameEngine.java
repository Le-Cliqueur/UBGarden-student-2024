    /*
     * Copyright (c) 2020. Laurent Réveillère
     */

    package fr.ubx.poo.ugarden.engine;

    import fr.ubx.poo.ugarden.game.Direction;
    import fr.ubx.poo.ugarden.game.Game;
    import fr.ubx.poo.ugarden.go.personage.Gardener;
    import fr.ubx.poo.ugarden.go.personage.Hedgehog;
    import fr.ubx.poo.ugarden.launcher.MapEntity;
    import fr.ubx.poo.ugarden.view.*;
    import javafx.animation.AnimationTimer;
    import javafx.application.Platform;
    import javafx.scene.Group;
    import javafx.scene.Scene;
    import javafx.scene.layout.Pane;
    import javafx.scene.layout.StackPane;
    import javafx.scene.paint.Color;
    import javafx.scene.text.Font;
    import javafx.scene.text.Text;
    import javafx.scene.text.TextAlignment;
    import javafx.stage.Stage;

    import java.util.*;


    public final class GameEngine {

        private static AnimationTimer gameLoop;
        private final Game game;
        private final Gardener gardener;
        private final List<Sprite> sprites = new LinkedList<>();
        private final Set<Sprite> cleanUpSprites = new HashSet<>();
        private final Stage stage;
        private final Pane layer = new Pane();
        private StatusBar statusBar;
        private Input input;

        public GameEngine(Game game, final Stage stage) {
            this.stage = stage;
            this.game = game;
            this.gardener = game.getGardener();
            initialize();
            buildAndSetGameLoop();
        }

        private void initialize() {
            Group root = new Group();

            int height = game.world().getGrid().height();
            int width = game.world().getGrid().width();
            int sceneWidth = width * ImageResource.size;
            int sceneHeight = height * ImageResource.size;
            Scene scene = new Scene(root, sceneWidth, sceneHeight + StatusBar.height);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/application.css")).toExternalForm());

            stage.setScene(scene);
            stage.setResizable(false);
            stage.sizeToScene();
            stage.hide();
            stage.show();

            input = new Input(scene);
            root.getChildren().add(layer);
            statusBar = new StatusBar(root, sceneWidth, sceneHeight);

            // Create sprites
            int currentLevel = game.world().currentLevel();

            for (var decor : game.world().getGrid().values()) {
                sprites.add(SpriteFactory.create(layer, decor));
                decor.setModified(true);
                var bonus = decor.getBonus();
                if (bonus != null) {
                    sprites.add(SpriteFactory.create(layer, bonus));
                    bonus.setModified(true);
                }
            }

            sprites.add(new SpriteGardener(layer, gardener));
        }

        void buildAndSetGameLoop() {
            gameLoop = new AnimationTimer() {
                public void handle(long now) {
                    checkLevel();

                    // Check keyboard actions
                    processInput();

                    // Do actions
                    update(now);
                    checkCollision();

                    // Graphic update
                    cleanupSprites();
                    render();
                    statusBar.update(game);
                }
            };
        }


        private void checkLevel() {
            if (game.isSwitchLevelRequested()) {
                // Find the new level to switch to
                // clear all sprites
                // change the current level
                // Find the position of the door to reach
                // Set the position of the gardener
                // stage.close();
                // initialize();
            }
        }

        private void checkCollision() {
            // Check a collision between a hornet and the gardener
        }

        private void processInput() {
            if (input.isExit()) {
                gameLoop.stop();
                Platform.exit();
                System.exit(0);
            } else if (input.isMoveDown()) {
                gardener.requestMove(Direction.DOWN);
            } else if (input.isMoveLeft()) {
                gardener.requestMove(Direction.LEFT);
            } else if (input.isMoveRight()) {
                gardener.requestMove(Direction.RIGHT);
            } else if (input.isMoveUp()) {
                gardener.requestMove(Direction.UP);
            }
            input.clear();
        }

        private void showMessage(String msg, Color color) {
            Text waitingForKey = new Text(msg);
            waitingForKey.setTextAlignment(TextAlignment.CENTER);
            waitingForKey.setFont(new Font(60));
            waitingForKey.setFill(color);
            StackPane root = new StackPane();
            root.getChildren().add(waitingForKey);
            Scene scene = new Scene(root, 400, 200, Color.WHITE);
            stage.setScene(scene);
            input = new Input(scene);
            stage.show();
            new AnimationTimer() {
                public void handle(long now) {
                    processInput();
                }
            }.start();
        }

        private void update(long now) {
            game.world().getGrid().values().forEach(decor -> decor.update(now));

            gardener.update(now);

            this.game.getTimer().update(now);
            if (this.game.getTimer().getRemaining() <= 0 && gardener.getEnergy() < 100) {
                gardener.hurt(-1);
                this.game.getTimer().stop();
                this.game.getTimer().start();
            }

            System.out.println(this.game.getTimer().getRemaining());

            if (gardener.getEnergy() <= 0) {
                gameLoop.stop();
                showMessage("Perdu!", Color.RED);
            }
            if (gardener.game.world().getGrid().get(gardener.getPosition()).getClass().equals(Hedgehog.class)) {
                gameLoop.stop();
                showMessage("Victoire!", Color.GREEN);
            }
        }

        public void cleanupSprites() {
            sprites.forEach(sprite -> {
                if (sprite.getGameObject().isDeleted()) {
                    cleanUpSprites.add(sprite);
                }
            });
            cleanUpSprites.forEach(Sprite::remove);
            sprites.removeAll(cleanUpSprites);
            cleanUpSprites.clear();
        }

        private void render() {
            sprites.forEach(Sprite::render);
        }

        public void start() {
            gameLoop.start();
        }
    }