package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import sample.pl.edu.mimuw.kn393599.CurrentlyUsedData;
import sample.pl.edu.mimuw.kn393599.GetWeatherInfo;
import sample.pl.edu.mimuw.kn393599.OpenweatherJSON.Sys;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class Controller implements Initializable
{
    private String city = "Warszawa";
    private boolean source = true;
    private Timer timer = new Timer();
    private CurrentlyUsedData data;

    @FXML
    private Label text, humm, press, visibility, windSpeed, windDir, temp, update, clouds, api, local;
    @FXML
    private Button refresh;
    @FXML
    private TextField input;
    @FXML
    private MenuButton menu;
    @FXML
    private MenuItem open, apixu;

    //wczytanie lokalizacji z TextField
    public void changeCity(ActionEvent e)
    {
        if(input.getText() != null && !input.getText().isEmpty())
        {
            city = input.getText();
        }
    }

    //Pobranie danych i wypisanie w oknie
    public void write(ActionEvent event)
    {
        try
        {
            if(source)
            {
                data = new CurrentlyUsedData(GetWeatherInfo.getOpenWeatherInfo(city));
                api.setText("OpenWeather");
            }
            else
            {
                data = new CurrentlyUsedData(GetWeatherInfo.getApixuWeatherInfo(city));
                api.setText("APIXU Weather");
            }

            text.setText(data.getText());
            humm.setText("Hummidity: " + data.getHumm());
            press.setText("Pressure: " + data.getPressure());
            visibility.setText("Visibility: " + data.getVisibility());
            windSpeed.setText("Wind speed: " + data.getWindSpeed());
            windDir.setText("Wind direction: " + data.getWindDir());
            update.setText(data.getLastUpdate());
            temp.setText(data.getTemp());
            clouds.setText("Clouds: " + data.getClouds());
            local.setText(data.getCity() + ", " + data.getCountry());
        }
        catch (IOException e)
        {
            alert("Cannot download data from the server");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        this.write(new ActionEvent());
        //Wczytanie danych i odświerzenie okna po wpisaniu lokalizacji do TextField
        input.setOnKeyPressed(keyEvent ->
        {
            if(keyEvent.getCode().equals(KeyCode.ENTER))
            {
                if( !input.getText().isEmpty() && input.getText() != null)
                {
                    city = input.getText();
                    this.write(new ActionEvent());
                }
            }
        });

        //Automatyczne odświerzanie
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run()
            {
                javafx.application.Platform.runLater(new Runnable() {
                    @Override
                    public void run()
                    {
                        write(new ActionEvent());
                    }
                });
            }
        }, 0, 2000);
    }

    private void alert(String message)
    {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Error");
        errorAlert.setContentText(message);
        errorAlert.showAndWait();
        timer.cancel();
    }

    public void sourceOpen(ActionEvent e)
    {
        source = true;
        refreshWindow(new ActionEvent());
        apixu.setStyle("-fx-background-color: white;");
        open.setStyle("-fx-background-color: cornflowerblue;");
    }

    public void sourceApixu(ActionEvent e)
    {
        source = false;
        refreshWindow(new ActionEvent());
        open.setStyle("-fx-background-color: white;");
        apixu.setStyle("-fx-background-color: cornflowerblue;");
    }

    public void refreshWindow(ActionEvent e)
    {
        changeCity(new ActionEvent());
        write(new ActionEvent());
    }

    public void kill()
    {
        timer.cancel();
    }
}
