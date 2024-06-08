package buginvaderz.window;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SpuuchifyController {
    @FXML
    private AnchorPane mainPaneSpuuchify;

    @FXML
    private ImageView imageViewer;

    @FXML
    private Pane headerSpuuchify;

    @FXML
    private VBox spotifyContainer = new VBox();

    private Pane app;
    Pane root = new Pane();
    BorderPane mainApplicationPane;
    private boolean isMaximized = true;

    @FXML
    public void initialize() {
        SpuuchifyTemplate music = new SpuuchifyTemplate("https://i1.sndcdn.com/artworks-o9PcVKoWiZt7-0-t500x500.jpg", "Backburner", "NIKI", "OCEANS", "2:56", "s");       
        spotifyContainer.getChildren().addAll(music);
    }

    public void resizeSpuuchify() {
        if (isMaximized) {
            app.setPrefSize(root.getWidth()-15, root.getHeight()-26);
            isMaximized = false;
        }
        else {
            app.setPrefSize(root.getWidth()-200, root.getHeight()-200);
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
