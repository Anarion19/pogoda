package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
    private Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root, 600, 300);
        scene.getStylesheets().add("sample/main.css");
        primaryStage.setTitle("Weather");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop()
    {
        try
        {
            super.stop();
            if(controller != null)
            {
                controller.kill();
            }

            Platform.exit();
            System.exit(0);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void main(String[] args)
    {
        launch(args);
    }
}
