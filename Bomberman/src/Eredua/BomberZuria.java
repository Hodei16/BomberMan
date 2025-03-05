package Eredua;
import java.util.ArrayList;

public class BomberZuria {
	private ArrayList <Bonba> listaBonba;
	private int posX;
	private int posY;
	
	public BomberZuria(int pPosx, int pPosY) {
		this.listaBonba = new ArrayList <Bonba>();
	}
	
	public void setPosX(int pPosX) {
		this.posX = pPosX;
	}
	public void setPosY(int pPosY) {
		this.posY = pPosY;
	}
	public int getPosX() {
		return this.posX;
	}
	public int getPosY() {
		return this.posY;
	}
}

