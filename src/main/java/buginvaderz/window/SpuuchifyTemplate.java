package buginvaderz.window;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.File;

public class SpuuchifyTemplate extends Pane {

    Pane leftContainer = new Pane();
    ImageView imageId = new ImageView();
    Label songId = new Label();
    Label artistId = new Label();
    
    Pane rightContainer = new Pane();
    Label timeLengthId = new Label();
    
    Label albumId = new Label();
    Pane centerContainer = new Pane();

    BorderPane borderPane = new BorderPane();

    ImageView playButton = new ImageView(new Image(getClass().getResourceAsStream("img/PlayIcon.png")));



    private String imageStringId;
    private String songStringId;
    private String artistStringId;
    private String albumStringId;
    private String timeLengthStringId;
    private String audioString;

    public SpuuchifyTemplate(String imageStringId, String songStringId, String artistStringId, 
                             String albumStringId, String timeLengthStringId, String audioString) {
        this.imageStringId = imageStringId;
        this.songStringId = songStringId;
        this.artistStringId = artistStringId;
        this.albumStringId = albumStringId;
        this.timeLengthStringId = timeLengthStringId;
        this.audioString = audioString;

        setPrefSize(1358, 50);
        initializeComponents();
        setBackground(Background.fill(Color.valueOf( "#192227")));
        playButton();
    }

    public void initializeComponents() {
        Image image = new Image(imageStringId);
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
        imageId.setLayoutY(9);  imageId.setFitWidth(35);
        songId.setLayoutX(131);  songId.setPrefSize(102, 15); 
        songId.setLayoutY(9);
        artistId.setLayoutX(131); artistId.setPrefSize(102, 15); 
        artistId.setLayoutY(24);
        playButton.setLayoutX(20); playButton.setFitHeight(25);
        playButton.setLayoutY(12);  playButton.setFitWidth(25);

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
        albumId.setLayoutY(12);
        centerContainer.getChildren().add(albumId);    
        
        borderPane.setLeft(leftContainer);
        borderPane.setCenter(centerContainer);
        borderPane.setRight(rightContainer);
        borderPane.setPrefWidth(1350);
        getChildren().addAll(borderPane);
    }

    public void playButton() {
        playButton.setOnMouseClicked(event -> {
            try {
                Media media = new Media(getClass().getResource(audioString).toExternalForm());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();
                System.out.println("Start");
            } 
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }

}
