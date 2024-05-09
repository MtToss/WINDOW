package buginvaderz.window;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class TitleBarController {

    private BorderPane borderPane; // Reference to the BorderPane



    @FXML
    public void minimizeButton() {
        System.out.println("HAI");
        // Minimize the borderPane (You can implement your logic here)
        borderPane.setVisible(false); // Example: hiding the BorderPane
    }

    @FXML
    public void closeButton() {
        // Implement your close button logic here
    }
}
