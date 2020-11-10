package sakao.common;

public class Zone {
	private int idZone;
	private String locations;
	private int idCity;

	public Zone() {
	}

	public Zone(int idZone, String locations, int idCity) {
		this.idZone = idZone;
		this.locations = locations;
		this.idCity = idCity;

	}


	public int getIdZone() {
		return idZone;
	}

	public void setIdZone(int idZone) {
		this.idZone = idZone;
	}

	public String getLocations() {
		return locations;
	}

	public void setLocations(String locations) {
		this.locations = locations;
	}

	public int getIdCity() {
		return idCity;
	}

	public void setIdCity(int idCity) {
		this.idCity = idCity;
	}
	
	

	@Override
	public String toString() {
		return "{\"idZone\":\"" + this.idZone + "\"," 
				+ "\"locations\":\"" + this.locations + "\"," 
				+ "\"idCity\":\"" + this.idCity + "\"}";
	}

}
