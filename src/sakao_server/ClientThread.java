package sakao_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import sakao_common.Request;
import sakao_common.Response;

//import sakao_client_insert.TablesToBeInserted;
import sakao_common.Bollard;
import sakao_common.Request;
import sakao_common.Response;
import sakao_common.SmartCity;
import sakao_common.VehicleSensor;
import sakao_common.SmartCity;

public class ClientThread extends Thread {
	private Socket clientSocket;
	private OutputStreamWriter out;
	private BufferedReader in;
	private Request request = new Request();
	private Response response = new Response();

	private ObjectMapper mapper;
	private static int position = 1;
	private boolean shouldRun = true;

	// private SensorsService sensorService;

	private BollardService bollardService;
	private VehiclesSensorService vehiclesSensorService;
	private smartCityServices smartCityServices;
	private ZoneService zoneService;

	private ArrayList<VehicleSensor> vehicleSensorObject;
	private ArrayList<Bollard> bollardObject;
	private SmartCity smartCityObject;

	public ClientThread(Socket socket) throws ClassNotFoundException {
		super("Client" + position);
		position++;
		this.clientSocket = socket;

		// sensorService = new SensorsService();

		bollardService = new BollardService();
		vehiclesSensorService = new VehiclesSensorService();
		smartCityServices = new smartCityServices();
		zoneService = new ZoneService();

	}

	public void GenerateObject() {

		try {

			vehicleSensorObject = vehiclesSensorService.GenerateAllVehicleSensors();
			bollardObject = bollardService.GenerateAllBollards();
			smartCityObject = smartCityServices.GenerateCity();

			/*
			 * System.out.println("Objets generés :"); System.out.println("");
			 * System.out.println(vehicleSensorObject); System.out.println("");
			 * System.out.println(bollardObject); System.out.println("");
			 * System.out.println(smartCityObject); System.out.println("");
			 * System.out.println("Fin objets generés");
			 */

		} catch (Exception e) {

		}

	}

	
	
	private void CheckVehiclesThreshold() throws ClassNotFoundException {

		bollardObject = bollardService.GenerateAllBollards();



		int NbVehicleInCirculation = smartCityObject.VehicleInCirculation(vehicleSensorObject);
		double PoltutionInTown= smartCityObject.PoltutionPerVehicleInCirculation(vehicleSensorObject);
		smartCityServices.updateNumberinCirculation(NbVehicleInCirculation);
		smartCityServices.updateCurrentPolution(PoltutionInTown);
		
		smartCityObject = smartCityServices.GenerateCity();

		System.out.println("NbVehicleInCirculation = " + NbVehicleInCirculation);
		System.out.println("Current Polution in Town= " + PoltutionInTown);

		int Max = smartCityObject.getMaxNumberVehicles();
		int Maxminus20 = ((Max) - ((Max * 20) / 100)); // -20% of max
		
		int MaxPolution = smartCityObject.getMaxPolution();
		int MaxPolutionminus20 = ((MaxPolution) - ((MaxPolution * 20) / 100)); // -20% of max

		if (smartCityObject.CheckThresholdNbMaxVehicles(NbVehicleInCirculation) == true) {
		// Si le nb de vehicle circulent est superieur au seuil

			// Alors on leve les borne
			bollardService.Updatetrue(bollardObject);
			bollardObject = bollardService.GenerateAllBollards();

			// et On augmente la frequence des tram aux max cad 10
			smartCityServices.updateTramFrequency(10);
			smartCityObject = smartCityServices.GenerateCity();



			System.out.println("Retractable bollards are raised");
			System.out.println("Tramfrequency =  10/10");

		} 
		
		else {
		// 2. SINON	
			

			if (NbVehicleInCirculation < Maxminus20) {
				//2.1 SI le nombre de ve

				bollardService.Updatefalse(bollardObject);
				bollardObject = bollardService.GenerateAllBollards();

				smartCityServices.updateTramFrequency(6);
				smartCityObject = smartCityServices.GenerateCity();

				System.out.println("Retractable bollards are lowered");
				System.out.println("Tramfrequency =  6/10");
			} else {

				if (bollardObject.get(1).getIsBollardState() == true) {

					smartCityServices.updateTramFrequency(8);
					smartCityObject = smartCityServices.GenerateCity();
					// Faire liste des bollard

					System.out.println("Number of vehicule is decreasing in town");
					System.out.println("Retractable bollards are raised");
					System.out.println("Tramfrequency =  8/10");
				} else {

					smartCityServices.updateTramFrequency(8);
					smartCityObject = smartCityServices.GenerateCity();
					// Faire liste des bollard

					System.out.println("Number of vehicule is increasing in town");
					System.out.println("Retractable bollards are lowered");
					System.out.println("Tramfrequency =  8/10");

				}

			}
		}

	}


