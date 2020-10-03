package sakao_common;

import java.util.ArrayList;
import java.util.Iterator;

public class SmartCity {
	private int id;
	private String name;
	private double heightkm;
	private double widthkm;

	private int maxNumberVehicles;
	private int numberVehicles; // In circulation

	private int maxPolution;
	private int polutionLevel; // In the town

	private int tramFrequency;

	public SmartCity() {
	}

	public SmartCity(int id, String name, double HeightKM, double WidthKM, int maxNumberVehicles, int numberVehicles,
			int maxPolution, int polutionLevel, int tramFrequency) {
///MIS A JOUR
		this.id = id;
		this.name = name;
		this.heightkm = HeightKM;
		this.widthkm = WidthKM;

		this.maxNumberVehicles = maxNumberVehicles;
		this.numberVehicles = numberVehicles;

		this.maxPolution = maxPolution;
		this.polutionLevel = polutionLevel;

		this.tramFrequency = tramFrequency;

	}

	public String toString() {
		return "{\"id\":\"" + this.id + "\"," + "\"name\":\"" + this.name + "\"," + "\"heightkm\":\""
				+ this.getHeightkm() + "\"," + "\"widthkm\":\"" + this.getWidthkm() + "\"," +

				"\"maxnumberofvehicles\":\"" + this.getMaxNumberVehicles() + "\"," + "\"numbervehicles\":\""
				+ this.getNumberVehicles() + "\"," +

				"\"maxPolution\":\"" + this.getMaxPolution() + "\"," + "\"polutionLevel\":\"" + this.getPolutionLevel()
				+ "\"," +

				"\"tramfrequency\":\"" + this.getTramFrequency() + "\"}";

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getHeightkm() {
		return this.heightkm;
	}

	public void setHeightkm(double height) {
		this.heightkm = height;
	}

	public double getWidthkm() {
		return this.widthkm;
	}

	public void setWidthkm(double width) {
		this.widthkm = width;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberVehicles() {
		return numberVehicles;
	}

	public void setNumberVehicles(int numberVehicles) {
		numberVehicles = numberVehicles;
	}

	public int getMaxNumberVehicles() {
		return maxNumberVehicles;
	}

	public void setMaxNumberVehicles(int maxNumberVehicles) {
		maxNumberVehicles = maxNumberVehicles;
	}

	public int getTramFrequency() {
		return tramFrequency;
	}

	public void setTramFrequency(int tramFrequency) {
		tramFrequency = tramFrequency;
	}

	public int getMaxPolution() {
		return maxPolution;
	}

	public void setMaxPolution(int maxPolution) {
		this.maxPolution = maxPolution;
	}

	public int getPolutionLevel() {
		return polutionLevel;
	}

	public void setPolutionLevel(int polutionLevel) {
		this.polutionLevel = polutionLevel;
	}

	
	
	
	
	
	
	
	
	///////////////////////////////////////////////////// A REVOIR (RAJOUTER CALCUL POLUTION)
/*
	public int CityThresholdPolution(ArrayList<Zone> listzone) {

		int cityThresholdPolution = 0;

		for (Zone zone : listzone) {

			cityThresholdPolution += zone.getThresholdBeta();

		}

		return cityThresholdPolution;

	}

	public int VehicleInCirculation(ArrayList<VehicleSensor> Sensorlist) {

		int inV = 0;
		int outV = 0;

		Iterator<VehicleSensor> it = Sensorlist.iterator();

		while (it.hasNext()) {
			VehicleSensor v = it.next();

			if (v.getSensorTypeIO().equals("Input")) {

				inV += v.getNumberOfVehicle();

			} else if (v.getSensorTypeIO().equals("Output")) {

				outV += v.getNumberOfVehicle();

			}

		}
		int NbVehicleInCirculation = (inV + this.numberVehicles) - outV;
		return NbVehicleInCirculation;
	}


	public boolean CheckThresholdNbMaxVehicles(int NbVehicleInCirculation) {

		if (NbVehicleInCirculation >= this.maxNumberVehicles) {
			return true;
		} else {
			return false;
		}

	}

	public boolean CheckThresholdBeta(int cityThresholdPolution, int CityPolution) {

		if (CityPolution >= cityThresholdPolution) {
			return true;
		} else {
			return false;
		}

	}
	
	
	*/
}
