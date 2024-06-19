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
    @FXML
    private Pane imagePane;

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

        imagePane.widthProperty().addListener((observablex, oldValue, newValue) -> {
            imageViewer.setFitWidth(newValue.doubleValue());
        });

        imagePane.heightProperty().addListener((observable, oldValue, newValue) -> {
            imageViewer.setFitHeight(newValue.doubleValue());
        });
        startMonitoring();
    }

    private void updateProgressBar(SpuuchifyTemplate music) {
        if (music.getMediaPlayer() != null) {
            double currentFrame = music.getMediaPlayer().getCurrentTime().toMillis();
            double fraction = currentFrame / music.getMediaPlayer().getTotalDuration().toMillis();
            progressBar.setProgress(fraction);
            if (fraction >= 1.0) {
                switchToRandomTrack();
            }
        }
        //itong progressbar na ito is siya bahala sa duration ng music and kapag natapos yung progress ay pupunta sa random track 
        //which mag pe-play ng another random track
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
        //ito yung thread na nagmo-monitor if may music na nagpe-play and if may nag pe-play papasok sa 
        //if statement and kukunin yung kukununin
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
        //ito yung operator na play and pause
    }

    private void stopOtherMusic(SpuuchifyTemplate currentMusic) {
        if (currentlyPlayingMusic == null) {
            currentlyPlayingMusic = currentMusic;
        } else if (currentlyPlayingMusic != currentMusic) {
            currentlyPlayingMusic.toPause();
            currentlyPlayingMusic.setBackground(Background.fill(Color.valueOf("#192227")));
            currentlyPlayingMusic = currentMusic;
        }
        //pinapalitan niya yung music and stop if may laman yung currentlyplayingmusic
    }

    private void switchToRandomTrack() {

        
        if (currentlyPlayingMusic != null) {
            currentlyPlayingMusic.toPause();
            currentlyPlayingMusic.setBackground(Background.fill(Color.valueOf("#192227")));
        }

        //ito pang shuffle stuff

        int randomIndex = (int) (Math.random() * spuuchifyTemplates.size());
        currentlyPlayingMusic = spuuchifyTemplates.get(randomIndex);
        currentlyPlayingMusic.play();


        //ina-update niya yung text and images ofcourse bro 
        Platform.runLater(() -> {
            titleId.setText(currentlyPlayingMusic.getSongString());
            artistId.setText(currentlyPlayingMusic.getArtistString());
            imageId.setImage(currentlyPlayingMusic.getImage());
        });
    }
}
