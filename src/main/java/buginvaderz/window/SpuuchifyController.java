package buginvaderz.window;

import java.io.IOException;
import java.util.List;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class SpuuchifyController {

    @FXML
    private AnchorPane mainPaneSpuuchify;
    @FXML
    private ImageView imageViewer;
    @FXML
    private Pane headerSpuuchify;
    @FXML
    private VBox spotifyContainer;
    @FXML
    private ImageView operatorButton;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Slider volumeScale;
    @FXML
    private Label titleId;
    @FXML
    private Label artistId;
    @FXML
    private ImageView imageId;

    boolean isPlaying = false;
    Image imageObjectPlay;
    Image imageObjectPause;

    List<SpuuchifyTemplate> spuuchifyTemplates;
    private SpuuchifyTemplate currentlyPlayingMusic = null;

    @FXML
    public void initialize() throws IOException {
        MusicDatabaseHandler dbHandler = new MusicDatabaseHandler();
        spuuchifyTemplates = dbHandler.fetchMusicData();

        imageObjectPlay = new Image(getClass().getResourceAsStream("img/Play.png"));
        imageObjectPause = new Image(getClass().getResourceAsStream("img/Pause.png"));

        spuuchifyTemplates.forEach(music -> {
            music.setOnPlayCallback(() -> stopOtherMusic(music));
            music.setOnProgressCallback(() -> updateProgressBar(music));
            spotifyContainer.getChildren().add(music);
        });

        volumeScale.valueProperty().addListener((observable, oldValue, newValue) -> {
            double volume = newValue.doubleValue() / 100.0;
            if (currentlyPlayingMusic != null) {
                currentlyPlayingMusic.setVolume(volume);
            }
        });

        headerSpuuchify.widthProperty().addListener((observable, oldValue, newValue) -> imageViewer.setFitWidth(newValue.doubleValue()));
        headerSpuuchify.heightProperty().addListener((observable, oldValue, newValue) -> imageViewer.setFitHeight(newValue.doubleValue()));

        startMonitoring();
    }

    private void updateProgressBar(SpuuchifyTemplate music) {
        if (music.getMediaPlayer() != null) {
            double currentFrame = music.getMediaPlayer().getCurrentTime().toMillis();
            double fraction = currentFrame / music.getMediaPlayer().getTotalDuration().toMillis();
            progressBar.setProgress(fraction);
        }
    }

    private void startMonitoring() {
        Thread thread = new Thread(() -> {
            while (true) {
                for (SpuuchifyTemplate music : spuuchifyTemplates) {
                    boolean musicPlaying = music.getIsPlaying();
                    Platform.runLater(() -> {
                        if (musicPlaying) {
                            operatorButton.setImage(imageObjectPlay);
                            titleId.setText(music.getSongString());
                            artistId.setText(music.getArtistString());
                            imageId.setImage(music.getImage());
                        }
                    });
                    try {
                        Thread.sleep(500); 
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread.setDaemon(true);
        thread.start();
    }

    @FXML
    public void touchOperatorButton() {
        if (currentlyPlayingMusic != null) {
            if (currentlyPlayingMusic.getIsPlaying()) {
                currentlyPlayingMusic.toPause();
                operatorButton.setImage(imageObjectPause);
            } else {
                currentlyPlayingMusic.toResume();
                operatorButton.setImage(imageObjectPlay);
            }
        }
    }

    private void stopOtherMusic(SpuuchifyTemplate currentMusic) {
        if (currentlyPlayingMusic == null) {
            currentlyPlayingMusic = currentMusic;
        } else if (currentlyPlayingMusic != currentMusic) {
            currentlyPlayingMusic.toPause();
            currentlyPlayingMusic.setBackground(Background.fill(Color.valueOf("#192227")));
            currentlyPlayingMusic = currentMusic;
        }
    }
}
