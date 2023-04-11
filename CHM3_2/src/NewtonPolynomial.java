import static java.lang.System.in;

public class NewtonPolynomial {
    public static double[] GetX(int n, double a, double b)
    {
        double [] x = new double[n];
        double h = (b-a) / (n-1);
        for(int i =0; i<n;i++)
        {
            x[i] = (double) Math.round(a + i*h * 1000) / 1000;
        }
        return x;
    }
    public static double[] GetY(double[] x)
    {
        double[] y = new double[x.length];
        for (int i=0; i<x.length; i++)
        {
            y[i] = (double) Math.round((Math.exp(x[i])- 2*Math.pow((x[i]-1),2)) * 1000) / 1000;
        }
        return y;
    }
    public static void newtonInterpolation(double[] x, double[] y) {
        int n = x.length - 1;
        double[] b = new double[n + 1];
        double element;
        for(int k=0; k<=n; k++)
        {
            b[k] = 0;
            for(int i = 0; i<=k; i++)
            {
                double multiply =1;
                element = y[i];
                for(int j=0; j<=k; j++)
                {
                    if(i != j)
                    {
                        multiply *= (x[i] -x[j]);
                    }
                }
                b[k] += (double) Math.round(element / multiply * 1000) / 1000;
            }
        }
        String result = String.format("%3f", b[0]);
        String term = "";
        for(int i =1; i<=n; i++)
        {
            term +=("(x - " + x[i-1]) +")";
            if(b[i] < 0)
                result += String.format("%3f", b[i]) + term;
            else
                result += " + " + String.format("%3f", b[i]) + term;
        }
        System.out.println("L(x) = " + result);
    }
    public static void main(String[] args) {
        int n = 17;
        var x = GetX(n,0,2);
        var y = GetY(x);
        for(int i =0; i < x.length; i++)
        {
            System.out.println("x[" + i + "]= " + x[i]);
        }
        System.out.println("-----------------------------------------------");
        for(int i =0; i < y.length; i++)
        {
            System.out.println("y[" + i + "]= " + y[i]);
        }
        newtonInterpolation(x,y);
    }
}