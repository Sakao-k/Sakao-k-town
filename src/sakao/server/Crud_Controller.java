package sakao.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import sakao.common.Bollard;
import sakao.common.SmartCity;
import sakao.common.VehicleSensor;
import sakao.common.Zone;
import sakao.connection.pool.DataSource;

public class Crud_Controller {

	public Crud_Controller() throws ClassNotFoundException {
	}

	/*
	 * 
	 * public Student existStudent(int id) throws ClassNotFoundException { Student
	 * retour = null; try { Connection con = DataSource.getConnection();
	 * 
	 * PreparedStatement pt =
	 * con.prepareStatement("select * from personne where id =" + id); ResultSet rs
	 * = pt.executeQuery(); while (rs.next()) { int idS = rs.getInt(1); String name
	 * = rs.getString(2); int age = rs.getInt(3); retour = new Student(idS, name,
	 * age); DataSource.returnConnection(con); }
	 * 
	 * } catch (SQLException ex) { System.out.println("erreur " + ex.getMessage());
	 * } return retour;
	 * 
	 * }
	 * 
	 * // Request SELECT
	 * 
	 * public ArrayList<Student> showStudent() throws ClassNotFoundException {
	 * ArrayList<Student> retour = new ArrayList<Student>(); try { Connection con =
	 * DataSource.getConnection();
	 * 
	 * PreparedStatement pt = con.prepareStatement("select * from personne");
	 * ResultSet rs = pt.executeQuery(); while (rs.next()) { int id = rs.getInt(1);
	 * String name = rs.getString(2); int age = rs.getInt(3); retour.add(new
	 * Student(id, name, age)); DataSource.returnConnection(con); }
	 * 
	 * } catch (SQLException ex) { System.out.println("erreur " + ex.getMessage());
	 * } return retour;
	 * 
	 * }
	 * 
	 * /* public void deleteStudentByName(int ID) throws ClassNotFoundException {
	 * try { Connection con = DataSource.getConnection(); Statement stmt =
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
	/*
	 * public void deleteStudentById(int ID) throws ClassNotFoundException { try {
	 * Connection con = DataSource.getConnection(); PreparedStatement pt =
	 * con.prepareStatement("delete from personne where id = " + ID); pt.execute();
	 * DataSource.returnConnection(con);
	 * 
	 * }
	 * 
	 * catch (SQLException ex) { System.out.println("erreur " + ex.getMessage()); }
	 * }
	 * 
	 * // Request INSERT
	 * 
	 * public void addStudent(Student p) throws ClassNotFoundException { try {
	 * Connection con = DataSource.getConnection();
	 * 
	 * String req = "insert into personne(name,age) values (?,?)"; PreparedStatement
	 * pstm = con.prepareStatement(req); pstm.setString(1, p.getName());
	 * pstm.setInt(2, p.getAge()); pstm.executeUpdate();
	 * DataSource.returnConnection(con);
	 * 
	 * } catch (SQLException ex) { ex.printStackTrace(); } }
	 * 
	 * // Request UPDATE
	 * 
	 * public void updateStudentAge(int id, int age) throws ClassNotFoundException {
	 * try { Connection con = DataSource.getConnection();
	 * 
	 * PreparedStatement pstm =
	 * con.prepareStatement(" UPDATE personne SET age = ?  WHERE id = ?");
	 * pstm.setInt(1, age); pstm.setInt(2, id); pstm.executeUpdate();
	 * DataSource.returnConnection(con);
	 * 
	 * } catch (SQLException ex) { System.out.println("erreur " + ex.getMessage());
	 * } }
	 * 
	 * public void updateStudentName(int id, String name) throws
	 * ClassNotFoundException { try { Connection con = DataSource.getConnection();
	 * 
	 * PreparedStatement pstm =
	 * con.prepareStatement(" UPDATE personne SET name = ?  WHERE id = ?");
	 * pstm.setString(1, name); pstm.setInt(2, id); pstm.executeUpdate();
	 * DataSource.returnConnection(con);
	 * 
	 * } catch (SQLException ex) { System.out.println("erreur " + ex.getMessage());
	 * }
	 * 
	 * }
	 * 
	 * public void deleteAllStudent() throws SQLException, ClassNotFoundException {
	 * Connection con = DataSource.getConnection();
	 * 
	 * Statement query = con.createStatement(); int result =
	 * query.executeUpdate("TRUNCATE TABLE personne");
	 * DataSource.returnConnection(con);
	 * 
	 * }
	 */
	public void updateTramFrequency(int c) throws ClassNotFoundException {
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement pt = con
					.prepareStatement("UPDATE smartcity SET tramfrequency=" + c + "  WHERE idcity = 1;");
			pt.execute();
			DataSource.returnConnection(con);
		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}

	}

	public void updateNumberinCirculation(int c) throws ClassNotFoundException {

		try {
			Connection con = DataSource.getConnection();
			PreparedStatement pt = con
					.prepareStatement("UPDATE smartcity SET numberofvehicules=" + c + "  WHERE idcity = 1;");
			pt.execute();
			DataSource.returnConnection(con);
		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}

	}

	public void updateCurrentPolution(double poltutionInTown) throws ClassNotFoundException {

		try {
			Connection con = DataSource.getConnection();
			PreparedStatement pt = con
					.prepareStatement("UPDATE smartcity SET polutionlevel=" + poltutionInTown + "  WHERE idcity = 1;");
			pt.execute();
			DataSource.returnConnection(con);
		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}

	}

	public void UpdateSmartCityVehicles(String target, ArrayList<String> list, SmartCity smartCityObject)
			throws ClassNotFoundException {

		try {
			Connection con = DataSource.getConnection();
			String req = "UPDATE smartcity SET maxnumbervehicles=?, numberofvehicules=?, maxpolution=?, polutionlevel=?, tramfrequency=?\r\n"
					+ "	WHERE idcity = 1";

			PreparedStatement pstm = con.prepareStatement(req);
			int i = 2;

			while (i < list.size()) {
				
				//System.out.println(list.get(i));
				pstm.setInt(1, Integer.parseInt((list.get(i)))); // MaxnumberVeh
				pstm.setInt(2, Integer.parseInt(list.get(i + 2))); // numberofvehicules
				pstm.setInt(3, Integer.parseInt(list.get(i + 4)));// maxpolution
				pstm.setInt(4, Integer.parseInt(list.get(i + 6)));// polutionlevel
				pstm.setInt(5, Integer.parseInt(list.get(i + 8)));// tramfrequecy
				pstm.executeUpdate();

			/*	smartCityObject.setMaxNumberVehicles(Integer.parseInt((list.get(i))));
				smartCityObject.setNumberVehicles(Integer.parseInt(list.get(i + 2)));
				smartCityObject.setMaxPolution(Integer.parseInt(list.get(i + 4)));
				smartCityObject.setPolutionLevel(Integer.parseInt(list.get(i + 6)));
				smartCityObject.setTramFrequency(Integer.parseInt(list.get(i + 8)));*/

				i += 12;

			}
			req = req.substring(0, req.length() - 1);

			DataSource.returnConnection(con);

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void UpdateBollardIsInstalled(int id, boolean install) throws ClassNotFoundException {
		try {
			Connection con = DataSource.getConnection();

			PreparedStatement pstm = con
					.prepareStatement(" UPDATE retractablebollard SET isinstalled = ?  WHERE idbollard = ?");
			pstm.setBoolean(1, install);
			pstm.setInt(2, id);
			pstm.executeUpdate();
			DataSource.returnConnection(con);

		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
	}

	public void deleteBollardById(int id) throws ClassNotFoundException {
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement pt = con.prepareStatement("delete from retractablebollard where idbollard = " + id);
			pt.execute();
			DataSource.returnConnection(con);
		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
	}

	public void deleteBollardNotInstalled() throws ClassNotFoundException {
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement pt = con.prepareStatement("delete from retractablebollard where IsInstalled= false  ");
			pt.execute();
			DataSource.returnConnection(con);
		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
	}
	public void UpdateBollardTrue(ArrayList<Bollard> listBollard) throws ClassNotFoundException {

		try {
			Connection con = DataSource.getConnection();
			PreparedStatement pt = con.prepareStatement("UPDATE retractablebollard SET bollardstate= true ;");
			pt.executeUpdate();

			for (Bollard bollard : listBollard) {

				bollard.setBollardState(true);

			}

			DataSource.returnConnection(con);

		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
	}

	public void UpdateBollardFalse(ArrayList<Bollard> listBollard) throws ClassNotFoundException {

		try {
			Connection con = DataSource.getConnection();
			PreparedStatement pt = con.prepareStatement("UPDATE retractablebollard SET bollardstate= false ;");
			pt.executeUpdate();

			for (Bollard bollard : listBollard) {

				bollard.setBollardState(false);

			}

			DataSource.returnConnection(con);

		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
	}

	public void UpdateBollardIsInstalled(String target, ArrayList<String> list, ArrayList<Bollard> listBollardObj)
			throws ClassNotFoundException {

		try {
			Connection con = DataSource.getConnection();
			String req = "UPDATE retractablebollard SET  IsInstalled=? WHERE idbollard = ? and idzone= ?;";
			PreparedStatement pstm = con.prepareStatement(req);
			int i = 2;

			System.out.println("taille " + list.size());
			System.out.println("case " + list.get(2));
			while (i < list.size()) {
				pstm.setInt(2, Integer.parseInt((list.get(i)))); // Idbollard
				pstm.setBoolean(1, Boolean.valueOf(list.get(i + 2)).booleanValue()); // IsInstalled
				pstm.setInt(3, Integer.parseInt(list.get(i + 4)));// Idzone
				pstm.executeUpdate();

				for (Bollard bollard : listBollardObj) {
					if ((bollard.getIdBollard() == Integer.parseInt((list.get(i))))) {

						bollard.setInstalled(Boolean.valueOf(list.get(i + 2)).booleanValue());

					}

				}

				i += 8;

			}
			req = req.substring(0, req.length() - 1);
			// System.out.println(req);
			// PreparedStatement pstm = con.prepareStatement(req);
			// pstm.executeUpdate();
			System.out.println("");
			// System.out.println(req);
			System.out.println("");
			DataSource.returnConnection(con);

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}
	
	public void deleteBollardInstallState(ArrayList<String> list)throws ClassNotFoundException {

		try {
			Connection con = DataSource.getConnection();
			String req = "DELETE FROM  retractablebollard WHERE IsInstalled=? AND bollardstate = ?;";
			PreparedStatement pstm = con.prepareStatement(req);
			int i = 2;

			while (i < list.size()) {
				pstm.setBoolean(2, Boolean.valueOf((list.get(i)))); // bollardState
				pstm.setBoolean(1, Boolean.valueOf(list.get(i + 2)).booleanValue()); // IsInstalled
				pstm.executeUpdate();

				i += 6;

			}
			req = req.substring(0, req.length() - 1);
			// System.out.println(req);
			// PreparedStatement pstm = con.prepareStatement(req);
			// pstm.executeUpdate();
			System.out.println("");
			// System.out.println(req);
			System.out.println("");
			DataSource.returnConnection(con);

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}


	public void UpdateBollardInstalledToTrueState(ArrayList<String> list) throws ClassNotFoundException {
		
		try {
			Connection con = DataSource.getConnection();
			String req = "UPDATE retractablebollard\r\n"
					+ "	SET  bollardstate=?\r\n"
					+ "	WHERE isinstalled=?;";
			PreparedStatement pstm = con.prepareStatement(req);
			int i = 2;

			while (i < list.size()) {
				pstm.setBoolean(1, Boolean.valueOf(list.get(i)).booleanValue()); // bollardstate
				pstm.setBoolean(2, Boolean.valueOf(list.get(i + 2)).booleanValue()); // IsInstalled


				pstm.executeUpdate();


				i += 6;

			}
			req = req.substring(0, req.length() - 1);
			// System.out.println(req);
			// PreparedStatement pstm = con.prepareStatement(req);
			// pstm.executeUpdate();
			System.out.println("");
			// System.out.println(req);
			System.out.println("");
			DataSource.returnConnection(con);

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		
	}

	public void updateBollard(String target, ArrayList<String> list, ArrayList<Bollard> listBollardObj)
			throws ClassNotFoundException {

		try {
			Connection con = DataSource.getConnection();
			String req = "UPDATE retractablebollard\r\n"
					+ "	SET  bollardstate=?, idzone=?, isinstalled=?, ipaddress=?, macaddress=?\r\n"
					+ "	WHERE idbollard=?;";
			PreparedStatement pstm = con.prepareStatement(req);
			int i = 2;

			while (i < list.size()) {
				pstm.setInt(6, Integer.parseInt((list.get(i)))); // Idbollard
				pstm.setBoolean(1, Boolean.valueOf(list.get(i + 2)).booleanValue()); // bollardstate
				pstm.setInt(2, Integer.parseInt(list.get(i + 4)));// Idzone
				pstm.setBoolean(3, Boolean.valueOf(list.get(i + 6)).booleanValue()); // IsInstalled
				pstm.setString(4, list.get(i + 8)); // ippadress
				pstm.setString(5, list.get(i + 10));// macadress

				pstm.executeUpdate();

				for (Bollard bollard : listBollardObj) {
					if ((bollard.getIdBollard() == Integer.parseInt((list.get(i))))) {

						bollard.setBollardState(Boolean.valueOf(list.get(i + 2)).booleanValue());
						bollard.setIdZone(Integer.parseInt(list.get(i + 4)));
						bollard.setInstalled(Boolean.valueOf(list.get(i + 6)).booleanValue());
						bollard.setIpaddress(list.get(i + 8));
						bollard.setMacaddress(list.get(i + 10));
					}

				}

				i += 14;

			}
			req = req.substring(0, req.length() - 1);
			// System.out.println(req);
			// PreparedStatement pstm = con.prepareStatement(req);
			// pstm.executeUpdate();
			System.out.println("");
			// System.out.println(req);
			System.out.println("");
			DataSource.returnConnection(con);

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	// A REECRIRE
	/*
	 * public ArrayList<VehicleSensor> GenerateAllVehicleSensors() throws
	 * ClassNotFoundException { ArrayList<VehicleSensor> retour = new
	 * ArrayList<VehicleSensor>(); ArrayList<Sensor> sensor = new
	 * ArrayList<Sensor>();
	 * 
	 * try { Connection con1 = DataSource.getConnection(); PreparedStatement pt1 =
	 * con1.prepareStatement("select * from sensor"); ResultSet rs1 =
	 * pt1.executeQuery();
	 * 
	 * while (rs1.next()) {
	 * 
	 * /* sensor.add(new Sensor(rs1.getInt(1), rs1.getString(2), rs1.getString(3),
	 * rs1.getInt(4), rs1.getString(5), rs1.getString(6), rs1.getBoolean(7)));
	 */
	/*
	 * sensor.add(new Sensor(rs1.getInt("idsensor"), rs1.getString("sensorstate"),
	 * rs1.getString("sensortype"), rs1.getInt("idzone"),
	 * rs1.getString("ipaddress"), rs1.getString("macaddress"),
	 * rs1.getBoolean("isinstalled")));
	 * 
	 * DataSource.returnConnection(con1); }
	 * 
	 * } catch (SQLException ex) { System.out.println("erreur " + ex.getMessage());
	 * }
	 * 
	 * try { Connection con2 = DataSource.getConnection(); PreparedStatement pt2 =
	 * con2.prepareStatement("select * from vehiclesensor"); ResultSet rs =
	 * pt2.executeQuery(); while (rs.next()) {
	 * 
	 * for (Sensor s : sensor) { if (s.getIdSensor() == rs.getInt(4)) {
	 * 
	 * retour.add(new VehicleSensor(rs.getInt(1), s.getSensorState(),
	 * s.getSensorType(), rs.getString(2), rs.getInt(3), s.getIdZone(),
	 * s.getIpAddress(), s.getMacAddress(), s.getIsInstalled()));
	 * DataSource.returnConnection(con2); }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * } catch (SQLException ex) { System.out.println("erreur " + ex.getMessage());
	 * } return retour; }
	 */

	public ArrayList<Bollard> GenerateAllBollards() throws ClassNotFoundException {
		ArrayList<Bollard> retour = new ArrayList<Bollard>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement pt = con.prepareStatement("select * from retractablebollard");
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				/*
				 * retour.add(new Bollard(rs.getInt(1), rs.getBoolean(2), rs.getInt(3),
				 * rs.getBoolean(4), rs.getString(5), rs.getString(6)));
				 */

				retour.add(new Bollard(rs.getInt("idbollard"), rs.getBoolean("bollardstate"), rs.getInt("idzone"),
						rs.getBoolean("isinstalled"), rs.getString("ipaddress"), rs.getString("macaddress")));

				
			}
			DataSource.returnConnection(con);

		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
		return retour;
	}

	public SmartCity GenerateCity() throws ClassNotFoundException {
		SmartCity smartcity = null;
		// ArrayList<smartcity> retour = new ArrayList<smartcity>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement pt = con.prepareStatement("select * from smartcity");
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				/*
				 * smartcity = new smartcity2(rs.getInt(1), rs.getDouble(3), rs.getDouble(4),
				 * rs.getInt(5), rs.getInt(6), rs.getInt(8), rs.getInt(7), rs.getInt(9),
				 * rs.getString(2)); maxnumbervehicles
				 */
				smartcity = new SmartCity(rs.getInt("idcity"), rs.getString("name"), rs.getDouble("heightkm"),
						rs.getDouble("widthkm"), rs.getInt("maxnumbervehicles"), rs.getInt("numberofvehicules"),
						rs.getInt("maxpolution"), rs.getInt("polutionlevel"), rs.getInt("tramfrequency"));
				
			}
			DataSource.returnConnection(con);

		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
		return smartcity;
	}

	public void UpdateSensorVehicles(String target, ArrayList<String> list,
			ArrayList<VehicleSensor> listVehicleSensorObj) throws ClassNotFoundException {
		try {
			Connection con = DataSource.getConnection();
			String req = "UPDATE vehiclesensor SET  nboflightcommercialvehicle=?, nbofcompactcar=?, nbofheavygoodsvehicle=?  WHERE idvehiclesensor =? and sensortype= ?;";

			PreparedStatement pstm = con.prepareStatement(req);
			int i = 2;

			// System.out.println("taille " + list.size());
			// System.out.println("case " + list.get(2));
			while (i < list.size()) {
				pstm.setInt(4, Integer.parseInt((list.get(i)))); // IDVehicule
				pstm.setString(5, list.get(i + 2)); // Sensortypeio
				pstm.setInt(1, Integer.parseInt(list.get(i + 4)));// nboflightcommercialvehicle
				pstm.setInt(2, Integer.parseInt(list.get(i + 6)));// nbofcompactcar
				pstm.setInt(3, Integer.parseInt(list.get(i + 8)));// nbofheavygoodsvehicle
				pstm.executeUpdate();

				
				/*for (VehicleSensor sensor : listVehicleSensorObj) {
					if ((sensor.getIdVehicleSensor() == Integer.parseInt((list.get(i))))) {// id sensor === id sensor

						sensor.setNbOfLightCommercialVehicle(Integer.parseInt(list.get(i + 4)));
						sensor.setNbOfCompactCar(Integer.parseInt(list.get(i + 6)));
						sensor.setNbOfHeavyGoodsVehicle(Integer.parseInt(list.get(i + 8)));

					}

				}*/

				i += 12;

			}
			req = req.substring(0, req.length() - 1);
			// System.out.println(req);
			// PreparedStatement pstm = con.prepareStatement(req);
			// pstm.executeUpdate();
			// System.out.println("");
			// System.out.println(req);
			// System.out.println("");
			DataSource.returnConnection(con);

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	/*
	 * public void UpdateSensorVehicles(String target, ArrayList<String> list,
	 * ArrayList<VehicleSensor> listVehicleSensorObj) throws ClassNotFoundException
	 * { try { Connection con = DataSource.getConnection(); String req =
	 * "UPDATE vehiclesensor SET  numberofvehicle=? WHERE idvehiclesensor =? and sensortype= ?;"
	 * ;
	 * 
	 * PreparedStatement pstm = con.prepareStatement(req); int i = 2;
	 * 
	 * // System.out.println("taille " + list.size()); // System.out.println("case "
	 * + list.get(2)); while (i < list.size()) { pstm.setInt(2,
	 * Integer.parseInt((list.get(i)))); // IDVehicule pstm.setString(3, list.get(i
	 * + 2)); // Sensortypeio pstm.setInt(1, Integer.parseInt(list.get(i + 4)));//
	 * NUMBERVEHICULE pstm.executeUpdate();
	 * 
	 * // Don't work if generateobject is commented in clientThread for
	 * (VehicleSensor sensor : listVehicleSensorObj) { if ((sensor.getIdSensor() ==
	 * Integer.parseInt((list.get(i))))) {
	 * 
	 * sensor.setNumberOfVehicle(Integer.parseInt(list.get(i + 4)));
	 * 
	 * }
	 * 
	 * }
	 * 
	 * i += 8;
	 * 
	 * } req = req.substring(0, req.length() - 1); // System.out.println(req); //
	 * PreparedStatement pstm = con.prepareStatement(req); // pstm.executeUpdate();
	 * // System.out.println(""); // System.out.println(req); //
	 * System.out.println(""); DataSource.returnConnection(con);
	 * 
	 * } catch (SQLException ex) { ex.printStackTrace(); } }
	 * 
	 * /* public ArrayList<String> showSensorById(int id) throws
	 * ClassNotFoundException { ArrayList<String> retour = new ArrayList<String>();
	 * try { Connection con = DataSource.getConnection(); PreparedStatement pt =
	 * con.prepareStatement("select * from sensor where idsensor =" + id); ResultSet
	 * rs = pt.executeQuery(); while (rs.next()) { retour.add(new
	 * Sensor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),
	 * rs.getString(5), rs.getString(6), rs.getBoolean(7)).toString());
	 * 
	 * } DataSource.returnConnection(con); } catch (SQLException ex) {
	 * System.out.println("erreur " + ex.getMessage()); } return retour;
	 * 
	 * }
	 */

	public void deleteVehiclesSensorById(int id) throws ClassNotFoundException {
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement pt = con.prepareStatement("delete from vehiclesensor where idvehiclesensor = " + id);
			pt.execute();
			DataSource.returnConnection(con);
		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
	}

	public void updateVehiclesSensorIsInstalled(int id, boolean install) throws ClassNotFoundException {
		try {
			Connection con = DataSource.getConnection();

			PreparedStatement pstm = con
					.prepareStatement(" UPDATE vehiclesensor SET isinstalled = ?  WHERE idvehiclesensor = ?");
			pstm.setBoolean(1, install);
			pstm.setInt(2, id);
			pstm.executeUpdate();
			DataSource.returnConnection(con);

		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
	}

	public void addOnVehiclesSensor(String target, ArrayList<String> list) throws ClassNotFoundException {
		try {
			Connection con = DataSource.getConnection();
			String req = "insert into  vehiclesensor"
					+ " (sensortype, ipaddress, macaddress, isinstalled, idzone, idbollard)\r\n" + " VALUES ";

			int i = 2;
			while (i < list.size()) {
				req += "(" + "\'" + list.get(i) + "\'" + "," + "\'" + list.get(i + 2) + "\'" + "," + list.get(i + 4)
						+ "," + "\'" + list.get(i + 6) + "\'" + "," + "\'" + list.get(i + 8) + "\'" + ","
						+ list.get(i + 10) + "),";
				i += 14;
			}
			req = req.substring(0, req.length() - 1);
			System.out.println(req);
			PreparedStatement pstm = con.prepareStatement(req);
			pstm.executeUpdate();
			System.out.println(req);
			DataSource.returnConnection(con);

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	


	public void addOnBollard(String target, ArrayList<String> list) throws ClassNotFoundException {
		try {
			Connection con = DataSource.getConnection();
			String req = "insert into " + target + " (BollardState, IDZone, IsInstalled,Ipaddress,Macaddress) VALUES ";
			int i = 2;

			// System.out.println("taille " + list.size());
			// System.out.println("case " + list.get(2));
			while (i < list.size()) {
				req += "(" + list.get(i) + "," + list.get(i + 2) + "," + list.get(i + 4) + "," + list.get(i + 6) + ","
						+ list.get(i + 8) + "),";
				i += 12;

			}

			// System.out.println("");

			// System.out.println("");
			req = req.substring(0, req.length() - 1);
			// System.out.println(req);
			PreparedStatement pstm = con.prepareStatement(req);
			pstm.executeUpdate();
			// System.out.println("");
			// System.out.println(req);
			// System.out.println("");
			DataSource.returnConnection(con);

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	/*
	 * public ArrayList<String> showAllSensorVehicles() throws
	 * ClassNotFoundException { ArrayList<String> retour = new ArrayList<String>();
	 * ArrayList<VehicleSensor> retour1 = new ArrayList<VehicleSensor>();
	 * ArrayList<Sensor> sensor = new ArrayList<Sensor>();
	 * 
	 * try { Connection con1 = DataSource.getConnection(); PreparedStatement pt1 =
	 * con1.prepareStatement("select * from sensor"); ResultSet rs1 =
	 * pt1.executeQuery();
	 * 
	 * while (rs1.next()) {
	 * 
	 * sensor.add(new Sensor(rs1.getInt(1), rs1.getString(2), rs1.getString(3),
	 * rs1.getInt(4), rs1.getString(5), rs1.getString(6), rs1.getBoolean(7)));
	 * 
	 * 
	 * } DataSource.returnConnection(con1); } catch (SQLException ex) {
	 * System.out.println("erreur " + ex.getMessage()); }
	 * 
	 * try { Connection con2 = DataSource.getConnection(); PreparedStatement pt2 =
	 * con2.prepareStatement("select * from vehiclesensor"); ResultSet rs =
	 * pt2.executeQuery(); while (rs.next()) {
	 * 
	 * for (Sensor s : sensor) { // System.out.println("SENSOR =" +s.getIdSensor());
	 * // System.out.println("Vehicule sensor id =" + rs.getInt(4) ); if
	 * (s.getIdSensor() == rs.getInt(4)) {
	 * 
	 * System.out.println("rs.getint(4) =" + rs.getInt(4)); //
	 * System.out.println("s.getSensorState() ="+s.getSensorState()); //
	 * System.out.println("s.getSensorType() ="+s.getSensorType());
	 * System.out.println("rs.getString(2) =" + rs.getString(2));
	 * System.out.println(" rs.getInt(3) =" + rs.getInt(3));
	 * System.out.println(" rs.getInt(3) =" + rs.getInt(3)); //
	 * System.out.println("s.getIdZone() ="+s.getIdZone()); //
	 * System.out.println("s.getIpAddress() ="+s.getIpAddress()); //
	 * System.out.println("s.getMacAddress() ="+s.getMacAddress()); //
	 * System.out.println("s.getIsInstalled()="+s.getIsInstalled());
	 * 
	 * // VehicleSensor c = new
	 * VehicleSensor(1,"12","23","12",12,12,"12","12",true);
	 * 
	 * VehicleSensor c = new VehicleSensor(rs.getInt(4), s.getSensorState(),
	 * s.getSensorType(), rs.getString(2), rs.getInt(3), s.getIdZone(),
	 * s.getIpAddress(), s.getMacAddress(), s.getIsInstalled()); String c1 =
	 * c.toString(); // System.out.println("c.getIdSensor(); =" + c.getIdSensor());
	 * // System.out.println("le c = "+c);
	 * 
	 * retour1.add(c);
	 * 
	 * retour.add(c1);
	 * 
	 * // System.out.println("retour1 =" + retour1);
	 * 
	 * 
	 * } }
	 * 
	 * } DataSource.returnConnection(con2); System.out.println("retour laaa   =" +
	 * retour);
	 * 
	 * } catch (SQLException ex) { System.out.println("erreur " + ex.getMessage());
	 * } return retour; }
	 */

	public ArrayList<String> showAllBollards() throws ClassNotFoundException, InterruptedException {
		ArrayList<String> retour = new ArrayList<String>();
		try {
			/*
			System.out.println("Connexion available on connexion pool before use : "+DataSource.getListConnectionavailable().size());
	        Thread.sleep(3000);
			System.out.println("Using");
			*/
			Connection con = DataSource.getConnection();
			
			PreparedStatement pt = con.prepareStatement("select * from retractablebollard");
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				retour.add(new Bollard(rs.getInt("idbollard"), rs.getBoolean("bollardstate"), rs.getInt("idzone"),
						rs.getBoolean("isinstalled"), rs.getString("ipaddress"), rs.getString("macaddress"))
								.toString());

			}
			
		   //   long start = System.currentTimeMillis();
		      
		       // System.out.println("Sleep time in ms = "+(System.currentTimeMillis()-start));
		        
		      /*  System.out.println("Connection available on connection pool after use "+DataSource.getListConnectionavailable().size());
		        System.out.println("10 seconde before the availability of the connection");
		        Thread.sleep(10000);
		        System.out.println(" return the connection+1");
		       */ DataSource.returnConnection(con);
		       // System.out.println("Connection available on connection pool "+DataSource.getListConnectionavailable().size());
		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
		return retour;

	}

	public ArrayList<String> showAllZone() throws ClassNotFoundException {
		ArrayList<String> retour = new ArrayList<String>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement pt = con.prepareStatement("select * from zone");
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				retour.add(new Zone(rs.getInt("idzone"), rs.getString("locations"), rs.getInt("IDCity")).toString());

			}
			DataSource.returnConnection(con);
		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
		return retour;
	}

	public ArrayList<String> showZoneById(int id) throws ClassNotFoundException {
		ArrayList<String> retour = new ArrayList<String>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement pt = con.prepareStatement("select * from zone where idzone =" + id);
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				retour.add(new Zone(rs.getInt("idzone"), rs.getString("locations"), rs.getInt("IDCity")).toString());

			}
			DataSource.returnConnection(con);
		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
		return retour;

	}

	public ArrayList<String> showAllSensorVehicles() throws ClassNotFoundException {
		ArrayList<String> retour = new ArrayList<String>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement pt = con.prepareStatement("select * from vehiclesensor");
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				retour.add(new VehicleSensor(rs.getInt("idvehiclesensor"), rs.getString("sensortype"),
						rs.getInt("nboflightcommercialvehicle"), rs.getInt("nbofcompactcar"),
						rs.getInt("nbofheavygoodsvehicle"), rs.getString("ipaddress"), rs.getString("macaddress"),
						rs.getBoolean("isinstalled"), rs.getInt("idzone"), rs.getInt("idbollard")).toString());

			}
			DataSource.returnConnection(con);
		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
		return retour;
	}

	public ArrayList<VehicleSensor> GenerateAllVehicleSensors() throws ClassNotFoundException {
		ArrayList<VehicleSensor> retour = new ArrayList<VehicleSensor>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement pt = con.prepareStatement("select * from vehiclesensor");
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				/*
				 * retour.add(new Bollard(rs.getInt(1), rs.getBoolean(2), rs.getInt(3),
				 * rs.getBoolean(4), rs.getString(5), rs.getString(6)));
				 */

				retour.add(new VehicleSensor(rs.getInt("idvehiclesensor"), rs.getString("sensortype"),
						rs.getInt("nboflightcommercialvehicle"), rs.getInt("nbofcompactcar"),
						rs.getInt("nbofheavygoodsvehicle"), rs.getString("ipaddress"), rs.getString("macaddress"),
						rs.getBoolean("isinstalled"), rs.getInt("idzone"), rs.getInt("idbollard")));

				
			}
			DataSource.returnConnection(con);
		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
		return retour;
	}



}
