module buginvaderz.window {
    requires javafx.controls;
    requires javafx.fxml;


    opens buginvaderz.window to javafx.fxml;
    exports buginvaderz.window;
}