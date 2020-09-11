package sakao_server;

import java.util.ArrayList;

import sakao_common.Student;

public class Crud_Service {

	private Crud_Controller controller;

	public Crud_Service() throws ClassNotFoundException {
		this.controller = new Crud_Controller();
	}

	public ArrayList<Student> showStudent() throws ClassNotFoundException {
		return controller.showStudent();
	}

	public boolean addStudent(String name, int age) {
		boolean b = false;
		try {
			Student p = new Student(name, age);
			controller.addStudent(p);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return b;
	}

	/*
	 * public boolean deleteStudentByName(String name) { boolean b = false; try {
	 * controller.deleteStudentByName(name);
	 * 
	 * b = true; } catch (Exception e) { e.printStackTrace();
	 * 
	 * } return b; }
	 */

	public boolean deleteStudentById(int ID) {
		boolean b = false;
		try {
			if (controller.existStudent(ID) == null)
				System.out.println("Id inexistant");
			controller.deleteStudentById(ID);

			b = true;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return b;
	}

	public boolean updateStudentAge(int id, int age) {
		boolean b = false;
		try {
			if (controller.existStudent(id) == null)
				System.out.println("Id inexistant");
			controller.updateStudentAge(id, age);

			b = true;
		} catch (Exception e) {
			e.printStackTrace();

		}

		return b;
	}

	public boolean deleteAllStudent() {
		boolean b = false;
		try {
			controller.deleteAllStudent();

			b = true;
		} catch (Exception e) {
			System.out.println("all rows deleted");
		}
		return b;
	}

	public boolean updateStudentName(int id, String name) {
		boolean b = false;
		try {
			if (controller.existStudent(id) == null)
				System.out.println("Id inexistant");
			controller.updateStudentName(id, name);

			b = true;
		}

		catch (Exception e) {
			e.printStackTrace();

		}
		return b;
	}

	public Crud_Controller getController() {
		return controller;
	}

	public void setController(Crud_Controller controller) {
		this.controller = controller;
	}

}
