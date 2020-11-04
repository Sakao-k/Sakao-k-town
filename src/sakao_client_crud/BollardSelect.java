package sakao_client_crud;

import java.io.IOException;

import org.json.JSONException;

public class BollardSelect extends Client {

	public BollardSelect() throws IOException, JSONException {
		super("file-for-crud/BollardSelect.json");
	}

	public static void main(String[] args) throws IOException, JSONException {
		BollardSelect client1 = new BollardSelect();
		

	}

}
