package sakao.client.test;

import java.io.IOException;

import org.json.JSONException;

import sakao.common.JsonToSend;

public class testinit {

	public static void main(String[] args) throws IOException, JSONException {
		JsonToSend init = new JsonToSend();

		init.RequestToSend("file-for-test/InitSmartCity.json");
		init.RequestToSend("file-for-test/InitVehiculesSensor.json");

	}

}
