package buginvaderz.window;

import java.io.File;
import java.io.IOException;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SpuuchifyTemplate extends BorderPane {

    Pane leftContainer = new Pane();
    ImageView imageId = new ImageView();
    Image image;
    Label songId = new Label();
    Label artistId = new Label();

    Pane rightContainer = new Pane();
    Label timeLengthId = new Label();

    Label albumId = new Label();
    Pane centerContainer = new Pane();

    BorderPane borderPane = new BorderPane();

    ImageView playButton = new ImageView(new Image(getClass().getResourceAsStream("img/PlayIcon.png")));

    boolean isPlaying = false;
    private MediaPlayer mediaPlayer;

    private String imageStringId;
    private String songStringId;
    private String artistStringId;
    private String albumStringId;
    private String timeLengthStringId;
    private String audioString;

    private Runnable onPlayCallback;
    private Runnable onProgressCallback;

    private double pauseTimePosition;

    public SpuuchifyTemplate(String imageStringId, String songStringId, String artistStringId,
                             String albumStringId, String timeLengthStringId, String audioString, boolean isPlaying) throws IOException {
        this.imageStringId = imageStringId;
        this.songStringId = songStringId;
        this.artistStringId = artistStringId;
        this.albumStringId = albumStringId;
        this.timeLengthStringId = timeLengthStringId;
        this.audioString = audioString;
        this.isPlaying = isPlaying;

        setPrefSize(1358, 50);
        initializeComponents();
        setBackground(Background.fill(Color.valueOf("#192227")));
        hoverPane();

        this.setOnMouseClicked(event -> {
            if (isPlaying) {
                this.toPause();
            } else {
                this.play();
                this.setBackground(Background.fill(Color.valueOf("#394447")));
            }
        });
    }

    public void setOnPlayCallback(Runnable onPlayCallback) {
        this.onPlayCallback = onPlayCallback;
    }

    public void initializeComponents() {
        image = new Image(imageStringId);
        imageId.setImage(image);

        songId.setText(songStringId);
        songId.setTextFill(Color.valueOf("#ffffff"));
        artistId.setText(artistStringId);
        artistId.setTextFill(Color.valueOf("#ffffff"));
        albumId.setText(albumStringId);
        albumId.setTextFill(Color.valueOf("#ffffff"));
        timeLengthId.setText(timeLengthStringId);
        timeLengthId.setTextFill(Color.valueOf("#ffffff"));

        imageId.setLayoutX(66); imageId.setFitHeight(35);
        imageId.setLayoutY(9); imageId.setFitWidth(35);
        songId.setLayoutX(105); songId.setPrefSize(102, 15);
        songId.setLayoutY(9);
        artistId.setLayoutX(105); artistId.setPrefSize(102, 15);
        artistId.setLayoutY(24);   artistId.setFont(Font.font("Verdana", FontWeight.BOLD, 7));
        playButton.setLayoutX(20); playButton.setFitHeight(25);
        playButton.setLayoutY(12); playButton.setFitWidth(25);

        leftContainer.getChildren().add(playButton);
        leftContainer.getChildren().add(imageId);
        leftContainer.getChildren().add(songId);
        leftContainer.getChildren().add(artistId);
        leftContainer.setPrefSize(400, 50);

        timeLengthId.setLayoutX(250);
        timeLengthId.setLayoutY(12);
        rightContainer.getChildren().add(timeLengthId);
        rightContainer.setPrefSize(325, 50);

        albumId.setLayoutX(250);
        albumId.setLayoutY(12); albumId.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
        centerContainer.getChildren().add(albumId);

        setLeft(leftContainer);
        setCenter(centerContainer); //old one is borderPane.setLeft,setCenter and setRight.
        setRight(rightContainer);
        //getChildren().addAll(borderPane);
    }

    public void setOnProgressCallback(Runnable onProgressCallback) {
        this.onProgressCallback = onProgressCallback;
    }

    public void hoverPane() {
        this.setOnMouseEntered(event -> {
            this.setBackground(Background.fill(Color.valueOf("#263238")));
            if(isPlaying) {
                this.setBackground(Background.fill(Color.valueOf("#394447")));
            }
        });
        this.setOnMouseExited(event -> {
            this.setBackground(Background.fill(Color.valueOf("#192227")));
            if(isPlaying) {
                this.setBackground(Background.fill(Color.valueOf("#394447")));
            }
        });
    }

    public void play() {
        try {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }

            Media media = new Media(new File(audioString).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setOnReady(() -> {
                mediaPlayer.play();
                isPlaying = true;
                if (onPlayCallback != null) {
                    onPlayCallback.run();
                }
                myThreadCed();
            });
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void toPause() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            isPlaying = false;
            pauseTimePosition = mediaPlayer.getCurrentTime().toMillis();
        }
    }

    public void toResume() {
        if (mediaPlayer != null) {
            mediaPlayer.seek(javafx.util.Duration.millis(pauseTimePosition));
            mediaPlayer.play();
            isPlaying = true;
            myThreadCed();
        }
    }

    public boolean getIsPlaying() {
        return isPlaying;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void myThreadCed() {
        new Thread(() -> {
            while (isPlaying) {
                if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                    if (onProgressCallback != null) {
                        System.out.println("Ganda ni Lei");
                        onProgressCallback.run();
                    }
                }

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void setVolume(double volume) {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(volume);
        }
    }

    public Image getImage() {
        return image;
    }

    public String getSongString() {
        return songStringId;
    }

    public String getArtistString() {
        return artistStringId;
    }
}
