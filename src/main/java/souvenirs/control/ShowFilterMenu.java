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

        Button filterByProducerButton = new Button("Filter by Producer");
        filterByProducerButton.setOnAction(event -> {
            Label producerLabel = new Label("Producer:");
            GridPane.setConstraints(producerLabel, 0, 1);
            TextField producerInput = new TextField();
            producerInput.setPromptText("Enter producer name");
            GridPane.setConstraints(producerInput, 1, 1);
            String producerName = producerInput.getText();
            dataManagerFacade.displaySouvenirsByProducer(producerName);
        });
        GridPane.setConstraints(filterByProducerButton, 0, 0);

        Button filterByCountryButton = new Button("Filter by Country");
        filterByCountryButton.setOnAction(event -> {
            Label countryLabel = new Label("Country:");
            GridPane.setConstraints(countryLabel, 0, 1);
            TextField countryNameInput = new TextField();
            countryNameInput.setPromptText("Enter country ");
            String countryName = countryNameInput.getText();
            GridPane.setConstraints(countryNameInput, 1, 1);
            dataManagerFacade.displaySouvenirsByCountry(countryName);
        });
        GridPane.setConstraints(filterByCountryButton, 1, 0);


        Button filterSouvenirsByYear = new Button("Filter by Year");
        filterSouvenirsByYear.setOnAction(event -> {
            Label yearLabel = new Label("Year:");
            GridPane.setConstraints(yearLabel, 0, 1);
            TextField yearInput = new TextField();
            yearInput.setPromptText("Enter country ");
            int year = Integer.parseInt(yearInput.getText());
            GridPane.setConstraints(yearInput, 1, 1);
            dataManagerFacade.displaySouvenirsByYear(year);
        });
        GridPane.setConstraints(filterSouvenirsByYear, 0, 1);


        Button filterProducersWithPriceBelow = new Button("Filter by Price");
        filterProducersWithPriceBelow.setOnAction(event -> {
            Label priceLabel = new Label("Price:");
            GridPane.setConstraints(priceLabel, 0, 1);
            TextField priceInput = new TextField();
            priceInput.setPromptText("Enter country ");
            int price = Integer.parseInt(priceInput.getText());
            GridPane.setConstraints(priceInput, 1, 1);
            dataManagerFacade.displayProducersWithPriceBelow(price);
        });
        GridPane.setConstraints(filterProducersWithPriceBelow, 1, 1);

        Button filterAllProducersWithSouvenirs = new Button("Filter by all Producer's souvenirs");
        filterAllProducersWithSouvenirs.setOnAction(event -> {
            dataManagerFacade.displayAllProducersWithSouvenirs();
        });
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

