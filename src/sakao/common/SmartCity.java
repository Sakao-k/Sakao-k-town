package sakao.common;

import java.util.ArrayList;
import java.util.Iterator;

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
	
	
	
	
	/*public String Check(ArrayList<VehicleSensor> Sensorlist) {
		
		String result = "No Result";
		int NbVehicleInCirculation = this.VehicleInCirculation(Sensorlist); // in + incity - out)
		double CurentPolution = this.PoltutionPerVehicleInCirculation(Sensorlist);
		int Max = this.getMaxNumberVehicles();
		int Maxminus20 = ((Max) - ((Max * 20) / 100)); // -20% of max

		double MaxPolution = this.getMaxPolution();
		double MaxPolutionmunus20 = ((MaxPolution) - ((MaxPolution * 20) / 100));
		
		if (this.CheckThresholdNbMaxVehicles(NbVehicleInCirculation) == true
				|| this.CheckThresholdMaxPolution(CurentPolution) == true) {

			
			
			if (this.CheckThresholdNbMaxVehicles(NbVehicleInCirculation) == true) {
				result += "Treshold Number of vehicles !!!" + "\n";
			}

			if (this.CheckThresholdMaxPolution(CurentPolution) == true) {
				result+= "Treshold Polution !!!" + "\n";

			}

			result+= "Retractable bollards are raised" + "\n";
			result+="Tramfrequency =  10/10" + "\n";

		}
		else {

			if (NbVehicleInCirculation < Maxminus20 && CurentPolution < MaxPolutionmunus20) {

				

  result+="Lower Number of vehicles & Lower Polution" + "\n";

				result+="Retractable bollards are lowered" + "\n";
				result+="Tramfrequency =  6/10" + "\n";
			} else {

				/*if (bollardObject.get(1).getIsBollardState() == true) {

					smartCityServices.updateTramFrequency(8);
					smartCityObject = smartCityServices.GenerateCity(); // Faire liste des bollard

					if (NbVehicleInCirculation > Maxminus20 - 1) {

						System.out.println("Number of vehicule is decreasing in town");
					}

					if (CurentPolution > MaxPolutionmunus20 - 1) {

						System.out.println("Polution is decreasing in town");
					}

					System.out.println("Retractable bollards are raised");
					System.out.println("Tramfrequency =  8/10");
				} else {

					smartCityServices.updateTramFrequency(8);
					smartCityObject = smartCityServices.GenerateCity(); // Faire liste des bollard

					if (NbVehicleInCirculation > Maxminus20 - 1) {

						System.out.println("Number of vehicule is increasing in town");
					}

					if (CurentPolution > MaxPolutionmunus20 - 1) {

						System.out.println("Polution is increasing in town");
					}

					System.out.println("Retractable bollards are lowered");
					System.out.println("Tramfrequency =  8/10");

				}

			}
		}
		
		
		return result;
		
		
	}*/

}
