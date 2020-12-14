package sakao.client.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import sakao.common.Request;
import sakao.common.SmartCity;
import sakao.common.VehicleSensor;

public class JsonToObject {
	private ObjectMapper mapper;
	private Request request = new Request();
	private JSONParser parser = new JSONParser();

	public JsonToObject() {

	}

	public ArrayList<String> JsonToObject(JSONObject jsonObject) throws IOException {
		mapper = new ObjectMapper();
		String injsonString = mapper.writeValueAsString(jsonObject);
		;
		// System.out.println(injsonString);
		request = mapper.readValue(injsonString, Request.class);
		System.out.println(request.getList());
		System.out.println();
		return request.getList();
	}

	public ArrayList<VehicleSensor> JsonToVehicleObject(ArrayList<String> list) {
		ArrayList<VehicleSensor> retour = new ArrayList<VehicleSensor>();
		try {

			int i = 2;

			while (i < list.size()) {

				retour.add(new VehicleSensor(Integer.parseInt((list.get(i))), list.get(i + 2),
						Integer.parseInt(list.get(i + 4)), Integer.parseInt(list.get(i + 8)),
						Integer.parseInt(list.get(i + 6)), "0", "0", true, 1, 1));

				i += 12;

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return retour;
	}

	public SmartCity JsonToSmartCityObject(ArrayList<String> list) {
		SmartCity retour = new SmartCity();

		int i = 2;

		while (i < list.size()) {

			/*
			 * pstm.setInt(1, Integer.parseInt((list.get(i)))); // maxpolution
			 * pstm.setInt(2, Integer.parseInt(list.get(i + 2))); // polutionlevel
			 * pstm.setInt(3, Integer.parseInt(list.get(i + 4)));// maxnumbervehicle
			 * pstm.setInt(4, Integer.parseInt(list.get(i + 6)));// tram pstm.setInt(5,
			 * Integer.parseInt(list.get(i + 8)));// number
			 */

			retour = new SmartCity(1, "sakao", 10000, 10000, Integer.parseInt(list.get(i + 4)),
					Integer.parseInt(list.get(i + 8)), Integer.parseInt(list.get(i)),
					Integer.parseInt(list.get(i + 2)), Integer.parseInt(list.get(i + 6)));
			// retour = new SmartCity(1, "sakao", 10000, 10000, 500, 200, 700, 200, 6);

			i += 12;
		}

		//System.out.println("////////////////////////ok//////");
		//System.out.println(retour);

		return retour;
	}

	public SmartCity SmartCityParsingJson(String path) throws FileNotFoundException, IOException, ParseException {

		Object obj = parser.parse(new FileReader(path));
		JSONObject jsonObject = (JSONObject) obj;

		ArrayList<String> resultlist = this.JsonToObject(jsonObject);

		SmartCity SmartCityObject = this.JsonToSmartCityObject(resultlist);
		// SmartCity SmartCityObject = new SmartCity(1, "sakao", 10000, 10000, 500, 200,
		// 700, 200, 6);
	//	System.out.println("/////////////////////////////////////");
		// System.out.println(SmartCityObject);

		return SmartCityObject;

	}

	public ArrayList<VehicleSensor> VehiclesParsingJson(String path)
			throws FileNotFoundException, IOException, ParseException {

		Object obj = parser.parse(new FileReader(path));
		JSONObject jsonObject = (JSONObject) obj;
		ArrayList<String> resultlist = this.JsonToObject(jsonObject);
		ArrayList<VehicleSensor> VehiclesSensorList = this.JsonToVehicleObject(resultlist);

		return VehiclesSensorList;

	}

}
