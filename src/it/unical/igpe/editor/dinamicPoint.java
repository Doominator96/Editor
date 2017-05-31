package it.unical.igpe.editor;

import java.awt.Point;

@SuppressWarnings("serial")
public class dinamicPoint extends Point
{
	private int id;

	public dinamicPoint(){
		
	}
	@Override
	public String  toString() {
		
		return (x +":"+ y);
	}
}
