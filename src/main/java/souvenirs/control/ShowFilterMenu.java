package souvenirs.control;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ShowFilterMenu extends Application {
    private final DataManagerFacade dataManagerFacade;

    public ShowFilterMenu(DataManagerFacade dataManagerFacade) {
        this.dataManagerFacade = dataManagerFacade;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Filter menu");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        Label producerLabel = new Label("Producer:");
        GridPane.setConstraints(producerLabel, 0, 1);
        TextField producerInput = new TextField();
        producerInput.setPromptText("Enter producer name");
        GridPane.setConstraints(producerInput, 1, 1);

        Button filterByProducerButton = new Button("Filter by Producer");
        filterByProducerButton.setOnAction(event -> {
            grid.getChildren().addAll(producerLabel, producerInput);
            String producerName = producerInput.getText();
            dataManagerFacade.displaySouvenirsByProducer(producerName);
        });
        GridPane.setConstraints(filterByProducerButton, 0, 0);

        Label countryLabel = new Label("Country:");
        GridPane.setConstraints(countryLabel, 0, 1);
        TextField countryNameInput = new TextField();
        countryNameInput.setPromptText("Enter country ");
        GridPane.setConstraints(countryNameInput, 1, 1);

        Button filterByCountryButton = new Button("Filter by Country");
        filterByCountryButton.setOnAction(event -> {
            grid.getChildren().addAll(countryLabel, countryNameInput);
            String countryName = countryNameInput.getText();
            dataManagerFacade.displaySouvenirsByCountry(countryName);
        });
        GridPane.setConstraints(filterByCountryButton, 1, 0);

        Label yearLabel = new Label("Year:");
        GridPane.setConstraints(yearLabel, 0, 1);
        TextField yearInput = new TextField();
        yearInput.setPromptText("Enter year ");
        GridPane.setConstraints(yearInput, 1, 1);

        Button filterSouvenirsByYear = new Button("Filter by Year");
        filterSouvenirsByYear.setOnAction(event -> {
            grid.getChildren().addAll(yearLabel, yearInput);
            int year = Integer.parseInt(yearInput.getText());
            dataManagerFacade.displaySouvenirsByYear(year);
        });
        GridPane.setConstraints(filterSouvenirsByYear, 0, 1);

        Label priceLabel = new Label("Price:");
        GridPane.setConstraints(priceLabel, 0, 1);
        TextField priceInput = new TextField();
        priceInput.setPromptText("Enter price ");
        GridPane.setConstraints(priceInput, 1, 1);

        Button filterProducersWithPriceBelow = new Button("Filter by Price");
        filterProducersWithPriceBelow.setOnAction(event -> {
            grid.getChildren().addAll(priceLabel, priceInput);
            int price = Integer.parseInt(priceInput.getText());
            dataManagerFacade.displayProducersWithPriceBelow(price);
        });
        GridPane.setConstraints(filterProducersWithPriceBelow, 1, 1);

        Button filterAllProducersWithSouvenirs = new Button("Filter by all Producer's souvenirs");
        filterAllProducersWithSouvenirs.setOnAction(event -> dataManagerFacade.displayAllProducersWithSouvenirs());
        GridPane.setConstraints(filterAllProducersWithSouvenirs, 0, 2);

        grid.getChildren().addAll(filterByProducerButton, filterByCountryButton, filterSouvenirsByYear, filterProducersWithPriceBelow, filterAllProducersWithSouvenirs);
        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

