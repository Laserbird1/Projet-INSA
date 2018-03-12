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
		// Pour empêcher le redimensionnement de la fenêtre sinon c'est vraiment relou
		this.setResizable(false);
		
		
	
	
		/**
    	 * Mon panneau Jeu
		 */
		panelMenu = new JPanel();
		add(panelMenu);
		panelMenu.setLayout(null);
		panelMenu.setBackground(Color.white);//panel de menu pour choisir de jouer quitter ou options
		
	
	
        //bouton jouer
		bJouer = new JButton("Jouer");
		bJouer.setBounds((int)(L/2-tailleButtonL/2),(int)(H/4+tailleButtonH/2),tailleButtonL,tailleButtonH);
		bJouer.setBackground(Color.white);
		bJouer.setForeground(Color.black);
		bJouer.addActionListener(this);
		
        //bouton quitter
		bQuitter = new JButton("Quitter");
		bQuitter.setBounds((int)(L/2-tailleButtonL/2),(int)(H/4+2*tailleButtonH),tailleButtonL,tailleButtonH);
		bQuitter.setBackground(Color.white);
		bQuitter.setForeground(Color.black);
		bQuitter.addActionListener(this);
		
		//bouton option
		bOptions = new JButton("Options");
		bOptions.setBounds((int)(L/2-tailleButtonL/2),(int)(H/4+7*tailleButtonH/2),tailleButtonL,tailleButtonH);
		bOptions.setBackground(Color.white);
		bOptions.setForeground(Color.black);
		bOptions.addActionListener(this);
		
		panelMenu.add(bJouer);
		panelMenu.add(bQuitter);
		panelMenu.add(bOptions);//ajout des bouton au panel de menu
		
		panelJeu = new PanelPrincipalJeu(L,H,this);
		panelOptions = new PanelOptions(L,H,this);//ajout de tous les panels a la fenetre, puis accès a chacun lors de setContentPane
	
		
		setContentPane(panelMenu);//accès panel menu
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
    
	
	public void actionPerformed(ActionEvent e){
		
		if(e.getSource()== bJouer){
			panelJeu.t.start();
            this.setContentPane(panelJeu);//accès au panel de jeu
        }
		
		if(e.getSource()== bQuitter){
			System.exit(0);
			//demander au prof
		}//quitter le programme
        
		if(e.getSource()==bOptions){
			
			this.setContentPane(panelOptions);//accès aux options
		}
		
	}
	
	public static void main (String [] args){
		FenetreJeu maFenetre = new FenetreJeu();//executer la fenetre
	}
	
}

