
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

 
 class TXT <T> {
     
    private final String  path;
    
    //constructure
    public TXT(String  path) {
        this.path=path;
    }
    
     
     
     
    /**
     * this method deserialize an object txt file
     * @return a list o custom object
     */
    public List<T> deserialize()    {
        List<T>  liste=null;
        try {
            File file=new File(this.path);
            ObjectInputStream oiS;
            try (InputStream inS = new FileInputStream(file)) {
                oiS = new ObjectInputStream(inS);
                liste=(List<T>) oiS.readObject();
            }
            oiS.close();
        } catch (FileNotFoundException | ClassNotFoundException e) {
            errMSG(e);
        } catch(IOException e){
            errMSG(e);
        }
        return liste;
    }
    
    
    
    
     
    
    public boolean serialize(List<T> list) {
        try {
            File file = new File(this.path);
            ObjectOutputStream ooS;
            try (FileOutputStream foS = new FileOutputStream(file)) {
                ooS = new ObjectOutputStream(foS);
                ooS.writeObject(list);
            }
            ooS.close();
        } catch (FileNotFoundException e) {
            errMSG(e);
        } catch (IOException e){
            errMSG(e);
        }
        return true;
    }
    

    
    
    private void errMSG(Exception e) {
        MSG.show("An error occured\nDetails:" + e.getMessage());
    }
 
}


class Test{
     public static void main(String[] args) {
        List<MyObject> liste=new ArrayList<>();
         liste.add(new MyObject(2,987));
         liste.add(new MyObject(234,2232));
         liste.add(new MyObject(24,2));
         liste.add(new MyObject(8988,23));
         
         //Test
         TXT<MyObject> txt=new TXT<>("client_id.txt");
         System.out.println(txt.serialize(liste));
         System.out.println(txt.deserialize());
    }
}



class MyObject implements Serializable{
    private static final long serialVersionUID = 1L;
    int a;
    int b;
    public MyObject(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return "["+a + ", " + b +"] ";
    }
}