package sakao_connection_pool;

import sakao_server.Crud_Service;;
// Inutile (à supprimer)
public class SakaoMain {

	public static void main(String[] args) throws ClassNotFoundException {
		Crud_Service cs = new Crud_Service();
		cs.addStudent("alain", 22);
		System.out.println("hi");

	}

}
