package proyectoentropía;

public class GenerarSimbolos {
    
    //int encontrado=0; //encontrado: Constante para saber si el dato está repetido
    private int i,j,k,l;    //i:Recorrer columnas
                    //j: Recorrer renglones de la base de datos
                    //k:Recorrer los renglones de la base de simbolos
                    //l: Posicion de renglones donde se guardan los simbolos
    
    String s[][]=null; //Base de datos de los simbolos
    
    public String[][] Simbolos(int Columtot,String d[][],int Renglontot)
    {
        boolean encontrado;
        s=new String[Renglontot][Columtot];  //Declaración de la base de simbolos
        
        for(i=0;i<Columtot;i++)             //Recorrer la base de datos
        {
            l=0;
            s[0][i]=d[0][i]; //Guarda el valor inicial por columna para hacer la comparación
            l++;
            
            for(j=0;j<Renglontot;j++)
            {
                encontrado=false;
               
               for(k=0;k<l;k++)
               {
                   if(d[j][i].equals(s[k][i]))
                   {
                       
                       encontrado=true;
                       
                   }
               }
               if(!encontrado)
                   {
                       s[l][i]=d[j][i];
                       //System.out.println(""+s[l][i]);
                       l++;
                   }
            }
        }                                      //Fin de recorrer la base de datos
        /*
        for(i=0;i<Columtot;i++)
        {
            System.out.println("");
            System.out.println("");
            System.out.println("");
            
            for(j=0;j<Renglontot;j++)
            {
                if(s[j][i]!=null)
                {
                     System.out.println(""+s[j][i]);
                }
               
            }
        }
        */
        return s;
    }
}