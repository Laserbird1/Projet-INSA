import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;

public abstract class Projectile {
    
    double vitessex;           //vitesse sur chaque axe
    double vitessey;
    
    double centrex;            //centre de gravité, référentiel à partir duquel est créé l'image du projectile, permet le déplacement facile du projectile
    double centrey;
    
    final float g;            //gravité, paramètre de mouvement pour la degression de la vitesse verticale
    
    int degats;             //dégats infligés aux monstres
    int reloadTime;         //temps avant de pouvoir tirer à nouveau en ms

    
    Projectile(float g){
        this.g=g;
    }
    
    public void move(){
        centrex+=vitessex;      
        vitessey+=g;            
        centrey+=vitessey;      
    }
    
    public abstract void dessin(Graphics g,PanelPrincipalJeu panelJeu);
    public abstract boolean collisionMonstre(Monstre monster);
    public abstract boolean collisionTerrain(Chateau castle);
    
    public static double[] shootingPulling(int X,int Y){
       
        double[]res = new double [2];
        
        //res[0]=VIni et res[1]=tetaIni
        res[0]=-Math.sqrt(X*X+Y*Y)/5;//r=sqrt(x²+y²)
                
        if(res[0]<-100) res[0]=-100;//limitation de la vitesse sinon c'est cheaté
                
        res[1] = Math.atan2(Y,X); //teta=atan2(x,y)
        
        return res;
    }
    
    public static double[] shootingPointing(int X1,int Y1,int XTir,int YTir,boolean angleShootLow, float g){
        double[]res = new double [2];
        //res[0]=VIni et res[1]=tetaIni
        //(X1,Y1) coord du point a atteindre en partant de (XTir,YTir)
        res[0]=60;//vitesse initiale imposée
                
        if(angleShootLow){//on veut l'angle bas
            res[1]=0.5*Math.PI;//parcours depuis le bas
            
            while(res[1]<1.5*Math.PI && res[1]>-0.5*Math.PI){//parcours de tous les angles jusqu'a ce qu'un angle convienne pour l'equation de trajectoire associée 
                                                            //Le parcours de fait a droite ou a gauche selon la position du point visé
                    
                double y=0.5*g*(X1-XTir)*(X1-XTir)/(res[0]*res[0]*Math.cos(res[1])*Math.cos(res[1]))+(X1-XTir)*Math.tan(res[1])+YTir;
                        
                if( Y1-2<y && y<2+Y1) break;//pour ce teta on peut atteindre le point fixé
                        
                if(((X1-XTir)>=0)) res[1]-=0.001; //increment du teta // si la souris est a droite du chateau increment par teta décroissant
                else  res[1]+=0.001; 
            }
            
        }else{//on veut l'angle haut
                
            res[1]=-0.5*Math.PI;;//parcours depuis le haut
                
            while(res[1]<0.5*Math.PI && res[1]>-1.5*Math.PI){//parcours de tous les angles jusqu'a ce qu'un angle convienne pour l'equation de trajectoire associée 
                                               //Le parcours de fait a droite ou a gauche selon la position du point visé
                                               
                double y=0.5*g*(X1-XTir)*(X1-XTir)/(res[0]*res[0]*Math.cos(res[1])*Math.cos(res[1]))+(X1-XTir)*Math.tan(res[1])+YTir;
                        
                if( Y1-5<y && y<5+Y1) break;//pour ce teta on peut atteindre le point fixé
                        
                if(((X1-XTir)>=0)) res[1]+=0.00001; //increment du teta // si la souris est a droite du chateau increment par teta croissant
                else  res[1]-=0.00001; 
            }
        }
            
        return res;
    }
    
    public abstract String toString();
    public LinkedList<Projectile> fragmentation(){return new LinkedList<Projectile>();}
}




