
import java.util.ArrayList;

public class InternalArtikli {

    public ArrayList<InternalArtikel> seznam = new ArrayList<>();

    public int getSize(){return seznam.size();}
    public ArrayList<InternalArtikel> getSeznam(){return seznam;}

    public void setSeznam(ArrayList<InternalArtikel> s) {
        this.seznam = s;
    }
    public void izbrisiArt(InternalArtikel a){seznam.remove(a);}

    @Override
    public String toString() {
        return seznam.toString();
    }
}
