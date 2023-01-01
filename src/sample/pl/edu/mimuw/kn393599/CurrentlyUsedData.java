package sample.pl.edu.mimuw.kn393599;

import sample.pl.edu.mimuw.kn393599.ApixuJSON.ApixuData;
import sample.pl.edu.mimuw.kn393599.OpenweatherJSON.OpenWeatherData;
import sample.pl.edu.mimuw.kn393599.OpenweatherJSON.Sys;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CurrentlyUsedData {
    private final DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd:MM:yyyy");
    private String temp, pressure, clouds, windSpeed, humm, country;
    private String visibility, lastUpdate, windDir, text, city;
    private Date date;

    public String getVisibility() {
        return visibility;
    }

    public String getTemp() {
        return temp;
    }

    public String getPressure() {
        return pressure;
    }

    public String getClouds() {
        return clouds;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getHumm() {
        return humm;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public String getWindDir() {
        return windDir;
    }

    public String getText() {
        return text;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    //Wczytywanie danych z danego źródła
    public void set(ApixuData data) {
        date = new Date();
        lastUpdate = dateFormat.format(date);
        if (data.getCurrent().getTemp_c() == null || data.getCurrent().getTemp_c().isEmpty()) {
            temp = "-";
        } else {
            temp = data.getCurrent().getTemp_c() + " \u00b0" + "C";
        }
        if (data.getCurrent().getPressure_mb() == null || data.getCurrent().getPressure_mb().isEmpty()) {
            pressure = "-";
        } else {
            pressure = data.getCurrent().getPressure_mb() + "hPa";
        }
        if (data.getCurrent().getHumidity() == null || data.getCurrent().getHumidity().isEmpty()) {
            humm = "-";
        } else {
            humm = data.getCurrent().getHumidity() + " %";
        }
        if (data.getCurrent().getCloud() == null || data.getCurrent().getCloud().isEmpty()) {
            clouds = "-";
        } else {
            clouds = data.getCurrent().getCloud() + " %";
        }
        if (data.getCurrent().getWind_kph() == null || data.getCurrent().getWind_kph().isEmpty()) {
            windSpeed = "-";
        } else {
            windSpeed = data.getCurrent().getWind_kph() + " km/h";
        }
        if (data.getCurrent().getWind_dir() == null || data.getCurrent().getWind_dir().isEmpty()) {
            windDir = "-";
        } else {
            windDir = data.getCurrent().getWind_dir();
        }
        if (data.getCurrent().getVis_km() == null || data.getCurrent().getVis_km().isEmpty()) {
            visibility = "-";
        } else {
            visibility = data.getCurrent().getVis_km() + " km";
        }
        if (data.getCurrent().getCondition().getText() == null || data.getCurrent().getCondition().getText().isEmpty()) {
            temp = "-";
        } else {
            text = data.getCurrent().getCondition().getText();
        }
        if (data.getLocation().getName() == null || data.getLocation().getName().isEmpty()) {
            city = "-";
        } else {
            city = data.getLocation().getName();
        }
        if (data.getLocation().getCountry() == null || data.getLocation().getCountry().isEmpty()) {
            country = "-";
        } else {
            country = data.getLocation().getCountry();
        }
    }

    public void set(OpenWeatherData data) {
        date = new Date();
        lastUpdate = dateFormat.format(date);
        if (data.getMain().getTemp() == null || data.getMain().getTemp().isEmpty()) {
            temp = "-";
        } else {
            temp = Double.toString(Math.round(Double.parseDouble(data.getMain().getTemp()) - 273.15)) + " \u00b0" + "C";
        }
        if (data.getMain().getPressure() == null || data.getMain().getPressure().isEmpty()) {
            pressure = "-";
        } else {
            pressure = data.getMain().getPressure() + " hPa";
        }
        if (data.getMain().getHumidity() == null || data.getMain().getHumidity().isEmpty()) {
            humm = "-";
        } else {
            humm = data.getMain().getHumidity() + " %";
        }
        if (data.getClouds().getAll() == null || data.getClouds().getAll().isEmpty()) {
            clouds = "-";
        } else {
            clouds = data.getClouds().getAll() + " %";
        }
        if (data.getWind().getSpeed() == null || data.getWind().getSpeed().isEmpty()) {
            windSpeed = "-";
        } else {
            windSpeed = Double.toString(Math.round(Double.parseDouble(data.getWind().getSpeed()) * 3.6)) + " km/h";
        }
        if (data.getVisibility() == null || data.getVisibility().isEmpty()) {
            visibility = "-";
        } else {
            visibility = Double.toString(Math.round(Double.parseDouble(data.getVisibility()) * 0.001)) + " km";
        }
        if (data.getWeather()[0].getMain() == null || data.getWeather()[0].getMain().isEmpty()) {
            text = "-";
        } else {
            text = data.getWeather()[0].getMain();
        }
        if (data.getName() == null || data.getName().isEmpty()) {
            city = "-";
        } else {
            city = data.getName();
        }
        if (data.getSys().getCountry() == null || data.getSys().getCountry().isEmpty()) {
            country = "-";
        } else {
            country = data.getSys().getCountry();
        }
        if (data.getWind().getDeg() == null || data.getWind().getDeg().isEmpty()) {
            windDir = "-";
        } else {
            double dir = Double.parseDouble(data.getWind().getDeg());
            //Konwerja stopni na kierunki wiatru
            if (dir >= 348.75 || dir < 11.25) {
                windDir = "N";
            } else if (dir >= 11.25 && dir < 33.75) {
                windDir = "NNE";
            } else if (dir >= 33.75 && dir < 56.25) {
                windDir = "NE";
            } else if (dir >= 56.75 && dir < 78.75) {
                windDir = "NNE";
            } else if (dir >= 78.75 && dir < 101.25) {
                windDir = "E";
            } else if (dir >= 101.25 && dir < 123.75) {
                windDir = "ESE";
            } else if (dir >= 123.75 && dir < 146.25) {
                windDir = "SE";
            } else if (dir >= 146.25 && dir < 168.75) {
                windDir = "SSE";
            } else if (dir >= 168.75 && dir < 191.25) {
                windDir = "S";
            } else if (dir >= 191.25 && dir < 213.75) {
                windDir = "SSW";
            } else if (dir >= 213.75 && dir < 236.25) {
                windDir = "SW";
            } else if (dir >= 236.25 && dir < 258.75) {
                windDir = "WSW";
            } else if (dir >= 258.75 && dir < 281.25) {
                windDir = "W";
            } else if (dir >= 281.25 && dir < 303.75) {
                windDir = "WNW";
            } else if (dir >= 303.75 && dir < 326.25) {
                windDir = "NW";
            } else if (dir >= 326.25 && dir < 348.75) {
                windDir = "NNW";
            }
        }
    }

    public CurrentlyUsedData(ApixuData data) {
        this.set(data);
    }

    public CurrentlyUsedData(OpenWeatherData data) {
        this.set(data);
    }
}
