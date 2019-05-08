import com.google.gson.Gson;
import java.util.ArrayList;

public class Companies implements JsonSupport {
    public ArrayList<Podjetje> seznam = new ArrayList<>();

    public ArrayList<Podjetje> getSeznam() {
        return seznam;
    }
    public void setSeznam(ArrayList<Podjetje> s) {
        this.seznam = s;
    }
    public int getSize() {
        return seznam.size();
    }

    public void izbrisiPod(Podjetje a) {
        seznam.remove(a);
    }

    public Companies() {
        getSeznam();
    }

    @Override
    public String toJson(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        System.out.println(json);

        Helper.writeToFile("E:\\Faks\\Companies.json",json);
        return json;
    }

    @Override
    public void fromJson(String s){
        Gson gson = new Gson();
        String reader = Helper.readFromFile(s);

        Companies companies = gson.fromJson(reader,Companies.class);
        System.out.println(companies);
    }
    @Override
    public String toString() {
        return seznam.toString();
    }
}

