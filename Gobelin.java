import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;

public class Gobelin extends Monstre{
    
    Gobelin(double centrex, double centrey,int L,int H,double vitesse){
        super(L,H);
        this.VIE_MAX=4;
        this.vie=VIE_MAX;
        this.vitesse=vitesse;
        this.degats=3;
        this.centrex=centrex;
        this.centrey=centrey;
        
    }
    
     public void dessin(Graphics g,PanelPrincipalJeu panelJeu){
		 super.dessin(g, panelJeu);
		 g.drawImage(panelJeu.imgGobelin,(int)(centrex),(int)(centrey),panelJeu);
	 }
    
    
}
