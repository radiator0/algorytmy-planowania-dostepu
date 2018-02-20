package algorytm;

public class SJFw extends  AlgorytmPlanowania{
	
	public SJFw(String etykieta, int interwalZmiany){
		super(etykieta, interwalZmiany);
	}

	public void dodaj(Proces p){
		int index = -1;
		if(kolejka.size()>=1){
			for(Proces p2 : kolejka){
				if(p.pobierzCzas()<p2.pobierzCzas()){
					index = kolejka.indexOf(p2);
					break;
				}else if(p.pobierzCzas()==p2.pobierzCzas()){
					index = kolejka.indexOf(p2)+1;
					break;
				}
			}
		}
		if(index<0){
			kolejka.add(p);
		}else{
			kolejka.add(index,p);
		}	
		ustawCzasOczekiwania(p);
		
		// podliczenie od nowa czasu oczekiwania
		if(index != -1){
			for(int i=index; i<kolejka.size(); i++){
				ustawCzasOczekiwania(kolejka.get(i));
			}
		}
		
		System.out.print(etykieta+" |Procesow w kolejce: " + kolejka.size());
		System.out.print(" Czas obecny oczekiwania: " + String.format("%5d%2s", p.pobierzCzasOczekiwania(), "ms") + "\n");
	}
	
	public void krok(){	
			// Jeœli kolejka ma jakis element to pobiera pierwszy;
			if(kolejka.size()>0){
				Proces p = kolejka.get(0);
				
				if(p.pobierzCzas()<=1){
					kolejka.remove(p);
					zmianaKontekstu();
					// tu jest zmiana kontekstu
				}
				if(czasZmiana>0){
					czasZmiana--;
				}else{
					p.zmniejszCzas(1);
				}
			}

	}	
	


}

