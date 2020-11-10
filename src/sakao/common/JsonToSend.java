package sakao.common;
//common with client and test
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;

public class JsonToSend {

	private Socket clientSocket;
	private OutputStreamWriter out;
	private BufferedReader in;
	private Response response = new Response();

	private final static int port = 3030;
	private final static String ip = "172.31.249.133";

	private final static String SELECT_ALL = "SELECT_ALL";

	private ObjectMapper mapper;


	public JsonToSend() throws IOException, JSONException {

		this.startConnection(ip, port);
		

	}

	public void startConnection(String ip, int port) throws IOException, JSONException {
		System.out.println("waiting for connection in to the server");
		clientSocket = new Socket(ip, port);
		out = new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
		System.out.println("connection succeed");

	}

	/*public String sendMessageToServer(Request request) throws IOException {
		mapper = new ObjectMapper();
		String outjsonString = mapper.writeValueAsString(request);
		System.out.println("REQUEST SENT");
		System.out.println(outjsonString);
		System.out.println(" _____");
		System.out.println("");
		out.write(outjsonString + "\n");
		out.flush();
		String injsonString = in.readLine();
		response = mapper.readValue(injsonString, Response.class);
		if (request.getOperation_type().equals(SELECT_ALL)) {
			System.out.println("Response");
			System.out.println(injsonString);
		}
		return response.toString();
		
	}*/
	
	public ArrayList<String> sendMessageToServer(Request request) throws IOException {
		mapper = new ObjectMapper();
		String outjsonString = mapper.writeValueAsString(request);
		System.out.println("REQUEST SENT");
		System.out.println(outjsonString);
		System.out.println(" _____");
		System.out.println("");
		out.write(outjsonString + "\n");
		out.flush();
		String injsonString = in.readLine();
		System.out.println(injsonString);
		response = mapper.readValue(injsonString, Response.class);
		if (request.getOperation_type().equals(SELECT_ALL)) {
			System.out.println("Response");
			System.out.println(injsonString);
		}

		
		return response.getList();
	}

	public void CloseConnection() throws IOException {
		System.out.println("waiting for disconnection");
		out.close();
		in.close();
		clientSocket.close();
		System.out.println("disconnected");
	}
	
	
	public String readFile(String path) throws IOException {

		InputStreamReader ipsr = new InputStreamReader(
				getClass().getClassLoader().getResourceAsStream(/* "file-for-crud/Bollard.json" */path));
		BufferedReader br = new BufferedReader(ipsr);
		String outjsonString = "";
		String chaine = "";

		while ((outjsonString = br.readLine()) != null) {
			chaine = chaine + outjsonString;
		}
		// System.out.println("Request sent : " + chaine);
		return chaine;
	}

	public void RequestToSend(String path) throws IOException {

		
		Request request = new ObjectMapper().readValue(this.readFile(path), Request.class);
		this.sendMessageToServer(request);
		
	}
	
	public Request requestParse (String path) throws JsonParseException, JsonMappingException, IOException {
		
		Request request = new ObjectMapper().readValue(this.readFile(path), Request.class);
		
		
		
		return request;
		
	}


}
