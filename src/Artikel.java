import com.keepautomation.barcode.BarCode;

public class Artikel implements Searchable {
    private String ime;
    private double cena;
    private int kolicina;
    private String lastnost;
    private double  davcnaStopnja;
    private String drzava;

    public Artikel(String i,double  c,String l,double  ds,String d,int k){
        ime = i;
        cena = c;
        lastnost = l;
        kolicina = k;
        davcnaStopnja = ds;
        drzava = d;
    }

    @Override
    public boolean search(String s) {
        if(s == ime || s == lastnost || s == String.valueOf(getCena()) ||
        s == String.valueOf(getKolicina()) || s == String.valueOf(getDavcnaStopnja())
        || s == String.valueOf(getDrzava())){
            return true;
        }
        return false;
    }
    @Override
    public String toString() {
        return "\n" +  ime + ", " + lastnost+ ", "  + cena + "€ ,Kolicina: " + kolicina + " Drzava Porekla: " + drzava +
                " DDV: " + davcnaStopnja + "% ";
    }

    public static boolean checkDigit(BarCode bc){
        String barcode = bc.getCodeToEncode();
        long[] sum = new long[barcode.length()];
        long vsota = 0;
        for(int i = 0; i<barcode.length();i++){
            sum[i] = barcode.charAt(i) - '0';
        }

        for(int i = 0;i<12;i++){
            if((i%2)==0){
                sum[i] = sum[i];
            }else{
                sum[i] = sum[i] * 3;
            }
        }
        for(int i = 0;i<12;i++){
            vsota = vsota + sum[i];
        }
        while(vsota > 0){
            vsota = vsota - 10;
        }
        vsota = Math.abs(vsota);

        if(sum[12] == vsota){
            return true;
        }else{
            return false;
        }
    }

    public double getCenaDDV(){
        return cena = cena * (1 + davcnaStopnja);
    }

    public String getLastnost() {
        return lastnost;
    }

    public void setLastnost(String lastnost) {
        this.lastnost = lastnost;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public double  getCena() {
        return cena;
    }

    public void setCena(double  cena) {
        this.cena = cena;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getDavcnaStopnja() {
        return davcnaStopnja;
    }

    public void setDavcnaStopnja(double  davcnaStopnja) {
        this.davcnaStopnja = davcnaStopnja;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

}
