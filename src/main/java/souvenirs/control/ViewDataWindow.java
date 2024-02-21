package souvenirs.control;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

public class ViewDataWindow extends Application {
    private final DataManagerFacade dataManagerFacade;
    private TableView<Souvenir> tableView;

    public ViewDataWindow(DataManagerFacade dataManagerFacade) {
        this.dataManagerFacade = dataManagerFacade;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("View Data about Souvenir");

        tableView = new TableView<>();
        ObservableList<Souvenir> souvenirs = FXCollections.observableArrayList(dataManagerFacade.getAllSouvenirs());

        TableColumn<Souvenir, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Souvenir, List<Producer>> producersColumn = new TableColumn<>("Producers");
        producersColumn.setCellValueFactory(new PropertyValueFactory<>("producers"));

        TableColumn<Souvenir, LocalDate> releaseDateColumn = new TableColumn<>("Release Date");
        releaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));

        TableColumn<Souvenir, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Souvenir, Void> editButtonColumn = new TableColumn<>("Edit");
        editButtonColumn.setCellFactory(param -> new TableCell<>() {
            private final Button editButton = new Button("Edit");

            {
                editButton.setOnAction(event -> {
                    Souvenir souvenir = getTableView().getItems().get(getIndex());
                    editSouvenir(souvenir);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(editButton);
                }
            }
        });

        TableColumn<Souvenir, Void> deleteButtonColumn = new TableColumn<>("Delete");
        deleteButtonColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setOnAction(event -> {
                    Souvenir souvenir = getTableView().getItems().get(getIndex());
                    deleteSouvenir(souvenir);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });

        tableView.getColumns().addAll(nameColumn, producersColumn, releaseDateColumn, priceColumn, editButtonColumn, deleteButtonColumn);
        tableView.setItems(souvenirs);

        VBox vbox = new VBox(tableView);
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);

        Scene scene = new Scene(vbox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void editSouvenir(Souvenir souvenir) {
        EditDataWindow editDataWindow = new EditDataWindow(dataManagerFacade);
        editDataWindow.setSouvenir(souvenir);
        editDataWindow.start(new Stage());
    }

    private void deleteSouvenir(Souvenir souvenir) {
        dataManagerFacade.deleteSouvenir(souvenir);
        tableView.getItems().remove(souvenir);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
