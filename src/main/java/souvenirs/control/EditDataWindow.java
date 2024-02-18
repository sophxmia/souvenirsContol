package souvenirs.control;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
public class EditDataWindow extends Application{
    private final DataManagerFacade dataManagerFacade;

    public EditDataWindow(DataManagerFacade dataManagerFacade) {
        this.dataManagerFacade = dataManagerFacade;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Edit Data about Souvenir");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
