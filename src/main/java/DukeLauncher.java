import duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ui.Ui;

import java.io.IOException;

/**
 * A launcher class.
 */
public class DukeLauncher {
    public static void main(String[] args) {
        Application.launch(DukeApplication.class, args);
    }

    public static class DukeApplication extends Application {
        private static final String PATH_FILE_TASK_LIST = System.getProperty("user.dir") + "/data/duke.txt";

        @Override
        public void start(Stage stage) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/MainWindow.fxml"));
                AnchorPane ap = fxmlLoader.load();

                Ui ui = fxmlLoader.getController();
                ui.setDuke(new Duke(ui, PATH_FILE_TASK_LIST));

                stage.setScene(new Scene(ap));
                stage.setTitle("Duke");
                stage.setResizable(false);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}