package sakao_server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import sakao_common.Student;
import sakao_connection_pool.DataSource;

public class Crud_Controller {

	public Crud_Controller() throws ClassNotFoundException {
	}

	public Student existStudent(int id) throws ClassNotFoundException {
		Student retour = null;
		try {
			Connection con = DataSource.getConnection();

			PreparedStatement pt = con.prepareStatement("select * from personne where id =" + id);
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				int idS = rs.getInt(1);
				String name = rs.getString(2);
				int age = rs.getInt(3);
				retour = new Student(idS, name, age);
				DataSource.returnConnection(con);
			}

		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
		return retour;

	}

	// Request SELECT

	public ArrayList<Student> showStudent() throws ClassNotFoundException {
		ArrayList<Student> retour = new ArrayList<Student>();
		try {
			Connection con = DataSource.getConnection();

			PreparedStatement pt = con.prepareStatement("select * from personne");
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				int age = rs.getInt(3);
				retour.add(new Student(id, name, age));
				DataSource.returnConnection(con);
			}

		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
		return retour;

	}


	/*
	 * public void deleteStudentByName(int ID) throws ClassNotFoundException { try {
	 * Connection con = DataSource.getConnection(); Statement stmt =
	 * con.createStatement(); ResultSet rslt =
	 * stmt.executeQuery("select id from Student where name = " + name);
	 * while(rslt.next()) { if(rslt.equals(obj)) }
	 * 
	 * PreparedStatement pt = con
	 * .prepareStatement("delete from Student where name like ?"); pt.setString(1,
	 * name); pt.execute(); DataSource.returnConnection(con);
	 * 
	 * } catch (SQLException ex) { System.out.println("erreur " + ex.getMessage());
	 * }
	 * 
	 * }
	 */

	public void deleteStudentById(int ID) throws ClassNotFoundException {
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement pt = con.prepareStatement("delete from personne where id = " + ID);
			pt.execute();
			DataSource.returnConnection(con);

		}

		catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
	}

	// Request INSERT

	public void addStudent(Student p) throws ClassNotFoundException {
		try {
			Connection con = DataSource.getConnection();

			String req = "insert into personne(name,age) values (?,?)";
			PreparedStatement pstm = con.prepareStatement(req);
			pstm.setString(1, p.getName());
			pstm.setInt(2, p.getAge());
			pstm.executeUpdate();
			DataSource.returnConnection(con);

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	// Request UPDATE

	public void updateStudentAge(int id, int age) throws ClassNotFoundException {
		try {
			Connection con = DataSource.getConnection();

			PreparedStatement pstm = con.prepareStatement(" UPDATE personne SET age = ?  WHERE id = ?");
			pstm.setInt(1, age);
			pstm.setInt(2, id);
			pstm.executeUpdate();
			DataSource.returnConnection(con);

		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
	}

	public void updateStudentName(int id, String name) throws ClassNotFoundException {
		try {
			Connection con = DataSource.getConnection();

			PreparedStatement pstm = con.prepareStatement(" UPDATE personne SET name = ?  WHERE id = ?");
			pstm.setString(1, name);
			pstm.setInt(2, id);
			pstm.executeUpdate();
			DataSource.returnConnection(con);

		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}

	}

	public void deleteAllStudent() throws SQLException, ClassNotFoundException {
		Connection con = DataSource.getConnection();

		Statement query = con.createStatement();
		int result = query.executeUpdate("TRUNCATE TABLE personne");
		DataSource.returnConnection(con);

	}
}
