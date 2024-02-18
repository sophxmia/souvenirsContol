package souvenirs.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {
    private DataManagerFacade dataManagerFacade;

    @Override
    public void start(Stage primaryStage) {
        dataManagerFacade = new DataManagerFacade();

        primaryStage.setTitle("Souvenirs Control");

        Button addSouvenirButton = new Button("Add Souvenir");
        addSouvenirButton.setOnAction(event -> {
            AddSouvenirWindow addSouvenirWindow = new AddSouvenirWindow(dataManagerFacade);
            addSouvenirWindow.start(new Stage());
        });

        Button addProducerButton = new Button("Add Producer");
        addProducerButton.setOnAction(event -> {
            AddProducerWindow addProducerWindow = new AddProducerWindow(dataManagerFacade);
            addProducerWindow.start(new Stage());
        });

        Button editDataButton = new Button("Edit Data");
        editDataButton.setOnAction(event -> {
            Souvenir selectedSouvenir = getSelectedSouvenir();
            if (selectedSouvenir != null) {
                EditDataWindow editDataWindow = new EditDataWindow(dataManagerFacade);
                editDataWindow.setSouvenir(selectedSouvenir);
                editDataWindow.start(new Stage());
            }
        });

        Button viewDataButton = new Button("View Data");
        viewDataButton.setOnAction(event -> {
            ViewDataWindow viewDataWindow = new ViewDataWindow(dataManagerFacade);
            viewDataWindow.start(new Stage());
        });

        Button filterButton = new Button("Filter");
        filterButton.setOnAction(event -> {
            ShowFilterMenu showFilterMenu = new ShowFilterMenu(dataManagerFacade);
            showFilterMenu.start(new Stage());
        });

        VBox root = new VBox(10);
        root.getChildren().addAll(addSouvenirButton, addProducerButton, editDataButton, viewDataButton, filterButton);
        Scene scene = new Scene(root, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Souvenir getSelectedSouvenir() {
        List<Souvenir> souvenirs = dataManagerFacade.getAllSouvenirs();
        if (!souvenirs.isEmpty()) {
            return souvenirs.getFirst();
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}