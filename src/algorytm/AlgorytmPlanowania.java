package algorytm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class AlgorytmPlanowania {
	protected String etykieta;
	protected List<Proces> kolejka;
	protected List<Proces> uzywane;
	protected int czasZmiana = 0;
	private int interwalZmiany;

	public AlgorytmPlanowania(String etykieta, int interwalZmiany){
		this.etykieta = etykieta;
		kolejka = new LinkedList<>();
		uzywane = new ArrayList<>();
		this.interwalZmiany = interwalZmiany;
	}

	/** 
	 *	Metoda przesuwa algorytm o krok w przód 
	 */
	public abstract void krok();
	
	/** 
	 * Metoda aktywuje akcje ktore maja sie wykonac przy zmianie kontekstu 
	*/
	public void zmianaKontekstu(){
		czasZmiana += interwalZmiany;
	}
	
	/** 
	 * Metoda liczy œredni czas oczekiwania
	 * @return sredni czas oczekiwania
	 */
	public double sredniCzasOczekiwania(){
		double czas = 0;
		for(Proces p : uzywane){
			czas += p.pobierzCzasOczekiwania();
		}
		return czas/uzywane.size();
	}
	
	/**
	 * Metoda ustawia czas oczekiwania dla podanego procesu
	 * @param p - proces
	 */
	public void ustawCzasOczekiwania(Proces p){
		int czas = 0;
		for(Proces x : kolejka){
			if(x.equals(p)){
				p.ustawCzasOczekiwania(czas);
			}
			czas += x.pobierzCzas();
			czas += interwalZmiany;
		}
		try {
			uzywane.add(p.clone());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Metoda dodaje nowy proces do kolejki
	 * @param p - proces
	 */
	public abstract void dodaj(Proces p);
	
	/**
	 * Metoda zwraca kolejke procesow
	 * @return kolejka
	 */
	public List<Proces> pobierzKolejke(){
		return kolejka;
	}
	
	/** 
	 * Metoda zwraca nazwe któr¹ jest etykietowany algorytm 
	 */
	public String pobierzEtykiete(){
		return etykieta;
	}
}
