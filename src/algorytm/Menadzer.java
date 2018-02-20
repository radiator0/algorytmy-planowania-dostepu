package algorytm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Menadzer {
	/** Lista dostêpnych procesów */
	private List<Proces> procesy;
	/** Lista algorytmów planowania */
	private List<AlgorytmPlanowania> algorytmy;
	/** Deklaracja algorytmu fcfs */
	private AlgorytmPlanowania fcfs;
	/** Deklaracja algorytmu sjf */
	private AlgorytmPlanowania sjf;
	/** Deklaracja algorytmu sjfw */
	private AlgorytmPlanowania sjfw;
	/** Deklaracja algorytmu rot */
	private AlgorytmPlanowania rot;
	/** Deklaracja szansy na zgloszenie sie procesu */
	private final int szansaNaZgloszenie;
	
	public Menadzer(int interwalZmiany, int kwant, int szansaNaZgloszenie){
		procesy = new ArrayList<>();
		algorytmy = new ArrayList<>();
		this.szansaNaZgloszenie = szansaNaZgloszenie;
		
		fcfs = new FCFS("Algorytm FCFS", interwalZmiany);
		sjf = new SJF("Algorytm SJF", interwalZmiany);
		sjfw = new SJFw("Algorytm SJF-w", interwalZmiany);
		rot = new Rotacyjny("Algorytm rotacyjny", interwalZmiany, kwant);
		
		algorytmy.add(fcfs);
		algorytmy.add(sjf);
		algorytmy.add(sjfw);
		algorytmy.add(rot);
	}
	
	/**
	 * Funkcja generuje pewn¹ pule procesów wed³ug podanych kryterów
	 * @param ilosc - ilosc procesów
	 * @param maxCzas - maksymalny czas trwania
	 */
	public void generujProcesy(int ilosc, int maxCzas, int szansaKrotkiProces){
		Random r = new Random();
		int czas;
		for(int i=0; i<ilosc; i++){
			int x = r.nextInt(100);
			if(x>szansaKrotkiProces){
				czas = r.nextInt(maxCzas)+(maxCzas/3);
			}else{
				czas = r.nextInt(maxCzas/3)+10;
			}			
			procesy.add(new Proces("nazwa"+i,czas));
		}
		procesy.get(0).ustawCzas(140);
	}
	
	/**
	 * Funkcja wyswietla procesy z listy procesow na ekranie
	 */
	public void wyswietlProcesy(){
		for(Proces p : procesy){
			System.out.println(p);
		}
	}

	/**
	 * Pobiera proces, który ma byæ dodany do kolejki
	 * @return proces
	 */
	public Proces pobierzProces(){
		Proces p = procesy.get(0);
		return p;
	}
	
	/**
	 * Usuwa proces z listy procesów 
	 * @param p - proces
	 */
	public void usunProces(Proces p){
		procesy.remove(p);
	}
	
	/**
	 * Funkcja zwraca obiekt powsta³y podczas klonowania
	 * @param p - proces
	 * @return proces
	 */
	public Proces klonujProces(Proces p){
		try {
			p = p.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	/**
	 * Funkcja losuje czy w danym cyklu dodac nowy proces do kolejki
	 * @return 	true - nale¿y dodaæ proces<br>
	 * 			false - nale¿y nie dodawaæ procesu
	 */
	public boolean losujCzyDodac(){
		Random r = new Random();
		int los = r.nextInt(1000)+1;
		if(los>(1000-szansaNaZgloszenie)){
			return true;
		}
		return false;
	}
	
	/**
	 * Funkcja wykonuje kolejny krok dla wszystkich algorytmów na raz
	 * @return 	true - krok zostal wykonany <br>
	 * 			false - nie mo¿na wykonaæ kroku 
	 */
	public boolean krok(){
		if(losujCzyDodac() && procesy.size()>0){
			Proces p = pobierzProces();
			System.out.println("System |Zglosil sie proces: " + p);
			for(AlgorytmPlanowania alg : algorytmy){
				alg.dodaj(klonujProces(p));
			}	
			usunProces(p);
		}
		for(AlgorytmPlanowania alg : algorytmy){
			alg.krok();
		}
		
		if(procesy.size()==0){
			return false;
		}	
		return true;
	}
	
	/**
	 * Funkcja liczy ile procesów jest aktywnych w danym cyklu
	 * @return ilosc procesów
	 */
	public int iloscAktywnychProcesow(){
		int i = 0;	
		for(AlgorytmPlanowania alg : algorytmy){
			i = i + alg.pobierzKolejke().size();
		}
		return i;
	}
	
	/**
	 * Funkcja zwraca kolejke dla danego algorytmu 
	 * @param alg - algorytm
	 * @return kolejka
	 */
	public List<Proces> pobierzKolejke(AlgorytmPlanowania alg){
		return alg.pobierzKolejke();
	}
	
	/**
	 * Funkcja zwraca etykiete dla danego algorytmu
	 * @param alg - algorym
	 * @return etykieta
	 */
	public String pobierzEtykiete(AlgorytmPlanowania alg){
		return alg.pobierzEtykiete();
	}
	
	/**
	 * Funkcja zwraca sredni czas dla danego algorymu
	 * @param alg - algorytm
	 * @return sredni czas
	 */
	public double pobierzSredniCzas(AlgorytmPlanowania alg){
		return alg.sredniCzasOczekiwania();
	}
	
	/**
	 * Funkcja zwraca liste z algorytmami 
	 * @return algorytmy
	 */
	public List<AlgorytmPlanowania> pobierzAlgorytmy(){
		return algorytmy;
	}
	
}
