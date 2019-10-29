/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p4;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 *
 * @author Usuario
 */
public class P4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, TransformerConfigurationException, TransformerException, ParserConfigurationException {
      
        File fichero = new File("./src/P4/Productos.dat");   
    RandomAccessFile file = new RandomAccessFile(fichero, "r");
      
   
   int  codigo;
   int posicion=0; //para situarnos al principio del fichero        
   Double precio;
   char producto[] = new char[10];
   char aux;
   String productos ;
   
     
   DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
  
   try{
     DocumentBuilder builder = factory.newDocumentBuilder();
     DOMImplementation implementation = builder.getDOMImplementation();
     Document document =implementation.createDocument(null, "Empleados", null);
     document.setXmlVersion("1.0"); 
   
     for(;;) {
	 file.seek(posicion); //nos posicionamos 
	 codigo=file.readInt();   // obtengo id de empleado	  	  
       for (int i = 0; i < producto.length; i++) {
         aux = file.readChar();
         producto[i] = aux;    
       }   
       productos = new String(producto);
       precio= file.readDouble();  
	 if(codigo>0) 
         { //codigo validos a partir de 1
	   Element raiz = document.createElement("empleado"); //nodo empleado
         document.getDocumentElement().appendChild(raiz); 
        
             System.out.println("cod "+codigo);                      
         CrearElemento("codigo",Integer.toString(codigo), raiz, document);         
                 System.out.println("Prod "+productos);  
         CrearElemento("producto",productos.trim(), raiz, document);  
          System.out.println("Prec "+precio);  
         CrearElemento("precio",Double.toString(precio), raiz,document); 
         
	 }	
	 
            posicion= posicion + 32;
             if (file.getFilePointer() == file.length()) break; 
     }//fin del for que recorre el fichero
    Source source = new DOMSource(document);
     Result result = 
            new StreamResult(new java.io.File("./src/P4/Empleados.xml"));        
     Transformer transformer =
            TransformerFactory.newInstance().newTransformer();
     transformer.transform(source, result);
    
    }catch(Exception e){ System.err.println("Error: "+e); }
    file.close();  //cerrar fichero 	
 }//fin de main
 
 //Inserción de los datos del empleado
 static void  CrearElemento(String datoEmple, String valor,Element raiz, Document document)
 {
    Element elem = document.createElement(datoEmple); 
    Text text = document.createTextNode(valor); //damos valor
    raiz.appendChild(elem); //pegamos el elemento hijo a la raiz
    elem.appendChild(text); //pegamos el valor		 	
 }
}
    

