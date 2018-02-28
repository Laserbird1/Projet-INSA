
// Chargement des bibliothèques Swing et AWT
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class FenetreJeu extends JFrame implements ActionListener, MouseListener{
	
	// Les Widgets à déclarer en dehors du constructeur
	
	Timer monTimer;
	long temps;
	String positionSouris;
	JTextField textChoix;
	JTextField affResult;
	JButton bJouer;
	JButton bQuitter;
	JButton bOption;
	
		
	public FenetreJeu(){

		this.setTitle("Jeu Tower defense  ");
		this.setLayout(null);
		this.setSize(600,600);
		this.setLocation(400,100);
		// Pour empêcher le redimensionnement de la fenêtre
		this.setResizable(false);
		
		// Pour gérer le temps
		monTimer = new Timer(100, this);
		//monTimer.start();
		temps=0;
		
		// Ecouteur Souris
		positionSouris="x / y";
		addMouseListener(this);
		
		

		
	
	
	
	
	
		/**
    	 * Mon panneau Jeu
		 */
		JPanel panelMenu = new JPanel();
		add(panelMenu);
		panelMenu.setBounds(0,0,600,600);
		panelMenu.setLayout(null);
		panelMenu.setBackground(Color.white);
		
	
	
	
		bJouer = new JButton("Jouer");
		bJouer.setBounds(250,300,100,50);
		bJouer.setBackground(Color.white);
		bJouer.setForeground(Color.black);
		/* branchement de l'écouteur*/
		bJouer.addActionListener(this);
		
		bQuitter = new JButton("Quitter");
		bQuitter.setBounds(250,360,100,50);
		bQuitter.setBackground(Color.white);
		bQuitter.setForeground(Color.black);
		/* branchement de l'écouteur*/
		bQuitter.addActionListener(this);
		
		
		bOption = new JButton("Option");
		bOption.setBounds(250,420,100,50);
		bOption.setBackground(Color.white);
		bOption.setForeground(Color.black);
		/* branchement de l'écouteur*/
		bOption.addActionListener(this);
		panelMenu.add(bJouer);
		panelMenu.add(bQuitter);
		panelMenu.add(bOption);
		
		
		// Pour rendre la fenêtre visible
		this.setVisible(true);
				// Pour permettre la fermeture de la fenêtre lors de l'appui sur la croix rouge
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	/**
	 * Pour faire des dessins simples
	 * @param l'objet graphics
	 */ 
	
	
	public void mouseReleased(MouseEvent e){};
	public void mouseEntered(MouseEvent e){};
	public void mouseExited(MouseEvent e){};
	public void mouseClicked(MouseEvent e){
		monTimer.start();
		positionSouris="x = "+e.getX()+" / y = "+e.getY();
	}
	public void mousePressed(MouseEvent e){};
	
	
	
	
	/**
	 * 
	 */
	public void actionPerformed(ActionEvent e){
		
		
	}
	
	
}
