import javax.swing.*;
import java.awt.*;


public class FenetrePrincipaleJeu extends JFrame implements ActionListener,MouseListener{
	
	public FenetrePrincipaleJeu() {
		
		this.setTitle("Jeu Tower defense");
		this.setLayout(null);
		this.setSize(800,600);
		this.setLocation(0,0);
		this.setBackground(Color.white);
		
		
		
		// Mon panneau Avec la tour
		
		JPanel panelDeJeu = new JPanel();
		add(panelDeJeu);
		panelDeJeu.setBounds(0,0,800,525);
		panelDeJeu.setLayout(null);
		panelDeJeu.setBackground(Color.white);
		
		
		//panneau au sol
		JPanel panelSol = new JPanel();
		add(panelSol);
		panelSol.setBounds(0,525,800,600);
		panelSol.setLayout(null);
		panelSol.setBackground(new Color(160, 82, 45));//couleur sienne
		
		
    	 	//panneau Tour
		
		JPanel panelTour = new JPanel();
		panelDeJeu.add(panelTour);
		panelTour.setBounds(40,175,100,350);
		panelTour.setLayout(null);
		panelTour.setBackground(new Color(135,206,235));//bleu ciel
		
		
		// Pour rendre la fenêtre visible
		this.setVisible(true);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void actionPerformed(ActionEvent e){}
	
	public void mouseReleased(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	
	

}
