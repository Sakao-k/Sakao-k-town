package sakao_client_test;

import java.io.IOException;
import org.json.JSONException;
import sakao_common.JsonToSend;

public class SpecPolutionDecrease {

	public static void main(String[] args) throws IOException, JSONException {

		JsonToSend SpecPolutionDecrease = new JsonToSend();

		SpecPolutionDecrease.RequestToSend("file-for-test/InitSmartCity.json");
		SpecPolutionDecrease.RequestToSend("file-for-test/TestPolution5.json");

		SpecPolutionDecrease.RequestToSend("file-for-test/TestPolution7.json");
		SpecPolutionDecrease.CloseConnection();

	}

}
