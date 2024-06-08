package buginvaderz.window;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class TitleBarController {

    BorderPane mainApplicationPane;

    Pane root = new Pane();

    @FXML
    private BorderPane container;

    @FXML
    private BorderPane titBar = new BorderPane();

    @FXML
    private Pane mainPane;

    @FXML
    Label label = new Label();

    private Pane app;

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

    }

    @FXML
    public void isMax() {

            if (isMaximized) {
                System.out.println(app);
                app.setPrefSize(root.getWidth()-15, root.getHeight()-26);
                titBar.setPrefSize(root.getWidth() - 15, 25);
                mainApplicationPane.setLayoutX(1); mainApplicationPane.setLayoutY(1);
                isMaximized = false;

            }
            else {
                app.setPrefSize(root.getWidth()-200, root.getHeight()-200);
                titBar.setPrefSize(root.getWidth() - 200, 25);

                mainApplicationPane.setLayoutX(35); mainApplicationPane.setLayoutY(35);
                isMaximized = true;
            }

    }


    public void setMainPane(Pane app) {
        this.app = app;
    }


    public void setRoot(Pane root) {
        this.root = root;
    }

    public void setMainApplicationPane(BorderPane applicationPane) {
        this.mainApplicationPane = applicationPane;
    }
}