package requesters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Response;
import org.springframework.web.client.RestTemplate;

public class WeatherRequester {

    private final String PREFIX = "https://samples.openweathermap.org/data/2.5/weather?q=";
    private final String POSTFIX = "&appid=439d4b804bc8187953eb36d2a8c26a02";

    public Response requestWeather(String city, String country) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String url = PREFIX + city + "," + country.toLowerCase() + POSTFIX;
        String jsonString = restTemplate.getForEntity(url, String.class).getBody();//два параметра - адрес, куда надо стучаться API и объект, который хотим получить обратно
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonString, Response.class);
    }
}
