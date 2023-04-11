public class LagrangePolynomialGenerator {
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
    public static void lagrangePolynomial(double[] x, double[] y) {
        String result = "";
        for (int i = 0; i < x.length; i++) {
            String term = "";
            double value = (double) Math.round(y[i] * 1000) / 1000;
            for (int j = 0; j < x.length; j++) {
                if (j != i) {
                    value = (double) Math.round(value / (x[i] - x[j]) * 1000) / 1000;
                    term += "(x - " + x[j] + ") * ";
                }
            }
            result += term + String.format("%.3f", value);
            if (i < x.length - 1) {
                result += " + ";
            }
        }
        System.out.println("\nІнтерполяційний поліном Лагранжа:\n" + "L(x)=" + result);
    }
   /* public static void lagrangePolynomialValue(double[] x, double[] y, double xV) {
        double resultF =  Math.exp(xV)- 2*Math.pow((xV-1),2);
        double result =0;
        for (int i = 0; i < x.length; i++) {
            double term = 1;
            double value = y[i];
            for (int j = 0; j < x.length; j++) {
                if (j != i) {
                    value /= x[i] - x[j];
                    term *= xV - x[j];
                }
            }
            result += (value * term);
        }
        System.out.println("\nІнтерполяційний поліном Лагранжа:\n" + "L(x)=" + result);
        System.out.println("\nЗначення функції в точці:\n" + "f(x)=" + resultF);
    }*/

    public static void main(String[] args) {
        int n = 3;
        double[] arr_x = GetX(n,0,2);
        for (int i=0; i< arr_x.length; i++)
        {
            System.out.println(String.format("x[%d]=%.3f", i, arr_x[i]));
        }
        System.out.println("--------------------------------------------------");
        double[] arr_y = GetY(arr_x);
        for (int i=0; i<arr_y.length; i++)
        {
            System.out.println(String.format("y[%d]=%.3f", i, arr_y[i]));
        }
        lagrangePolynomial(arr_x, arr_y);
        //lagrangePolynomialValue(arr_x, arr_y, 1.5);
    }
}
