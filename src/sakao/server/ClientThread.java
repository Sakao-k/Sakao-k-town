package sakao.server;

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

import sakao.common.Bollard;
import sakao.common.JsonToSend;
import sakao.common.Request;
import sakao.common.Response;
import sakao.common.ResultTreshold;
import sakao.common.SmartCity;
import sakao.common.VehicleSensor;

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
	private ResultTreshold resultTreshold;
	private ArrayList<String> retourCheck;
	private ArrayList<String> reponseresult;

	public ClientThread(Socket socket) throws ClassNotFoundException {
		super("Client" + position);
		position++;
		this.clientSocket = socket;

		// sensorService = new SensorsService();

		retourCheck = new ArrayList<String>();
		resultTreshold = new ResultTreshold();
		reponseresult = new ArrayList<String>();

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

	private ResultTreshold CheckVehiclesThreshold() throws ClassNotFoundException, IOException, JSONException {

//		bollardObject = bollardService.GenerateAllBollards();
//		vehicleSensorObject = vehiclesSensorService.GenerateAllVehicleSensors();
//		smartCityObject = smartCityServices.GenerateCity();
		resultTreshold.setEntry(true);
		double CurrentPolution = smartCityObject.getPolutionLevel();
		int NbVehicleInCirculation = smartCityObject.getNumberVehicles();

		System.out.println("CurrentPolution : " + CurrentPolution);
		System.out.println("NbVehicleInCirculation : " + NbVehicleInCirculation);

		ArrayList<Integer> ListInWait = smartCityObject.VehicleInCirculationWait(vehicleSensorObject);
		int OutNbVehicle = ListInWait.get(7);
		int InNBVehicle = ListInWait.get(8);

		System.out.println("OutNbVehicle : " + OutNbVehicle);
		System.out.println("InNBVehicle : " + InNBVehicle);

		int outLight = ListInWait.get(1);
		int outCompact = ListInWait.get(2);
		int outHeavy = ListInWait.get(3);

		System.out.println("outLight : " + outLight);
		System.out.println("outCompact : " + outCompact);
		System.out.println("outHeavy : " + outHeavy);

		int inLight = ListInWait.get(4);
		int inCompact = ListInWait.get(5);
		int inHeavy = ListInWait.get(6);

		System.out.println("inLight : " + inLight);
		System.out.println("inCompact : " + inCompact);
		System.out.println("inHeavy : " + inHeavy);

		double PolutionforOneLightCommercialVehicle = smartCityObject.getPolutionlightcommercialvehicle();
		double PolutionforOneCompactCar = smartCityObject.getPolutioncompactcar();
		double PolutionforOneHeavyGoodsVehicle = smartCityObject.getPolutionheavygoodsvehicle();

		System.out.println("PolutionforOneLightCommercialVehicle : " + PolutionforOneLightCommercialVehicle);
		System.out.println("PolutionforOneCompactCar : " + PolutionforOneCompactCar);
		System.out.println("PolutionforOneHeavyGoodsVehicle : " + PolutionforOneHeavyGoodsVehicle);

//Pour avoir le CURRENT POLUTION ET CURRENT CIRCULATION
//Pour tout les type de voiture, A FAIRE PAR TYPE DE VEHICELES	

/////SI OUTPUT 
		// ALORS on le soustrait au nbV et on soustrait son taux de polution
		// On revoie le currentpolution et currentCirculation
		// UPDATE

		if (OutNbVehicle > 0) {

			for (int i = 0; i < outLight; i++) {

				NbVehicleInCirculation -= 1;
				CurrentPolution = CurrentPolution - PolutionforOneLightCommercialVehicle;

			}
			for (int i = 0; i < outCompact; i++) {

				NbVehicleInCirculation -= 1;
				CurrentPolution = CurrentPolution - PolutionforOneCompactCar;

			}
			for (int i = 0; i < outHeavy; i++) {

				NbVehicleInCirculation -= 1;
				CurrentPolution = CurrentPolution - PolutionforOneHeavyGoodsVehicle;

			}

			System.out.println("NbVehicleInCirculation after substract OutNbVehicle : " + NbVehicleInCirculation);
			System.out.println("CurrentPolution after substract OutNbVehicle : " + CurrentPolution);

		} 
		
		
		if (InNBVehicle> 0) {

			//// SINON (SI INPUT)

			// Si AncienCirculation <= maxCirculation OU AncienPolution<= maxPolution

			if (NbVehicleInCirculation >= smartCityObject.getMaxNumberVehicles()
					|| CurrentPolution >= smartCityObject.getMaxPolution()) {

				System.out.println("A AJOUTER DANS RESULT TRESHOLD");
				resultTreshold.setEntry(false);

				// Alors on le refuse -> on envoie un result refuser
				// On garde les ancien Current NBVEHICLES et Current Polution

			} else {

				// Sinon

				// int NBVACCEPTABLE = smartCityObject.getMaxNumberVehicles() -
				// NbVehicleInCirculation;

				// System.out.println("NBVACCEPTABLE : " + NBVACCEPTABLE);

				for (int i = 0; i < inLight; i++) {

					double PolutioninWait = CurrentPolution + PolutionforOneLightCommercialVehicle;

					if (PolutioninWait > smartCityObject.getMaxPolution()) {

						System.out.println("A AJOUTER DANS RESULT TRESHOLD");
						resultTreshold.setEntry(false);

					} else {

						if (NbVehicleInCirculation >= smartCityObject.getMaxNumberVehicles()) {

							System.out.println("A AJOUTER DANS RESULT TRESHOLD");
							resultTreshold.setEntry(false);

						} else {

							NbVehicleInCirculation += 1;
							CurrentPolution = CurrentPolution + PolutionforOneLightCommercialVehicle;

						}

					}

				}

				for (int i = 0; i < inCompact; i++) {

					double PolutioninWait = CurrentPolution + PolutionforOneCompactCar;

					if (PolutioninWait > smartCityObject.getMaxPolution()) {

						System.out.println("A AJOUTER DANS RESULT TRESHOLD");
						resultTreshold.setEntry(false);

					} else {

						if (NbVehicleInCirculation >= smartCityObject.getMaxNumberVehicles()) {

							System.out.println("A AJOUTER DANS RESULT TRESHOLD");
							resultTreshold.setEntry(false);

						} else {

							NbVehicleInCirculation += 1;
							CurrentPolution = CurrentPolution + PolutionforOneCompactCar;

						}

					}

				}

				for (int i = 0; i < inHeavy; i++) {

					double PolutioninWait = CurrentPolution + PolutionforOneHeavyGoodsVehicle;

					if (PolutioninWait > smartCityObject.getMaxPolution()) {

						System.out.println("A AJOUTER DANS RESULT TRESHOLD");
						resultTreshold.setEntry(false);

					} else {

						if (NbVehicleInCirculation >= smartCityObject.getMaxNumberVehicles()) {

							System.out.println("A AJOUTER DANS RESULT TRESHOLD");
							resultTreshold.setEntry(false);

						} else {

							NbVehicleInCirculation += 1;
							CurrentPolution = CurrentPolution + PolutionforOneHeavyGoodsVehicle;

						}

					}

				}

				
				System.out.println("NbVehicleInCirculation after add InNbVehicle : " + NbVehicleInCirculation);
				System.out.println("CurrentPolution after add InNbVehicle : " + CurrentPolution);
				// Si InL > NBVACCEPTABLE

				// pour chaque in -> i=0 i<in, i++

				// Si la PolutionIn+AncienPolution > maxPolution
				// Alors on le refuse -> on envoie un result refuser
				// On garde les ancien Current NBVEHICLES et Current Polution
				// SINON

				// on l'ajoute au currentCirculation et on ajoute sa polution au currentPOLUTION
				// On revoie le currentpolution et currentCirculation
				// UPDATE

				// FIN RETOUR DE CURRENTCIRCULATION ET CURENTPOLUTION
			}
		}

		String m = "";

		System.out.println("NbVehicleInCirculation : " + NbVehicleInCirculation);
		System.out.println("CurrentPolution : " + CurrentPolution);
		// int NbVehicleInCirculation =
		// smartCityObject.VehicleInCirculation(vehicleSensorObject); // in + incity -
		// out)

		// double CurentPolution =
		// smartCityObject.PoltutionPerVehicleInCirculation(vehicleSensorObject);

		if (NbVehicleInCirculation >= smartCityObject.getMaxNumberVehicles()
				|| NbVehicleInCirculation >= smartCityObject.getMaxNumberVehicles()) {

			if (NbVehicleInCirculation >= smartCityObject.getMaxNumberVehicles()) {

				NbVehicleInCirculation = smartCityObject.getMaxNumberVehicles();

				smartCityServices.updateNumberinCirculation(NbVehicleInCirculation);
				smartCityServices.updateCurrentPolution(CurrentPolution);

				smartCityObject = smartCityServices.GenerateCity();

				resultTreshold.setNbVehicleInCirculation(NbVehicleInCirculation);

				m = "Treshold Number of vehicles !!!";
				resultTreshold.setTresholdResult(m);

				resultTreshold.setCurentPolution(CurrentPolution);

			}

			if (CurrentPolution >= smartCityObject.getMaxPolution()) {

				CurrentPolution = smartCityObject.getMaxPolution();

				smartCityServices.updateNumberinCirculation(NbVehicleInCirculation);
				smartCityServices.updateCurrentPolution(CurrentPolution);

				smartCityObject = smartCityServices.GenerateCity();

				resultTreshold.setNbVehicleInCirculation(NbVehicleInCirculation);

				m += "   Treshold Polution !!!";

				resultTreshold.setTresholdResult(m);

				resultTreshold.setCurentPolution(CurrentPolution);

			}

			bollardService.Updatetrue(bollardObject);
			bollardObject = bollardService.GenerateAllBollards();

			smartCityServices.updateTramFrequency(10);
			smartCityObject = smartCityServices.GenerateCity();

			System.out.println("Retractable bollards are raised");

			resultTreshold.setBollardStateResult(true);

			System.out.println("Tramfrequency =  10/10");

			resultTreshold.setTramFrequencyResult(10);

		} else {

			smartCityServices.updateNumberinCirculation(NbVehicleInCirculation);
			smartCityServices.updateCurrentPolution(CurrentPolution);

			smartCityObject = smartCityServices.GenerateCity();

			System.out.println(vehicleSensorObject);
			System.out.println(smartCityObject);

			/////////////////////////////////////////////////////////////////////////////////////////
			System.out.println("Seuil : " + smartCityObject.getMaxNumberVehicles());
			System.out.println("NbVehicleInCirculation = " + NbVehicleInCirculation);
			resultTreshold.setNbVehicleInCirculation(NbVehicleInCirculation);

			System.out.println("CurentPolution = " + CurrentPolution);
			resultTreshold.setCurentPolution(CurrentPolution);

			int Max = smartCityObject.getMaxNumberVehicles();
			int Maxminus20 = ((Max) - ((Max * 20) / 100)); // -20% of max

			double MaxPolution = smartCityObject.getMaxPolution();
			double MaxPolutionmunus20 = ((MaxPolution) - ((MaxPolution * 20) / 100));

			if (smartCityObject.CheckThresholdNbMaxVehicles(NbVehicleInCirculation) == true
					|| smartCityObject.CheckThresholdMaxPolution(CurrentPolution) == true) {

				bollardService.Updatetrue(bollardObject);
				bollardObject = bollardService.GenerateAllBollards();

				smartCityServices.updateTramFrequency(10);
				smartCityObject = smartCityServices.GenerateCity();

				if (smartCityObject.CheckThresholdNbMaxVehicles(NbVehicleInCirculation) == true) {
//faire m
					System.out.println("Treshold Number of vehicles !!!");

					resultTreshold.setTresholdResult("Treshold Number of vehicles !!!");
				}

				if (smartCityObject.CheckThresholdMaxPolution(CurrentPolution) == true) {
					System.out.println("Treshold Polution !!!");

					resultTreshold.setTresholdResult("Treshold Polution !!!");

				}

				System.out.println("Retractable bollards are raised");

				resultTreshold.setBollardStateResult(true);

				System.out.println("Tramfrequency =  10/10");

				resultTreshold.setTramFrequencyResult(10);

			} /*
				 * else if (smartCityObject.CheckThresholdNbMaxVehicles(NbVehicleInCirculation)
				 * == false && bollardObject.get(0).getIsInstalled() == true) {
				 */

			else {

				if (NbVehicleInCirculation < Maxminus20 && CurrentPolution < MaxPolutionmunus20) {

					bollardService.Updatefalse(bollardObject);
					bollardObject = bollardService.GenerateAllBollards();

					smartCityServices.updateTramFrequency(6);
					smartCityObject = smartCityServices.GenerateCity();

					// Treshold
					System.out.println("Lower Number of vehicles & Lower Polution");

					resultTreshold.setTresholdResult("Lower Number of vehicles & Lower Polution");

					// bollard
					System.out.println("Retractable bollards are lowered");

					resultTreshold.setBollardStateResult(false);

					// Tram freq
					System.out.println("Tramfrequency =  6/10");

					resultTreshold.setTramFrequencyResult(6);

				} else {

					if (bollardObject.get(1).getIsBollardState() == true) {

						smartCityServices.updateTramFrequency(8);
						smartCityObject = smartCityServices.GenerateCity(); // Faire liste des bollard

						if (NbVehicleInCirculation > Maxminus20 - 1) {

							System.out.println("Number of vehicule is decreasing in town");

							resultTreshold.setTresholdResult("Number of vehicule is decreasing in town");
						}

						if (CurrentPolution > MaxPolutionmunus20 - 1) {

							System.out.println("Polution is decreasing in town");

							resultTreshold.setTresholdResult("Polution is decreasing in town");
						}

						System.out.println("Retractable bollards are raised");

						resultTreshold.setBollardStateResult(true);

						System.out.println("Tramfrequency =  8/10");
						resultTreshold.setTramFrequencyResult(8);

					} else {

						smartCityServices.updateTramFrequency(8);
						smartCityObject = smartCityServices.GenerateCity(); // Faire liste des bollard

						if (NbVehicleInCirculation > Maxminus20 - 1) {

							System.out.println("Number of vehicule is increasing in town");
							resultTreshold.setTresholdResult("Number of vehicule is increasing in town");

						}

						if (CurrentPolution > MaxPolutionmunus20 - 1) {

							System.out.println("Polution is increasing in town");
							resultTreshold.setTresholdResult("Polution is increasing in town");
						}

						System.out.println("Retractable bollards are lowered");
						resultTreshold.setBollardStateResult(false);

						System.out.println("Tramfrequency =  8/10");
						resultTreshold.setTramFrequencyResult(8);

					}

				}
			}
		}

		System.out.println("RESULT TRESHOLD  : " + resultTreshold.toString());
		// this.initVehicles();
		return resultTreshold;

	}

	private void initVehicles() throws IOException, JSONException, ClassNotFoundException {

		JsonToSend j = new JsonToSend();
		Request req = j.requestParse("file-for-test/InitVehiculesSensor.json");
		ArrayList<String> listR = req.getList();
		vehiclesSensorService.UpdateSensorVehicles("vehiclesensor", listR, vehicleSensorObject);
		vehicleSensorObject = vehiclesSensorService.GenerateAllVehicleSensors();

	}

	public void CrudBollard(String operation_type, String target, ArrayList<String> list) throws ClassNotFoundException,
			JsonGenerationException, JsonMappingException, IOException, InterruptedException {

		switch (operation_type) {

		// Crud
		case "SELECT_ALL":

			response.setList(bollardService.showAllBollards());
			String outjsonStringSelectAll = mapper.writeValueAsString(response);
			out.write(outjsonStringSelectAll + "\n");
			out.flush();
			System.out.println("Display done to " + this.getName());
			System.out.println("********************");
			break;

		// CRUD
		case "INSERT":

			bollardService.addBollard("retractablebollard", list);//
			bollardObject = bollardService.GenerateAllBollards();

			System.out.println(bollardObject);

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
			System.out.println(this.bollardService.UpdateBollardIsInstalled(
					Integer.parseInt(this.request.getList().get(0)), Boolean.valueOf(this.request.getList().get(1))));
			String outjsonStringUPDATEinstall = mapper.writeValueAsString(response);
			out.write(outjsonStringUPDATEinstall + "\n");
			out.flush();
			System.out.println("Update sensor done for " + this.getName());
			System.out.println("********************");
			break;

		// CRUD
		case "UPDATEInstalledToTrueState":
			// Afficher le update
			bollardService.UpdateBollardInstalledToTrueState(list);
			// bollardObject = bollardService.GenerateAllBollards();
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

		// CRUD
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
			throws ClassNotFoundException, JsonGenerationException, JsonMappingException, IOException, JSONException {

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

			// System.out.println("Avant :" + vehicleSensorObject);
			vehiclesSensorService.UpdateSensorVehicles(target, list, vehicleSensorObject);
			vehicleSensorObject = vehiclesSensorService.GenerateAllVehicleSensors();
			System.out.println("");
			System.out.println("List Sensor Objet Updated :");
			System.out.println("");
			System.out.println(vehicleSensorObject);
			System.out.println("");
			System.out.println("Fin object updated");
			System.out.println("");
			resultTreshold = this.CheckVehiclesThreshold();
			reponseresult.add(resultTreshold.toString());
			response.setList(reponseresult);
			// response
			String outjsonStringUpdate = mapper.writeValueAsString(response);
			out.write(outjsonStringUpdate + "\n");
			out.flush();
			reponseresult.clear();
			System.out.println("Update Vehicle Sensor done for " + this.getName());
			System.out.println("********************");

			break;
		}

	}

	private void CrudSmartcity(String operation_type, String target, ArrayList<String> list)
			throws ClassNotFoundException, JsonGenerationException, JsonMappingException, IOException {

		switch (operation_type) {

		case "SELECT_ALL":
			response.setList(smartCityServices.showCity());
			String outjsonStringSelectAllSmart = mapper.writeValueAsString(response);
			out.write(outjsonStringSelectAllSmart + "\n");
			out.flush();
			System.out.println("Display done to " + this.getName() + "WITH :" + outjsonStringSelectAllSmart);
			System.out.println("********************");
			break;

		case "Update":

			smartCityServices.UpdateSmartCityVehicles(target, list, smartCityObject);
			smartCityObject = smartCityServices.GenerateCity();

			String outjsonStringUpdateSmart = mapper.writeValueAsString(response);

			out.write(outjsonStringUpdateSmart + "\n");

			out.flush();

			// System.out.println("");
			System.out.println("SmartCity object Updated :");
			System.out.println("");
			System.out.println(smartCityObject);
			System.out.println("");
			System.out.println("Fin object updated");
			System.out.println("");
			System.out.println("Update smartcity done for " + this.getName());

			System.out.println("********************");
			break;
		}

	}

	private void CrudCheck(String operation_type, String target, ArrayList<String> list)
			throws ClassNotFoundException, JsonGenerationException, JsonMappingException, IOException, JSONException {

		switch (operation_type) {

		case "SELECT_ALL":

			this.initVehicles();
			bollardObject = bollardService.GenerateAllBollards();
			smartCityObject = smartCityServices.GenerateCity();
			vehicleSensorObject = vehiclesSensorService.GenerateAllVehicleSensors();
			resultTreshold = this.CheckVehiclesThreshold();
			retourCheck.add(resultTreshold.toString());
			response.setList(retourCheck);
			String outjsonStringSelectChecl = mapper.writeValueAsString(response);
			out.write(outjsonStringSelectChecl + "\n");
			out.flush();
			System.out.println("Display Check done to " + this.getName());
			System.out.println("********************");
			vehicleSensorObject.clear();

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

		System.out.println("Recieved from client : " + this.getName());
		System.out.println(outjsonString);

		request = mapper.readValue(outjsonString, Request.class);
		System.out.println("List :");
		System.out.println(request.getList());
		// System.out.println(request.getList().size());
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

		case "check":
			try {
				this.CrudCheck(this.request.getOperation_type(), this.request.getTarget(), this.request.getList());
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
