package wyswietlanie;

import algorytm.AlgorytmPlanowania;
import algorytm.Proces;
import algorytm.Menadzer;

import java.awt.*;
import javax.swing.JPanel;
import java.util.List;

public class Panel extends JPanel {
	private static final long serialVersionUID = -8378771651986102384L;
	Menadzer m;

	public Panel(Menadzer m) {
		this.m = m;
		setPreferredSize(new Dimension(1200, 310));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
			
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Dimension arcs = new Dimension(20,20);
		
		g2d.drawString("Copyright " + "\u00a9" + " 2017 Krzysztof Pilarczyk. All rights reserved.", 20, 304);
		List<AlgorytmPlanowania> algorytmy = m.pobierzAlgorytmy();
		int odstep = 0;
		for(AlgorytmPlanowania alg : algorytmy){
			int czasRazem = 0;
			List<Proces> kolejka = m.pobierzKolejke(alg);
			
			g2d.setColor(Color.BLUE);
			g2d.drawString(alg.pobierzEtykiete(), 20, 20+odstep);
			g2d.drawString(String.format("%29s%5.2f%2s", "Sredni czas oczekiwania: ",alg.sredniCzasOczekiwania(),"ms"), 150, 20+odstep);
			g2d.drawString("Procesow w kolejce: "+kolejka.size(), 400, 20+odstep);
			
			if(kolejka.size()>0){
				kolejka.get(0).ustawColor(Color.RED);
			}			
			for(Proces p : kolejka){
				int czas = p.pobierzCzas();
				//Rysowanie prostokata
				g2d.setColor(p.pobierzColor());
				if(kolejka.indexOf(p)==0){
					g2d.setColor(Color.ORANGE);
				}
				g2d.fillRoundRect(czasRazem+20, 25+odstep, czas, 50, arcs.width, arcs.height);
				//Rysowanie ramki
				g2d.setColor(new Color(128,156,7));
				if(p.pobierzColor() == Color.RED){
					g2d.setColor(new Color(204,0,0));
				}
				g2d.drawRoundRect(czasRazem+20, 25+odstep, czas, 50, arcs.width, arcs.height);
				czasRazem += czas;
			}
			odstep += 70;
		}
	}
}