package sakao_common;




public class ResultTreshold {

	private boolean bollardStateResult;
	private int tramFrequencyResult;
	public String TresholdResult; //Object mapper don't recognize in private (IHM)
	public int NbVehicleInCirculation; //Object mapper don't recognize in private (IHM)
	public double CurentPolution; //Object mapper don't recognize in private (IHM)

	public ResultTreshold() {

	}

	public ResultTreshold(boolean bollardStateResult, int tramFrequencyResult, String TresholdResult, int NbVehicleInCirculation, int CurentPolution) {
		this.bollardStateResult = bollardStateResult;
		this.tramFrequencyResult = tramFrequencyResult;
		this.TresholdResult = TresholdResult;
		this.NbVehicleInCirculation = NbVehicleInCirculation;
		this.CurentPolution =CurentPolution;
	}

	public boolean getisBollardStateResult() {
		return bollardStateResult;
	}

	public void setBollardStateResult(boolean bollardStateResult) {
		this.bollardStateResult = bollardStateResult;
	}

	public int getTramFrequencyResult() {
		return tramFrequencyResult;
	}

	public void setTramFrequencyResult(int tramFrequencyResult) {
		this.tramFrequencyResult = tramFrequencyResult;
	}

	public String getTresholdResult() {
		return TresholdResult;
	}

	public void setTresholdResult(String tresholdResult) {
		TresholdResult = tresholdResult;
	}
	
	public int getNbVehicleInCirculation() {
		return NbVehicleInCirculation;
	}

	public void setNbVehicleInCirculation(int nbVehicleInCirculation) {
		NbVehicleInCirculation = nbVehicleInCirculation;
	}

	public double getCurentPolution() {
		return CurentPolution;
	}

	public void setCurentPolution(double curentPolution) {
		CurentPolution = curentPolution;
	}

	public String toString() {
		return "{\"bollardStateResult\":" + this.getisBollardStateResult() + "," 
				+ "\"tramFrequencyResult\":" + this.getTramFrequencyResult() + "," 
				+ "\"NbVehicleInCirculation\":" + this.getNbVehicleInCirculation()+ "," 
				+ "\"CurentPolution\":" + this.getCurentPolution()+ "," 
				+ "\"TresholdResult\":\"" + this.getTresholdResult()
				+ "\"}";
	}
	
	

}
