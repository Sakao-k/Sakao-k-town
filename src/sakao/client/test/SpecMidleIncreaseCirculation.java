package sakao.client.test;

import java.io.IOException;
import org.json.JSONException;

import sakao.common.JsonToSend;


public class SpecMidleIncreaseCirculation {

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
		
		
		JsonToSend SpecMidleIncreaseCirculation = new JsonToSend();
		
		SpecMidleIncreaseCirculation.RequestToSend("file-for-test/InitSmartCity.json");
		SpecMidleIncreaseCirculation.RequestToSend("file-for-test/InitVehiculesSensor.json");
		

		
		System.out.println("TEST 3");
		System.out.println("Updated SensorVehiculeInput1 to 200 ");
		System.out.println("SensorVehiculeOutput2 = 200");
		System.out.println("SensorVehiculeInput3 = 200");
		System.out.println("SensorVehiculeOutput4 = 0");
		
		
		
		System.out.println("Expected results : retractablebollard are lower");
		System.out.println("Number of vehicule in circulation = 200+400-200 = 400");
		System.out.println("Current Polution = 400");
		System.out.println("Inncreasing Number of vehicles");
		System.out.println("TramFrequency : 8/10");
		//
		
		
		SpecMidleIncreaseCirculation.RequestToSend("file-for-test/TestVehicles3.json");
		SpecMidleIncreaseCirculation.CloseConnection();

		
	}
	
}
