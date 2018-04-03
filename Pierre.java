import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.util.*;

public class Pierre extends Projectile{
    int R;      //rayon de la pierre, pour la hitbox
    
    Pierre(double tetaIni,double vitesseIni,int R,double centrex,double centrey,float g){ 
        super(g);
        this.vitessex=vitesseIni*Math.cos(tetaIni);
        this.vitessey=vitesseIni*Math.sin(tetaIni);  //assignation des valeurs de vitesses initiales
        
        this.centrex=centrex; //positionnement initial
        this.centrey=centrey;
        
        this.reloadTime=800;
        this.degats=3;
        
        this.R=R;
        
    }
    
    public void dessin(Graphics g,PanelPrincipalJeu panelJeu){
        
        g.drawImage(panelJeu.imgPierre,(int)centrex,(int)centrey,panelJeu);
    }
    
    public boolean collisionMonstre(Monstre monster){
        
        Ellipse2D.Double box1 = new Ellipse2D.Double(centrex,centrey,2*R,2*R);               //On crée une hitbox pour le projectile
        Rectangle box2 = new Rectangle((int)(monster.centrex),(int)(monster.centrey),monster.L,monster.H); //on crée une hitbox pour le monstre
        return box1.intersects(box2);                                                        //sont-ils en collision ?
        
    }
    
    public boolean collisionTerrain(Chateau castle){
        boolean res=false;
        
        if(centrey+2*R>castle.H_TERRAIN){
            res=true;
        }
        
        return res;
    }
    
    public String toString(){ return "Pierre";}
    
    
}



