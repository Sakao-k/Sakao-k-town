package sakao_client_test;

import java.io.IOException;

import org.json.JSONException;

import sakao_client_crud.ClientMain;

public class SpecMidleIncreaseCirculation {

	public static void main(String[] args) throws IOException, JSONException {
		

		System.out.println("********************************");
		System.out.println("INITIALIZATION");
		System.out.println("Vehicles in Circulation in the city: 200");
		System.out.println("Vehicles MaxNumberThreshold : 500");
		System.out.println("MaxPolution : 2000");
		System.out.println("Polutionlevel : 500");
		System.out.println("SensorVehicule 1 (Input) = 0");
		System.out.println("SensorVehicule 2 (Output) = 0");
		System.out.println("SensorVehicule 3 (Input) = 0");
		System.out.println("SensorVehicule 4 (Output) = 0");
		System.out.println("********************************");
		
		
		
		ClientMain InitSmartCity = new ClientMain("file-for-test/InitSmartCity.json");
		ClientMain InitVehiculesSensor = new ClientMain("file-for-test/InitVehiculesSensor.json");

		
		System.out.println("TEST 3");
		System.out.println("Updated SensorVehiculeInput1 to 200 ");
		System.out.println("SensorVehiculeOutput2 = 200");
		System.out.println("SensorVehiculeInput3 = 200");
		System.out.println("SensorVehiculeOutput4 = 0");
		
		
		
		System.out.println("Expected results : retractablebollard are lower");
		System.out.println("Number of vehicule in circulation = 200+400-200 = 400");
		System.out.println("TramFrequency : 8/10");
		
		ClientMain MidleIncreaseCirulation = new ClientMain("file-for-test/TestVehicles3.json");

		
	}
	
}
