/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Usuario
 */
public class P2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
     
          File fichero=new File("./src/P4/Productos.dat");         
         
          int codigo[]={11,12,13,14,15,16,17,18};
          String producto[]={"PC","MONITOR","TECLADO","RATÓN","PENDRIVE","CABLEAVG","CABLEHDMI","PROYECTOR"};
          double precio[]={1022.57,50.15,10.22,2.75,30.20,5.30,10.22,1002.87};
          
                  
          EscribirFichero(fichero,codigo,producto,precio);
          LeerFichero(fichero,producto);
    }
    public static void EscribirFichero(File fichero,int[] codigo,String[] producto,double[] precio) throws FileNotFoundException, IOException{
                DataOutputStream dataOS = new DataOutputStream(new FileOutputStream(fichero));
                StringBuffer buffer=new StringBuffer();
                int r;
                for(int i=0;i<codigo.length;i++)
                {
                    r=10-producto[i].length();
                       buffer.setLength(r);
                     
                    buffer.append(producto[i]);                    
                    dataOS.writeInt(codigo[i]);
                    dataOS.writeChars(buffer.toString());
                    dataOS.writeDouble(precio[i]);
                    System.out.println("CAD "+buffer.length());
                    
                }
                dataOS.close();
    }
    public static void LeerFichero(File fichero,String[] producto) throws FileNotFoundException, IOException{
        DataInputStream dataIS = new DataInputStream(new FileInputStream(fichero));
        char n;
        char[] cad=new char[10];
        int e;
        double a;
        
        System.out.println("Código \t Producto \t Precio");
        try{
            while(true){
              
                e=dataIS.readInt();
                
                for(int i=0;i<cad.length;i++){
                    n=dataIS.readChar(); 
                     cad[i]=n;
                }
                
                String cadena = new String(cad);
              
                a=dataIS.readDouble();
                
                System.out.println(e+"\t "+cadena+"\t "+a+"\n");
                
            }
        }catch(EOFException eo){}
        
        
        dataIS.close();
    }
}
