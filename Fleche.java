import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.util.*;

public class Fleche extends Projectile{
    
    float l;     //longueur de la fleche
    double teta; //orientation de la flèche
    
    double centre2x;
    double centre2y;//deuxième centre de la flèche (=point centre(t-1)) pour definir teta
    
    //centrex et centrey definissent la pointe de la fleche, centre2 le point qu'ocuppait centre a l'instant (t-1)

    Fleche(double tetaIni,double vitesseIni,int l,double centrex,double centrey,float g){ 
        super(g);
        this.vitessex=vitesseIni*Math.cos(tetaIni);
        this.vitessey=vitesseIni*Math.sin(tetaIni);  //assignation des valeurs de vitesses initiales
        
        this.centrex=centrex; //positionnement initial
        this.centrey=centrey;
        
        this.reloadTime=400;
        this.degats=2;
        
        this.l=l;
        

    }
    
    public void move(){
        centre2x=centrex;
        centre2y=centrey;
        
        centrex+=vitessex;      
        vitessey+=g;            
        centrey+=vitessey;
        
        teta = Math.PI+Math.atan2((centre2y-centrey),(centre2x-centrex));
        
    }
    
    public void dessin(Graphics g,PanelPrincipalJeu panelJeu){
        //il faut faire tourner l'image
        Image imgthis=ImageWorker.rotateImage(panelJeu.imgFleche,teta);
        g.drawImage(imgthis,(int)centrex,(int)centrey,panelJeu);//et l'afficher
        
    }
    
    public boolean collisionMonstre(Monstre monster){
        
        Line2D.Double box1 = new Line2D.Double(centrex,centrey,centrex+l*Math.cos(teta),centrey+l*Math.sin(teta));    //On crée une hitbox pour le projectile
        Rectangle box2 = new Rectangle((int)(monster.centrex),(int)(monster.centrey),monster.L,monster.H); //on crée une hitbox pour le monstre
        return box1.intersects(box2);                                                        //sont-ils en collision ?
        
    }
    
    public boolean collisionTerrain(Chateau castle){
        
        if(centrey>castle.H_TERRAIN){//si le bout de la fleche est trop bas (planté dans le sol)
            return true;
        }else return false;
    }
    
    public String toString(){ return "Fleche";}
}



