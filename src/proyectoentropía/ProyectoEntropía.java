package proyectoentropía;
import java.lang.*;
import java.io.*;
import java.util.*;
import java.math.*;
import javax.swing.table.DefaultTableModel;
import proyectoentropía.Interfaz;
/**
 * @author al171800
 */
public class ProyectoEntropía
{
    public static void main(String[] args)
    {
        Interfaz interfaz =new Interfaz();
        Scanner entrada=new Scanner (System.in);
        interfaz.setVisible(true);
        
        int contCOLUMNAS=0,contRENGLONES=0;//contCOLUMNAS=columnas,contRENGLONES=renglones
        int h=0,t,i,j;//Variables genericas para ciclos
        int cont=0;
        int cont4=0;
          int c=2;
        
        double RestaEntropia2=0;
        
        boolean ba = false;
        double H,e=0.5;
        double entropia;
        double RestaEntropia=0;
        double EntropiaPrevia=0;
        double EntropiaPosterior=0; 
        double Epsilon=0;
        double entropiaoriginal=0;
        
        String n = null;//n: INSTRUCCION PPROPIA DE LOS ARCHIVOS PARA LEER
        String name=null;//name: Nombre del archivo
        String ENCABEZADO[]; //encabezado
        String DATOS[][] = null; //todos los datos 
        String SIMBOLOS[][]=null;//simbolos diferentes
        String TablaEncontrada[][]=null;
        
        
        int tamaño[];
        String generada[][] = null;
        ValidaciondeTabla v=new ValidaciondeTabla();

        name = interfaz.getFileName();


        
        
        try
        {   
            FileReader input=new FileReader(name);
            try (BufferedReader binput = new BufferedReader(input)) {
                n=binput.readLine();
                
                for(i=0;i<n.length();i++)//Cuenta el número de columnas
                {
                    if(n.charAt(i)==',')
                    {
                        contCOLUMNAS++;
                    }
                }
                contCOLUMNAS++;          //Termina de contar el número de columnas
                
                ENCABEZADO=new String[contCOLUMNAS];//Se declarara el encabezado con el número de columnas dado
                
                
                for(i=0;i<contCOLUMNAS;i++)  //Se crea el encabezado vacío
                {
                    ENCABEZADO[i]="";
                }                           //Se termina de crear el encabezado

                i=0;
                for(h=0;h<n.length();h++)   //Se llena el encabezado
                {
                    if(n.charAt(h)!=',')
                    {
                        ENCABEZADO[i]=ENCABEZADO[i]+n.charAt(h);
                    }
                    else
                    {
                        i++;
                    }
                }                          //Se termina de llenar el encabezado
                
                while(n!=null)             //Se cuenta el número de renglones de la base de datos principal
                {
                    n=binput.readLine();
                    if (n==null)
                    {
                        contRENGLONES=contRENGLONES-1;
                    }
                    else
                    {
                        contRENGLONES++;
                        // System.out.println(""+n);
                    }
                    
                }                         //Se termina de contar el número de renglones de la base de datos principal
                
                DATOS= new String[contRENGLONES][contCOLUMNAS];  //Se declara la base de  datos con el tamño encontrado de renglones y columnas
            }
         
        }
        catch(IOException ex)
        {
            System.out.println("\nNo se pudo cargar el archivo\n");
        }
        
        try 
        {
            FileReader input=new FileReader(name);
            BufferedReader binput=new BufferedReader(input);
            binput.readLine();
            
            for(t=0;t<contRENGLONES;t++)   //Se crea la base de datos vacía
            {
                for(h=0;h<contCOLUMNAS;h++)
                {
                    DATOS[t][h]="";
                }
            }                             //Se termina de crear la base de datos vacía
            
            for(t=0;t<contRENGLONES;t++)  //Se llena la base de datos
            {
                n=binput.readLine();
                i=0;
                for(h=0;h<n.length();h++)
                {
                    if(n.charAt(h)!=',')
                    {
                        DATOS[t][i]= DATOS[t][i]+n.charAt(h);
                    }
                    else
                    {
                        i++;
                    }
                }
            }                           //Se termina de llenar la base de datos
        }
        catch(IOException ex)
        {
            System.out.println("\nNo se pudo cargar el archivo\n");
        }
         
       /////////////////////////////Fin de llenar base de datos////////////////////////////////////////////////////////////
       int cont2=0;
       int cont3=0;
       while(cont2<5)
        {
       
       
        GenerarSimbolos s=new GenerarSimbolos();//Se declara la variable de la matriz de simbolos
        SIMBOLOS=s.Simbolos(contCOLUMNAS, DATOS, contRENGLONES);//Se crea la matriz de simbolos
        
        
        
        AplicarFormula a=new AplicarFormula();//Se declara una variable de tipo AplicarFormula
        tamaño=a.Tamaño(contCOLUMNAS, SIMBOLOS);
        entropia=a.Entropia(contCOLUMNAS, DATOS, SIMBOLOS, contRENGLONES, tamaño);
        entropiaoriginal=entropia;
        if(cont3==0)
        {
         interfaz.setText(entropia);
        Epsilon=interfaz.getEpsilon();
        cont3++;
        }
        
        
        ////////////////////////////Tabala generada/////////////////////////////////////////////
       
        
        while(ba==false)
        {
            
            GenerarTablas b=new GenerarTablas();
          
            generada=new String[c][contCOLUMNAS];
            generada=b.TablaGenerada(contCOLUMNAS, DATOS, contRENGLONES, c);
            ba=v.Validación(contCOLUMNAS, generada, c, 0.00000000000000000000001);
            EntropiaPrevia=a.Entropia(contCOLUMNAS, generada, SIMBOLOS, c, tamaño);
            RestaEntropia=EntropiaPrevia;
            
       if(Epsilon>10*entropiaoriginal)
       {
           Epsilon=entropiaoriginal*1.5;cont4++;
       }
         if(Epsilon<entropiaoriginal) 
         {
             Epsilon=entropiaoriginal*1.5;cont4++;
         }
             if(cont2==0&&cont4!=0)//System.out.println("Fue necesario hacer un cambio al valor dado para otener un mejor resultado");
                
            while(RestaEntropia<Epsilon)
            {
                RestaEntropia=RestaEntropia2;

            c++;
            generada=new String[c][contCOLUMNAS];
            generada=b.TablaGenerada(contCOLUMNAS, DATOS, contRENGLONES, c);

            EntropiaPosterior=a.Entropia(contCOLUMNAS, generada, SIMBOLOS, c, tamaño);

            RestaEntropia=Math.abs(EntropiaPosterior-EntropiaPrevia);
            EntropiaPrevia=EntropiaPosterior;
            RestaEntropia=RestaEntropia/EntropiaPosterior;

            cont++;
              //System.out.println(RestaEntropia);

           if(cont<5)
                {
                    RestaEntropia2=RestaEntropia;
                    RestaEntropia=.1;
                }
            }

            //System.out.println("CONT"+cont);
            if(cont2==0)
            {
                interfaz.setTabla(generada);
            for(j=0;j<c;j++)
            {
                for(i=0;i<contCOLUMNAS;i++)
                {
                    //System.out.print("  "+generada[j][i]);
                    interfaz.setTexto("  "+generada[j][i],false);
                }
                interfaz.setTexto("",true);
            }
            }
        }
        cont2++;
        
        if(ba!=false)
        {
                Epsilon=Epsilon+.01;
            
            ba=false;
        }
        }
       interfaz.setMensaje();
        
        interfaz.generarCampos(contCOLUMNAS,c);
        interfaz.setGenerada(generada, contCOLUMNAS,c);
}
}