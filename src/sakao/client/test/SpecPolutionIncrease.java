package sakao.client.test;

import java.io.IOException;
import org.json.JSONException;

import sakao.common.JsonToSend;

public class SpecPolutionIncrease {

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
		
		JsonToSend SpecPolutionIncrease = new JsonToSend();
		SpecPolutionIncrease.RequestToSend("file-for-test/InitSmartCity.json");
		SpecPolutionIncrease.RequestToSend("file-for-test/InitVehiculesSensor.json");

		SpecPolutionIncrease.RequestToSend("file-for-test/TestPolution6.json");
		
		System.out.println("TEST 6");
		System.out.println("Updated SensorVehiculeInput1 nbofcompactcar : 187 ");
		System.out.println("SensorVehiculeOutput2  nboflightcommercialvehicle : 100");
		System.out.println("SensorVehiculeInput3 = nbofcompactcar : 187");
		System.out.println("SensorVehiculeOutput4 = nboflightcommercialvehicle: 100");

		System.out.println("Expected results : retractablebollard are lowered");
		System.out.println("Number of vehicule in circulation = 200-200+187+187= = 374");
		System.out.println("Current Polution = 374*1.5= 561");
		System.out.println("TramFrequency : 8/10");
		System.out.println("Inncreasing Polution");
		
		SpecPolutionIncrease.CloseConnection();

	}

}
