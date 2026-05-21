package model;

import java.awt.Rectangle;

public interface Collideable {
	boolean collidesWith(Collideable other); 
	Rectangle getBounds();
	

}
