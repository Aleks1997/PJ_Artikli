
import com.google.gson.Gson;
import java.util.ArrayList;


public class Invoices implements JsonSupport{


    public ArrayList<Račun> seznam = new ArrayList<>();

    public ArrayList<Račun> getSeznam(){
        return seznam;
    }
    public void setSeznam(ArrayList<Račun> s) {
        this.seznam = s;
    }
    public int getSize(){return seznam.size();}
    public void izbrisiRac(Račun a){seznam.remove(a);}

    public Invoices() {
        getSeznam();
    }

    @Override
    public String toJson(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        System.out.println(json);

        Helper.writeToFile("E:\\Faks\\Invoices.json",json);
        return json;
    }

    @Override
    public void fromJson(String s){
        Gson gson = new Gson();
        String reader = Helper.readFromFile(s);

        Invoices invoices = gson.fromJson(reader,Invoices.class);
        System.out.println(invoices);
    }
    @Override
    public String toString() {
        return seznam.toString();
    }
}

