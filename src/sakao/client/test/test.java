package sakao.client.test;

import java.io.IOException;
import java.util.ArrayList;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;

import sakao.common.Bollard;
import sakao.common.JsonToSend;
import sakao.common.Request;
import sakao.common.SmartCity;
import sakao.server.smartCityServices;

public class test {

	public static void main(String[] args) throws IOException, JSONException {
		
		
		JsonToSend app = new JsonToSend();
		// app.RequestToSend("file-for-test/InitVehiculesSensor.json");
		// Request request = new Request(SELECT_ALL, "smartcity");

		Request request = new Request("SELECT_ALL", "bollard");

		ArrayList<String> al = app.sendMessageToServer(request);
		
		System.out.println(al);

		String s = al.get(0) + "\n";
	//A FAIRE PROBLEME AVEC AL
		System.out.println(s);

		Bollard req1 = new ObjectMapper().readValue(s, Bollard.class);
		
		System.out.println(req1.toString());
	
		
		System.out.println("//////");

		Request request2 = new Request("SELECT_ALL", "smartcity");

		ArrayList<String> al2 = app.sendMessageToServer(request2);
		
		System.out.println(al2);

		String s2 = al2.get(0) + "\n";
	//A FAIRE PROBLEME AVEC AL
		System.out.println(s2);

		SmartCity req2 = new ObjectMapper().readValue(s2, SmartCity.class);
		
		System.out.println(req2.toString());		
		
		
	}

}
