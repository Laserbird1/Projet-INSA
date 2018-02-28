import java.awt.*;
import javax.swing.*;

public class Monstre{
    int vie;        //points de vie
    int vitesse;    //vitesse de déplacement
    int degats;     //degats infligés lors de la rencontre avec le chateau
    int centrex;    //coord x du centre de gravité 
    int centrey;    //coord y du centre de gravité
    int L;          //largeur de la hitbox
    int H;          //hauteur de la hitbox
    
    Monstre(){
        L=50;//en pixel
        H=70;//en pixel
    }
    
    public void move(){
        this.centrex+=vitesse;///ou -= selon orientation du chateau
    }
}
