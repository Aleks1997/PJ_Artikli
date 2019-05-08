
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Helper{

    public static void writeToFile(String fileName,String s){
        try(FileWriter writer = new FileWriter(fileName)){
            writer.write(s);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static String readFromFile(String fileName){
        String text = null;

        File file = new File(fileName);
        try{
            FileReader reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            text = new String(chars);
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return text;
    }
}
