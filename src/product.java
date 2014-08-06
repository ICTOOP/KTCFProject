import java.io.*;
public class product {
  String id;
  String name;
  float price;
  int rec;
  void addData(String fn, String id,String name, float price) {
    File fname = new File(fn);
    FileOutputStream f_out = null;
    BufferedOutputStream b_out = null;
    DataOutputStream d_out = null;
    try {
      f_out = new FileOutputStream(fname,true);
      b_out = new BufferedOutputStream(f_out);
      d_out = new DataOutputStream(b_out);
      d_out.writeUTF(id);
      d_out.writeUTF(name);
      d_out.writeFloat(price);
    }
    catch (Exception e) {
      System.out.println(e);
    }
    finally {
      try {
        if (d_out != null)
          d_out.close();
      }
      catch (IOException e){
        System.out.println(e);
      }
    }
  }
  product[] readdata(String fn) {
    File ifile = new File(fn);
    FileInputStream f_in = null;
    BufferedInputStream b_in = null;
    DataInputStream d_in = null;
    boolean eof = false;
    product[] pr = new product[50];
    try {
      f_in = new FileInputStream(ifile);
      b_in = new BufferedInputStream(f_in);
      d_in = new DataInputStream(b_in);
      rec = 0;
      while (!eof) {
        pr[rec] = new product();
        pr[rec].id = d_in.readUTF();
        pr[rec].name = d_in.readUTF();
        pr[rec].price = d_in.readFloat();
        rec++;
      }
    }
    catch (EOFException e){
      eof = true;
    }
    catch (FileNotFoundException e){
      return null;
    }
    catch (IOException e){
      return null;
    }
    finally {
      try {
        if (d_in != null)
          d_in.close();
      }
      catch (IOException e){
        System.out.println(e);
      }
    }
    return pr;
  }
}