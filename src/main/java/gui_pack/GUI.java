package gui_pack;
import business_logic.SimulationManager;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class GUI extends JPanel{

		public GUI()
		{  
			
	    JFrame f= new JFrame("QUEUE MANAGER");  
	    final JTextField nrPers;
		final JTextField nrCozi;
		final JTextField durataSim;  
		final JTextField asteptareMini;
		final JTextField servireMini;  
		final JTextField asteptareMaxi; 
		final JTextField servireMaxi;
	    
	    JLabel l1 = new JLabel("NUMAR PERSOANE ");
	    l1.setBounds(25,5, 130,40); 
	    f.add(l1);
	    
	    JLabel l2 = new JLabel("NUMAR COZI ");
	    l2.setBounds(25,60, 130,40); 
	    f.add(l2);
	    
	    JLabel l3 = new JLabel("DURATA SIMULARE");
	    l3.setBounds(25,125, 150,40); 
	    f.add(l3);
	    
	    JLabel l4 = new JLabel("TIMP SOSIRE");
	    l4.setBounds(200,5, 150,40); 
	    f.add(l4);
	    
	    JLabel l5 = new JLabel("DURATA SERVIRE");
	    l5.setBounds(200,60, 150,40); 
	    f.add(l5);
	    
	    JButton start = new JButton("START");
	    start.setBounds(200, 145, 140, 50);

	    nrPers=new JTextField();  
	    nrPers.setBounds(25,40, 150,30);  
	    nrCozi=new JTextField();  
	    nrCozi.setBounds(25,95, 150,30);  
	    durataSim=new JTextField();  
	    durataSim.setBounds(25,160, 150,30); 
	    servireMini=new JTextField();  
	    servireMaxi=new JTextField();
	    servireMaxi.setBounds(200,95, 60,30);   
	    servireMini.setBounds(270,95, 60,30);  
	    asteptareMaxi=new JTextField();  
	    asteptareMaxi.setBounds(200,40, 60,30); 
	    asteptareMini=new JTextField();  
	    asteptareMini.setBounds(270,40, 60,30); 
	    f.add(nrPers); f.add(nrCozi); f.add(durataSim);  f.add(asteptareMini); f.add(servireMini); f.add(asteptareMaxi); f.add(servireMaxi);
	    f.add(start); 
	    f.setSize(400,300);  
	    f.setLayout(null);  
	    f.setVisible(true); 
	    
		start.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	String text=nrPers.getText();
	        	if(invalidInput(text)==false)
	        		{System.out.println("invalid input for number of persons!");
	        	return;}
	        	int nrP=parseInt(text);
	        	
	        	text=nrCozi.getText();
	        	if(invalidInput(text)==false)
        		{System.out.println("invalid input for number of queues!");
        	return;}
	        	int nrC=parseInt(text);
	        	
	        	text=durataSim.getText();
	        	if(invalidInput(text)==false)
        		{System.out.println("invalid input for simulation time!");
        	return;}
	        	int timeS=parseInt(text);
	        	
	        	text=asteptareMini.getText();
	        	if(invalidInput(text)==false)
        		{System.out.println("invalid input for maximum waiting time!");
        	return;}
	        	int astMin=parseInt(text);

	        	text=asteptareMaxi.getText();
	        	if(invalidInput(text)==false)
        		{System.out.println("invalid input for minimum waiting time!");
        	 return;}
	        	int astMax=parseInt(text);
	        	
	        	text=servireMini.getText();
	        	if(invalidInput(text)==false)
        		{System.out.println("invalid input for maximum serving time!");
	        	return;}
	        	int serMin=parseInt(text);

	        	text=servireMaxi.getText();
	        	if(invalidInput(text)==false)
        		{System.out.println("invalid input for minimum serving time!");
	        	return;}
	        	int serMax=parseInt(text);
	        	
	        	SimulationManager sim = new SimulationManager(timeS,serMin,serMax,nrC,nrP,astMax,astMin);
                Thread thread = new Thread(sim);
                thread.start();
	        }
	    });	
		
		}
		
		public boolean invalidInput(String text) {
			for(int i=0;i<text.length();i++)
				if(text.charAt(i)<48 || text.charAt(i)>57)
					return false;
			return true;
		}
		public int parseInt(String str) {
			int i;
			int x=0,y=0;
			for(i=0;i<str.length();i++) {
				x=x+str.charAt(i)-48;
			    x=x*10;
			}
			x/=10;
			return x;
		}
}
