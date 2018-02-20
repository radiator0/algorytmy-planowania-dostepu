package algorytm;

public class FCFS extends AlgorytmPlanowania{

	public FCFS(String etykieta, int interwalZmiany){
		super(etykieta, interwalZmiany);
	}

	public void dodaj(Proces p){
		kolejka.add(p);
		ustawCzasOczekiwania(p);
		System.out.print(etykieta+" |Procesow w kolejce: " + kolejka.size());
		System.out.print(" Czas obecny oczekiwania: " + String.format("%5d%2s", p.pobierzCzasOczekiwania(), "ms") + "\n");
	}
	
	public void krok(){
			if(kolejka.size()>0){		
				Proces p = kolejka.get(0);			
				if(p.pobierzCzas()<=1){
					kolejka.remove(p);
					zmianaKontekstu();
				}
				
				if(czasZmiana>0){
					czasZmiana--;
				}else{
					p.zmniejszCzas(1);
				}
			}
	}	
}

