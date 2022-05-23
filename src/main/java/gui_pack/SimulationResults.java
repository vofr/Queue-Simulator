package gui_pack;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class SimulationResults extends JPanel {
	
	JTextArea simulation;
	
	public SimulationResults() {
		JFrame f1= new JFrame("SIMULATIE");  
		simulation = new JTextArea();
		simulation.setBounds(30,10, 1000, 10000);
        JScrollPane scrollPane = new JScrollPane(simulation);
        scrollPane.setBounds(0,0,400,400);
        simulation.setPreferredSize(new Dimension(50000,99000));
        simulation.setEditable(false);
	    f1.add(scrollPane);
        f1.setSize(500,500);  
	    f1.setLayout(null);  
	    f1.setVisible(true); 
		}
	
	public void Display(String a) {
		 simulation.setText(a);
	}
}
