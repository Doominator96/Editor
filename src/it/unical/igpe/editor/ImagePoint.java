package it.unical.igpe.editor;


import java.awt.Point;
import java.awt.image.BufferedImage;

public class ImagePoint {
	
	public enum type{
		PLAYER,ENEMY1,ENEMY2,STAIR,KEY,REDKEY,BLUEKEY,GREENKEY;
	}
	
	private Point point;
	private BufferedImage image;
	type type;

	@SuppressWarnings("static-access")
	public ImagePoint(Point point,BufferedImage image,int id){

		this.point = point;
		this.image = image;
		
		if(id==2)
			type=type.STAIR;
		if(id==6)
			type=type.KEY;
		if(id==7)
			type=type.REDKEY;
		if(id==8)
			type=type.BLUEKEY;
		if(id==9)
			type=type.GREENKEY;
		if(id==10)
			type=type.PLAYER;
		if(id==11)
			type=type.ENEMY1;
		if(id==12)
			type=type.ENEMY2;
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
