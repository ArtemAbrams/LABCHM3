public class Main {
    public static double[] GetX(int n, double a, double b)
    {
        double [] x = new double[n];
        double h = (b-a) / (n-1);
        for(int i =0; i<n;i++)
        {
            x[i] = a + i*h;
        }
        return x;
    }
    public static double[] GetY(double[] x)
    {
        double[] y = new double[x.length];
        for (int i=0; i<x.length; i++)
        {
            y[i] = Math.exp(x[i])- 2*Math.pow((x[i]-1),2);
        }
        return y;
    }
    private double[] x;
    private double[] y;
    private double[] a;
    private double[] b;
    private static double[] c;
    private double[] d;

    public Main(double[] x, double[] y) {
        this.x = x;
        this.y = y;
        this.a = y.clone();
        this.b = new double[x.length];
        this.c = new double[x.length];
        this.d = new double[x.length];
        computeCoefficients();
    }

    private void computeCoefficients() {
        int n = x.length;
        double[] h = new double[n-1];
        double[] alpha = new double[n - 1];
        double[] l = new double[n];
        double[] mu = new double[n];
        double[] z = new double[n];

        for (int i = 0; i < n - 1; i++) {
            h[i] = x[i + 1] - x[i];
        }

        for (int i = 1; i < n - 1; i++) {
            alpha[i] = 6 * ((a[i + 1] - a[i]) / h[i] - (a[i] - a[i - 1]) / h[i - 1]);
        }

        l[0] = 1;
        mu[0] = 0;
        z[0] = 0;

        for (int i = 1; i < n - 1; i++) {
            l[i] = 2 * (x[i + 1] - x[i - 1]) - h[i - 1] * mu[i - 1];
            mu[i] = h[i] / l[i];
            z[i] = (alpha[i] - h[i - 1] * z[i - 1]) / l[i];
        }

        l[n - 1] = 1;
        z[n - 1] = 0;
        c[n - 1] = 0;

        for (int j = n - 2; j >= 0; j--) {
            c[j] = z[j] - mu[j] * c[j + 1];
            d[j] = (c[j + 1] - c[j]) / (h[j]);
        }
        for (int i =1; i<b.length;i++)
        {
            b[i] = c[i]*h[i-1]/2 - Math.pow(h[i-1], 2)*d[i-1]/6 + (a[i] - a[i-1]) / h[i-1];
        }
    }
    public void Print()
    {
        for(int i=0; i<x.length-1;i++)
        {
            System.out.println("interval[" + x[i] + ";" + x[i+1] +"]  " + y[i+1] + " + " + b[i+1] +"(x - " + x[i+1]+") +  " + c[i+1]/2 +"(x - " + x[i+1]+")^2 + " + d[i]/6+"(x - " + x[i+1]+")^3");
        }
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
        Main cubicSpline = new Main(x, y);
        cubicSpline.Print();
    }
}