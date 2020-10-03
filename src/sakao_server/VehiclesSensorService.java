package sakao_server;

import java.util.ArrayList;

import sakao_common.VehicleSensor;


public class VehiclesSensorService {

	private Crud_Controller controller;

	public VehiclesSensorService() throws ClassNotFoundException {
		this.controller = new Crud_Controller();
	}

	public ArrayList<VehicleSensor> GenerateAllVehicleSensors() throws ClassNotFoundException {
		return controller.GenerateAllVehicleSensors();
	}
	
	
	
	public ArrayList<String> showAllSensorVehicles() throws ClassNotFoundException {
		return controller.showAllSensorVehicles();
	}
	
	
	

	public boolean UpdateSensorVehicles(String target,ArrayList<String> list, ArrayList<VehicleSensor> listVehicleSensorObj ) {
		boolean b = false;
		try {
			/// Student p = new Student(name, age);
			// Don't work if generateobject is commented
			controller.UpdateSensorVehicles(target,list,listVehicleSensorObj);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return b;
	}

	public boolean addOnVehiclesSensor(String target, ArrayList<String> list) {
		boolean b = false;
		try {
			controller.addOnVehiclesSensor(target, list);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return b;
		
	}

	public boolean deleteVehiclesSensorById(int ID) {
		boolean b = false;
		try {
			controller.deleteSensorById(ID);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	public boolean updateVehiclesSensorIsInstalled(int id, boolean install) {
		boolean b = false;
		try {
			controller.updateSensor(id, install);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	
	
}
