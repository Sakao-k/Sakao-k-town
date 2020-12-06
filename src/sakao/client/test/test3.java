package sakao.client.test;

import java.io.IOException;

import org.json.JSONException;

import sakao.common.JsonToSend;

public class test3 {

	public static void main(String[] args) throws IOException, JSONException {

		JsonToSend app = new JsonToSend();
		// app.RequestToSend("file-for-test/InitVehiculesSensor.json");
		// Request request = new Request(SELECT_ALL, "smartcity");

		app.RequestToSend("file-for-test2/addCommercial.json");

	}

}
