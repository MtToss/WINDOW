package buginvaderz.window;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class TaskBarController {

    @FXML
    Label textDebug;

    @FXML
    public void mouseClicked() {
               textDebug.setText("Clicked!");
    }




}
