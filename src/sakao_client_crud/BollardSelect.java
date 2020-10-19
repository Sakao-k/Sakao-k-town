package sakao_client_crud;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;

import sakao_common.Request;
import sakao_common.Response;

public class BollardSelect {
	private Socket clientSocket;
	private OutputStreamWriter out;
	private BufferedReader in;
	private Response response = new Response();

	private ObjectMapper mapper;
	private final static String SELECT_ALL = "SELECT_ALL";

	public void startConnection(String ip, int port) throws IOException, JSONException {
		System.out.println("waiting for connection in to the server");
		clientSocket = new Socket(ip, port);
		out = new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
		System.out.println("connection succeed");

	}

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
		response = mapper.readValue(injsonString, Response.class);
		return response.getList();
	}

	public void CloseConnection() throws IOException {
		System.out.println("waiting for disconnection");
		out.close();
		in.close();
		clientSocket.close();
		System.out.println("disconnected");
	}

	public static void main(String[] args) throws IOException, JSONException {
		BollardSelect client1 = new BollardSelect();
		//client1.startConnection("localhost", 3030);
		client1.startConnection("172.31.249.133", 3030);
		Request request = new Request(SELECT_ALL, "bollard");
		System.out.println("SERVER RESPONSE : " + client1.sendMessageToServer(request));
		System.out.println("Display done");
		System.out.println("********************");
		client1.CloseConnection();
	}

}
