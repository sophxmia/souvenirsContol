package souvenirs.control;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

public class ViewDataWindow extends Application {
    private final DataManagerFacade dataManagerFacade;

    public ViewDataWindow(DataManagerFacade dataManagerFacade) {
        this.dataManagerFacade = dataManagerFacade;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("View Data about Souvenir");

        TableView<Souvenir> tableView = new TableView<>();
        ObservableList<Souvenir> souvenirs = FXCollections.observableArrayList(dataManagerFacade.getAllSouvenirs());

        TableColumn<Souvenir, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Souvenir, List<Producer>> producersColumn = new TableColumn<>("Producers");
        producersColumn.setCellValueFactory(new PropertyValueFactory<>("producers"));

        TableColumn<Souvenir, LocalDate> releaseDateColumn = new TableColumn<>("Release Date");
        releaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));

        TableColumn<Souvenir, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        tableView.getColumns().addAll(nameColumn, producersColumn, releaseDateColumn, priceColumn);
        tableView.setItems(souvenirs);

        VBox vbox = new VBox(tableView);
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);

        Scene scene = new Scene(vbox, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
