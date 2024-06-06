package buginvaderz.window;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class TitleBarController {

    BorderPane mainApplicationPane;

    Pane root = new Pane();

    @FXML
    private Pane mainPaneSpuuchify;

    @FXML
    private BorderPane titBar = new BorderPane();

    @FXML
    private Button minimizeButton;

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

    }

    @FXML
    public void isMax() {

            if (isMaximized) {
                mainPaneSpuuchify.setPrefSize(root.getWidth()-15, root.getHeight()-26);
                ide.setPrefSize(root.getWidth() - 15, root.getHeight() - 26);
                titBar.setPrefSize(root.getWidth() - 15, 25);
                mainApplicationPane.setLayoutX(1); mainApplicationPane.setLayoutY(1);
                isMaximized = false;
            }
            else {
                mainPaneSpuuchify.setPrefSize(root.getWidth()-200, root.getHeight()-200);
                ide.setPrefSize(root.getWidth() - 200, root.getHeight() - 200);
                titBar.setPrefSize(root.getWidth() - 200, 25);
                mainApplicationPane.setLayoutX(35); mainApplicationPane.setLayoutY(35);
                isMaximized = true;
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