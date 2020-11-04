package sakao_client_crud;

import java.io.IOException;

import org.json.JSONException;

public class BollardInsert extends Client {

	public BollardInsert() throws IOException, JSONException {
		super("file-for-crud/Bollard.json");
	}

	public static void main(String[] args) throws IOException, JSONException {
		BollardInsert client1 = new BollardInsert();
		

	}

}
