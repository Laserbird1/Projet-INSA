import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.geom.*;
import java.awt.image.*; 

public class ImageWorker{
	
	

	//Cette fonction permet de charger une image dans votre programme quand elles sont placees a cote des classes java
	//imgName: nom de l'image (avec l'extention)
	public static Image loadImage(String imgName){
		 return loadImage(imgName, ".");
	}
	
	//Cette fonction permet de charger une image dans votre programme quand elles sont rangees dans un sous-dossier
	//imgName: nom de l'image (avec l'extention)
	//imgPath: nom du dossier contenant les images 
	public static Image loadImage(String imgName,String imgPath){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		return toolkit.getImage(imgPath+"/"+imgName);
	}
	
	
	


	//Cette fonction permet de redimentionner une image (objet de type Image)
	//Note2: si vous voulez stocker le resultat dans une buffered image il suffit de faire un "cast" sur le retour de cette methode
	//OriginalImage: l'image a redimentionner
	//biggerWidth: la largeur de la nouvelle image
	//biggerHeight: la hauteur de la nouvelle image
	public static Image resizeImage(Image originalImage, double newWidth, double newHeight) {
		int type = BufferedImage.TYPE_INT_ARGB;
		
		int w=(int)newWidth;
		int h=(int)newHeight;

		BufferedImage resizedImage = new BufferedImage(w, h, type);
		Graphics2D g = resizedImage.createGraphics();

		g.setComposite(AlphaComposite.Src);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.drawImage(originalImage, 0, 0, w, h, null);
		g.dispose();

		return resizedImage;
	}
	
	
	//pivote un image d'un certain angle donne en parametre
	//img: image a pivoter
	//angle: angle de rotation
	public static Image rotateImage(Image img, double angle){
		AffineTransform transformer = new AffineTransform();
		transformer.rotate(angle, img.getWidth(null)/2, img.getHeight(null)/2);
		AffineTransformOp op = new AffineTransformOp(transformer,AffineTransformOp.TYPE_BILINEAR);
		return op.filter(toBufferedImage(img), null);
	}

	//Cette fonction vous permet de faire attendre le programme pour s'assurer que l'image
	//Donnee est completement chargee en memoire
	//img: l'image pour laquelle le programme doit attendre
	//comp: pour faire simple c'est un pointeur vers votre fenetre (celle dnas lequel sera affiche l'image par le futur)
	public static void waitUtilFullyLoaded(Image img,Component comp){
        MediaTracker tracker=new MediaTracker(comp);
        tracker.addImage(img, 0);
		
		try{
			tracker.waitForAll();
		}catch(InterruptedException e) {
			System.out.println("image loading failed");
		}
	}
	
	//Convertit un objet Image en objet BufferedImage 
	public static BufferedImage toBufferedImage(Image img)
	{
		if (img instanceof BufferedImage)
		{
			return (BufferedImage) img;
		}

		// Create a buffered image with transparency
		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		// Draw the image on to the buffered image
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();

		// Return the buffered image
		return bimage;
	}

}
