package algorytm;

public class Rotacyjny extends  AlgorytmPlanowania{
	private int kwant;
	private int czasKwantu = 0;
	
	public Rotacyjny(String etykieta, int interwalZmiany, int kwant){
		super(etykieta, interwalZmiany);
		this.kwant = kwant;
	}

	public void dodaj(Proces p){
		kolejka.add(p);
		ustawCzasOczekiwania(p);
		System.out.print(etykieta+" |Procesow w kolejce: " + kolejka.size());
		System.out.print(" Czas obecny oczekiwania: " + String.format("%5d%2s", p.pobierzCzasOczekiwania(), "ms") + "\n");
	}
	
	public void krok(){	
			// Jeœli kolejka ma jakis element to pobiera pierwszy;
			if(kolejka.size()>0){
				Proces p = kolejka.get(0);
				if(p.pobierzCzas()<=1){
					czasKwantu = 0;
					kolejka.remove(p);
					zmianaKontekstu();
				}else if(czasKwantu==kwant){
					kolejka.remove(p);
					kolejka.add(p);
					if(kolejka.size()>1){
						zmianaKontekstu();
					}
					ustawCzasOczekiwania(p);
					czasKwantu = 0;
				}
				if(czasZmiana>0){
					czasZmiana--;
				}else{
					p.zmniejszCzas(1);
					czasKwantu++;
				}
			}else{
				czasKwantu = 0;
			}

	}	
	


}

