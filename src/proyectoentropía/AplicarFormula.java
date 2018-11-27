package proyectoentropía;

public class AplicarFormula
{
    private double max=0;
    private double sumatot;
    private double suma1=0;
    private double suma2=0;
    private int band;
    
    private int i,j,k,l; //i:Recorrer columnas
                 //j: Recorrer renglones de la base de datos
                 //k:Recorrer los renglones de la base de simbolos 
                 //l: Posicion de renglones donde se guardan los simbolos

    public int[] Tamaño(int COLUMtot, String s[][])
    {
        
        int tamaño[]=new int[COLUMtot];//Arreglo donde se va a guardar la canridad de simbolos por columna
        int t=0;//Variable para medir el tamaño de cada una de las columnas de simbolos
        
        for(i=0;i<COLUMtot;i++)
        {
            k=0; 
            t=0;
            while(s[k][k]!=null)
            {
                t++;
                k++;
            }
            tamaño[i]=t;
           
        }
        return tamaño;
    }

    
    public double Entropia(int COLUMtot, String d[][], String s[][],int Renglontot,int simbolos[])
    {
     double sumatotCOLUM=0;
       for(i=0;i<COLUMtot;i++)
       {
           sumatot=0;
            /////////////////////////////////////For total///////////////////////////////////////////////////
            for(k=0;k<simbolos[i];k++)
            {
                ////////////////////////////////////Primer for//////////////////////////////////////////////////
                for(j=0;j<Renglontot;j++)
                {
                    if(d[j][i].equals(s[k][i])) 
                    {
                        band=1;
                    }
                    else
                    {
                        band=0;
                    }
                    suma1=suma1+band;
                }
                suma1=suma1/Renglontot;
                /////////////////////////////Fin del primer For/////////////////////////////////////////////////////
                
                ///////////////////////////Segundo for/////////////////////////////////////////////////////////
                for(j=0;j<Renglontot;j++)
                {
                    if(d[j][i].equals(s[k][i])) 
                    {
                        band=1;
                    }
                    else
                    {
                        band=0;
                    }
                    suma2=suma2+band;
                }suma2=suma2/Renglontot;
                /////////////////////////Fin del seguno for/////////////////////////////////////////////////////////
                
                sumatot=sumatot+(suma1*(Math.log10(suma2) / Math.log10(10)));//tal vez sea base 2 
                
            }
            //////////////////////////////////////////////////Fin del for total///////////////////////////////////////
            //System.out.println("Entropía: "+i+" "+sumatot);
            sumatotCOLUM=sumatotCOLUM+sumatot;
       }
        return sumatotCOLUM*-1;
        
    } 
    }
//EPSIlON:6.5;