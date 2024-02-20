package souvenirs.control;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AddProducerWindow extends Application {
    private final DataManagerFacade dataManagerFacade;

    public AddProducerWindow(DataManagerFacade dataManagerFacade) {
        this.dataManagerFacade = dataManagerFacade;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Add Producer");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        Label nameLabel = new Label("Producer's Name:");
        GridPane.setConstraints(nameLabel, 0, 0);
        TextField nameInput = new TextField();
        nameInput.setPromptText("Enter producer's name");
        GridPane.setConstraints(nameInput, 1, 0);

        Label countryLabel = new Label("Country:");
        GridPane.setConstraints(countryLabel, 0, 1);
        TextField countryInput = new TextField();
        countryInput.setPromptText("Enter country's name");
        GridPane.setConstraints(countryInput, 1, 1);

        Button addButton = new Button("Add Souvenir");
        GridPane.setConstraints(addButton, 1, 4);

        addButton.setOnAction(e -> {
            String name = nameInput.getText();
            String countryName = countryInput.getText();
            Producer producer = new Producer(name, countryName);
            dataManagerFacade.addProducer(producer);
            primaryStage.close();
        });

        grid.getChildren().addAll(nameLabel, nameInput, countryLabel, countryInput, addButton);
        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
