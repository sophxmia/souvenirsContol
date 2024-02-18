package souvenirs.control;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class ViewDataWindow extends Application {
    private final DataManagerFacade dataManagerFacade;

    public ViewDataWindow(DataManagerFacade dataManagerFacade) {
        this.dataManagerFacade = dataManagerFacade;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("View Data about Souvenir");

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);

        List<Souvenir> souvenirs = dataManagerFacade.getAllSouvenirs();

        ListView<String> listView = new ListView<>();
        ObservableList<String> souvenirNames = FXCollections.observableArrayList();

        for (Souvenir souvenir : souvenirs) {
            String souvenirInfo = String.format("Name: %s, Producers: %s, Release Date: %s, Price: %.2f",
                    souvenir.getName(), souvenir.getProducers().toString(), souvenir.getReleaseDate().toString(), souvenir.getPrice());
            souvenirNames.add(souvenirInfo);
        }

        listView.setItems(souvenirNames);

        vbox.getChildren().add(listView);

        Scene scene = new Scene(vbox, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
