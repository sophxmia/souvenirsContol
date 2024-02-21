package souvenirs.control;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ShowFilterMenu extends Application {
    private final DataManagerFacade dataManagerFacade;

    public ShowFilterMenu(DataManagerFacade dataManagerFacade) {
        this.dataManagerFacade = dataManagerFacade;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Filter menu");

        VBox mainLayout = new VBox(10);
        mainLayout.setPadding(new Insets(10));

        Button filterByProducerButton = new Button("Filter by Producer");
        filterByProducerButton.setOnAction(event -> showFilterWindowForString("Producer", "Enter producer name", dataManagerFacade::displaySouvenirsByProducer));

        Button filterByCountryButton = new Button("Filter by Country");
        filterByCountryButton.setOnAction(event -> showFilterWindowForString("Country", "Enter country name", dataManagerFacade::displaySouvenirsByCountry));

        Button filterByYearButton = new Button("Filter by Year");
        filterByYearButton.setOnAction(event -> showFilterWindowForInt("Year", "Enter year", dataManagerFacade::displaySouvenirsByYear));

        Button filterByPriceButton = new Button("Filter by Price");
        filterByPriceButton.setOnAction(event -> showFilterWindowForInt("Price", "Enter price", dataManagerFacade::displayProducersWithPriceBelow));

        Button filterProducersOfSouvenirInYearButton = new Button("Filter Producers of Souvenir in Year");
        filterProducersOfSouvenirInYearButton.setOnAction(event -> {
            showFilterWindowForStringAndInt("Producers of Souvenir in Year", "Enter souvenir name", "Enter year",
                    dataManagerFacade::displayProducersOfSouvenirInYear);
        });

        Button filterAllProducersWithSouvenirsButton = new Button("Filter by all Producer's souvenirs");
        filterAllProducersWithSouvenirsButton.setOnAction(event -> dataManagerFacade.displayAllProducersWithSouvenirs());

        mainLayout.getChildren().addAll(filterByProducerButton, filterByCountryButton, filterByYearButton, filterByPriceButton,filterProducersOfSouvenirInYearButton, filterAllProducersWithSouvenirsButton);

        Scene scene = new Scene(mainLayout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showFilterWindowForString(String label, String prompt, FilterFunctionForString filterFunction) {
        Stage filterStage = new Stage();
        filterStage.setTitle("Filter by " + label);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(5);
        grid.setHgap(5);

        Label filterLabel = new Label(label + ":");
        GridPane.setConstraints(filterLabel, 0, 0);
        TextField filterInput = new TextField();
        filterInput.setPromptText(prompt);
        GridPane.setConstraints(filterInput, 1, 0);

        Button applyFilterButton = new Button("Apply Filter");
        applyFilterButton.setOnAction(event -> {
            String filterValue = filterInput.getText();
            filterFunction.apply(filterValue);
            filterStage.close();
        });
        GridPane.setConstraints(applyFilterButton, 0, 1);

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> filterStage.close());
        GridPane.setConstraints(backButton, 1, 1);

        grid.getChildren().addAll(filterLabel, filterInput, applyFilterButton, backButton);

        Scene scene = new Scene(grid, 300, 100);
        filterStage.setScene(scene);
        filterStage.show();
    }

    private void showFilterWindowForInt(String label, String prompt, FilterFunctionForInt filterFunction) {
        Stage filterStage = new Stage();
        filterStage.setTitle("Filter by " + label);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(5);
        grid.setHgap(5);

        Label filterLabel = new Label(label + ":");
        GridPane.setConstraints(filterLabel, 0, 0);
        TextField filterInput = new TextField();
        filterInput.setPromptText(prompt);
        GridPane.setConstraints(filterInput, 1, 0);

        Button applyFilterButton = new Button("Apply Filter");
        applyFilterButton.setOnAction(event -> {
            int filterValue = Integer.parseInt(filterInput.getText());
            filterFunction.apply(filterValue);
            filterStage.close();
        });
        GridPane.setConstraints(applyFilterButton, 0, 1);

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> filterStage.close());
        GridPane.setConstraints(backButton, 1, 1);

        grid.getChildren().addAll(filterLabel, filterInput, applyFilterButton, backButton);

        Scene scene = new Scene(grid, 300, 100);
        filterStage.setScene(scene);
        filterStage.show();
    }

    private void showFilterWindowForStringAndInt(String label, String stringPrompt, String intPrompt, FilterFunctionForStringAndInt filterFunction) {
        Stage filterStage = new Stage();
        filterStage.setTitle("Filter by " + label);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(5);
        grid.setHgap(5);

        Label stringFilterLabel = new Label("Souvenir Name:");
        GridPane.setConstraints(stringFilterLabel, 0, 0);
        TextField stringFilterInput = new TextField();
        stringFilterInput.setPromptText(stringPrompt);
        GridPane.setConstraints(stringFilterInput, 1, 0);

        Label intFilterLabel = new Label("Year:");
        GridPane.setConstraints(intFilterLabel, 0, 1);
        TextField intFilterInput = new TextField();
        intFilterInput.setPromptText(intPrompt);
        GridPane.setConstraints(intFilterInput, 1, 1);

        Button applyFilterButton = new Button("Apply Filter");
        applyFilterButton.setOnAction(event -> {
            String souvenirName = stringFilterInput.getText();
            int year = Integer.parseInt(intFilterInput.getText());
            filterFunction.apply(souvenirName, year);
            filterStage.close();
        });
        GridPane.setConstraints(applyFilterButton, 0, 2);

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> filterStage.close());
        GridPane.setConstraints(backButton, 1, 2);

        grid.getChildren().addAll(stringFilterLabel, stringFilterInput, intFilterLabel, intFilterInput, applyFilterButton, backButton);

        Scene scene = new Scene(grid, 300, 150);
        filterStage.setScene(scene);
        filterStage.show();
    }


    @FunctionalInterface
    private interface FilterFunctionForString {
        void apply(String value);
    }

    @FunctionalInterface
    private interface FilterFunctionForInt {
        void apply(int value);
    }

    @FunctionalInterface
    private interface FilterFunctionForStringAndInt {
        void apply(String stringValue, int intValue);
    }

    public static void main(String[] args) {
        launch(args);
    }
}



