package buginvaderz.window;

import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Drag {
    private double mouseX;
    private double mouseY;
    private Pane rootPane;

    public Drag(Parent parent, Pane rootPane) {
        this.rootPane = rootPane;
        parent.setOnMouseDragged(this::onMouseDragged);
    }



    private void onMouseDragged(MouseEvent e) {
        Parent parent = (Parent) e.getSource();


        double newX = e.getSceneX() - mouseX;
        double newY = e.getSceneY() - mouseY;

        double maxWidth = rootPane.getWidth();
        double maxHeight = rootPane.getHeight();

        if (newX >= 0 && newX + parent.getBoundsInParent().getWidth() <= maxWidth - 7) {
            parent.setLayoutX(newX);
        }
        if (newY >= 0 && newY + parent.getBoundsInParent().getHeight() <= maxHeight) {
            parent.setLayoutY(newY);
        }
    }
}
