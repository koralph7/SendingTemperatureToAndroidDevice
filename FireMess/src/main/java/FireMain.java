import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.json.simple.parser.ParseException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.google.firebase.messaging.TopicManagementResponse;

public class FireMain {

	public static void main(String[] args) throws IOException, FirebaseMessagingException, InterruptedException, ParseException {
		
		
		FileInputStream serviceAccount =
				  new FileInputStream("src/main/pushnotif.json");

				FirebaseOptions options = new FirebaseOptions.Builder()
				  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
				  .setDatabaseUrl("https://pushnotif-65650.firebaseio.com")
				  .build();

				FirebaseApp.initializeApp(options);
		
				
				List<String> registrationTokens = Arrays.asList(
						"SECRET_TOKEN"
					);



		
				
				while (true) {
						 
						 WeatherReader weatherReader = new WeatherReader();
						 
						 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
							Date date = new Date();
							String time = dateFormat.format(date);
							
							String weather = weatherReader.checkTemp();
							
							double tempInt = Double.parseDouble(weather);
							
							System.out.println(tempInt);
							
							if (tempInt > 12) {
//								
//								
//								
					Message message = Message.builder()
						.setNotification(new Notification(time, "Temperatura" + weatherReader.checkTemp()))
						.setTopic(topic)
						.build();
								
								

					// Send a message to the devices subscribed to the provided topic.
					String response = FirebaseMessaging.getInstance().send(message);
					// Response is a message ID string.
					System.out.println("Successfully sent message: " + response);
								
								
							}
						 
					else {
								
							
							
					Message message = Message.builder()
						.setNotification(new Notification("temperatura nie zmienia siê ", ""))
						.setTopic(topic)
						.build();	 
							
							

						// Send a message to the devices subscribed to the provided topic.
						String response = FirebaseMessaging.getInstance().send(message);
						// Response is a message ID string.
						System.out.println("Successfully sent message: " + response);		
							}
						 Thread.sleep(10000);
					}
					 
				 
	}}
				
				
	
	

