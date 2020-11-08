package sakao_client_ihm;


import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;

import sakao_common.*;

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

public class Simulation extends JPanel {
	private JTextField textField_TresholdMaxNumberOfVehicles;
	private JTextField textField_NbVehiclesInTown;
	private JTextField textField_PolutionLevel;
	private JTextField textField_TresholdPolution;
	
	
	private JTextField textField_nboflightcommercialvehicle;
	private JTextField textField_nbofcompactcar;
	private JTextField textField_nbofheavygoodsvehicle;

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
		
		JButton btnLoadButton_1 = new JButton("Load\r\n");
		btnLoadButton_1.setForeground(Color.WHITE);
		btnLoadButton_1.setBackground(new Color(0, 128, 0));
		btnLoadButton_1.setBounds(586, 192, 85, 21);
		panel.add(btnLoadButton_1);
		
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
		btnInitButton.setBounds(213, 214, 96, 21);
		panel.add(btnInitButton);
		
		JComboBox comboBoxTramFrequency = new JComboBox();
		comboBoxTramFrequency.setModel(new DefaultComboBoxModel(new String[] {"6/10", "8/10", "10/10"}));
		comboBoxTramFrequency.setBounds(213, 168, 96, 18);
		panel.add(comboBoxTramFrequency);
		
		JTextPane Result = new JTextPane();
		Result.setBackground(Color.WHITE);
		Result.setEditable(false);
		Result.setBounds(242, 289, 401, 62);
		panel.add(Result);
		
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
		comboBox_SensorTypeIO.setModel(new DefaultComboBoxModel(new String[] {"Input", "Output"}));
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

		
		btnInitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String message = "Mesage";
				
				
				
				
				
				Result.setText(message);
				
				
				
				
				
				

			}
		});

		// Action to estimate pollution threshold
	/*	btnReleod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalculS calculS = new CalculS();

				Request request = new Request("SELECT_ALL", "empreinte");
				ArrayList<String> al;
				try {
					al = appStructure.sendMessageToServer(request);

					if ((textField.getText().equals("") || Integer.parseInt(textField.getText()) <= 0)
							|| (nbUsesTram90.getText().equals("") || Integer.parseInt(nbUsesTram90.getText()) <= 0)
							|| (textField_3.getText().equals("") || Integer.parseInt(textField_3.getText()) <= 0)
							|| (spinner.toString().isEmpty() || ((Integer) spinner.getValue() <= 0))) {
						JOptionPane.showMessageDialog(null,
								"The inputs are incorrects, all the inputs must be greater then 0");
					} else {
						String s = al.get(0) + "\n";
						SmartCity req1 = new ObjectMapper().readValue(s, SmartCity.class);
						double calcul1 = calculS.estimatedThreshold((Integer) spinner.getValue(), req1.getHeightkm(),
								Integer.parseInt(textField_3.getText()), Integer.parseInt(nbUsesTram90.getText()),
								Integer.parseInt(textField.getText()));
						double sumFields = Integer.parseInt(textField_3.getText())
								+ Integer.parseInt(nbUsesTram90.getText()) + Integer.parseInt(textField.getText())
								+ (Integer) spinner.getValue();
						if (sumFields > NB_POPULATION) {
							JOptionPane.showMessageDialog(null,
									"The sums of the users of transport must be less than the number of population");
						} else {

							String calcul = String.valueOf(calcul1);
							DecimalFormat numberFormat = new DecimalFormat("#.00");
							String limitCalcul = numberFormat.format(calcul1);

							if (limitCalcul.contains(",")) {
								limitCalcul = limitCalcul.replace(",", ".");
							}
							textField_1.setText(limitCalcul);

							if (!textField_1.getText().equals("") && !textField_8.getText().equals("")) {
								btnConclusion.setVisible(true);

							}
						}
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});*/

		// Action to display conclusion
	/*	btnConclusion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					Double param1 = Double.parseDouble(textField_1.getText());
					Double param2 = Double.parseDouble(textField_8.getText());

					if (param1 < param2) {
						JOptionPane.showMessageDialog(null,
								"the proposed travel policy is better than the current travel policy");
					} else {
						JOptionPane.showMessageDialog(null,
								"the current travel policy is better than the proposed travel policy");

					}

				} catch (Exception expt) {
					expt.printStackTrace();

				}
			}
		});*/

	}
}
