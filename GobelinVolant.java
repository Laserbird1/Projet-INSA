import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;

public class GobelinVolant extends Monstre{

    double y_init;
    
    GobelinVolant (double centrex, double centrey,int L,int H,double vitesse){
    
        super(L,H);
        this.VIE_MAX=3;
        this.vie=VIE_MAX;
        this.vitesse=vitesse*1.5;
        this.degats=2; 
        this.centrex=centrex;
        this.centrey=centrey; 
        y_init=centrey;      
    }
    
  

	  public void dessin(Graphics g,PanelPrincipalJeu panelJeu){
        super.dessin(g, panelJeu);
        g.drawImage(panelJeu.imgGobelinVolant,(int)(centrex),(int)(centrey),panelJeu);
	 }
	 
	 public void move(){
        this.centrex-=vitesse;
        centrey=H*Math.cos(L*centrex/10000.0)+y_init; 
	}
    
    public String toString (){
        return "Gobelin Volant";
    } 
}

