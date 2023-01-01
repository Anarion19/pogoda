package sample.pl.edu.mimuw.kn393599;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import sample.pl.edu.mimuw.kn393599.ApixuJSON.ApixuData;
import sample.pl.edu.mimuw.kn393599.OpenweatherJSON.OpenWeatherData;

public class GetWeatherInfo
{
    private static final String ApiKey = "&appid=9567b63da9a5517fc70a3577397fe920";
    private static final String openWeatherURL = "http://api.openweathermap.org/data/2.5/weather?q=";

    private static final String apixuURL = "http://api.apixu.com/v1/current.json?key=5b24c32a3a4a4900ab885632181306&q=";

    public static boolean connectionCheck()
    {
        try
        {
            final URL url = new URL("http://www.google.com");
            final URLConnection conn = url.openConnection();
            conn.connect();
            conn.getInputStream().close();
            return true;
        }
        catch (MalformedURLException e)
        {
            throw new RuntimeException(e);
        }
        catch (IOException e)
        {
            return false;
        }
    }
    public static OpenWeatherData getOpenWeatherInfo(String cityName) throws IOException
    {
        URL oracle = new URL(openWeatherURL + cityName + ApiKey);

        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        String inputLine = in.readLine();
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        OpenWeatherData p = gson.fromJson(inputLine, OpenWeatherData.class);

        yc.getInputStream().close();

        return p;
    }

    public static ApixuData getApixuWeatherInfo(String cityName) throws IOException
    {
        URL oracle = new URL(apixuURL + cityName);

        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        String inputLine = in.readLine();
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        ApixuData p = gson.fromJson(inputLine, ApixuData.class);

        yc.getInputStream().close();

        return p;
    }

}
