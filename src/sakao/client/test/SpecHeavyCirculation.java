package sakao.client.test;

import java.io.IOException;
import org.json.JSONException;

import sakao.common.JsonToSend;

public class SpecHeavyCirculation {
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

		JsonToSend SpecHeavyCirculation = new JsonToSend();

		SpecHeavyCirculation.RequestToSend("file-for-test/InitSmartCity.json");
		SpecHeavyCirculation.RequestToSend("file-for-test/InitVehiculesSensor.json");

		System.out.println("TEST 2");
		System.out.println("Updated SensorVehiculeInput1 to 300 ");
		System.out.println("SensorVehiculeOutput2 = 200");
		System.out.println("SensorVehiculeInput3 = 300");
		System.out.println("SensorVehiculeOutput4 = 100");

		System.out.println("Expected results : retractablebollard are raised");
		System.out.println("Number of vehicule in circulation = 200+600-300 = 500");
		System.out.println("CurentPolution = 500.0");
		System.out.println("TramFrequency : 10/10");
		System.out.println("Treshold Number of vehicles !!!");
		
		SpecHeavyCirculation.RequestToSend("file-for-test/TestVehicles2.json");
		
		SpecHeavyCirculation.CloseConnection();

	}

}
