package sakao_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;

import sakao_common.Request;
import sakao_common.Response;

public class ClientSakao {
	private Socket clientSocket;
	private OutputStreamWriter out;
	private BufferedReader in;
	private Response response = new Response();

	private ObjectMapper mapper;
	private final static String SELECT_ALL = "SELECT_ALL";
	private final static String DELETE_ALL = "DELETE_ALL";
	private final static String INSERT = "INSERT";
	private final static String DELETE = "DELETE";
	private final static String UPDATE_NAME = "UPDATE_NAME";
	private final static String UPDATE_AGE = "UPDATE_AGE";
	private final static String STUDENT = "Student";

	public void startConnection(String ip, int port) throws IOException, JSONException {
		System.out.println("waiting for connection in to the server");
		clientSocket = new Socket(ip, port);
		out = new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
		System.out.println("connection succeed");
		this.StartHMIStudent();
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
		if (request.getOperation_type().equals(SELECT_ALL)) {
			System.out.println("Response");
			System.out.println(injsonString);
		}

		response = mapper.readValue(injsonString, Response.class);
		return response.toString();
	}

	public void CloseConnection() throws IOException {
		System.out.println("waiting for disconnection");
		out.close();
		in.close();
		clientSocket.close();
		System.out.println("disconnected");
	}

	/////The following function will be use in the futur to decide first in which table you want to send request
	public void StartHMI() {
		Scanner sco = new Scanner(System.in);
		System.out.println("LIST OF TABLES, PLEASE CHOOSE A TABLE");
		int choice = 0;

		while (choice < 6 && choice >= 0) { 
			System.out.println("1.Student");
			System.out.println("2.upcoming soon");
			System.out.println("3.upcoming soon");
			System.out.println("4.upcoming soon");
			System.out.println("5.upcoming soon");
			System.out.println("6.Log out");

			int choix = sco.nextInt();
			choice = choix;

			switch (choix) {
			case 1:
				this.StartHMIStudent();

			case 6:
				try {
					this.CloseConnection();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}

	/*
	 * 
	 * while (true) { try { age = clavier.nextInt(); break; } catch (Exception) {
	 * System.out.println("Uniquement des chiffres s'il-te-pla�t !"); } }
	 * 
	 */

	public void StartHMIStudent() {
		Scanner sc = new Scanner(System.in);

		System.out.println("CRUD MENU");
		int choice = 0;

		while (choice < 7 && choice >= 0) {
			System.out.println("1.Add a student");
			System.out.println("2.View all students");
			System.out.println("3.Delete a student");
			System.out.println("4.Update the age of a student");
			System.out.println("5.Update the name of a student");
			System.out.println("6.Delete all students");
			System.out.println("7.Log out");
			System.out.println("********************");
			System.out.println("");

			int choix = sc.nextInt();

			choice = choix;

			switch (choix) {

			case 1:
				System.out.println("Please enter the name");
				String nameInsert = sc.next();
				System.out.println("Please enter the age");
				int ageInsert = sc.nextInt();
				try {
					Request request = new Request(INSERT, STUDENT, nameInsert, ageInsert);
					this.sendMessageToServer(request);
					System.out.println("Insert done");
					System.out.println("********************");
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				break;

			case 2:///// OK
				try {
					Request request = new Request(SELECT_ALL, STUDENT);
					this.sendMessageToServer(request);
					System.out.println("Display done");
					System.out.println("********************");
				} catch (IOException e) {
					e.printStackTrace();
				}

				break;

			case 3:
				System.out.println("Please enter the id");
				int idtodelete = sc.nextInt();
				try {
					Request request = new Request(DELETE, STUDENT, idtodelete);
					this.sendMessageToServer(request);
					System.out.println("Delete done");
					System.out.println("********************");
				} catch (IOException e2) {
					e2.printStackTrace();
				}

				break;

			case 4:
				System.out.println("Please enter the id");
				int idupdateage = sc.nextInt();
				System.out.println("Please enter the new age");
				int ageupdateage = sc.nextInt();
				try {
					Request request = new Request(UPDATE_AGE, STUDENT, idupdateage, ageupdateage);
					this.sendMessageToServer(request);
					System.out.println("Update age done");
					System.out.println("********************");
				} catch (IOException e2) {
					e2.printStackTrace();
				}

				break;

			case 5:
				System.out.println("Please enter the id");
				int idupdatename = sc.nextInt();
				System.out.println("");
				System.out.println("Please enter the new name");
				String nameupdatename = sc.next();
				try {
					Request request = new Request(UPDATE_NAME, STUDENT, idupdatename, nameupdatename);
					this.sendMessageToServer(request);
					System.out.println("Update name done");
					System.out.println("********************");
				} catch (IOException e2) {
					e2.printStackTrace();
				}

				break;

			case 6:
				try {
					Request request = new Request(DELETE_ALL, STUDENT);
					this.sendMessageToServer(request);
					System.out.println("Delete all done");
					System.out.println("********************");
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				break;

			case 7: ///// OK
				try {
					System.out.println("********************");
					System.out.println("You left the menu, see you soon thank you !");
					sc.close();
					this.CloseConnection();

				} catch (Exception e) {
					;
				}

			}

		}

	}

	public static void main(String[] args) throws IOException, JSONException {
		ClientSakao client1 = new ClientSakao();
		client1.startConnection("172.31.249.133", 3030);

	}

}
