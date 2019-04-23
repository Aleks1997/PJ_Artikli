
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Račun implements Searchable{
    private String id;
    private Date datum;
    private String davcnaStPodjetja;
    private Podjetje izdajatelj;
    transient private Artikli artikli;
    private String skupnaCena;
    private String skupnaCenaDDV;

    transient DecimalFormat numberFormat = new DecimalFormat("#.00");

    public String toString() {
        String out;
        out = izdajatelj.toString() + artikli.toString() ;
        return out;
    }

    public Račun(){
        String UID = UUID.randomUUID().toString();
        id = UID;
        System.out.println(UID);
        DateFormat dF = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        datum = new Date();
        izdajatelj = getIzdajatelj();
        davcnaStPodjetja = "10023291";
        System.out.println(dF.format(datum));
    }

    public String izracunaj(ArrayList<Artikel> b){
        double cena = 0;
        for(int i = 0; i <  b.size(); i++){
            String teza = b.get(i).getTeza();

            if(teza.equals("0001")){
                cena += b.get(i).getCena();
            }else{
            cena +=  b.get(i).getCena() * (Double.parseDouble(teza)/1000);
            }
        }
        return numberFormat.format(cena);
    }
    public String izracunajDDV(ArrayList<Artikel> b){
        double cena = 0;
        for(int i = 0; i <  b.size(); i++){
            String teza = b.get(i).getTeza();
            if(teza.equals("0001")){
                cena += b.get(i).getCenaDDV();
            }else{
            cena +=  b.get(i).getCenaDDV() * (Double.parseDouble(teza)/1000);
            }
        }
        return numberFormat.format(cena);
    }

    @Override
    public boolean search(String s) {
        if( s.equals(String.valueOf(getId())) || s.equals(String.valueOf(getDatum()))
        || s.equals(String.valueOf(getDavcnaStPodjetja()))){
            return true;
        }
        return false;
    }

    public void checkBarcode(String s,Artikli artikli){
        String check = s.substring(0,2);

        if(check.contains("99")){
            String datum = s.substring(2,6);
            try {
                if (new SimpleDateFormat("MMyy").parse(datum).after(new Date())) {
                    String popust = s.substring(6,8);
                    double pop = Double.parseDouble(popust);
                    pop = 1 - (pop / 100);
                    String tmp = izracunajDDV(artikli.seznam);
                    double cena = Double.parseDouble(tmp.replace(",","."));
                    double cenaPop = cena * pop;

                    System.out.println("Skupna cena s popustom je " + numberFormat.format(cenaPop) + "€. ");

                    //POPUST EAN KODA IMA 8 mest 99datumPopust 99051915
                }else{
                    System.out.println("Potekel je rok popusta");
                }
            }catch (java.text.ParseException e) {
                e.printStackTrace();
            }
        }else if(check.length() == 13){
            String id = s.substring(0,4);
            String oddelek = s.substring(4,8);
            String teza = s.substring(8,12);

            Artikel artikel = new Artikel(id,oddelek,teza);

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
}
