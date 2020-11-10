package sakao.client.ihm;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import sakao.common.Bollard;
import sakao.common.ResultTreshold;
import sakao.common.SmartCity;
import sakao.common.VehicleSensor;

public class SimulationCalcul {

	private VehicleSensor vehicle;
	private ArrayList<VehicleSensor> vehicleSensorObject = new ArrayList<VehicleSensor>();
	private SmartCity smartCityObject;
	// private ResultTreshold resultTreshold;
	// private Bollard bollardObject;

	public SimulationCalcul(SmartCity smartCityObject, VehicleSensor vehicle, ResultTreshold resultTreshold) {

		this.smartCityObject = smartCityObject;
		this.vehicle = vehicle;
		// this.resultTreshold = resultTreshold;
		this.vehicleSensorObject.add(vehicle);

	}

	public VehicleSensor getVehicle() {
		return vehicle;
	}

	public void setVehicle(VehicleSensor vehicle) {
		this.vehicle = vehicle;
	}

	public ArrayList<VehicleSensor> getVehicleSensorObject() {
		return vehicleSensorObject;
	}

	public void setVehicleSensorObject(ArrayList<VehicleSensor> vehicleSensorObject) {
		this.vehicleSensorObject = vehicleSensorObject;
	}

	public SmartCity getSmartCityObject() {
		return smartCityObject;
	}

	public void setSmartCityObject(SmartCity smartCityObject) {
		this.smartCityObject = smartCityObject;
	}

	/*
	 * public ResultTreshold getResultTreshold() { return resultTreshold; }
	 * 
	 * 
	 * public void setResultTreshold(ResultTreshold resultTreshold) {
	 * this.resultTreshold = resultTreshold; }
	 */

	public ResultTreshold CheckVehiclesThreshold(ResultTreshold resultTreshold) {

//		bollardObject = bollardService.GenerateAllBollards();
//		vehicleSensorObject = vehiclesSensorService.GenerateAllVehicleSensors();
//		smartCityObject = smartCityServices.GenerateCity();

		int NbVehicleInCirculation = smartCityObject.VehicleInCirculation(vehicleSensorObject); // in + incity - out)

		double CurentPolution = smartCityObject.PoltutionPerVehicleInCirculation(vehicleSensorObject);

		// System.out.println(CurentPolution);

		if (NbVehicleInCirculation < 0 || CurentPolution < 0) {
			if (NbVehicleInCirculation < 0) {

				resultTreshold.setTresholdResult("Error NbVehicleInCirculation");
			}
			if (CurentPolution < 0) {

				resultTreshold.setTresholdResult("Error CurentPolution");
			}

		} else {

			smartCityObject.setNumberVehicles(NbVehicleInCirculation);
			smartCityObject.setPolutionLevel(CurentPolution);

			/////////////////////////////////////////////////////////////////////////////////////////
			System.out.println("NbVehicleInCirculation = " + NbVehicleInCirculation);
			resultTreshold.setNbVehicleInCirculation(NbVehicleInCirculation);

			System.out.println("CurentPolution = " + CurentPolution);
			resultTreshold.setCurentPolution(CurentPolution);

			int Max = smartCityObject.getMaxNumberVehicles();
			int Maxminus20 = ((Max) - ((Max * 20) / 100)); // -20% of max

			double MaxPolution = smartCityObject.getMaxPolution();
			double MaxPolutionmunus20 = ((MaxPolution) - ((MaxPolution * 20) / 100));

			if (smartCityObject.CheckThresholdNbMaxVehicles(NbVehicleInCirculation) == true
					|| smartCityObject.CheckThresholdMaxPolution(CurentPolution) == true) {

				// bollardObject.setBollardState(true);

				smartCityObject.setTramFrequency(10);

				if (smartCityObject.CheckThresholdNbMaxVehicles(NbVehicleInCirculation) == true) {

					System.out.println("Treshold Number of vehicles !!!");

					resultTreshold.setTresholdResult("Treshold Number of vehicles !!!");
				}

				if (smartCityObject.CheckThresholdMaxPolution(CurentPolution) == true) {
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

				if (NbVehicleInCirculation < Maxminus20 && CurentPolution < MaxPolutionmunus20) {

					// bollardObject.setBollardState(false);
					smartCityObject.setTramFrequency(6);

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

					if (resultTreshold.getisBollardStateResult() == true) {

						smartCityObject.setTramFrequency(8);

						if (NbVehicleInCirculation > Maxminus20 - 1) {

							System.out.println("Number of vehicule is decreasing in town");

							resultTreshold.setTresholdResult("Number of vehicule is decreasing in town");
						}

						if (CurentPolution > MaxPolutionmunus20 - 1) {

							System.out.println("Polution is decreasing in town");

							resultTreshold.setTresholdResult("Polution is decreasing in town");
						}

						System.out.println("Retractable bollards are raised");

						resultTreshold.setBollardStateResult(true);

						System.out.println("Tramfrequency =  8/10");
						resultTreshold.setTramFrequencyResult(8);

					} else {

						smartCityObject.setTramFrequency(8);

						if (NbVehicleInCirculation > Maxminus20 - 1) {

							System.out.println("Number of vehicule is increasing in town");
							resultTreshold.setTresholdResult("Number of vehicule is increasing in town");

						}

						if (CurentPolution > MaxPolutionmunus20 - 1) {

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
		return resultTreshold;

	}

}
