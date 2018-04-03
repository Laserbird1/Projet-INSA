import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.util.*;

public class PierreFrag extends Pierre{
    
    PierreFrag(double tetaIni,double vitesseIni,int R,double centrex,double centrey,float g){ 
        super(tetaIni,vitesseIni,R,centrex,centrey,g);
       
        
        this.reloadTime=1500;
        
        this.R=R;
        
    }
    
    public void dessin(Graphics g,PanelPrincipalJeu panelJeu){
        
        g.drawImage(panelJeu.imgPierreBig,(int)centrex,(int)centrey,panelJeu);
        
    }
    
    public LinkedList<Projectile> fragmentation (){
        
        LinkedList<Projectile> res=new LinkedList<Projectile>();
        int nbFrag=3+(int)(Math.random()*2);//3 ou 4 fragments
        
        for(int i=0;i<nbFrag;i++){
           // Projectile p = new Pierre(Math.PI*(0.3+Math.random()*0.4)*(-1),20+Math.random()*40,(int)(R*2/3.0),centrex,centrey-20,g);
            res.add(new Pierre(Math.PI*(0.3+Math.random()*0.4)*(-1),20+Math.random()*40,(int)(R*2/3.0),centrex,centrey-20,g));
        }
        return res;
    }
    
    public String toString(){ return "PierreFrag";}
    
    
}
