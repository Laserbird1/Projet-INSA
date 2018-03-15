import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;

public class Chateau{
    int vie;                //vie actuelle du chateau
    final int VIE_MAX=10;  //vie max du chateau
    
    LinkedList<Projectile> listArmes;   //tous les projectiles en l'air
    LinkedList<Monstre> listEnemis;     //tous les monstres sur le terrain
    
    int L;      //Hauteur et largeur du chateau a defendre
    int H;      //
    
    int L_TERRAIN;   //Hauteur et largeur du terrain total 
    int H_TERRAIN;   //(chateau et zone de déplacement des monstres et projectiles
    int H_SOL;
    
    PanelPrincipalJeu panelJeu;
    
    
    Chateau(int L_TERRAIN, int H_TERRAIN,int H_SOL,PanelPrincipalJeu panelJeu){
        this.vie=VIE_MAX;
        this.L_TERRAIN=L_TERRAIN;
        this.H_TERRAIN=H_TERRAIN;
        this.H_SOL=H_SOL;
        
        L=(int)(L_TERRAIN/6);
        H=(int)(4*H_TERRAIN/5);
        
        this.panelJeu=panelJeu;
        
        listArmes=new LinkedList<Projectile>();
        listEnemis=new LinkedList<Monstre>();
    }
    
    public void dessinTerCha(Graphics g){
        
        g.drawImage(panelJeu.imgChateau,L_TERRAIN-L,H_TERRAIN-H,panelJeu);
        g.setColor(Color.black);
        g.drawLine(0,H_TERRAIN,L_TERRAIN,H_TERRAIN);
        
        int lBarre=(int)(L*2/3); //longeur barre (2/3 de la longueur du chateau)
        int X=(int)(L/4);              //debut barre (coord x)
        int Y=(int)(H_TERRAIN+H_SOL/10);//(coord y)
        int hBarre=(int)(H_SOL/5);//hauteur barre
        panelJeu.dessinBarre(g,lBarre,hBarre,X,Y,vie,VIE_MAX,new Color(46, 139, 87));
        
            for(Projectile proj : listArmes){
                proj.dessin(g,panelJeu);
            }
        
            for(Monstre monster : listEnemis){
                monster.dessin(g,panelJeu);
            }
        
        
    }
    
    public void move(){
        if(listArmes.size()!=0){
            for(Projectile proj : listArmes){
                proj.move();
            }
        }
        
        if(listEnemis.size()!=0){
            for(Monstre monster : listEnemis){
                monster.move();
            }
        }
    }
    
    public boolean collisions(){//le booleen retourne la defaite si le chateau n'a plus de vie
        boolean res=false;
        
        if(listArmes.size()!=0){
            for(Projectile proj: listArmes){//pour tous les projectiles
                if(proj.collisionTerrain(this)){//le projectile touche t il le sol ?
                    listArmes.remove(proj);//si oui le projectile disparait
                    break;
                }
                
                for(Monstre monster:listEnemis){//pour ce projectile et chaque monstre
                    if(proj.collisionMonstre(monster)){//le projectile touche t il un monstre ?
                        monster.vie-=proj.degats;
                        if(monster.vie<=0) listEnemis.remove(monster);//si oui le monstre perd de la vie et peut disparaitre 
                        listArmes.remove(proj);//le projectile disparait
                        break;
                    }
                }
            }
        }
        
        if(listEnemis.size()!=0){ 
            for(Monstre monster:listEnemis){//pour tous les monstres
                if(monster.collisionChateau(this)){//touchent t ils le chateau?
                    vie-=monster.degats;
                    listEnemis.remove(monster);//si oui il fait des degats et disparait
                    if(vie<=0) res= true;
                    break;
                }
            }
        }
        
        return res;
        
    }
    
    
}


