    package buginvaderz.window;

    import javafx.fxml.FXML;
    import javafx.geometry.Rectangle2D;
    import javafx.scene.control.Button;
    import javafx.scene.layout.BorderPane;
    import javafx.stage.Screen;
    import javafx.stage.Stage;

    public class TitleBarController {

        public BorderPane titBar;
        public Button minimizeButton;
        private IDEController ide = new IDEController();

        boolean isMaximized = true;


        @FXML
        public void handleMinimizeButton() {
            titBar.setVisible(false);
        }

        @FXML
        public void handleCloseButton() {
            titBar.setVisible(false);
        }

        @FXML
        public void handleMoveWindow() {
            System.out.println(titBar.getParent().getScene().getWidth());
            System.out.println(titBar.getParent().getScene().getHeight());
        }

        @FXML
        public void handleMaximizeButton() {

            if (isMaximized) {

                ide.getSizeWidthHeight(700, 700);
                isMaximized = false;
            } else {

                ide.getSizeWidthHeight(300,300);
                isMaximized = true;
            }
            System.out.println(isMaximized);
        }

        public void setMainPane(BorderPane pane) {
            this.titBar = pane;
        }
    }