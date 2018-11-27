package proyectoentropía;
import java.util.Random;

public class ValidaciondeTabla
{
    public boolean Validación(int columnas,String g[][],int renglones,double gama)
    {
        
        int aleatorio1, aleatorio2,i,j,band=0;
        double s1=0,s2=0,resultado=0,r=0,emax = 0,emin = 0;
        Double e[]=new Double[5];
        
        for(j=0;j<5;j++)
        {
            aleatorio1= (int)(Math.random()*columnas);
            aleatorio2= (int)(Math.random()*columnas);
            while(aleatorio2==aleatorio1)
            {
                aleatorio2= (int)(Math.random()*columnas);
            }
            for(i=0;i<renglones;i++)
            {
                s1=Double.parseDouble(g[i][aleatorio1]);
                s2=Double.parseDouble(g[i][aleatorio2]);
                resultado = resultado + Math.pow((s1-s2),2);
            }
            e[j]=Math.pow(resultado,0.5);
        }
        i=0;
        emin=e[0];
        while(i<5)
        {
            if(emin>e[i])
            {
                emin=e[i];
            }
            i++;
        }
        i=0;
        emax=e[0];
        while(i<5)
        {
            if(emin<e[i])
            {
                emin=e[i];
            }
            i++;
        }
        r=emax/emin;
        if(r<=1+gama)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
}
