
import com.google.gson.Gson;
import java.util.ArrayList;

public class Artikli implements JsonSupport{

    public ArrayList<Artikel> seznam = new ArrayList<>();

    public int getSize(){return seznam.size();}
    public ArrayList<Artikel> getSeznam(){return seznam;}

    public void setSeznam(ArrayList<Artikel> s) {
        this.seznam = s;
    }
    public void izbrisiArt(Artikel a){seznam.remove(a);}

    @Override
    public String toJson(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        System.out.println(json);

        Helper.writeToFile("E:\\Faks\\Artikli.json",json);
        return json;
    }
    @Override
    public void fromJson(String s){
        Gson gson = new Gson();
        String reader = Helper.readFromFile(s);

        Artikli artikli = gson.fromJson(reader,Artikli.class);
        System.out.println(artikli);
    }
    @Override
    public String toString() {
        return seznam.toString();
    }
}
