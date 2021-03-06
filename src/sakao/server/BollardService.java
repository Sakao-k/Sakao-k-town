package sakao.server;

import java.util.ArrayList;

import sakao.common.Bollard;
import sakao.common.VehicleSensor;

public class BollardService {

	private Crud_Controller controller;

	public BollardService() throws ClassNotFoundException {
		this.controller = new Crud_Controller();
	}

	public ArrayList<Bollard> GenerateAllBollards() throws ClassNotFoundException {
		return controller.GenerateAllBollards();
	}

	public ArrayList<String> showAllBollards() throws ClassNotFoundException, InterruptedException {
		return controller.showAllBollards();
	}

	public boolean addBollard(String target, ArrayList<String> list) {
		boolean b = false;
		try {
			/// Student p = new Student(name, age);
			controller.addOnBollard(target, list);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return b;
	}
	public boolean UpdateBollardIsInstalled(int id, boolean install) {
		boolean b = false;
		try {
			controller.UpdateBollardIsInstalled(id, install);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public boolean UpdateBollardIsInstalled(String target, ArrayList<String> list, ArrayList<Bollard> listBollardObj) {
		boolean b = false;
		try {
			/// Student p = new Student(name, age);
			controller.UpdateBollardIsInstalled(target, list, listBollardObj);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return b;
	}

	
	
	

	public boolean Updatetrue(ArrayList<Bollard> listBollard) {
		boolean b = false;
		try {

			/// Student p = new Student(name, age);
			controller.UpdateBollardTrue(listBollard);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return b;

	}

	public boolean Updatefalse(ArrayList<Bollard> listBollard) {
		boolean b = false;
		try {

			/// Student p = new Student(name, age);
			controller.UpdateBollardFalse(listBollard);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return b;

	}

	public boolean deleteBollardById(int ID) {
		boolean b = false;
		try {
			controller.deleteBollardById(ID);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	public boolean updateBollard(String target, ArrayList<String> list, ArrayList<Bollard> bollardObject) {
		boolean b = false;
		try {
			/// Student p = new Student(name, age);
			controller.updateBollard(target, list, bollardObject);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return b;
	}

	public boolean deleteBollardInstallState(ArrayList<String> list) {
		boolean b = false;
		try {
			controller.deleteBollardInstallState(list);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	public boolean UpdateBollardInstalledToTrueState(ArrayList<String> list) {
		boolean b = false;
		try {
			/// Student p = new Student(name, age);
			controller.UpdateBollardInstalledToTrueState(list);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return b;
	}


}
