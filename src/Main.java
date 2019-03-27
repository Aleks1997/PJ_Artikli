import com.keepautomation.barcode.BarCode;
import com.keepautomation.barcode.IBarCode;
import java.util.ArrayList;

public class Main
{

    public static void main(String [ ] args)
    {
        ArrayList<Račun> računi = new ArrayList<Račun>();

        Podjetje pod = new Podjetje("Henkel Maribor d.o.o.",58665765,6261752000L,true,"02 2222100", "Industrijska ulica 23, Maribor, 2000 Maribor");
        System.out.println(pod.toString());

        BarCode bc = new BarCode();
        bc.setCodeToEncode("6291041500213");
        bc.setSymbology(IBarCode.EAN13);
        bc.setChecksumEnabled(false);
        bc.setFnc1(IBarCode.FNC1_NONE);
        BarCode bc1 = new BarCode();
        bc1.setCodeToEncode("6291041500214");


        Artikel A1 = new Artikel("pivo",0.80,"Lasko",0.22,"Slovenia",7);
        Artikel A2 = new Artikel("kruh",1.20,"koruzni",0.095,"Slovenia",1);
        Artikel A3 = new Artikel("salama",5.00,"domaca",0.095,"Slovenia",1);

        Račun r = new Račun();
        računi.add(r);
        r.seznam.add(A1);
        r.seznam.add(A2);
        r.seznam.add(A3);
        System.out.println(r);
        System.out.println("Skupna cena brez DDV" + " " + r.izracunaj(r.seznam)+ "€." + "\n");
        System.out.println("Skupna cena z DDV" + " " + r.izracunajDDV(r.seznam)+ "€." + "\n");

        System.out.println("Ali je crtna koda veljavna " + Artikel.checkDigit(bc));
        System.out.println("Ali je crtna koda veljavna " + Artikel.checkDigit(bc1));

        System.out.println(pod.search("58665765"));
    }

}
