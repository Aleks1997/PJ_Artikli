import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Račun extends Artikli implements Searchable{
    private String id;
    private Date datum;
    private String izdajatelj;
    private int davcnaStPodjetja;


    @Override
    public String toString() {
        return seznam.toString() ;
    }

    public Račun(){
        String UID = UUID.randomUUID().toString();
        System.out.println(UID);
        DateFormat dF = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        datum = new Date();
        System.out.println(dF.format(datum));

    }

    public double izracunaj(ArrayList<Artikel> b){
        double cena = 0;

        for(int i = 0; i <  b.size(); i++){
            cena +=  b.get(i).getCena() * b.get(i).getKolicina();
        }
        return cena;
    }
    public double izracunajDDV(ArrayList<Artikel> b){
        double cena = 0;

        for(int i = 0; i <  b.size(); i++){
            cena +=  b.get(i).getCenaDDV() * b.get(i).getKolicina();
        }
        return cena;
    }

    @Override
    public boolean search(String s) {
        if(s.equals(izdajatelj) || s.equals(String.valueOf(getId())) || s.equals(String.valueOf(getDatum()))
        || s.equals(String.valueOf(getDavcnaStPodjetja()))){
            return true;
        }
        return false;
    }

    public String getId() {
        return id;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getIzdajatelj() {
        return izdajatelj;
    }

    public void setIzdajatelj(String izdajatelj) {
        this.izdajatelj = izdajatelj;
    }

    public int getDavcnaStPodjetja() {
        return davcnaStPodjetja;
    }

    public void setDavcnaStPodjetja(int davcnaStPodjetja) {
        this.davcnaStPodjetja = davcnaStPodjetja;
    }
}
