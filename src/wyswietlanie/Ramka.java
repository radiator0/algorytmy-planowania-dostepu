package wyswietlanie;

import algorytm.Menadzer;

import javax.swing.*;
import javax.swing.JOptionPane;

public class Ramka extends JFrame {
	/** Czas jaki jest potrzebny na zmiane kontekstu w ms*/
	private final int interwalZmianyKontekstu;
	/** Długość kwantu czasu (do algorytmu rotacyjnego) */
	private final int kwant;
	/** Ilość procesów jaka bedzie użyta w symulacji */
	private final int iloscProcesow;
	/** Maksymalny czas trwania procesu w symulacji w ms */
	private final int maksymalnyCzasProcesu;
	/** Szansa na zgloszenie się procesu w danym cyklu (w promilach, 10 = 1procent) */
	private final int szansaNaZgloszenie;
	/** Szansa na to że zgłoszony proces nie będzie dłuższy od średniej długości (w procentach) */
	private final int szansaKrotkiProces;

	/** Czas w jakim wyswietlany jest jeden cykl na ekranie w ms*/
	private final int interwalOdswiezania;
	
	/** Deklaracja menadżera */
	private Menadzer m;
	
	/** Deklaracja panelu */
	private JPanel panel;
	
	public Ramka() {
		super("Algorytmy planowania dostępu");
		interwalZmianyKontekstu = Integer.parseInt(JOptionPane.showInputDialog(null,"Podaj interwał zmiany kontekstu (ms):",1));
		kwant = Integer.parseInt(JOptionPane.showInputDialog(null,"Podaj kwant czasu:",10));
		iloscProcesow = Integer.parseInt(JOptionPane.showInputDialog(null,"Podaj ilość procesów jaka ma być użyta w teście:",20));
		maksymalnyCzasProcesu = Integer.parseInt(JOptionPane.showInputDialog(null,"Podaj maksymalny czas procesu (ms):",170));
		szansaNaZgloszenie = Integer.parseInt(JOptionPane.showInputDialog(null,"Podaj szanse na zgłoszenie sie procesu w danym cyklu (1-1000):",21));
		szansaKrotkiProces = Integer.parseInt(JOptionPane.showInputDialog(null,"Podaj szanse na to że proces będzie krótki (%):",98));
		interwalOdswiezania = Integer.parseInt(JOptionPane.showInputDialog(null,"Podaj interwał czasu w jakim ma się odświeżać ekran (ms):",40));
		
		m = new Menadzer(interwalZmianyKontekstu, kwant, szansaNaZgloszenie);
		panel = new Panel(m);
		add(panel);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);		
	}

	/**
	 * Metoda pozwala na odswieżenie ekranu
	 */
	public void odswiez(){
		panel.repaint();
		panel.revalidate();
	}

	public Menadzer pobierzMenadzer(){
		return m;
	}

	public int pobierzInterwalZmianyKontekstu() {
		return interwalZmianyKontekstu;
	}

	public int pobierzKwant() {
		return kwant;
	}

	public int pobierzIloscProcesow() {
		return iloscProcesow;
	}

	public int pobierzMaksymalnyCzasProcesu() {
		return maksymalnyCzasProcesu;
	}

	public int pobierzSzansaNaZgloszenie() {
		return szansaNaZgloszenie;
	}

	public int pobierzSzansaKrotkiProces() {
		return szansaKrotkiProces;
	}

	public int pobierzInterwalOdswiezania() {
		return interwalOdswiezania;
	}

	private static final long serialVersionUID = -2122161377842820073L;
}