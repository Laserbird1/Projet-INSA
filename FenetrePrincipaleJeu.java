import javax.swing.*;
import java.awt.*;


public class FenetrePrincipaleJeu extends JPanel implements ActionListener,MouseListener{
	
	public FenetrePrincipaleJeu() {
		
		
		this.setLayout(null);
		this.setSize(800,600);
		this.setLocation(0,0);
		this.setBackground(Color.white);
		
		
		//panneau au sol
		JPanel panelSol = new JPanel();
		add(panelSol);
		panelSol.setBounds(0,400,800,200);
		panelSol.setLayout(null);
		panelSol.setBackground(new Color(160, 82, 45));//couleur sienne
		
		
    	 	//panneau Tour
		
		JPanel panelTour = new JPanel();
		add(panelTour);
		panelTour.setBounds(0,0,800,400);
		panelTour.setLayout(null);
		panelTour.setBackground(new Color(135,206,235));//bleu ciel
		
		
		// Pour rendre la fenêtre visible
		this.setVisible(true);
		
		
	}
	
	public void actionPerformed(ActionEvent e){}
	
	public void mouseReleased(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	
	

}
