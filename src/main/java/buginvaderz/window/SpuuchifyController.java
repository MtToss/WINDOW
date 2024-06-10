package buginvaderz.window;

import java.io.IOException;
import java.io.InputStream;

import javafx.application.Platform;
import javafx.concurrent.Task;
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
    private VBox spotifyContainer = new VBox();

    @FXML
    private ImageView operatorButton;

    @FXML
    private ImageView imageId;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Slider volumeScale;

    @FXML
    private Label titleId;

    @FXML
    private Label artistId;


    boolean isPlaying = false;
    InputStream inStreamPlay = getClass().getResourceAsStream("img/Play.png");
    InputStream inStreamPause = getClass().getResourceAsStream("img/Pause.png");
    Image imageObjectPlay = new Image(inStreamPlay);
    Image imageObjectPause = new Image(inStreamPause);

    SpuuchifyTemplate music1;
    SpuuchifyTemplate music2;
    SpuuchifyTemplate music3;
    SpuuchifyTemplate music4;
    SpuuchifyTemplate music5;

    SpuuchifyTemplate[] spuuchifyTemplates;
    private SpuuchifyTemplate currentlyPlayingMusic = null;

    @FXML
    public void initialize() throws IOException {
        music1 = new SpuuchifyTemplate("https://i1.sndcdn.com/artworks-o9PcVKoWiZt7-0-t500x500.jpg", "Backburner", "NIKI", "Nicole", "3:53", "src/main/resources/buginvaderz/window/mp3/Backburner.wav", isPlaying);
        music2 = new SpuuchifyTemplate("https://is1-ssl.mzstatic.com/image/thumb/Music221/v4/64/2f/91/642f91e6-00a1-cb7c-7577-67c3f6a9d183/cover.jpg/1200x1200bf-60.jpg", "Misteryoso", "Cup Of Joe", "Misteryoso", "3:43", "src/main/resources/buginvaderz/window/mp3/Misteryoso.wav", isPlaying);
        music3 = new SpuuchifyTemplate("https://images.genius.com/52e1de2cca85a2c12de875230487d3ef.1000x1000x1.jpg", "Bad", "Wave To Earth", "0.1 flaws and all.","4:23", "src/main/resources/buginvaderz/window/mp3/Bad.wav", isPlaying);
        music4 = new SpuuchifyTemplate("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSm3f58ujVjJTOcyVJqzDx3elS8OBs6tRj1Tg&s", "Always", "Daniel Caesar", "Never Enough", "3:45", "src/main/resources/buginvaderz/window/mp3/Always.wav", isPlaying);
        music5 = new SpuuchifyTemplate("https://www.billboard.com/wp-content/uploads/media/Daniel-Caesar-2016-by-Keith-Henry-press-billboard-1548.jpg", "Get You", "Daniel Caesar, Kali Uchis", "Freudian", "4:38", "src/main/resources/buginvaderz/window/mp3/Get You.wav", isPlaying);

        music1.setOnPlayCallback(() -> stopOtherMusic(music1));
        music1.setOnProgressCallback(() -> updateProgressBar(music1));

        music2.setOnPlayCallback(() -> stopOtherMusic(music2));
        music2.setOnProgressCallback(() -> updateProgressBar(music2));

        music3.setOnPlayCallback(() -> stopOtherMusic(music3));
        music3.setOnProgressCallback(() -> updateProgressBar(music3));

        music4.setOnPlayCallback(() -> stopOtherMusic(music4));
        music4.setOnProgressCallback(() -> updateProgressBar(music4));

        music5.setOnPlayCallback(() -> stopOtherMusic(music5));
        music5.setOnProgressCallback(() -> updateProgressBar(music5));

        spotifyContainer.getChildren().addAll(music1, music2, music3, music4, music5);

        spuuchifyTemplates = new SpuuchifyTemplate[]{music1, music2, music3, music4, music5};

        volumeScale.valueProperty().addListener((observable, oldValue, newValue) -> {
            double volume = newValue.doubleValue() / 100.0;
            if (currentlyPlayingMusic != null) {
                currentlyPlayingMusic.setVolume(volume);
            }
        });

        startMonitoring();


    }

    private void updateProgressBar(SpuuchifyTemplate music) {
                long currentFrame = music.getClipp().getMicrosecondPosition();
                double fraction = (double) currentFrame / music.getClipp().getMicrosecondLength();
                progressBar.setProgress(100);
                progressBar.setProgress(fraction);

    }

    private void startMonitoring() {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                while (true) {
                    for (SpuuchifyTemplate music : spuuchifyTemplates) {
                        boolean musicPlaying = music.getIsPlaying();
                        Platform.runLater(() -> {
                            if (musicPlaying) {
                                operatorButton.setImage(imageObjectPlay);
                                imageId.setImage(currentlyPlayingMusic.getImage());
                                titleId.setText(currentlyPlayingMusic.getSongString());
                                artistId.setText(currentlyPlayingMusic.getArtistString());

                            }
                        });
                        Thread.sleep(500);
                    }
                }
            }
        };

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    @FXML
    public void touchOperatorButton() {
        if (currentlyPlayingMusic != null) {
            if (currentlyPlayingMusic.getIsPlaying()) {
                currentlyPlayingMusic.toPause();
                isPlaying = false;
                operatorButton.setImage(imageObjectPause);
            } else {
                currentlyPlayingMusic.toResume();
                isPlaying = true;
                operatorButton.setImage(imageObjectPlay);
            }
        }
    }

    private void stopOtherMusic(SpuuchifyTemplate currentMusic) {
        if (currentlyPlayingMusic == null) {
            currentlyPlayingMusic = currentMusic;
        }
        else {
            currentlyPlayingMusic.setBackground(Background.fill(Color.valueOf("#192227")));
            currentlyPlayingMusic.toPause();

            if(currentlyPlayingMusic != currentMusic) {
                currentlyPlayingMusic = currentMusic;
            }
        }
        
    }
}
