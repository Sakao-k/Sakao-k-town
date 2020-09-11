package sakao_common;

import java.util.ArrayList;

public class Response {
	private ArrayList<Student> students;

	public Response() {
	}

	public String toString() {
		return "Students : [ " + this.getStudents() + "; ]";
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}
}
