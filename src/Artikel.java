

public class Artikel implements Searchable {
    private String ime;
    private double cena;
    private String teza;
    private double  davcnaStopnja;
    private int kolicina;
    private String bc;

    public Artikel(String i,String t,double c,double ds,int k,String b){
        ime = i;
        cena = c;
        teza = t;
        davcnaStopnja = ds;
        kolicina = k;
        bc = b;
    }


    @Override
    public boolean search(String s) {
        if(s.contains(ime) || s.contains(String.valueOf(getCena())) ||
        s.contains(String.valueOf(getTeza())) || s.contains(String.valueOf(getDavcnaStopnja()))){
            return true;
        }
        return false;
    }
    @Override
    public String toString() {
        return "\n" + ime + ", " + cena + "€ ,Teza: " + teza +
                " DDV: " + davcnaStopnja + "% " + bc;
    }

    public static boolean checkDigit(String barcode) {

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

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getTeza() {
        return teza;
    }

    public void setTeza(String teza) {
        this.teza = teza;
    }

    public double getDavcnaStopnja() {
        return davcnaStopnja;
    }

    public void setDavcnaStopnja(double davcnaStopnja) {
        this.davcnaStopnja = davcnaStopnja;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public String getBc() {
        return bc;
    }

    public void setBc(String bc) {
        this.bc = bc;
    }
}
