package sakao_common;

import java.sql.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VehicleSensor {

	private int idVehicleSensor;
	private String sensorType;

	private int NbOfLightCommercialVehicle;
	private int NbOfCompactCar;
	private int NbOfHeavyGoodsVehicle;

	private String ipAddress;
	private String macAddress;
	private boolean isInstalled;

	private int idZone;
	private int idBollard;

	public VehicleSensor() {

	};

	public VehicleSensor(int idVehicleSensor, String sensorType, int NbOfLightCommercialVehicle, int NbOfCompactCar,
			int NbOfHeavyGoodsVehicle, String ipAddress, String macAddress, boolean isInstalled, int idZone,
			int idBollard) {

		this.idVehicleSensor = idVehicleSensor;
		this.sensorType = sensorType;
		this.NbOfLightCommercialVehicle = NbOfLightCommercialVehicle;
		this.NbOfCompactCar = NbOfCompactCar;
		this.NbOfHeavyGoodsVehicle = NbOfHeavyGoodsVehicle;
		this.ipAddress = ipAddress;
		this.macAddress = macAddress;
		this.isInstalled = isInstalled;
		this.idZone = idZone;
		this.idBollard = idBollard;

	}

	public String toString() {
		return "{\"idVehicleSensor\":\"" + this.idVehicleSensor + "\","

				+ "\"sensorType\":\"" + this.getSensorType() + "\","

				+ "\"NbOfLightCommercialVehicle\":\"" + this.getNbOfLightCommercialVehicle() + "\","
				+ "\"NbOfCompactCar\":\"" + this.getNbOfCompactCar() + "\"," 
				+ "\"NbOfHeavyGoodsVehicle\":\""+ this.getNbOfHeavyGoodsVehicle() + "\","

				+ "\"ipAddress\":\"" + this.getIpAddress() + "\"," + "\"macAddress\":\"" + this.getMacAddress() + "\","

				+ "\"isInstalled\":\"" + this.getisInstalled() + "\","

				+ "\"idZone\":\"" + this.getIdZone() + "\"," + "\"idBollard\":\"" + this.getIdBollard() + "\"}";

	}

	
	
	public int getIdVehicleSensor() {
		return idVehicleSensor;
	}

	
	/*public String toString() {
		return "VehicleSensor [idVehicleSensor=" + idVehicleSensor + ", sensorType=" + sensorType
				+ ", NbOfLightCommercialVehicle=" + NbOfLightCommercialVehicle + ", NbOfCompactCar=" + NbOfCompactCar
				+ ", NbOfHeavyGoodsVehicle=" + NbOfHeavyGoodsVehicle + ", ipAddress=" + ipAddress + ", macAddress="
				+ macAddress + ", isInstalled=" + isInstalled + ", idZone=" + idZone + ", idBollard=" + idBollard + "]";
	}
*/
	public void setIdVehicleSensor(int idVehicleSensor) {
		this.idVehicleSensor = idVehicleSensor;
	}

	public String getSensorType() {
		return sensorType;
	}

	public void setSensorType(String sensorType) {
		this.sensorType = sensorType;
	}

	public int getNbOfLightCommercialVehicle() {
		return NbOfLightCommercialVehicle;
	}

	public void setNbOfLightCommercialVehicle(int nbOfLightCommercialVehicle) {
		NbOfLightCommercialVehicle = nbOfLightCommercialVehicle;
	}

	public int getNbOfCompactCar() {
		return NbOfCompactCar;
	}

	public void setNbOfCompactCar(int nbOfCompactCar) {
		NbOfCompactCar = nbOfCompactCar;
	}

	public int getNbOfHeavyGoodsVehicle() {
		return NbOfHeavyGoodsVehicle;
	}

	public void setNbOfHeavyGoodsVehicle(int nbOfHeavyGoodsVehicle) {
		NbOfHeavyGoodsVehicle = nbOfHeavyGoodsVehicle;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public boolean getisInstalled() {
		return isInstalled;
	}

	public void setInstalled(boolean isInstalled) {
		this.isInstalled = isInstalled;
	}

	public int getIdZone() {
		return idZone;
	}

	public void setIdZone(int idZone) {
		this.idZone = idZone;
	}

	public int getIdBollard() {
		return idBollard;
	}

	public void setIdBollard(int idBollard) {
		this.idBollard = idBollard;
	}

}
