package sakao_client_test;

import java.io.IOException;
import org.json.JSONException;
import sakao_common.JsonToSend;

public class SpecHeavyPolution {

	public static void main(String[] args) throws IOException, JSONException {

		System.out.println("********************************");
		System.out.println("INITIALIZATION");
		System.out.println("Vehicles in Circulation in the city: 200");
		System.out.println("Vehicles MaxNumberThreshold : 500");
		System.out.println("MaxPolution : 700");
		System.out.println("Polutionlevel : 200");
		System.out.println("SensorVehicule 1 (Input) = 0");
		System.out.println("SensorVehicule 2 (Output) = 0");
		System.out.println("SensorVehicule 3 (Input) = 0");
		System.out.println("SensorVehicule 4 (Output) = 0");
		System.out.println("********************************");
		
		
		JsonToSend SpecHeavyPolution = new JsonToSend();

		SpecHeavyPolution.RequestToSend("file-for-test/InitSmartCity.json");
		SpecHeavyPolution.RequestToSend("file-for-test/InitVehiculesSensor.json");

		SpecHeavyPolution.RequestToSend("file-for-test/TestPolution5.json");
		SpecHeavyPolution.CloseConnection();

	}

}
