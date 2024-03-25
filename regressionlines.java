

public class regressionlines 
{
    public static void main(String[] args) 
    {
        int[] X = {72 , 64 , 65 , 69 , 75 , 79 , 72 , 74 };
        int[] Y = { 127 , 125 , 149 , 145 , 168 , 152 , 181, 209};

        int[] x_square = new int[X.length];
        int[] y_square = new int[Y.length];
        int[] xy = new int[X.length];
        int xsum =0, ysum =0, xysum =0, xsquaresum =0 , ysquaresum =0;

        for (int i = 0; i < X.length; i++)
        {

            x_square[i] = X[i]*X[i];
            y_square[i] = Y[i]*Y[i];
            xy[i] = X[i]*Y[i];

            xsum += X[i];
            ysum += Y[i];

            xsquaresum += x_square[i];
            ysquaresum += y_square[i];
            xysum += xy[i];
        }


        System.out.println("SNO\tX\tY\tX^2\tY^2\tXY");
        for (int i = 0; i < X.length; i++)
        {
            System.out.println((i+1) + "\t" + X[i] + "\t" + Y[i] + "\t" + x_square[i] + "\t" + y_square[i] + "\t" + xy[i]);
        }

        System.out.println("SUM\t" + xsum + "\t" + ysum + "\t" + xsquaresum + "\t" + ysquaresum + "\t" + xysum );

        
        System.out.println("MEAN:");

        double meanx =(double) xsum/X.length ;
        double meany =(double) ysum/Y.length ;

        System.out.println( "MEAN(X) = " + meanx  );

        System.out.println("MEAN(Y) = " + meany );

        System.out.println("VARIANCE : ");

        double varx =  ( (double)xsquaresum/X.length) - meanx*meanx ;

        double vary = ((double)ysquaresum/Y.length) - meany*meany ;

        System.out.println("\u03c3x^2 = " + varx );
        System.out.println("\u03c3y^2 = " + vary );

        System.out.println("COVARIANCE : ");

        double cov =( (double) xysum/X.length) - meanx*meany ;

        System.out.println("COV(X,Y) = " + cov  );

        System.out.println("Correlation : ");

        double correlation = cov/  ((Math.pow(varx, 0.5)*Math.pow(vary, 0.5) )) ;

        System.err.println("correlation = " + correlation );

        System.out.println("REGRESSION LINES :" );
        
        double coefx = correlation*(Math.pow(vary, 0.5)/(Math.pow(varx, 0.5)));
        double coefy = correlation*(Math.pow(varx, 0.5)/(Math.pow(vary, 0.5)));

        System.out.println("Y - Ybar = " + coefx + " (X - Xbar )" );
        System.out.println("X - Xbar = " + coefy + " (Y - Ybar )" );

        System.out.println("Y = " +coefx+ "X +(" + (meany-coefx*meanx)  +")" );
        System.out.println("X = " +coefy+ "Y +(" + (meanx-coefy*meany) + ")" );




    }
}
