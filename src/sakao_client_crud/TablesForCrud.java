package sakao_client_crud;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.codehaus.jackson.JsonParser;

public class TablesForCrud {

	public String readFileToInsertBollard() throws IOException {
		InputStream fis = new FileInputStream("resources\\file-for-crud\\Bollard.json");
		InputStreamReader ipsr = new InputStreamReader(fis);
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
		InputStream fis = new FileInputStream("resources\\file-for-crud\\BollardUpdate.json");
		InputStreamReader ipsr = new InputStreamReader(fis);
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
		InputStream fis = new FileInputStream("resources\\file-for-crud\\BollardDelete.json");
		InputStreamReader ipsr = new InputStreamReader(fis);
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