	public void CrudBollard(String operation_type, String target, ArrayList<String> list)
			throws ClassNotFoundException, JsonGenerationException, JsonMappingException, IOException, InterruptedException {

		switch (operation_type) {
		
		//Crud
		case "SELECT_ALL":

			response.setList(bollardService.showAllBollards());
			String outjsonStringSelectAll = mapper.writeValueAsString(response);
			out.write(outjsonStringSelectAll + "\n");
			out.flush();
			System.out.println("Display done to " + this.getName());
			System.out.println("********************");
			break;
			
			
			//CRUD
		case "INSERT":

			bollardService.addBollard("retractablebollard", list);//
			bollardObject = bollardService.GenerateAllBollards();

		//	System.out.println(bollardObject);

			String outjsonStringInsert = mapper.writeValueAsString(response);
			out.write(outjsonStringInsert + "\n");
			out.flush();
			System.out.println("Insert done for " + this.getName());
			System.out.println("********************");

			break;

		case "Update":
			// Afficher le update
			bollardService.updateBollard(target, list, bollardObject);
			bollardObject = bollardService.GenerateAllBollards();
			System.out.println("Update done on bollard");

			String outjsonStringUpdate = mapper.writeValueAsString(response);
			out.write(outjsonStringUpdate + "\n");
			out.flush();

			System.out.println("Update done to " + this.getName());
			System.out.println("********************");
			break;

		case "UPDATEinstall":
			System.out.println(this.bollardService.UpdateBollardIsInstalled(Integer.parseInt(this.request.getList().get(0)),
					Boolean.valueOf(this.request.getList().get(1))));
			String outjsonStringUPDATEinstall = mapper.writeValueAsString(response);
			out.write(outjsonStringUPDATEinstall + "\n");
			out.flush();
			System.out.println("Update sensor done for " + this.getName());
			System.out.println("********************");
			break;
			
			//CRUD
		case "UPDATEInstalledToTrueState":
			// Afficher le update
			bollardService.UpdateBollardInstalledToTrueState(list);
			bollardObject = bollardService.GenerateAllBollards();
			System.out.println("Update done on bollard");

			String outjsonStringUpdateBollardInstalledToTrueState = mapper.writeValueAsString(response);
			out.write(outjsonStringUpdateBollardInstalledToTrueState + "\n");
			out.flush();

			System.out.println("Update done to " + this.getName());
			System.out.println("********************");
			break;

		case "DELETE":
			System.out.println(bollardService.deleteBollardById(Integer.parseInt(this.request.getList().get(0))));
			String outjsonStringDeleteABollard = mapper.writeValueAsString(response);
			out.write(outjsonStringDeleteABollard + "\n");
			out.flush();
			System.out.println("A row deleted for " + this.getName());
			System.out.println("********************");
			break;
			
			//CRUD
		case "DELETE_INSTALL_STATE":
			System.out.println(bollardService.deleteBollardInstallState(list));
			String outjsonStringDeleteUninstalledBollard = mapper.writeValueAsString(response);
			out.write(outjsonStringDeleteUninstalledBollard + "\n");
			out.flush();
			System.out.println("A row deleted for " + this.getName());
			System.out.println("********************");
			break;
		

		}

	}

	public void CrudVehiclesSensor(String operation_type, String target, ArrayList<String> list)
			throws ClassNotFoundException, JsonGenerationException, JsonMappingException, IOException {

		switch (operation_type) {

		case "SELECT_ALL":

			response.setList(vehiclesSensorService.showAllSensorVehicles());
			String outjsonStringSelectAll = mapper.writeValueAsString(response);
			out.write(outjsonStringSelectAll + "\n");
			out.flush();
			System.out.println("Display done to " + this.getName());
			System.out.println("********************");
			break;
		case "INSERT":
			vehiclesSensorService.addOnVehiclesSensor(target, list);
			String outjsonStringDeleteAll = mapper.writeValueAsString(response);
			out.write(outjsonStringDeleteAll + "\n");
			out.flush();
			System.out.println("Insert done for " + this.getName());
			System.out.println("********************");
			break;
		case "DELETE":
			System.out.println(
					vehiclesSensorService.deleteVehiclesSensorById(Integer.parseInt(this.request.getList().get(0))));
			String outjsonStringDeleteAStudent = mapper.writeValueAsString(response);
			out.write(outjsonStringDeleteAStudent + "\n");
			out.flush();
			System.out.println("A row deleted for " + this.getName());
			System.out.println("********************");
			break;
		case "UPDATEisInstaled":
			System.out.println(this.vehiclesSensorService.updateVehiclesSensorIsInstalled(
					Integer.parseInt(this.request.getList().get(0)), Boolean.valueOf(this.request.getList().get(1))));
			String outjsonStringUpdateAge = mapper.writeValueAsString(response);
			out.write(outjsonStringUpdateAge + "\n");
			out.flush();
			System.out.println("Update sensor done for " + this.getName());
			System.out.println("********************");
			break;

		//

		case "Update":

			vehiclesSensorService.UpdateSensorVehicles(target, list, vehicleSensorObject);
			vehicleSensorObject = vehiclesSensorService.GenerateAllVehicleSensors();
			/*System.out.println("");
			System.out.println("List Sensor Objet Updated :");
			System.out.println("");
			System.out.println(vehicleSensorObject);
			System.out.println("");
			System.out.println("Fin object updated");
			System.out.println("");*/
			//this.CheckVehiclesThreshold();
			String outjsonStringUpdate = mapper.writeValueAsString(response);
			out.write(outjsonStringUpdate + "\n");
			out.flush();
			System.out.println("Update Vehicle Sensor done for " + this.getName());
			System.out.println("********************");
			break;
		}

	}

