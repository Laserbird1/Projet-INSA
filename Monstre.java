import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;

public abstract class Monstre{
    int vie;        //points de vie
    int VIE_MAX;
    double vitesse;    //vitesse de déplacement
    int degats;     //degats infligés lors de la rencontre avec le chateau
    
    double centrex;    //coord du centre de gravité, référentiel a partir duquel est 
    double centrey;    //créé l'image du monstre, permet le déplacement facile du monstre
                    //Est en réalité le coin supérieur droit du monstre
    
    int L;          //dimensions hitbox
    int H;          //Largeur,Hauteur
    
    boolean boss;
    
    Monstre(int L,int H){
        this.L=L;//en pixel
        this.H=H;//en pixel
    }
    
    public void move(){
        this.centrex-=vitesse;
    }
    
    public boolean collisionChateau(Chateau castle){
        boolean res=false;
        
        if(centrex<=castle.L)
        res=true;
        
        return res;
    }
    
    public void dessin(Graphics g,PanelPrincipalJeu panelJeu){
        
        //barre de vie
        int lBarre=L; //longeur barre (longueur du monstre)
        int X=(int)(centrex);              //debut barre (coord x)
        int Y=(int)(centrey-30);           //(coord y)
        int hBarre=(int)(H/6);//hauteur barre
        
        panelJeu.dessinBarre(g,lBarre,hBarre,X,Y,vie,VIE_MAX,new Color(46, 139, 87));
    }
    
    public abstract String toString();
}


