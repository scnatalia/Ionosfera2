
package proyectoentropía;

import java.util.Random;


public class GenerarTablas {
    
    public  String[][] TablaGenerada(int COLUMtot, String d[][],int Renglontot,int Renglones)
    {   
        int i;
        int j;
        int k;
        int valor;
        boolean band=false;
        
        
        Random aleatorio = new Random(System.currentTimeMillis());
        
        
        String arreglo[][]=new String[Renglones][COLUMtot];
        int Revisar[];
        Revisar=new int[Renglontot];
        for(i=0;i<Renglontot;i++)       //inicializar el arreglo
        {
            Revisar[i]=-1;
        }
        
        for(j=0;j<Renglones;j++)
        {
            valor=aleatorio.nextInt(Renglontot);
            for(k=0;k<Renglones;k++)
            {
               if(Revisar[k]==valor) 
               {
                   band=true;
               }
            }
            if(band==false)
            Revisar[j]=valor;
            //System.out.println("Valor: "+Revisar[j]);

            for(i=0;i<COLUMtot;i++)
            {
                arreglo[j][i]=d[valor][i];
               //System.out.print("  "+arreglo[j][i]);
            }
        }
        
        return arreglo;
    }
     
}