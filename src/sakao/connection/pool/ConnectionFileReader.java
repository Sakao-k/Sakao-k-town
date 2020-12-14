package sakao.connection.pool;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConnectionFileReader {

	private Properties p;
	private int MAX_CONNECTIONS;


	public ConnectionFileReader(int i) {
		p = new Properties();
		MAX_CONNECTIONS = i;
		
	}

	///// open the file and read it
	public void Read() {
		InputStream fis;
		try {
			fis = getClass().getClassLoader().getResourceAsStream("sakao/connection/pool/ConnectionFile.xml");
			p.loadFromXML(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	///// return the keys of the file
	///// 
	///// this function allows to browse the file
	public String getProperty(String key) {
		return p.getProperty(key);

	}

	public Properties getP() {
		return p;
	}

	public void setP(Properties p) {
		this.p = p;
	}

	public int getMaxConnections() {
		return MAX_CONNECTIONS;
	}


	public void setMAX_CONNECTIONS(int mAX_CONNECTIONS) {
		MAX_CONNECTIONS = mAX_CONNECTIONS;
	}


}
