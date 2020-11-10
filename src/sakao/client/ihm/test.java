package sakao.client.ihm;

import java.io.IOException;
import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;

import sakao.common.JsonToSend;
import sakao.common.Request;
import sakao.common.ResultTreshold;
import sakao.common.Zone;



public class test {
	
	//private JsonToSend app;
	
	
	public static void main(String[] args) throws IOException, JSONException {
		
		
		JsonToSend app = new JsonToSend();
		//app.RequestToSend("file-for-test/InitVehiculesSensor.json");
		
		Request request = new Request("SELECT_ALL", "check");
		
		System.out.println("Ooooooooooooooook");
		ArrayList<String> al = app.sendMessageToServer(request);
		
			String s = al.get(0) + "\n";
			
			System.out.println(s);
			
			ResultTreshold req1 = new ObjectMapper().readValue(s, ResultTreshold.class);
			System.out.println("NbVehicleInCirculation =" +req1.getNbVehicleInCirculation());
			System.out.println("CurentPolution =" +req1.getCurentPolution());
			System.out.println("TresholdResult =" +req1.getTresholdResult());
			System.out.println("bollardStateResult =" +req1.getisBollardStateResult());
			System.out.println("tramFrequencyResult =" +req1.getTramFrequencyResult());
			
			System.out.println("okkk");
		
			
			

			

		
		
		
		

	}
}


