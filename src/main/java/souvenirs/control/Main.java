package souvenirs.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        Button deleteProducerAndSouvenirsButton = new Button("Delete Producer and Souvenirs");
        deleteProducerAndSouvenirsButton.setOnAction(event -> {
            Producer selectedProducer = getSelectedProducer();
            if (selectedProducer != null) {
                dataManagerFacade.deleteProducerAndSouvenirs(selectedProducer);
            }
        });

        VBox root = new VBox(10);
        root.getChildren().addAll(addSouvenirButton, addProducerButton, editDataButton, viewDataButton, filterButton, deleteProducerAndSouvenirsButton);
        Scene scene = new Scene(root, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Producer getSelectedProducer() {
        List<Producer> producers = dataManagerFacade.getAllProducers();

        // Створюємо список імен виробників для діалогового вікна
        List<String> producerNames = producers.stream()
                .map(Producer::getName)
                .collect(Collectors.toList());

        // Створюємо діалогове вікно з вибором виробника
        ChoiceDialog<String> dialog = new ChoiceDialog<>(producerNames.getFirst(), producerNames);
        dialog.setTitle("Select Producer");
        dialog.setHeaderText("Select a producer to delete");
        dialog.setContentText("Producer:");

        // Показуємо діалогове вікно і чекаємо на вибір виробника
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String selectedProducerName = result.get();
            // Знаходимо об'єкт виробника за ім'ям
            return producers.stream()
                    .filter(p -> p.getName().equals(selectedProducerName))
                    .findFirst()
                    .orElse(null);
        } else {
            // Якщо користувач не обрав виробника, повертаємо null
            return null;
        }
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