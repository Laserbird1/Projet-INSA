import java.awt.*;
import java.awt.event.*;


public class PanelPrincipalJeu extends JPanel implements ActionListener, MouseListener{
	
	
	public PanelPrincipalJeu(int L, int H) {
		
		
		this.setLayout(null);
		this.setSize(L,H);
		
		
		//panneau au sol
		JPanel panelSol = new JPanel();
		add(panelSol);
		panelSol.setBounds(0,(int)(5*H/6),L,(H/6));
		panelSol.setLayout(null);
		panelSol.setBackground(new Color(160, 82, 45));//couleur sienne
		
		
    	 	//panneau Tour
		
		JPanel panelTour = new JPanel();
		add(panelTour);
		panelTour.setBounds(0,0,L,(int)(5*H/6));
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