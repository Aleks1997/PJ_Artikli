

public class Podjetje implements Searchable{
    private String ime;
    private int davcnaSt;
    private long maticnaSt;
    private boolean davcniZavezanec;
    private String naslov;
    private String tel;

    public Podjetje(String i,int d,long m,boolean dZ,String t,String n) {
        ime = i;
        davcnaSt = d;
        maticnaSt = m;
        davcniZavezanec = dZ;
        naslov = n;
        tel = t;
    }

    @Override
    public boolean search(String s) {
        if( s.contains(ime) || s.equals(naslov) || s.equals(tel) || s.equals(String.valueOf(getDavcnaSt()))
        || s.equals(String.valueOf(getMaticnaSt()))){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Ime:" + ime + ", " + naslov + "\n Davcna St.: "
                + davcnaSt + ", Maticna St.: " + maticnaSt +
                ", Tel: " + tel + "\n Ali je davcni zavezanec: " + davcniZavezanec + "\n";
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public int getDavcnaSt() {
        return davcnaSt;
    }

    public void setDavcnaSt(int davcnaSt) {
        this.davcnaSt = davcnaSt;
    }

    public long getMaticnaSt() {
        return maticnaSt;
    }

    public void setMaticnaSt(long maticnaSt) {
        this.maticnaSt = maticnaSt;
    }

    public boolean isDavcniZavezanec() {
        return davcniZavezanec;
    }

    public void setDavcniZavezanec(boolean davcniZavezanec) {
        this.davcniZavezanec = davcniZavezanec;
    }
}
