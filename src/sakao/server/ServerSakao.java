package sakao.server;

import java.io.IOException;
import java.net.ServerSocket;

import org.json.JSONException;

import sakao.connection.pool.DataSource;

public class ServerSakao {
	private ServerSocket serverSocket;
	private static DataSource datasource;
	private static int i;

	public void start(int port) throws IOException, JSONException, ClassNotFoundException {
		serverSocket = new ServerSocket(port);
		
		while (true) {
			new ClientThread(serverSocket.accept()).start();
		}
	}

	public void CloseConnection() throws IOException {
		serverSocket.close();
		System.out.println("Server deconnecte");
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	public static void main(int args) throws IOException, JSONException, ClassNotFoundException {
		
		datasource = new DataSource(args);
		ServerSakao serveur1 = new ServerSakao();
		serveur1.start(3030);
		serveur1.CloseConnection();
	}

}
