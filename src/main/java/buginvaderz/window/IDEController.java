package buginvaderz.window;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class IDEController {

    @FXML
    Pane ide = new Pane();

    @FXML
    Label label= new Label();


    public IDEController(){
        ide.setPrefSize(1050, 1050);
    }
    public void getSizeWidthHeight(double w, double h) {
        ide.setPrefSize(250,250);
        label.setText("LEEEI");
        System.out.println("EHH");
    }

}
