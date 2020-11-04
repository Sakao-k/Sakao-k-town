package sakao_client_crud;

import java.io.IOException;

import org.json.JSONException;

public class ClientMain extends Client {

	public ClientMain(String path) throws IOException, JSONException {
		super(path);
	}

	public static void main(String[] args) throws IOException, JSONException {
		new ClientMain(args[0]);
	}

}
