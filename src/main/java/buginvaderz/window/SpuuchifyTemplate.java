package buginvaderz.window;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SpuuchifyTemplate extends Pane {

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
    private Clip clip;

    private String imageStringId;
    private String songStringId;
    private String artistStringId;
    private String albumStringId;
    private String timeLengthStringId;
    private String audioString;

    private Runnable onPlayCallback;
    private Runnable onProgressCallback;

    private long totalFrames;
    private long pauseFramePosition;

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

        borderPane.setLeft(leftContainer);
        borderPane.setCenter(centerContainer);
        borderPane.setRight(rightContainer);
        borderPane.setPrefWidth(1350);
        getChildren().addAll(borderPane);

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
            if (clip != null && clip.isOpen()) {
                clip.close();
                clip.setFramePosition((int) pauseFramePosition);
            }

            File file = new File(audioString);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);

            clip = AudioSystem.getClip();
            clip.open(audioIn);
            totalFrames = clip.getFrameLength();
            clip.start();
            
            // clip.setMicrosecondPosition(); //set the 

            isPlaying = true;
            if (onPlayCallback != null) {
                onPlayCallback.run();
            }
            myThreadCed();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void toPause() {
        if (clip != null) {
            clip.stop();
            isPlaying = false;
            pauseFramePosition = clip.getMicrosecondPosition();
        }
    }

    public void toResume() {
        if (clip != null) {
            clip.start();
            isPlaying = true;
            myThreadCed();
        }
    }

    public boolean getIsPlaying() {
        return isPlaying;
    }

    public Clip getClipp() {
        return clip;
    }

    public void myThreadCed() {
        new Thread(() -> {
            while (isPlaying) {
                if (clip.isRunning()) {
                    if (onProgressCallback != null) {
                        System.out.println("Ganda ni Lei");
                        System.out.println(getClass().getResourceAsStream(""));
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
        if (clip != null && clip.isOpen()) {
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
            volumeControl.setValue(dB);
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
