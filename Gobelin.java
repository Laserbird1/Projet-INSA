import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;

public class Gobelin extends Monstre{
    
    
    Gobelin(double centrex, double centrey,int L,int H,double vitesse, boolean boss){
        super(L,H);
        
        this.VIE_MAX=4;
        this.degats=3;
        
        this.vie=VIE_MAX;
        this.vitesse=vitesse;
        this.centrex=centrex;
        this.centrey=centrey;
        
        this.boss=boss;
        
        if(boss){
            this.VIE_MAX=8;
            this.vie=VIE_MAX;
            this.vitesse=vitesse/2.0;
            this.degats=degats*3;
            this.L=3*L;
            this.H=3*H;
            this.centrey=0;
            
        }else{
            this.VIE_MAX=4;
            this.degats=3;
            
            this.vie=VIE_MAX;
            this.vitesse=vitesse;
            this.centrex=centrex;
            this.centrey=centrey;
        }
    }
    
    public void dessin(Graphics g,PanelPrincipalJeu panelJeu){
        super.dessin(g, panelJeu);
        if(!boss)
            g.drawImage(panelJeu.imgGobelin,(int)(centrex),(int)(centrey),panelJeu);
        else
            g.drawImage(panelJeu.imgBoss,(int)(centrex),(int)(centrey),panelJeu);
    }
    
    public String toString(){
        if(boss) return "Boss";
        else return "Gobelin";
    }
}
