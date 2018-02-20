import algorytm.AlgorytmPlanowania;
import algorytm.Menadzer;
import wyswietlanie.Ramka;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.nio.charset.Charset;

public class Main {

    public static void main(String[] args) {
        System.setProperty("file.encoding","UTF-8");
        Field charset = null;
        try {
            charset = Charset.class.getDeclaredField("defaultCharset");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        charset.setAccessible(true);
        try {
            charset.set(null,null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Ramka f = new Ramka();
                Menadzer menadzer = f.pobierzMenadzer();
                menadzer.generujProcesy(f.pobierzIloscProcesow(), f.pobierzMaksymalnyCzasProcesu(), f.pobierzSzansaKrotkiProces());
                final Timer timer = new Timer(f.pobierzInterwalOdswiezania(), new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent e) {
                        f.odswiez();
                        if(!menadzer.krok() && menadzer.iloscAktywnychProcesow()==0){
                            ((Timer)e.getSource()).stop();
                            f.setVisible(false);
                            System.out.println("*********************");
                            System.out.println("ZAKOÑCZONO SYMULACJE");

                            java.util.List<AlgorytmPlanowania> algorytmy = menadzer.pobierzAlgorytmy();
                            String wyniki = "Œrednie czasy : \n";
                            for(AlgorytmPlanowania alg : algorytmy){
                                wyniki += alg.pobierzEtykiete() + " | " + String.format("%5.2f%2s", alg.sredniCzasOczekiwania(),"ms");
                                wyniki += "\n";
                            }
                            System.out.print(wyniki);
                            JOptionPane.showMessageDialog(null, wyniki, "Symulacja zakoñczona", JOptionPane.INFORMATION_MESSAGE);
                            f.dispose();
                        }
                    }
                });
                timer.start();
            }
        });
    }
}
