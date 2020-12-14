package sakao.client.test;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import sakao.common.Bollard;
import sakao.common.Request;
import sakao.common.Response;
import sakao.common.ResultTreshold;
import sakao.common.SmartCity;
import sakao.common.VehicleSensor;
import sakao.connection.pool.DataSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class SpecWithJson {

	public static void main(String[] args) throws IOException, ParseException, ClassNotFoundException, JSONException {

		JsonToObject TestVechicleSensor1 = new JsonToObject();

		

		ArrayList<VehicleSensor> VehiclesSensorList = TestVechicleSensor1
				.VehiclesParsingJson("C:\\Users\\K\\Desktop\\TestJson\\InitVehicle.json");
		
		
		SmartCity smartCityObject = TestVechicleSensor1
				.SmartCityParsingJson("C:\\Users\\K\\Desktop\\TestJson\\InitSmartCity.json");

		System.out.println(VehiclesSensorList);
		System.out.println(smartCityObject);

		ResultTreshold resultTreshold = new ResultTreshold();

		System.out.println();

		// SmartCity smartCityObject = new SmartCity(1, "sakao", 10000, 10000, 500, 200,
		// 700, 200, 6);
		Bollard bollard = new Bollard(1, false, 1, true, "0", "0");

		ArrayList<Bollard> bollardObject = new ArrayList<Bollard>();
		bollardObject.add(bollard);
		resultTreshold = smartCityObject.CheckVehiclesThreshold(smartCityObject, VehiclesSensorList, bollardObject);
		
	 VehiclesSensorList = TestVechicleSensor1
				.VehiclesParsingJson("C:\\Users\\K\\Desktop\\TestJson\\TestVehiclesSensor1.json");
	 resultTreshold = smartCityObject.CheckVehiclesThreshold(smartCityObject, VehiclesSensorList, bollardObject);

	}
}