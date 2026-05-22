package model;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D.Double;

public interface Collideable {
	boolean collidesWith(Collideable other); 
	Double getBounds();
	

}
