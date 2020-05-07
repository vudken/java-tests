package stepDefs;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Main;
import model.Response;
import model.Sys;
import model.Weather;
import requesters.WeatherRequester;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherStepDefs {

    private String cityName;
    private String countryName;
    private Response response;

    @Given("city {string}")
    public void set_city(String cityName) {
        this.cityName = cityName;
    }

    @Given("country {string}")
    public void set_country(String countryName) {
        this.countryName = countryName;
    }

    @When("we are requesting weather data")
    public void request_weather() throws JsonProcessingException {
        WeatherRequester requester = new WeatherRequester();
        response = requester.requestWeather(cityName, countryName);
    }

    @Then("coord are:")
    public void check_coord(Map<String, Float> args) {
        assertEquals(args.get("lon"), response.getCoord().getLon(), "Wrong longitude");
        assertEquals(args.get("lat"), response.getCoord().getLat(), "Wrong latitude");
    }

    @Then("weather is:")
    public void check_weather(Map<String, String> args) {
        Weather weather = response.getWeathers().get(0);
        assertEquals(Long.parseLong(args.get("id")), weather.getId(), "Wrong weather id");
        assertEquals(args.get("main"), weather.getMain(), "Wrong main weather");
        assertEquals(args.get("description"), weather.getDescription(), "Wrong weather description");
        assertEquals(args.get("icon"), weather.getIcon(), "Wrong weather icon");
    }

    @Then("base is: {string}")
    public void check_base(String base) {
        assertEquals(base, response.getBase(), "Wrong base");
    }

    @Then("main is:")
    public void check_main(Map <String, Float> args) {
        Main main = response.getMain();
        assertEquals(args.get("temp"), main.getTemp(), "Wrong temp");
        assertEquals(args.get("pressure"), main.getPressure(), "Wrong pressure");
        assertEquals(args.get("humidity"), main.getHumidity(), "Wrong humidity");
        assertEquals(args.get("temp_min"), main.getTemp_min(), "Wrong min temp");
        assertEquals(args.get("temp_max"), main.getTemp_max(), "Wrong max temp");
    }

    @Then("visibility is: {long}")
    public void check_visibility(long value) {
        assertEquals(value, response.getVisibility(), "Wrong visibility value");
    }

    @Then("clouds are: {long}")
    public void check_clouds(long value) {
        assertEquals(value, response.getClouds().getAll(), "Wrong clouds value");
    }

    @Then("dt is: {long}")
    public void check_dt(long value) {
        assertEquals(value, response.getDt(), "Wrong dt value");
    }

    @Then("wind is:")
    public void check_wind(Map <String, Float> args) {
        assertEquals(args.get("speed"), response.getWind().getSpeed(), "Wrong wind speed");
        assertEquals(args.get("deg"), response.getWind().getDeg(), "Wrong wind deg");
    }

    @Then("sys is:")
    public void check_sys(Map<String, String> args) {
        Sys sys = response.getSys();
        assertEquals(Long.parseLong(args.get("type")), sys.getType(), "Wrong sys type");
        assertEquals(Long.parseLong(args.get("id")), sys.getId(), "Wrong sys id");
        assertEquals(Float.valueOf(args.get("message")), sys.getMessage(), "Wrong message");
        assertEquals(args.get("country"), sys.getCountry(), "Wrong country");
        assertEquals(Long.parseLong(args.get("sunrise")), sys.getSunrise(), "Wrong sunrise");
        assertEquals(Long.parseLong(args.get("sunset")), sys.getSunset(), "Wrong sunset");
    }

    @Then("id is: {long}")
    public void check_id(long id) {
        assertEquals(id, response.getId(), "Wrong id");
    }

    @Then("name is: {string}")
    public void check_name(String name) {
        assertEquals(name, response.getName(), "Wrong name");
    }

    @Then("cod is: {long}")
    public void check_cod(long cod) {
        assertEquals(cod, response.getCod(), "Wrong cod value");
    }












//////


//    @Then("wind deg is: {long}")
//    public void check_wind_deg(long value) {
//    }
//    }
//    @Then("weather is: {string}")
//    public void check_weather(String str) {
//    }
//    @Then("weather description is: {string}")
//    public void check_weather_description(String str) {
//    }
//    @Then("weather icon is: {string}")
//    public void check_weather_icon(String str) {
//    }
//
//
//    @Then("pressure is: {long}")
//    public void check_pressure(long press) {
//    }
//    @Then("humidity is: {long}")
//    public void check_humidity(float humidity) {
//    }
//    @Then("min temp is: {float}")
//    public void check_min_temp(float temp) {
//    }
//    @Then("max temp is: {float}")
//    public void check_max_temp(float temp) {
//    }
//
//
//    @Then("sys_id is: {long}")
//    public void check_sys_id(long id) {
//    }
//    @Then("message is: {float}")
//    public void check_message(float msg) {
//    }
//    @Then("country is: {string}")
//    public void check_country(String country) {
//    }
//    @Then("sunrise is {long}")
//    public void check_sunrise(long sunrise) {
//    }
//    @Then("sunset is {long}")
//    public void check_sunset(long sunset) {
//    }
//
//
}
