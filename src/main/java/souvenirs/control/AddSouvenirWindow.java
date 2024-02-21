package souvenirs.control;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class AddSouvenirWindow extends Application {

    private final DataManagerFacade dataManagerFacade;

    public AddSouvenirWindow(DataManagerFacade dataManagerFacade) {
        this.dataManagerFacade = dataManagerFacade;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Add Souvenir");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        Label nameLabel = new Label("Name:");
        GridPane.setConstraints(nameLabel, 0, 0);
        TextField nameInput = new TextField();
        nameInput.setPromptText("Enter souvenir name");
        GridPane.setConstraints(nameInput, 1, 0);

        Label producerLabel = new Label("Producer:");
        GridPane.setConstraints(producerLabel, 0, 1);
        TextField producerInput = new TextField();
        producerInput.setPromptText("Enter producer name");
        GridPane.setConstraints(producerInput, 1, 1);

        Label countryLabel = new Label("Country:");
        GridPane.setConstraints(countryLabel, 0, 2);
        TextField countryInput = new TextField();
        countryInput.setPromptText("Enter country's name");
        GridPane.setConstraints(countryInput, 1, 2);

        Label releaseDateLabel = new Label("Release Date:");
        GridPane.setConstraints(releaseDateLabel, 0, 3);
        TextField releaseDateInput = new TextField();
        releaseDateInput.setPromptText("YYYY-MM-DD");
        GridPane.setConstraints(releaseDateInput, 1, 3);

        Label priceLabel = new Label("Price:");
        GridPane.setConstraints(priceLabel, 0, 4);
        TextField priceInput = new TextField();
        priceInput.setPromptText("Enter price");
        GridPane.setConstraints(priceInput, 1, 4);

        Button addButton = new Button("Add Souvenir");
        GridPane.setConstraints(addButton, 1, 5);
        addButton.setOnAction(e -> {
            String name = nameInput.getText();
            String producerName = producerInput.getText();
            String countryName = countryInput.getText();
            LocalDate releaseDate = LocalDate.parse(releaseDateInput.getText());
            double price = Double.parseDouble(priceInput.getText());
            Producer producer = null;
            List<Producer> producers = dataManagerFacade.getAllProducers();
            for (Producer existingProducer : producers) {
                if (existingProducer.getName().equalsIgnoreCase(producerName)) {
                    producer = existingProducer;
                    break;
                }
            }

            if (producer == null) {
                producer = new Producer(producerName, countryName);
                dataManagerFacade.addProducer(producer);
            }

            Souvenir souvenir = new Souvenir(name, Collections.singletonList(producer), releaseDate, price);
            dataManagerFacade.addSouvenir(souvenir);

            primaryStage.close();
        });

        grid.getChildren().addAll(nameLabel, nameInput, producerLabel, producerInput, countryLabel, countryInput, releaseDateLabel, releaseDateInput, priceLabel, priceInput, addButton);

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
