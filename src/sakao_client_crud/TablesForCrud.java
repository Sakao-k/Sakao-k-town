package sakao_client_crud;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.codehaus.jackson.JsonParser;

public class TablesForCrud {

	
	public String readFileToInsertBollard() throws IOException {
		
		InputStreamReader ipsr = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("file-for-crud/Bollard.json"));
		BufferedReader br = new BufferedReader(ipsr);
		String outjsonString = "";
		String chaine = "";

		while ((outjsonString = br.readLine()) != null) {
			chaine = chaine + outjsonString;
		}
		//System.out.println("Request sent : " + chaine);
		return chaine;
	}
	
	
	
	public String readFileToUpdateBollard() throws IOException {
		
		InputStreamReader ipsr = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("file-for-crud/BollardUpdate.json"));
		BufferedReader br = new BufferedReader(ipsr);
		String outjsonString = "";
		String chaine = "";

		while ((outjsonString = br.readLine()) != null) {
			chaine = chaine + outjsonString;
		}
		//System.out.println("Request sent : " + chaine);
		return chaine;
	}
	
	

	
	
	public String readFileToDeleteBollard() throws IOException {
	
		InputStreamReader ipsr = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("file-for-crud/BollardDelete.json"));
		BufferedReader br = new BufferedReader(ipsr);
		String outjsonString = "";
		String chaine = "";

		while ((outjsonString = br.readLine()) != null) {
			chaine = chaine + outjsonString;
		}
		//System.out.println("Request sent : " + chaine);
		return chaine;
	}
	
	
}