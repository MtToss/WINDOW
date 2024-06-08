package buginvaderz.window;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SpotifyController {
    @FXML
    private AnchorPane mainPaneSpuuchify;

    @FXML
    private ImageView imageViewer;

    @FXML
    private Pane headerSpuuchify;

    @FXML
    private VBox spotifyContainer = new VBox();

    @FXML
    public void initialize() {
        SpuuchifyTemplate music = new SpuuchifyTemplate("https://i1.sndcdn.com/artworks-o9PcVKoWiZt7-0-t500x500.jpg", "Backburner", "NIKI", "OCEANS", "2:56", "s");       
        spotifyContainer.getChildren().addAll(music);
    }
}
