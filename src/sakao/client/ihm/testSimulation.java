package sakao.client.ihm;

import java.io.IOException;

import org.json.JSONException;

import sakao.common.ResultTreshold;
import sakao.common.SmartCity;
import sakao.common.VehicleSensor;

public class testSimulation {
	public static void main(String[] args) {
		
		SmartCity smart = new SmartCity(1, "name", 1000, 1000, 500, 200, 700, 200, 6);
		
		VehicleSensor vehicle = new VehicleSensor(1, "Input", 300, 0, 0, "100.100.100.100", "AA:AA:AA", true, 1, 1);
		
		ResultTreshold resultTreshold = new ResultTreshold();
		
		SimulationCalcul simul = new SimulationCalcul(smart, vehicle, resultTreshold);
		
		
		resultTreshold = simul.CheckVehiclesThreshold(resultTreshold);
		smart  = new SmartCity(1, "name", 1000, 1000, 500, resultTreshold.getNbVehicleInCirculation(), 700, resultTreshold.getCurentPolution(), resultTreshold.getTramFrequencyResult());
	    vehicle = new VehicleSensor(2, "Output", 100, 0, 0, "100.100.100.100", "AA:AA:AA", true, 1, 1);
		System.out.println(resultTreshold);
		
		SimulationCalcul simul2 = new SimulationCalcul(smart, vehicle, resultTreshold);
		
		ResultTreshold resultTreshold2 = new ResultTreshold();
		resultTreshold2 = simul2.CheckVehiclesThreshold(resultTreshold);
		
		System.out.println(resultTreshold2);
		
		
		
	}

}
