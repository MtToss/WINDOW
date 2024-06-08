package buginvaderz.window;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class RowMusicTemplate extends Pane{
    @FXML
    ImageView imageId = new ImageView();

    @FXML
    Label songId = new Label();

    @FXML
    Label artistId = new Label();

    @FXML
    Label albumId = new Label();

    @FXML
    Label timeLengthId = new Label();

    @FXML
    Pane mainSpuuchifyWindow = new Pane();

    private String imageStringId;
    private String songStringId;
    private String artistStringId;
    private String albumStringId;
    private String timeLengthStringId;
    private String audioString;

    RowMusicTemplate(String imageStringId, String songStringId, String artistStringId, String albumStringId, String timeLengthId, String audioString) {
        this.imageStringId = imageStringId;
        this.songStringId = songStringId;
        this.artistStringId = artistStringId;
        this.albumStringId = albumStringId;
        this.timeLengthStringId = timeLengthStringId;
        this.audioString = audioString;

        setToInitialize();
    
    }



    public String getAudioString() {
        return audioString;
    }

    public Pane setToInitialize() {
        try {
            Image image = new Image(imageStringId);
            imageId.setImage(image);

            songId.setText(songStringId);
            artistId.setText(artistStringId);
            albumId.setText(albumStringId);
            timeLengthId.setText(timeLengthStringId);
            
            getChildren().addAll(mainSpuuchifyWindow);

        } catch (Exception e) {
            System.err.println("Failed to load image: " + e.getMessage());
        }
        return this;



    }


}
