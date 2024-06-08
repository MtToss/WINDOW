package buginvaderz.window;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class TitleBarController {

    BorderPane mainApplicationPane;

    Pane root = new Pane();

    @FXML
    private AnchorPane mainPaneSpuuchify;

    @FXML
    private ImageView imageViewer;

    @FXML
    private BorderPane container;

    @FXML
    private Pane headerSpuuchify;

    @FXML
    private BorderPane titBar = new BorderPane();

    @FXML
    private VBox spotifyContainer = new VBox();

    @FXML
    private Pane ide;

    @FXML
    Label label = new Label();

    private boolean isMaximized = true;


    @FXML
    public void handleMinimizeButton() {
        mainApplicationPane.setVisible(false);
    }

    @FXML
    public void handleCloseButton() {
        mainApplicationPane.setVisible(false);
    }

    @FXML
    public void initialize() {
        root.widthProperty().addListener((obs, oldVal, newVal) -> isMax());
        root.heightProperty().addListener((obs, oldVal, newVal) -> isMax());

        SpuuchifyTemplate music = new SpuuchifyTemplate("https://i1.sndcdn.com/artworks-o9PcVKoWiZt7-0-t500x500.jpg", "Backburner", "NIKI", "OCEANS", "2:56", "s");
        
        spotifyContainer.getChildren().addAll(music);
        

    }

    @FXML
    public void isMax() {

            if (isMaximized) {
                mainPaneSpuuchify.setPrefSize(root.getWidth()-15, root.getHeight()-26);
                ide.setPrefSize(root.getWidth() - 15, root.getHeight() - 26);
                titBar.setPrefSize(root.getWidth() - 15, 25);
                mainApplicationPane.setLayoutX(1); mainApplicationPane.setLayoutY(1);
                isMaximized = false;

                for (Node child : mainPaneSpuuchify.getChildren()) {
                    Pane pane = (Pane) child;
                    pane.prefWidthProperty().bind(mainPaneSpuuchify.widthProperty());
                    pane.prefHeightProperty().bind(mainPaneSpuuchify.heightProperty());
                    System.out.println(child);
                                   
                }

                for (Node child : headerSpuuchify.getChildren()) {
                    if(child.getId().equals("imageViewer")) {
                    ImageView image = (ImageView) child;
                    image.fitWidthProperty().bind(headerSpuuchify.widthProperty());
                    }
                    else{
                        System.out.println(child);
                    }
                }

            }
            else {
                mainPaneSpuuchify.setPrefSize(root.getWidth() - 200, root.getHeight() - 200);
                ide.setPrefSize(root.getWidth() - 200, root.getHeight() - 200);
                titBar.setPrefSize(root.getWidth() - 200, 25);

                mainApplicationPane.setLayoutX(35); mainApplicationPane.setLayoutY(35);
                isMaximized = true;

                for (Node child : mainPaneSpuuchify.getChildren()) {
                        Pane pane = (Pane) child;
                        pane.prefWidthProperty().bind(mainPaneSpuuchify.widthProperty());
                        pane.prefHeightProperty().bind(mainPaneSpuuchify.heightProperty().subtract(50)); 
                }
            }

    }


    public void setIdePane(AnchorPane bodyRoot) {
        this.ide = bodyRoot;
        this.mainPaneSpuuchify = bodyRoot;
    }


    public void setRoot(Pane root) {
        this.root = root;
    }

    public void setMainApplicationPane(BorderPane applicationPane) {
        this.mainApplicationPane = applicationPane;
    }
}