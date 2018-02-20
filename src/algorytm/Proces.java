package algorytm;

import java.awt.Color;

public class Proces implements Cloneable{
	/** Pole statyczne przechowuj¹ce ostatni nadany indeks */
	private static int index=0;
	/** Indeks procesu */
	private int id;
	/** Nazwa procesu*/
	private String nazwa;
	/** Czas trwania procesu */
	private int czas;
	/** Czas oczekiwania procesu */
	private int czasOczekiwania=0;
	/** Kolor procesu */
	private Color color = new Color(160,196,9);
	
	public Proces(String nazwa, int czas){
		++index;
		this.id = index;
		this.nazwa = nazwa;
		this.czas = czas;
	}
	
	/**
	 * Zwraca sformatowany tekst z danymi procesu
	 */
	public String toString(){
		return String.format("%5d%10s%8s",id, nazwa,czas+"ms");
	}
	
	/**
	 * Zwraca nazwe procesu
	 * @return nazwa
	 */
	public String pobierzNazwa() {
		return nazwa;
	}

	/**
	 * Zwraca czas trwania procesu
	 * @return czas 
	 */
	public int pobierzCzas() {
		return czas;
	}
	
	/**
	 * Zwraca indeks procesu
	 * @return indeks
	 */
	public int pobierzId(){
		return id;
	}
	
	/**
	 * Ustawia czas trwania procesu
	 * @param czas - czas trwania
	 */
	public void ustawCzas(int czas){
		this.czas = czas;
	}
	
	/**
	 * Zwraca kolor procesu
	 * @return kolor
	 */
	public Color pobierzColor(){
		return color;
	}
	
	/**
	 * Ustawia kolor procesu
	 * @param color - kolor
	 */
	public void ustawColor(Color color){
		this.color = color;
	}
	
	/**
	 * Ustawia czas oczekiwania procesu
	 * @param czasOczekiwania - czas oczekiwania
	 */
	public void ustawCzasOczekiwania(int czasOczekiwania){
		this.czasOczekiwania = czasOczekiwania;
	}
	
	/**
	 * Zwraca czas oczekiwania procesu
	 * @return czas oczekiwania
	 */
	public int pobierzCzasOczekiwania(){
		return czasOczekiwania;
	}
	
	/**
	 * Zmniejsza czas trwania procesu o podany czas
	 * @param czas - czas o jaki zmniejsza
	 */
	public void zmniejszCzas(int czas){
		this.czas -= czas;
	}
	
	/**
	 * Klonuje proces i go zwrca
	 * @return Proces sklonowany
	 */
    public Proces clone() throws CloneNotSupportedException {
        return (Proces)super.clone();
    }	
}
