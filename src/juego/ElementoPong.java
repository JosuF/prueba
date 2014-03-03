package juego;

import android.graphics.Rect;

public abstract class ElementoPong {

	private Coordenada origen;
	private int ancho;
	private int alto;
	
	public ElementoPong(Coordenada origen, int ancho, int alto) {
		this.origen = origen;
		this.ancho = ancho;
		this.alto = alto;
	}
	
	public int getOrigenX() {
		return origen.getX();
	}
	
	public int getOrigenY() {
		return origen.getY();
	}
	
	public int getAncho() {
		return ancho;
	}
	
	public int getAlto() {
		return alto;
	}
	
	public Rect getRectElemento() {
		return new Rect(getOrigenX(), getOrigenY(), getOrigenX()+ancho, getOrigenY()+alto);
	}
}
