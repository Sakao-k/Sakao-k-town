package sakao_client_crud;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;

import sakao_common.Request;
import sakao_common.Response;

public class Client {

	private Socket clientSocket;
	private OutputStreamWriter out;
	private BufferedReader in;
	private Response response = new Response();

	private final static int port = 3030;
	private final static String ip = "localhost";

	private final static String SELECT_ALL = "SELECT_ALL";

	private ObjectMapper mapper;


	public Client(String path) throws IOException, JSONException {

		this.startConnection(ip, port);
		this.RequestToSend(path);
		this.CloseConnection();

	}

	public void startConnection(String ip, int port) throws IOException, JSONException {
		System.out.println("waiting for connection in to the server");
		clientSocket = new Socket(ip, port);
		out = new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
		System.out.println("connection succeed");

	}

	public String sendMessageToServer(Request request) throws IOException {
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
		
	}

	public void CloseConnection() throws IOException {
		System.out.println("waiting for updated disconnection");
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

	private void RequestToSend(String path) throws IOException {

		
		Request request = new ObjectMapper().readValue(this.readFile(path), Request.class);
		this.sendMessageToServer(request);

	}


}
