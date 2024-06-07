module buginvaderz.window {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens buginvaderz.window to javafx.fxml;
    exports buginvaderz.window;
}