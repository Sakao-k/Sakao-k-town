package sakao.client.test;

import java.io.IOException;
import org.json.JSONException;

import sakao.common.JsonToSend;

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
		
		System.out.println("TEST 5");
		System.out.println("Updated SensorVehiculeInput1 nbofheavygoodsvehicle : 150 ");
		System.out.println("SensorVehiculeOutput2  nboflightcommercialvehicle : 100,");
		System.out.println("SensorVehiculeInput3 = nbofheavygoodsvehicle : 150");
		System.out.println("SensorVehiculeOutput4 = nboflightcommercialvehicle: 100,");

		System.out.println("Expected results : retractablebollard are raised");
		System.out.println("Number of vehicule in circulation = 200+300-200 = 300");
		System.out.println("Current Polution = 300*2.5= 750");
		System.out.println("TramFrequency : 10/10");
		System.out.println("Treshold Polution !!!");
		SpecHeavyPolution.CloseConnection();

	}

}
