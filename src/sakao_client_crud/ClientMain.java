package sakao_client_crud;

import java.io.IOException;
import org.json.JSONException;

public class ClientMain extends Client {

	public ClientMain(String path) throws IOException, JSONException {
		super(path);
	}

	public static void main(String[] args) throws IOException, JSONException {
		new ClientMain(args[0]);
		//new ClientMain("file-for-crud/BollardSelect.json");
		// Eclipse run configaration : 
		//args[0] : Select
		//args[1] : Insert
		//args[2] : Update
		//args[3] : Delete
		
	}

}
