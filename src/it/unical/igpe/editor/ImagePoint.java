package it.unical.igpe.editor;


import java.awt.Point;
import java.awt.image.BufferedImage;

public class ImagePoint {
	
	public enum type{
		PLAYER,ENEMY1,ENEMY2,ENEMY3,ENEMY4,STAIR,KEY,REDKEY,BLUEKEY,GREENKEY;
	}
	
	private Point point;
	private BufferedImage image;
	type type;

	public ImagePoint(Point point,BufferedImage image,int id){

		this.point = point;
		this.image = image;
		
		if(id==9)
			type=type.STAIR;
		if(id==13)
			type=type.KEY;
		if(id==14)
			type=type.REDKEY;
		if(id==15)
			type=type.BLUEKEY;
		if(id==16)
			type=type.GREENKEY;
		if(id==17)
			type=type.PLAYER;
		if(id==18)
			type=type.ENEMY1;
		if(id==19)
			type=type.ENEMY2;
		if(id==20)
			type=type.ENEMY3;		
		if(id==21)
			type=type.ENEMY4;
	}


	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public type Type(){
		return type;
	}
	
	public void seType(type type){
		this.type=type;
	}
	
	@Override
	public String toString() {
			return (type + ":" + point.x + ":" + point.y);
		
	}
	
}
