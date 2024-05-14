package buginvaderz.window;

import javafx.scene.layout.BorderPane;

public class Application {
    private static Application instance;
    protected BorderPane mainWindow = new BorderPane();

    private Application() {

    }

    public static Application getInstance() {
        if(instance == null) {
            instance = new Application();
        }

        return instance;
    }

    public BorderPane getMainWindow() {
        return mainWindow;
    }


}
