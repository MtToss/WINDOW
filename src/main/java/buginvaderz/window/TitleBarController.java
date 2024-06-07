package buginvaderz.window;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class TitleBarController {

    BorderPane mainApplicationPane;

    Pane root = new Pane();

    @FXML
    private AnchorPane mainPaneSpuuchify;

    @FXML
    private BorderPane titBar = new BorderPane();

    @FXML
    private VBox spotifyContainer = new VBox();

    @FXML
    private Pane ide;

    @FXML
    Label label = new Label();

    private boolean isMaximized = true;


    @FXML
    public void handleMinimizeButton() {
        mainApplicationPane.setVisible(false);
    }

    @FXML
    public void handleCloseButton() {
        mainApplicationPane.setVisible(false);
    }

    @FXML
    public void initialize() {
        root.widthProperty().addListener((obs, oldVal, newVal) -> isMax());
        root.heightProperty().addListener((obs, oldVal, newVal) -> isMax());

        RowMusicTemplate music1 = new RowMusicTemplate("https://i.kym-cdn.com/photos/images/newsfeed/001/065/439/e9e.jpegg", "Backburner", "Niki", "OCEANS", "1:00" , "audioIncap");

        spotifyContainer.getChildren().add(music1);

    }

    @FXML
    public void isMax() {

            if (isMaximized) {
                mainPaneSpuuchify.setPrefSize(root.getWidth()-15, root.getHeight()-26);

                ide.setPrefSize(root.getWidth() - 15, root.getHeight() - 26);

                titBar.setPrefSize(root.getWidth() - 15, 25);

                mainApplicationPane.setLayoutX(1); mainApplicationPane.setLayoutY(1);
                isMaximized = false;

                for (Node child : mainPaneSpuuchify.getChildren()) {
                    if (child instanceof Pane) {
                        Pane pane = (Pane) child;
                        pane.prefWidthProperty().bind(mainPaneSpuuchify.widthProperty());
                        pane.prefHeightProperty().bind(mainPaneSpuuchify.heightProperty());
                    }
                }

            }
            else {
                mainPaneSpuuchify.setPrefSize(root.getWidth() - 200, root.getHeight() - 200);

                ide.setPrefSize(root.getWidth() - 200, root.getHeight() - 200);
                titBar.setPrefSize(root.getWidth() - 200, 25);

                mainApplicationPane.setLayoutX(35);
                mainApplicationPane.setLayoutY(35);
                isMaximized = true;

                for (Node child : mainPaneSpuuchify.getChildren()) {
                    if (child instanceof Pane) {
                        Pane pane = (Pane) child;
                        pane.prefWidthProperty().bind(mainPaneSpuuchify.widthProperty());
                        pane.prefHeightProperty().bind(mainPaneSpuuchify.heightProperty().subtract(50)); // Adjust height according to your needs
                    }
                }
            }

    }

    public void setIdePane(AnchorPane bodyRoot) {
        this.ide = bodyRoot;
        this.mainPaneSpuuchify = bodyRoot;
    }


    public void setRoot(Pane root) {
        this.root = root;
    }

    public void setMainApplicationPane(BorderPane applicationPane) {
        this.mainApplicationPane = applicationPane;
    }
}