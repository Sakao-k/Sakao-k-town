package sakao_server;

import java.util.ArrayList;

public class ZoneService {
	private Crud_Controller controller;

	public ZoneService() throws ClassNotFoundException {
		this.controller = new Crud_Controller();
	}

	public ArrayList<String> showAllZone() throws ClassNotFoundException {
		return controller.showAllZone();
	}

	public ArrayList<String> showZoneById(int id) throws ClassNotFoundException {
		return controller.showZoneById(id);
	}


	

}
