public class InternalArtikel {
    private String oddelek;
    private String id;
    private String kolicina;
    private double cena;

    public InternalArtikel(String barcode){
        oddelek = barcode.substring(0,3);
        id = barcode.substring(3,7);
        kolicina = barcode.substring(7,12);

        if(id.contains("8795")){
            cena = 2.10;
        }else if(id.contains("8892")){
            cena = 1.12;
        }else if(id.contains("8095")){
            cena = 0.70;
        }
    }

    public static String checkDigit(String id,String kolicina,double cena){

        String barcode = id + kolicina + cena;
        System.out.println(barcode);

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

    @Override
    public String toString() {
        return "\n" + oddelek+id+kolicina;
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

    public String getKolicina() {
        return kolicina;
    }

    public void setKolicina(String kolicina) {
        this.kolicina = kolicina;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }
}
