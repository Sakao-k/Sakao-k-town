package sakao_client_crud;

import java.io.IOException;

import org.json.JSONException;

public class BollardDelete extends Client {

	public BollardDelete() throws IOException, JSONException {
		super("file-for-crud/BollardDelete.json");
	}

	public static void main(String[] args) throws IOException, JSONException {
		BollardDelete client1 = new BollardDelete();
		

	}

}

