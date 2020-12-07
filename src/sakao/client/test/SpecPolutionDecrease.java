package sakao.client.test;

import java.io.IOException;
import org.json.JSONException;

import sakao.common.JsonToSend;

public class SpecPolutionDecrease {

	public static void main(String[] args) throws IOException, JSONException {

		JsonToSend SpecPolutionDecrease = new JsonToSend();

		System.out.println("********************************");
		System.out.println("INITIALIZATION");
		System.out.println("Vehicles in Circulation in the city: 200");
		System.out.println("Vehicles MaxNumberThreshold : 500");
		System.out.println("MaxPolution : 700");
		System.out.println("Polutionlevel : 200");
		System.out.println("Updated SensorVehiculeInput1 nbofheavygoodsvehicle : 150 ");
		System.out.println("SensorVehiculeOutput2  nboflightcommercialvehicle : 100,");
		System.out.println("SensorVehiculeInput3 = nbofheavygoodsvehicle : 150");
		System.out.println("SensorVehiculeOutput4 = nboflightcommercialvehicle: 100");
		System.out.println("********************************");
		SpecPolutionDecrease.RequestToSend("file-for-test/InitSmartCity.json");
		SpecPolutionDecrease.RequestToSend("file-for-test/InitTest7.json");

		SpecPolutionDecrease.RequestToSend("file-for-test/TestPolution7.json");
		
		System.out.println("TEST 7");
		System.out.println("Updated SensorVehiculeInput1 nboflightcommercialvehicle: 160,nbofheavygoodsvehicle: 50 ");
		System.out.println("SensorVehiculeOutput2  	nbofheavygoodsvehicle : 150");
		System.out.println("SensorVehiculeInput3 = nbofcompactcar: 100,\r\n"
				+ "nbofheavygoodsvehicle: 50");
		System.out.println("SensorVehiculeOutput4 = nbofheavygoodsvehicle: 150");
		
		System.out.println("Expected results : retractablebollard are raised");
		System.out.println("Number of vehicule in circulation = 200+300-200 = 300");
		System.out.println("Current Polution = 300*2.5= 750");
		System.out.println("TramFrequency : 8/10");
		System.out.println("Polution is decreasing in town !!!");
		SpecPolutionDecrease.CloseConnection();

	}

}
