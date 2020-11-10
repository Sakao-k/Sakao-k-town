package sakao.client.test;

import java.io.IOException;
import org.json.JSONException;

import sakao.common.JsonToSend;

public class SpecMidleDecreaseCirculation {
	public static void main(String[] args) throws IOException, JSONException {

		System.out.println("********************************");
		System.out.println("INITIALIZATION");
		System.out.println("Vehicles in Circulation in the city: 200");
		System.out.println("Vehicles MaxNumberThreshold : 500");
		System.out.println("SensorVehiculeInput1 = 300");
		System.out.println("SensorVehiculeOutput2 = 200");
		System.out.println("SensorVehiculeInput3 = 300");
		System.out.println("SensorVehiculeOutput4 = 100");
		System.out.println("Number of vehicule in circulation = 200+600-300 = 500");
		System.out.println("********************************");

		JsonToSend SpecMidleDecreaseCirculation = new JsonToSend();

		SpecMidleDecreaseCirculation.RequestToSend("file-for-test/InitSmartCity.json");

		SpecMidleDecreaseCirculation.RequestToSend("file-for-test/TestVehicles2.json");

		System.out.println("TEST 4");
		System.out.println("Updated SensorVehiculeInput1 to 300 ");
		System.out.println("SensorVehiculeOutput2 = 300");
		System.out.println("SensorVehiculeInput3 = 400");
		System.out.println("SensorVehiculeOutput4 = 500");

		System.out.println("Expected results : retractablebollard are Raised");
		System.out.println("Number of vehicule in circulation = 500+700-800 = 400");
		System.out.println("TramFrequency : 8/10");

		SpecMidleDecreaseCirculation.RequestToSend("file-for-test/TestVehicles4.json");
		SpecMidleDecreaseCirculation.CloseConnection();

	}

}
