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
						"eBUq0Zo2TZE:APA91bHr7y4tOAwj_j2sw7GQr0Tdki0NgsL9Tf3aK7yk0h3G9rK5Ix7S0eNSYmYHGWM0GlP8PM-cMH7xpRebNLbBGg9m62EmARwvolBSaEynHmJz0KXX4Ann5e6zmjD4SiGDju45hV3t"
					);

//				TopicManagementResponse response = FirebaseMessaging.getInstance().subscribeToTopic(
//					    registrationTokens, "updates");
//				
//				System.out.println(response.getSuccessCount() + " tokens were subscribed successfully");


//				TopicManagementResponse response = FirebaseMessaging.getInstance().unsubscribeFromTopic(
//					    registrationTokens, "updates");
//					// See the TopicManagementResponse reference documentation
//					// for the contents of response.
//					System.out.println(response.getSuccessCount() + " tokens were unsubscribed successfully");

				String jaja = "easy? ";
				String jak = "abby?";
				
				String berety = jaja.concat(jak);
				
				String topic = "updates";
				
//				 while(true) {
//					 
//										 
//					 for (int i= 0; i<20; i++) {
//					 
//					 if (i%2==0) {
//						
//						 String ejee = Integer.toString(i);
				
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
//								String tempSTr = Integer.toString(tempInt);
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
				
				
	
	

