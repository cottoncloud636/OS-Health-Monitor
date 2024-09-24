import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;




public class JavaFXApp extends Application{
    private SystemMonitor systemMonitor = new SystemMonitor();

    @override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Battery Monitor: ");
        Label label = new Label("Battery Information: ");

        TextArea batteryInfoArea = new TextArea();
        batteryInfoArea.setEditable(false);

        Button refreshButton = new Button("Refresh");
        // | JavaFX event handler that uses a lambda expression to define when refreshButton is clicked, fetch the
        //   latest battery information
        refreshButton.setOnAction(e -> {
            String batteryInfo = systemMonitor.getBatteryInfo();
            batteryInfoArea.setText(batteryInfo);
        })
    }
}
