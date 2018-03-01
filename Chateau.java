import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;

public class Chateau{
    int vie;                //vie actuelle du chateau
    final int VIE_MAX=100;  //vie max du chateau
    
    LinkedList<Projectile> listArmes;   //tous les projectiles en l'air
    LinkedList<Monstre> listEnemis;     //tous les monstres sur le terrain
    
    int L;      //Hauteur et largeur du chateau a defendre
    int H;      //
    
    int L_TERRAIN;   //Hauteur et largeur du terrain total 
    int H_TERRAIN;   //(chateau et zone de déplacement des monstres et projectiles)
    
    BufferedImage image;
    
    
    Chateau(int L_TERRAIN, int H_TERRAIN){
        vie=VIE_MAX;
        this.L_TERRAIN=L_TERRAIN;
        this.H_TERRAIN=H_TERRAIN;
        
        L=(int)(L_TERRAIN/8);
        H=(int)(4*H_TERRAIN/5);
    }
    
    public void dessinTerCha(Graphics g){
        g.setColor(new Color(135, 89, 26));                                  //mordoré, marron chelou            
        g.fillRect(0,(int)(5*H_TERRAIN/6),L_TERRAIN,(int)(H_TERRAIN/6));     //pour le sol 
        
        g.setColor(new Color(58, 157, 35));                                  //vert gazon
        g.fillRect(0,(int)(4*H_TERRAIN/6),L_TERRAIN,(int)(H_TERRAIN/6));     //pour les arbres
        
        g.setColor(new Color(135,206,235));                                  //bleu ciel
        g.fillRect(0,0,L_TERRAIN,(int)(4*H_TERRAIN/6));                      //pour le ciel
        
        g.setColor(new Color(169,169,169));           //couleur gris foncé
        g.fillRect(0,H_TERRAIN-H,L,H);                //chateau a gauche
        
    }
    
    public void dessinHP(Graphics g){
        int lBarre=200; //longeur barre
        int l1=30;//debut barre (coord x et y en pixel)
        int l2=(int)(30+(lBarre*(VIE_MAX-vie)/VIE_MAX)); //coord x séparation vert/noir
        int l3=lBarre+30;//coord x en pixel de la fin de la barre
        int h=40;//hauteur barre
        
        g.setColor(new Color(46, 139, 87));//vert océan (vie restante)
        g.fillRect(l1,l1,l2-l1,h);
        
        g.setColor(Color.black);//noir (vie perdue)
        g.fillRect(l2,l1,l3-l2,h);
        
    }
    
    
}
