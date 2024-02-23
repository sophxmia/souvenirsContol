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
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EditDataWindow extends Application {
    private final DataManagerFacade dataManagerFacade;
    private Souvenir souvenir;

    public EditDataWindow(DataManagerFacade dataManagerFacade) {
        this.dataManagerFacade = dataManagerFacade;
    }

    public void setSouvenir(Souvenir souvenir) {
        this.souvenir = souvenir;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Edit Data about Souvenir");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        Label nameLabel = new Label("Name:");
        GridPane.setConstraints(nameLabel, 0, 0);
        TextField nameField = new TextField(souvenir.getName());
        nameField.setPromptText("Enter souvenir name");
        GridPane.setConstraints(nameField, 1, 0);

        Label producerLabel = new Label("Producers:");
        GridPane.setConstraints(producerLabel, 0, 1);
        TextField producerField = new TextField(souvenir.getProducers().stream().map(Producer::getName).collect(Collectors.joining(", ")));
        producerField.setPromptText("Enter producer names separated by commas");
        GridPane.setConstraints(producerField, 1, 1);

        Label releaseDateLabel = new Label("Release Date:");
        GridPane.setConstraints(releaseDateLabel, 0, 2);
        TextField releaseDateField = new TextField(souvenir.getReleaseDate().toString());
        releaseDateField.setPromptText("YYYY-MM-DD");
        GridPane.setConstraints(releaseDateField, 1, 2);

        Label priceLabel = new Label("Price:");
        GridPane.setConstraints(priceLabel, 0, 3);
        TextField priceField = new TextField(String.valueOf(souvenir.getPrice()));
        priceField.setPromptText("Enter price");
        GridPane.setConstraints(priceField, 1, 3);

        Button saveButton = new Button("Save");
        GridPane.setConstraints(saveButton, 1, 4);
        saveButton.setOnAction(event -> {
            String newName = nameField.getText();
            List<Producer> newProducers = Stream.of(producerField.getText().split(","))
                    .map(String::trim)
                    .map(name -> dataManagerFacade.getAllProducers().stream()
                            .filter(producer -> producer.getName().equalsIgnoreCase(name))
                            .findFirst().orElseGet(() -> new Producer(name, "")))
                    .collect(Collectors.toList());
            LocalDate newReleaseDate = LocalDate.parse(releaseDateField.getText());
            double newPrice = Double.parseDouble(priceField.getText());

            dataManagerFacade.editSouvenir(souvenir, newName, newProducers, newReleaseDate, newPrice);
            primaryStage.close();
        });

        grid.getChildren().addAll(nameLabel, nameField, producerLabel, producerField, releaseDateLabel, releaseDateField, priceLabel, priceField, saveButton);
        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
