package sakao.client.crud;

import java.io.IOException;
import org.json.JSONException;

import sakao.common.JsonToSend;

public class ClientMain extends JsonToSend {

	public ClientMain() throws IOException, JSONException {
		super();
	}

	public static void main(String[] args) throws IOException, JSONException {
		ClientMain client1 = new ClientMain();
		client1.RequestToSend(args[0]);
		//new ClientMain("file-for-crud/BollardSelect.json");
		// Eclipse run configaration : 
		//args[0] : Select
		//args[1] : Insert
		//args[2] : Update
		//args[3] : Delete
		
	}

}
