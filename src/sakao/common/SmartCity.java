package sakao.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONException;

public class SmartCity {
	private int id;
	private String name;
	private int heightkm;
	private int widthkm;

	private int maxNumberVehicles;
	private int numberVehicles; // In circulation

	private int maxPolution;
	private double polutionLevel; // In the town
	

	private final static double PolutionLightCommercialVehicle = 1;
	private final static double PolutionCompactCar = 1.5;
	private final static double PolutionHeavyGoodsVehicle = 2.5;
	
	
	private ResultTreshold resultTreshold;

	public  double getPolutionlightcommercialvehicle() {
		return PolutionLightCommercialVehicle;
	}

	public  double getPolutioncompactcar() {
		return PolutionCompactCar;
	}

	public  double getPolutionheavygoodsvehicle() {
		return PolutionHeavyGoodsVehicle;
	}

	private int tramFrequency;

	public SmartCity() {
	}

	public SmartCity(int id, String name, int HeightKM, int WidthKM, int maxNumberVehicles, int numberVehicles,
			int maxPolution, double polutionLevel, int tramFrequency) {
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
		  
			return "{\"id\":\"" + this.getId() + "\"," 
					+ "\"name\":\"" + this.getName() + "\"," 
					+ "\"heightkm\":\"" + this.getHeightkm() + "\","
					+ "\"widthkm\":\"" + this.getWidthkm()+ "\"," 
					
					+ "\"maxNumberVehicles\":\"" + this.getMaxNumberVehicles()+ "\"," 
				    + "\"numberVehicles\":\"" + this.getNumberVehicles()+ "\"," 
				    + "\"maxPolution\":\"" + this.getMaxPolution()+ "\"," 
					+ "\"polutionLevel\":\"" + this.getPolutionLevel()+ "\"," 
				    
					+ "\"tramFrequency\":\"" + this.getTramFrequency()

					+ "\"}";
	  
	  }
	 

/*	@Override
	public String toString() {
		return "SmartCity [id=" + id + ", name=" + name + ", heightkm=" + heightkm + ", widthkm=" + widthkm
				+ ", maxNumberVehicles=" + maxNumberVehicles + ", numberVehicles=" + numberVehicles + ", maxPolution="
				+ maxPolution + ", polutionLevel=" + polutionLevel + ", tramFrequency=" + tramFrequency + "]";
	}*/

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHeightkm() {
		return this.heightkm;
	}

	public void setHeightkm(int height) {
		this.heightkm = height;
	}

	public int getWidthkm() {
		return this.widthkm;
	}

