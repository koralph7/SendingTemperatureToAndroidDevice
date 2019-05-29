import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.util.Locale;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class WeatherReader {


public String checkTemp() throws ParseException {
		
		
		
		Client client=ClientBuilder.newClient();
		
		String name = client.target("http://api.openweathermap.org/data/2.5/weather/").queryParam("q","Gdansk,PL").queryParam("appid","0d59e3528f50d460ce3953f320ffb07e")
				.request(MediaType.APPLICATION_JSON).get(String.class);
		
//		System.out.println(name);
		
		client.close();

		JSONObject object = (JSONObject) new JSONParser().parse(name);
		JSONObject main = (JSONObject) object.get("main");
		String temp = main.get("temp").toString();
		
		double temporaryTemp = Double.parseDouble(temp); 
		
		
		
		double cTemp = (temporaryTemp - 273.15);
		
		DecimalFormat df = new DecimalFormat("#.00",
                DecimalFormatSymbols.getInstance(Locale.US));
		String formatt = df.format(cTemp);
		
		return formatt;
}}