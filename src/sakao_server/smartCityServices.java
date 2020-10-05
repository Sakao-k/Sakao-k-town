package sakao_server;

import java.util.ArrayList;

import sakao_common.SmartCity;

public class smartCityServices {

	private Crud_Controller controller;

	public smartCityServices() throws ClassNotFoundException {
		this.controller = new Crud_Controller();
	}

	public SmartCity GenerateCity() throws ClassNotFoundException {
		return controller.GenerateCity();
	}

	public boolean UpdateSmartCityVehicles(String target, ArrayList<String> list, SmartCity smartCityObject) {
		boolean b = false;
		try {
			/// Student p = new Student(name, age);
			controller.UpdateSmartCityVehicles(target, list, smartCityObject);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return b;
	}

	public void updateNumberinCirculation(int c) {

		try {
			controller.updateNumberinCirculation(c);

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	// nessecaire pour calcul ?

	public void updateCurrentPolution(int c) {

		try {
			controller.updateCurrentPolution(c);

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public void updateTramFrequency(int c) {

		try {
			controller.updateTramFrequency(c);

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
