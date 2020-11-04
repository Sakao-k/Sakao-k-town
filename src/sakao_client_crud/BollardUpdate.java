package sakao_client_crud;

import java.io.IOException;

import org.json.JSONException;

public class BollardUpdate extends Client {

	public BollardUpdate() throws IOException, JSONException {
		super("file-for-crud/BollardUpdate.json");
	}

	public static void main(String[] args) throws IOException, JSONException {
		BollardUpdate client1 = new BollardUpdate();
		

	}

}

