// Chargement des bibliothèques Swing et AWT
import javax.swing.*;
import java.awt.*;


public class FenetrePrincipaleJeu extends JPanel {
	
	
	
	public FenetrePrincipaleJeu() {
		
		
		this.setLayout(null);
		this.setSize(600,600);
		this.setLocation(400,100);
		this.setBackground(Color.white);
		
		
		
		/**
    	 * Mon panneau Avec la tour
		 */
		/*JPanel panelDeJeu = new JPanel();
		add(panelDeJeu);
		panelDeJeu.setBounds(0,0,600,400);
		panelDeJeu.setLayout(null);
		panelDeJeu.setBackground(Color.white);
		
		
		/**
    	 * Mon panneau sol
		 */
		JPanel panelSol = new JPanel();
		add(panelSol);
		panelSol.setBounds(0,400,600,600);
		panelSol.setLayout(null);
		panelSol.setBackground(Color.green);
		
		/**
    	 * Mon panneau Tour
		 */
		JPanel panelTour = new JPanel();
		add(panelTour);
		panelTour.setBounds(0,0,600,600);
		panelTour.setLayout(null);
		panelTour.setBackground(Color.yellow);
		
		
		// Pour rendre la fenêtre visible
		this.setVisible(true);
		
		
	}
	
	

}