	public void setWidthkm(int width) {
		this.widthkm = width;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberVehicles() {
		return this.numberVehicles;
	}

	public void setNumberVehicles(int numberVehicles) {
		this.numberVehicles = numberVehicles;
	}

	public int getMaxNumberVehicles() {
		return this.maxNumberVehicles;
	}

	public void setMaxNumberVehicles(int maxNumberVehicles) {
		this.maxNumberVehicles = maxNumberVehicles;
	}

	public int getTramFrequency() {
		return this.tramFrequency;
	}

	public void setTramFrequency(int tramFrequency) {
		this.tramFrequency = tramFrequency;
	}

	public int getMaxPolution() {
		return this.maxPolution;
	}

	public void setMaxPolution(int maxPolution) {
		this.maxPolution = maxPolution;
	}

	public double getPolutionLevel() {
		return this.polutionLevel;
	}

	public void setPolutionLevel(double curentPolution) {
		this.polutionLevel = curentPolution;
	}

	public ArrayList<Integer> VehicleInCirculationWait (ArrayList<VehicleSensor> Sensorlist) {

		ArrayList<Integer> NbVehicleInCirculation = new ArrayList<Integer>(8);
		//int inV = 0;
		//int outV = 0;
		
		int inLight =0;
		int outLight =0;
		int inCompact =0;
		int outCompact =0;
		int inHeavy =0;
		int outHeavy =0;

		Iterator<VehicleSensor> it = Sensorlist.iterator();

		while (it.hasNext()) {
			VehicleSensor v = it.next();

			if (v.getSensorType().equals("Input")) {

				inCompact += v.getNbOfCompactCar();
				inHeavy += v.getNbOfHeavyGoodsVehicle();
				inLight += v.getNbOfLightCommercialVehicle();

			} else if (v.getSensorType().equals("Output")) {

				outCompact += v.getNbOfCompactCar();
				outHeavy += v.getNbOfHeavyGoodsVehicle();
				outLight += v.getNbOfLightCommercialVehicle();

			}

		}
		
	
		
		int out = outLight+outCompact+outHeavy;
		int in = inLight+inCompact+inHeavy;
		NbVehicleInCirculation.add(0, 0);
		NbVehicleInCirculation.add(1, outLight);
		NbVehicleInCirculation.add(2, outCompact);
		NbVehicleInCirculation.add(3, outHeavy);
		
		NbVehicleInCirculation.add(4, inLight);
		NbVehicleInCirculation.add(5, inCompact);
		NbVehicleInCirculation.add(6, inHeavy);
		
		NbVehicleInCirculation.add(7,out);
		NbVehicleInCirculation.add(8,in);
		
		
		//NbVehicleInCirculation.get(1);
		
		return NbVehicleInCirculation;
	}
	public int VehicleInCirculation(ArrayList<VehicleSensor> Sensorlist) {

		int inV = 0;
		int outV = 0;

		Iterator<VehicleSensor> it = Sensorlist.iterator();

		while (it.hasNext()) {
			VehicleSensor v = it.next();

			if (v.getSensorType().equals("Input")) {

				inV += v.getNbOfCompactCar();
				inV += v.getNbOfHeavyGoodsVehicle();
				inV += v.getNbOfLightCommercialVehicle();

			} else if (v.getSensorType().equals("Output")) {

				outV += v.getNbOfCompactCar();
				outV += v.getNbOfHeavyGoodsVehicle();
				outV += v.getNbOfLightCommercialVehicle();

			}

		}
		int NbVehicleInCirculation = (inV + this.numberVehicles) - outV;
		return NbVehicleInCirculation;
	}

	public double PoltutionPerVehicleInCirculation(ArrayList<VehicleSensor> Sensorlist) {

		double PinV = 0;
		double PoutV = 0;

		Iterator<VehicleSensor> it = Sensorlist.iterator();

		while (it.hasNext()) {
			VehicleSensor v = it.next();

			if (v.getSensorType().equals("Input")) {

				PinV += (v.getNbOfCompactCar() * PolutionCompactCar);
				PinV += (v.getNbOfHeavyGoodsVehicle() * PolutionHeavyGoodsVehicle);
				PinV += (v.getNbOfLightCommercialVehicle() * PolutionLightCommercialVehicle);

			} else if (v.getSensorType().equals("Output")) {

				PoutV += (v.getNbOfCompactCar() * PolutionCompactCar);
				PoutV += (v.getNbOfHeavyGoodsVehicle() * PolutionHeavyGoodsVehicle);
				PoutV += (v.getNbOfLightCommercialVehicle() * PolutionLightCommercialVehicle);
			}

		}
		double PoltutionPerVehicleInCirculation = (PinV + this.polutionLevel) - PoutV;
		return PoltutionPerVehicleInCirculation;
	}

	public boolean CheckThresholdNbMaxVehicles(int NbVehicleInCirculation) {
		// Si le nb de vehicle circulent est superieur au seuil

		if (NbVehicleInCirculation >= this.maxNumberVehicles) {
			return true;
		} else {
			return false;
		}

	}

	public boolean CheckThresholdMaxPolution(double PoltutionPerVehicleInCirculation) {
		// Si la polution dans la ville est superieur AU seuil

		if (PoltutionPerVehicleInCirculation >= this.maxPolution) {
			return true;
		} else {
			return false;
		}

	}
	
	
	
	
	
//////////////////////////////////////////////////////////////////////////

	public ResultTreshold CheckVehiclesThreshold(SmartCity smartCityObject,
			ArrayList<VehicleSensor> vehicleSensorObject, ArrayList<Bollard> bollardObject)
			throws ClassNotFoundException, IOException, JSONException {


		//		bollardObject = bollardService.GenerateAllBollards();
//		vehicleSensorObject = vehiclesSensorService.GenerateAllVehicleSensors();
//		smartCityObject = smartCityServices.GenerateCity();
		ResultTreshold resultTreshold = new ResultTreshold(false,6,"NO RESULT",0,0,true);
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

		if (InNBVehicle > 0) {

			//// SINON (SI INPUT)

			// Si AncienCirculation <= maxCirculation OU AncienPolution<= maxPolution

			if (NbVehicleInCirculation >= smartCityObject.getMaxNumberVehicles()
					|| CurrentPolution >= smartCityObject.getMaxPolution()) {

				System.out.println("Some vehicles could not enter");
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

						System.out.println("Some vehicles could not enter");
						resultTreshold.setEntry(false);

					} else {

						if (NbVehicleInCirculation >= smartCityObject.getMaxNumberVehicles()) {

							System.out.println("Some vehicles could not enter");
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

						System.out.println("Some vehicles could not enter");
						resultTreshold.setEntry(false);

					} else {

						if (NbVehicleInCirculation >= smartCityObject.getMaxNumberVehicles()) {

							System.out.println("Some vehicles could not enter");
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

						System.out.println("Some vehicles could not enter");
						resultTreshold.setEntry(false);

					} else {

						if (NbVehicleInCirculation >= smartCityObject.getMaxNumberVehicles()) {

							System.out.println("Some vehicles could not enter");
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
				|| NbVehicleInCirculation >= smartCityObject.getMaxPolution()) {

			if (NbVehicleInCirculation >= smartCityObject.getMaxNumberVehicles()) {

				NbVehicleInCirculation = smartCityObject.getMaxNumberVehicles();

				/*
				 * smartCityServices.updateNumberinCirculation(NbVehicleInCirculation);
				 * smartCityServices.updateCurrentPolution(CurrentPolution);
				 * 
				 * smartCityObject = smartCityServices.GenerateCity();
				 */

				resultTreshold.setNbVehicleInCirculation(NbVehicleInCirculation);

				m = "Treshold Number of vehicles !!!";
				resultTreshold.setTresholdResult(m);

				resultTreshold.setCurentPolution(CurrentPolution);

			}

			if (CurrentPolution >= smartCityObject.getMaxPolution()) {

				CurrentPolution = smartCityObject.getMaxPolution();

				/*
				 * smartCityServices.updateNumberinCirculation(NbVehicleInCirculation);
				 * smartCityServices.updateCurrentPolution(CurrentPolution);
				 * 
				 * smartCityObject = smartCityServices.GenerateCity();
				 */

				resultTreshold.setNbVehicleInCirculation(NbVehicleInCirculation);

				m += "   Treshold Polution !!!";

				resultTreshold.setTresholdResult(m);

				resultTreshold.setCurentPolution(CurrentPolution);

			}

			/*
			 * bollardService.Updatetrue(bollardObject); bollardObject =
			 * bollardService.GenerateAllBollards();
			 * 
			 * smartCityServices.updateTramFrequency(10); smartCityObject =
			 * smartCityServices.GenerateCity();
			 */

			System.out.println("Retractable bollards are raised");

			resultTreshold.setBollardStateResult(true);

			System.out.println("Tramfrequency =  10/10");

			resultTreshold.setTramFrequencyResult(10);

		} else {

			/*
			 * smartCityServices.updateNumberinCirculation(NbVehicleInCirculation);
			 * smartCityServices.updateCurrentPolution(CurrentPolution);
			 */

			// smartCityObject = smartCityServices.GenerateCity();

		//	System.out.println(vehicleSensorObject);
		//	System.out.println(smartCityObject);

			/////////////////////////////////////////////////////////////////////////////////////////
			//System.out.println("Seuil MaxVehicles : " + smartCityObject.getMaxNumberVehicles());
			//System.out.println("Seuil MaxVehicles : " + smartCityObject.getMaxPolution());
			
			//System.out.println("NbVehicleInCirculation = " + NbVehicleInCirculation);
			resultTreshold.setNbVehicleInCirculation(NbVehicleInCirculation);

			//System.out.println("CurentPolution = " + CurrentPolution);
			resultTreshold.setCurentPolution(CurrentPolution);

			int Max = smartCityObject.getMaxNumberVehicles();
			int Maxminus20 = ((Max) - ((Max * 20) / 100)); // -20% of max

			double MaxPolution = smartCityObject.getMaxPolution();
			double MaxPolutionmunus20 = ((MaxPolution) - ((MaxPolution * 20) / 100));

			if (smartCityObject.CheckThresholdNbMaxVehicles(NbVehicleInCirculation) == true
					|| smartCityObject.CheckThresholdMaxPolution(CurrentPolution) == true) {

				// bollardService.Updatetrue(bollardObject);
				// bollardObject = bollardService.GenerateAllBollards();

				// smartCityServices.updateTramFrequency(10);
				// smartCityObject = smartCityServices.GenerateCity();

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

					// bollardService.Updatefalse(bollardObject);
					// bollardObject = bollardService.GenerateAllBollards();

					// smartCityServices.updateTramFrequency(6);
					// smartCityObject = smartCityServices.GenerateCity();

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

					if (bollardObject.get(0).getIsBollardState() == true) {

						// smartCityServices.updateTramFrequency(8);
						// smartCityObject = smartCityServices.GenerateCity(); // Faire liste des
						// bollard

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

						// smartCityServices.updateTramFrequency(8);
						// smartCityObject = smartCityServices.GenerateCity(); // Faire liste des
						// bollard

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
	
///////////////////////////////////////////////////////////////////////////////////////////	

}
