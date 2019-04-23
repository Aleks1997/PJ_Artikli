

public class Artikel implements Searchable {
    private String oddelek; //4 stevila rezervirana
    private String id; //4 stevila rezervirana
    private double cena;
    private String teza; //4 stevila rezervirana zadnjo z check digit
    private double  davcnaStopnja;
    private String drzava;
    private String koda;

    public Artikel(String i,String o,double  c,double  ds,String d,String t){
        oddelek = o;//.....
        id = i;//.......
        cena = c;//ID
        teza = t;//.....
        davcnaStopnja = ds;// ID
        drzava = d;//ID
        koda = checkDigit(t,i,o);//...
    }
    public Artikel(String i,String o,String t){
        id = i;
        oddelek = o;
        teza = t;
    }

    @Override
    public boolean search(String s) {
        if(s.contains(id) || s.contains(String.valueOf(getCena())) ||
        s.contains(String.valueOf(getTeza())) || s.contains(String.valueOf(getDavcnaStopnja()))
        || s.contains(String.valueOf(getDrzava()))){
            return true;
        }
        return false;
    }
    @Override
    public String toString() {
        return "\n" +  oddelek + ", "+ id + ", " + cena + "€ ,Teza: " + teza + " Drzava Porekla: " + drzava +
                " DDV: " + davcnaStopnja + "% " + koda;
    }

    public static String checkDigit(String t,String id,String oddelek){
        String barcode = t+id+oddelek;
        long[] sum = new long[barcode.length() +2];
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

        sum[13] = vsota;

        barcode = barcode + sum[13];

        return barcode;
    }

    public double getCenaDDV(){
        double sum = cena * (1 + davcnaStopnja);
        return sum;
    }

    public double  getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
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

    public String getOddelek() {
        return oddelek;
    }

    public void setOddelek(String oddelek) {
        this.oddelek = oddelek;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeza() {
        return teza;
    }

    public void setTeza(String  teza) {
        this.teza = teza;
    }

    public String getKoda() {
        return koda;
    }

    public void setKoda(String oddelek,String id,String teza) {
        this.koda = oddelek + id + teza;
    }
}
/*
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
        */
