package sakao.client.ihm;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;

import sakao.common.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class Simulation extends JPanel {
	private JTextField textField_TresholdMaxNumberOfVehicles;
	private JTextField textField_NbVehiclesInTown;
	private JTextField textField_PolutionLevel;
	private JTextField textField_TresholdPolution;

	private JTextField textField_nboflightcommercialvehicle;
	private JTextField textField_nbofcompactcar;
	private JTextField textField_nbofheavygoodsvehicle;

	private SmartCity smart;
	private VehicleSensor vehicle;

	private SimulationCalcul simul;
	private ResultTreshold resultTreshold;
	private String message;

	private JsonToSend app;

	public Simulation(JsonToSend appStructure) throws IOException, JSONException {

		setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 713, 410);
		add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Simulation", null, panel, null);
		panel.setLayout(null);

		textField_TresholdMaxNumberOfVehicles = new JTextField();
		textField_TresholdMaxNumberOfVehicles.setBounds(213, 84, 96, 19);
		textField_TresholdMaxNumberOfVehicles.setColumns(10);

		textField_PolutionLevel = new JTextField();
		textField_PolutionLevel.setBounds(213, 117, 96, 19);
		textField_PolutionLevel.setColumns(10);

		textField_NbVehiclesInTown = new JTextField();
		textField_NbVehiclesInTown.setBounds(213, 52, 96, 19);
		textField_NbVehiclesInTown.setColumns(10);

		JLabel lblTresholdPolution = new JLabel("Treshold Polution");
		lblTresholdPolution.setBounds(10, 145, 193, 13);

		JLabel lblNumberOfVehicules = new JLabel("Number of vehicules in town");
		lblNumberOfVehicules.setBounds(10, 56, 193, 13);

		JLabel lblTresholdMaxNumber = new JLabel("Treshold max number of vehicules");
		lblTresholdMaxNumber.setBounds(10, 86, 201, 16);

		JLabel lblPolutionLevel = new JLabel("Polution Level");
		lblPolutionLevel.setBounds(10, 121, 162, 13);

		panel.add(lblTresholdPolution);
		panel.add(textField_NbVehiclesInTown);
		panel.add(lblNumberOfVehicules);
		panel.add(lblTresholdMaxNumber);
		panel.add(lblPolutionLevel);
		panel.add(textField_TresholdMaxNumberOfVehicles);
		panel.add(textField_PolutionLevel);

		JButton btnSendButton_1 = new JButton("Send");

		btnSendButton_1.setForeground(Color.WHITE);
		btnSendButton_1.setBackground(new Color(0, 128, 0));
		btnSendButton_1.setBounds(586, 192, 85, 21);
		panel.add(btnSendButton_1);

		textField_TresholdPolution = new JTextField();
		textField_TresholdPolution.setColumns(10);
		textField_TresholdPolution.setBounds(213, 141, 96, 19);
		panel.add(textField_TresholdPolution);

		JLabel lblTramFrequency = new JLabel("Tram frequency");
		lblTramFrequency.setBounds(10, 171, 193, 13);
		panel.add(lblTramFrequency);

		JButton btnInitButton = new JButton("Initilization");
		btnInitButton.setForeground(Color.WHITE);
		btnInitButton.setBackground(new Color(0, 102, 255));
		btnInitButton.setBounds(213, 203, 96, 21);
		panel.add(btnInitButton);

		JComboBox comboBoxTramFrequency = new JComboBox();
		comboBoxTramFrequency.setModel(new DefaultComboBoxModel(new String[] { "6/10", "8/10", "10/10" }));
		comboBoxTramFrequency.setBounds(213, 168, 96, 18);
		panel.add(comboBoxTramFrequency);

		JLabel lblSmartcityInitiliz = new JLabel("SmartCity initialization");
		lblSmartcityInitiliz.setBounds(110, 11, 180, 29);
		panel.add(lblSmartcityInitiliz);

		JLabel lblVehiclesSensors = new JLabel("Vehicles Sensors");
		lblVehiclesSensors.setBounds(482, 11, 114, 29);
		panel.add(lblVehiclesSensors);

		textField_nboflightcommercialvehicle = new JTextField();
		textField_nboflightcommercialvehicle.setColumns(10);
		textField_nboflightcommercialvehicle.setBounds(575, 85, 96, 19);
		panel.add(textField_nboflightcommercialvehicle);

		textField_nbofcompactcar = new JTextField();
		textField_nbofcompactcar.setColumns(10);
		textField_nbofcompactcar.setBounds(575, 118, 96, 19);
		panel.add(textField_nbofcompactcar);

		textField_nbofheavygoodsvehicle = new JTextField();
		textField_nbofheavygoodsvehicle.setColumns(10);
		textField_nbofheavygoodsvehicle.setBounds(575, 146, 96, 19);
		panel.add(textField_nbofheavygoodsvehicle);

		JComboBox comboBox_SensorTypeIO = new JComboBox();
		comboBox_SensorTypeIO.setModel(new DefaultComboBoxModel(new String[] { "Input", "Output" }));
		comboBox_SensorTypeIO.setBounds(575, 52, 96, 18);
		panel.add(comboBox_SensorTypeIO);

		JLabel lblSensorType = new JLabel("Sensor Type");
		lblSensorType.setBounds(362, 56, 180, 13);
		panel.add(lblSensorType);

		JLabel lblNboflightcommercialvehicle = new JLabel("Number of light commercial vehicles");
		lblNboflightcommercialvehicle.setBounds(362, 88, 217, 13);
		panel.add(lblNboflightcommercialvehicle);

		JLabel lblNumberOfCompact = new JLabel("Number of compact car");
		lblNumberOfCompact.setBounds(362, 121, 180, 13);
		panel.add(lblNumberOfCompact);

		JLabel lblNumber = new JLabel("Number of heavy goods vehicles");
		lblNumber.setBounds(362, 149, 217, 13);
		panel.add(lblNumber);

		JButton btnCurentSituation = new JButton("Curent Situation");

		btnCurentSituation.setForeground(Color.WHITE);
		btnCurentSituation.setBackground(new Color(30, 144, 255));
		btnCurentSituation.setBounds(59, 289, 132, 21);
		panel.add(btnCurentSituation);

		JTextArea textArea = new JTextArea();
		textArea.setBackground(UIManager.getColor("Button.background"));
		textArea.setEditable(false);
		textArea.setBounds(246, 246, 425, 107);
		panel.add(textArea);

		message = "";

		// Request request = new Request("SELECT_ALL", "check");

		btnInitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int TresholdMaxNumberOfVehicles = 0;
				int NbVehiclesInTown = 0;
				int TresholdPolution = 0;
				double PolutionLevel = 0;

				boolean b = false;

				try {// if is number
					NbVehiclesInTown = Integer.parseInt(textField_NbVehiclesInTown.getText());

				} catch (NumberFormatException e1) {

					textField_NbVehiclesInTown.setForeground(Color.RED);
					textField_NbVehiclesInTown.setText("Incorrect NumberFormat");
					b = true;

				}

				try {// if is number
					TresholdMaxNumberOfVehicles = Integer.parseInt(textField_TresholdMaxNumberOfVehicles.getText());
				} catch (NumberFormatException e1) {

					textField_TresholdMaxNumberOfVehicles.setForeground(Color.RED);
					textField_TresholdMaxNumberOfVehicles.setText("Incorrect NumberFormat");
					b = true;

				}
				try {// if is number
					PolutionLevel = Double.parseDouble(textField_PolutionLevel.getText());
				} catch (NumberFormatException e1) {

					textField_PolutionLevel.setForeground(Color.RED);
					textField_PolutionLevel.setText("Incorrect NumberFormat");
					b = true;

				}
				try {// if is number
					TresholdPolution = Integer.parseInt(textField_TresholdPolution.getText());
				} catch (NumberFormatException e1) {

					textField_TresholdPolution.setForeground(Color.RED);
					textField_TresholdPolution.setText("Incorrect NumberFormat");
					b = true;

				}

				String TramFrequency = (String) comboBoxTramFrequency
						.getItemAt(comboBoxTramFrequency.getSelectedIndex());

				int tramfreq = Integer.parseInt(TramFrequency.substring(0, 1));
				if (tramfreq == 1) {
					tramfreq = 10;

				}

				if (b == true) {

					textArea.setText("Incorect Number Format");
				} else {
					if (smart == null) {

						smart = new SmartCity(1, "name", 1000, 1000, TresholdMaxNumberOfVehicles, NbVehiclesInTown,
								TresholdPolution, PolutionLevel, tramfreq);
						// String Newligne=System.getProperty("line.separator");
						message = "Smartcity is initialized with" + "\n";
						message += "Number of vehicle in circulation = " + smart.getNumberVehicles() + "\n";
						message += "CurentPolution = " + smart.getPolutionLevel() + " \n";
						message += "Max vehicles number treshold  = " + smart.getMaxNumberVehicles() + "\n";
						message += "Max Polution treshold  = " + smart.getMaxPolution() + "\n";
						message += "Tram Frequency = " + smart.getTramFrequency() + "/10" + "\n";

						textArea.setText(message);
						// textFieldResultTreshold.setText(message);

					} else {

						smart = null;
						smart = new SmartCity(1, "name", 1000, 1000, TresholdMaxNumberOfVehicles, NbVehiclesInTown,
								TresholdPolution, PolutionLevel, tramfreq);
						message = "Smartcity is initialized with" + "\n";
						message += "Number of vehicle in circulation = " + smart.getNumberVehicles() + "\n";
						message += "CurentPolution = " + smart.getPolutionLevel() + " \n";
						message += "Max vehicles number treshold  = " + smart.getMaxNumberVehicles() + "\n";
						message += "Max Polution treshold  = " + smart.getMaxPolution() + "\n";
						message += "Tram Frequency = " + smart.getTramFrequency() + "/10" + "\n";

						textArea.setText(message);
					}

				}

			}
		});

		resultTreshold = new ResultTreshold();

		btnSendButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int nboflightcommercialvehicle = 0;
				int nbofcompactcar = 0;
				int nbofheavygoodsvehicle = 0;

				boolean b = false;

				try {// if is number
					nboflightcommercialvehicle = Integer.parseInt(textField_nboflightcommercialvehicle.getText());

				} catch (NumberFormatException e1) {

					textField_nboflightcommercialvehicle.setForeground(Color.RED);
					textField_nboflightcommercialvehicle.setText("Incorrect NumberFormat");
					b = true;

				}

				try {// if is number
					nbofcompactcar = Integer.parseInt(textField_nbofcompactcar.getText());

				} catch (NumberFormatException e2) {

					textField_nbofcompactcar.setForeground(Color.RED);
					textField_nbofcompactcar.setText("Incorrect NumberFormat");
					b = true;

				}

				try {// if is number
					nbofheavygoodsvehicle = Integer.parseInt(textField_nbofheavygoodsvehicle.getText());

				} catch (NumberFormatException e3) {

					textField_nbofheavygoodsvehicle.setForeground(Color.RED);
					textField_nbofheavygoodsvehicle.setText("Incorrect NumberFormat");
					b = true;

				}

				String sensorType = (String) comboBox_SensorTypeIO.getItemAt(comboBox_SensorTypeIO.getSelectedIndex());

				if (b == true) {

					textArea.setText("Incorect Number Format");
				} else {

					vehicle = new VehicleSensor(1, sensorType, nboflightcommercialvehicle, nbofcompactcar,
							nbofheavygoodsvehicle, "198.172.122.12", "AA:AA:AA:AA:AA", true, 1, 1);
					if (smart == null) {
						textArea.setText("No smart city");
						// textFieldResultTreshold.setText(smart.toString());
					} else {

						simul = new SimulationCalcul(smart, vehicle, resultTreshold);
						resultTreshold = simul.CheckVehiclesThreshold(resultTreshold);
						// resultTreshold.getisBollardStateResult();
						if (resultTreshold.TresholdResult == "Error NbVehicleInCirculation"
								|| resultTreshold.TresholdResult == "Error CurentPolution") {

							message = "Output vehicles may not exceed  NbVehicleInCirculation";

							if (resultTreshold.TresholdResult == "Error NbVehicleInCirculation") {

							}
							if (resultTreshold.TresholdResult == "Error CurentPolution") {

							}

						} else {

							int maxnumber = smart.getMaxNumberVehicles();
							int maxPolution = smart.getMaxPolution();
							smart = null;
							smart = new SmartCity(1, "sakao", 4000, 4000, maxnumber,
									resultTreshold.getNbVehicleInCirculation(), maxPolution,
									resultTreshold.getCurentPolution(), resultTreshold.getTramFrequencyResult());

							message = "NbVehicleInCirculation = " + resultTreshold.getNbVehicleInCirculation() + "\n";
							message += "CurentPolution  = " + resultTreshold.getCurentPolution() + "\n";
							message += resultTreshold.getTresholdResult() + "\n";
							if (resultTreshold.getisBollardStateResult() == true) {

								message += " Retractable bollards are Raised";

							} else {
								message += " Retractable bollards are lower";

							}

							message += "Tramfrequency  = " + resultTreshold.getTramFrequencyResult() + "\n";
						}
						textArea.setText(message);

					}

				}

			}
		});

		app = new JsonToSend();
		btnCurentSituation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Request request = new Request("SELECT_ALL", "check");
				try {

					ArrayList<String> al = app.sendMessageToServer(request);

					String s = al.get(0) + "\n";

					System.out.println(s);

					ResultTreshold req1 = new ObjectMapper().readValue(s, ResultTreshold.class);

					message = "NbVehicleInCirculation =" + req1.getNbVehicleInCirculation()+ "\n";
					message += "CurentPolution =" + req1.getCurentPolution()+ "\n";
					message += "TresholdResult =" + req1.getTresholdResult()+ "\n";
					message += "bollardStateResult =" + req1.getisBollardStateResult()+ "\n";
					message += "tramFrequencyResult =" + req1.getTramFrequencyResult()+ "\n";
					textArea.setText(message);

					

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// app.RequestToSend("file-for-test/InitVehiculesSensor.json");

			}
		});
	}
}
