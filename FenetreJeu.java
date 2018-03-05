// Chargement des bibliothèques Swing et AWT
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class FenetreJeu extends JFrame implements ActionListener{
	
	// Les Widgets à déclarer en dehors du constructeur
	JButton bJouer;
	JButton bQuitter;
	JButton bOptions;
	JPanel panelMenu;
	PanelPrincipalJeu panelJeu;
	PanelOptions panelOptions;
	
	public FenetreJeu(){

		int L = (int)getToolkit().getScreenSize().getWidth();
		int H = ((int)getToolkit().getScreenSize().getHeight() - 45);
		
		int tailleButtonL=(int)(L/12);
		int tailleButtonH=(int)(H/12);
		
		

		this.setTitle("Jeu Tower defense  ");
		this.setLayout(null);
		this.setSize(L, H);
		// Pour empêcher le redimensionnement de la fenêtre
		this.setResizable(false);
		
		
	
	
		/**
    	 * Mon panneau Jeu
		 */
		panelMenu = new JPanel();
		add(panelMenu);
		panelMenu.setLayout(null);
		panelMenu.setBackground(Color.white);
		
	
	
	
		bJouer = new JButton("Jouer");
		bJouer.setBounds((int)(L/2-tailleButtonL/2),(int)(H/4+tailleButtonH/2),tailleButtonL,tailleButtonH);
		bJouer.setBackground(Color.white);
		bJouer.setForeground(Color.black);
		/* branchement de l'écouteur*/
		bJouer.addActionListener(this);
		
		bQuitter = new JButton("Quitter");
		bQuitter.setBounds((int)(L/2-tailleButtonL/2),(int)(H/4+2*tailleButtonH),tailleButtonL,tailleButtonH);
		bQuitter.setBackground(Color.white);
		bQuitter.setForeground(Color.black);
		/* branchement de l'écouteur*/
		bQuitter.addActionListener(this);
		
		
		bOptions = new JButton("Options");
		bOptions.setBounds((int)(L/2-tailleButtonL/2),(int)(H/4+7*tailleButtonH/2),tailleButtonL,tailleButtonH);
		bOptions.setBackground(Color.white);
		bOptions.setForeground(Color.black);
		/* branchement de l'écouteur*/
		bOptions.addActionListener(this);
		
		panelMenu.add(bJouer);
		panelMenu.add(bQuitter);
		panelMenu.add(bOptions);
		
		panelJeu = new PanelPrincipalJeu(L,H);
		panelOptions = new PanelOptions(L,H);
	
		
		setContentPane(panelMenu);
		
		
		// Pour rendre la fenêtre visible
		this.setVisible(true);
		// Pour permettre la fermeture de la fenêtre lors de l'appui sur la croix rouge
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
	
	
	
	
	
	public void actionPerformed(ActionEvent e){
		
		if(e.getSource()== bJouer){
			this.setContentPane(panelJeu);
		}
		
		if(e.getSource()== bQuitter){
			//demander au prof
		}
		if(e.getSource()==bOptions){
			
			this.setContentPane(panelOptions);
		}
		
	}
	
	public static void main (String [] args){
		FenetreJeu maFenetre = new FenetreJeu();
	}
	
}