	private void CrudSmartcity(String operation_type, String target, ArrayList<String> list)
			throws ClassNotFoundException, JsonGenerationException, JsonMappingException, IOException {

		switch (operation_type) {

		case "Update":

			smartCityServices.UpdateSmartCityVehicles(target, list, smartCityObject);
			smartCityObject = smartCityServices.GenerateCity();

			String outjsonStringUpdateSmart = mapper.writeValueAsString(response);
			out.write(outjsonStringUpdateSmart + "\n");
			out.flush();
			// System.out.println("");
			System.out.println("SmartCity object Updated :");
			System.out.println("");
		//	System.out.println(smartCityObject);
			System.out.println("");
			System.out.println("Fin object updated");
			System.out.println("");
			System.out.println("Update smartcity done for " + this.getName());

			System.out.println("********************");
			break;
		}

	}

	public void CrudZone(String operation_type, String target, ArrayList<String> list)
			throws ClassNotFoundException, JsonGenerationException, JsonMappingException, IOException {

		switch (operation_type) {
		case "SELECT_ALL":
			response.setList(zoneService.showAllZone());
			String outjsonStringSelectAll = mapper.writeValueAsString(response);
			out.write(outjsonStringSelectAll + "\n");
			out.flush();
			System.out.println("Display done to " + this.getName());
			System.out.println("********************");
			break;

		case "SELECT_ZONE":
			response.setList(zoneService.showZoneById(Integer.parseInt(list.get(0))));
			String outjsonStringSelectZone = mapper.writeValueAsString(response);
			out.write(outjsonStringSelectZone + "\n");
			out.flush();
			System.out.println("Display done to " + this.getName());
			System.out.println("********************");
			break;

		/*
		 * case "UPDATE_BETA": System.out.println(
		 * this.zoneService.updateZoneBeta(Integer.parseInt(list.get(0)),
		 * Integer.parseInt(list.get(1)))); String outjsonStringUpdateZoneBeta =
		 * mapper.writeValueAsString(response); out.write(outjsonStringUpdateZoneBeta +
		 * "\n"); out.flush(); System.out.println("Update zone done for " +
		 * this.getName()); System.out.println("********************"); break; case
		 * "UPDATE": System.out
		 * .println(this.zoneService.updateZone(Integer.parseInt(list.get(0)),
		 * Boolean.valueOf(list.get(1)))); String outjsonStringUpdateZone =
		 * mapper.writeValueAsString(response); out.write(outjsonStringUpdateZone +
		 * "\n"); out.flush(); System.out.println("Update zone done for " +
		 * this.getName()); System.out.println("********************"); break; case
		 * "INSERT": zoneService.addOnZone(target, list); String outjsonStringDeleteAll
		 * = mapper.writeValueAsString(response); out.write(outjsonStringDeleteAll +
		 * "\n"); out.flush(); System.out.println("Insert done for " + this.getName());
		 * System.out.println("********************"); break;
		 */
		}

	}

	public void StartCrud() throws JsonParseException, JsonMappingException, IOException, ClassNotFoundException,
			NullPointerException, JSONException {
		mapper = new ObjectMapper();
		String outjsonString = in.readLine();
		
		System.out.println("Recieved from client : "+ this.getName());
		System.out.println(outjsonString);

		request = mapper.readValue(outjsonString, Request.class);
		System.out.println("List :");
		System.out.println(request.getList());
	//	System.out.println(request.getList().size());
		System.out.println("       ");
///// String operation_type = request.getOperation_type();
		String target = this.request.getTarget();
		switch (target) {

		case "zone":
			try {
				this.CrudZone(this.request.getOperation_type(), this.request.getTarget(), this.request.getList());
			} catch (Exception e) {
			}

			break;

		case "smartcity":
			try {
				this.CrudSmartcity(this.request.getOperation_type(), this.request.getTarget(), this.request.getList());
			} catch (Exception e) {
			}

			break;
		case "vehiclesSensor":
			try {
				this.CrudVehiclesSensor(this.request.getOperation_type(), this.request.getTarget(),
						this.request.getList());
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "bollard":
			try {
				this.CrudBollard(this.request.getOperation_type(), this.request.getTarget(), this.request.getList());
			} catch (Exception e) {
			}

			break;

		}

	}

	public void run() {
		try {
			out = new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			System.out.println(this.getName() + " connected");
			System.out.println("********************");
			System.out.println("");
			 this.GenerateObject(); 
			// this.CheckVehiclesThreshold();
			while (shouldRun) {
				this.StartCrud();
			}

			in.close();
			out.close();
			clientSocket.close();

		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println(this.getName() + " disconnected");
			System.out.println("********************");

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
