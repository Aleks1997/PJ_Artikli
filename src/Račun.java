
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import static java.lang.Math.round;

public class Račun implements Searchable{
    private String id;
    private Date datum;
    private String davcnaStPodjetja;
    private Podjetje izdajatelj;
    transient private Artikli artikli;
    transient private InternalArtikli internalArtikli;
    private String skupnaCena;
    private String skupnaCenaDDV;

    transient private DecimalFormat numberFormat = new DecimalFormat("#.00");

    public String toString() {
        String out;
        out = id + izdajatelj.toString() + artikli.toString() +"\n"+ internalArtikli.toString() ;
        return out;
    }

    public Račun(){
        String UID = UUID.randomUUID().toString();
        id = UID;
        //System.out.println(UID);
        DateFormat dF = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        datum = new Date();
        izdajatelj = getIzdajatelj();
        davcnaStPodjetja = "10023291";
        System.out.println(dF.format(datum));
    }

    public double izracunaj(ArrayList<Artikel> b,ArrayList<InternalArtikel> bi){
        double cena = 0;
        for(int i = 0; i <  b.size(); i++){
            String teza = b.get(i).getTeza();

            if(teza.equals("0001")){
                cena += b.get(i).getCena();
            }else{
            cena +=  b.get(i).getCena() * (Double.parseDouble(teza)/1000);
            }
        }
        for(int i = 0; i <  bi.size(); i++){
            String teza = bi.get(i).getKolicina();

            if(teza.equals("0001")){
                cena += bi.get(i).getCena();
            }else{
                cena += bi.get(i).getCena() * (Double.parseDouble(teza)/1000);
            }
        }
        return round(cena);
    }
    public double izracunajDDV(ArrayList<Artikel> b,ArrayList<InternalArtikel> bi){
        double cena = 0;
        for(int i = 0; i <  b.size(); i++){
            String teza = b.get(i).getTeza();

            if(teza.equals("0001")){
                cena += b.get(i).getCena() * (b.get(i).getDavcnaStopnja() + 1);
            }else{
            cena +=  b.get(i).getCena() * (b.get(i).getDavcnaStopnja() + 1) * (Double.parseDouble(teza)/1000);
            }

        }
        for(int i = 0; i < bi.size();i++){
            String teza = bi.get(i).getKolicina();

            if(teza.equals("0001")){
                cena += bi.get(i).getCena() * ( 0.095 + 1);
            }else{
                cena +=  bi.get(i).getCena() * ( 0.095 + 1) * (Double.parseDouble(teza)/1000);
            }

        }
        return round(cena);
    }

    public double izracunajInternalDDV(ArrayList<InternalArtikel> b){
        Double cena = 0.0;
        for(int i = 0; i <  b.size(); i++){
            String teza = b.get(i).getKolicina();

            if(teza.equals("00001")){
                cena += b.get(i).getCena() * (0.095 + 1);
            }else{
                cena += b.get(i).getCena() * (0.095 + 1) * (Double.parseDouble(teza)/1000);
            }
        }
        return round(cena);
    }

    @Override
    public boolean search(String s) {
        if( s.equals(String.valueOf(getId())) || s.equals(String.valueOf(getDatum()))
        || s.equals(String.valueOf(getDavcnaStPodjetja()))){
            return true;
        }
        return false;
    }

    public void checkBarcode(String s,Artikli artikli,InternalArtikli internal){
        String check = s.substring(0,2);

        if(check.contains("99")){
            String datum = s.substring(2,6);
            try {
                if (new SimpleDateFormat("MMyy").parse(datum).after(new Date())) {
                    String popust = s.substring(6,8);
                    double pop = Double.parseDouble(popust);
                    pop = 1 - (pop / 100);
                    Double tmp = izracunajDDV(artikli.seznam,internal.seznam);
                    String tmp2 = String.valueOf(tmp);
                    double cena = Double.parseDouble(tmp2.replace(",","."));
                    double cenaPop = cena * pop;

                    System.out.println("Skupna cena s popustom je " + numberFormat.format(cenaPop) + "€. ");

                    //POPUST EAN KODA IMA 8 mest 99datumPopust 99051915
                }else{
                    System.out.println("Potekel je rok popusta");
                }
            }catch (java.text.ParseException e) {
                e.printStackTrace();
            }
        }else if(check.contains("2")){
            checkDigit(s);

            InternalArtikel artikel = new InternalArtikel(s);
            internalArtikli.seznam.add(artikel);

        }else{
            System.out.println("Napacna ean koda");
        }
    }

    public String getSkupnaCena() {
        return skupnaCena;
    }

    public void setSkupnaCena(String skupnaCena) {
        this.skupnaCena = skupnaCena;
    }

    public String getSkupnaCenaDDV() {
        return skupnaCenaDDV;
    }

    public void setSkupnaCenaDDV(String skupnaCenaDDV) {
        this.skupnaCenaDDV = skupnaCenaDDV;
    }

    public String getId() {
        return id;
    }

    public Date getDatum() {
        return datum;
    }

    public Artikli getSeznam() {
        return artikli;
    }

    public void setSeznam(Artikli seznam) {
        this.artikli = seznam;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Podjetje getIzdajatelj() {
        return izdajatelj;
    }

    public void setIzdajatelj(Podjetje izdajatelj) {
        this.izdajatelj = izdajatelj;
    }

    public String getDavcnaStPodjetja() {
        return davcnaStPodjetja;
    }

    public void setDavcnaStPodjetja(String davcnaStPodjetja) {
        this.davcnaStPodjetja = davcnaStPodjetja;
    }
    public boolean checkDigit(String barcode) {

        long[] sum = new long[barcode.length()];
        long vsota = 0;
        for (int i = 0; i < barcode.length(); i++) {
            sum[i] = barcode.charAt(i) - '0';
        }

        for (int i = 0; i < 12; i++) {
            if ((i % 2) == 0) {
                sum[i] = sum[i];
            } else {
                sum[i] = sum[i] * 3;
            }
        }
        for (int i = 0; i < 12; i++) {
            vsota = vsota + sum[i];
        }
        while (vsota > 0) {
            vsota = vsota - 10;
        }
        vsota = Math.abs(vsota);

        if (sum[12] == vsota) {
            return true;
        } else {
            return false;
        }
    }

    public InternalArtikli getInternalArtikli() {
        return internalArtikli;
    }

    public void setInternalArtikli(InternalArtikli internalArtikli) {
        this.internalArtikli = internalArtikli;
    }
}

