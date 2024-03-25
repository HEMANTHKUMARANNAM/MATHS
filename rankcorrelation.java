import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class element
{
    int data;
    ArrayList<Integer> rankholder;
    int count;
    element(int data)
    {
        this.data = data;
        rankholder = new ArrayList<Integer>();
        count =0;
    }
    void addelement(int data, int rank)
    {
        rankholder.add(rank);
        count++;
    }
    float getrank()
    {
        int ranksum =0;
        for(int i=0; i<rankholder.size(); i++)
        {
            ranksum = ranksum + rankholder.get(i);
        }
        float rank = (float) ranksum/rankholder.size();
        return rank;
    }

    int getcount()
    {
        return count;
    }
}

public class rankcorrelation
{
    public static void main(String[] args) 
    {
        int[] X = { 69 , 63 , 74 , 51 , 63 , 80 , 75 , 40  , 55 , 64};
        int[] Y = { 63 , 59 , 68 , 45 , 81 , 60 , 68 , 49 , 51 , 71  };

        ArrayList<Float> CF = new ArrayList<Float>();

        ArrayList<Float> CX = new ArrayList<Float>();
        ArrayList<Float> CY = new ArrayList<Float>();

        float[] xrank = rankcal(X , CF , CX);
        float[] yrank = rankcal(Y , CF , CY);


        float[] d = new float[X.length];

        float dsquare =0;

        for(int i=0; i<X.length; i++)
        {
            d[i] = xrank[i]-yrank[i];
            float t = d[i]*d[i];
            dsquare = dsquare + t;
        }

        int n = X.length;

        float rank_correlation = 1-( 6*(dsquare + CF.get(0) + CF.get(1) )/(n*(n*n-1))  );

        
        System.out.println("SNO\tX\tY\tRank(x)\tRank(y)\t d\tdsquare");
        for(int i =0 ; i< X.length ; i++ )
        {
            System.out.println( (i+1)+"\t" +X[i] + "\t" + Y[i] +"\t" + xrank[i] + "\t"  + yrank[i] +  "\t" + d[i] + "\t" + d[i]*d[i] );
        }
        System.out.println("SUM\t\t\t\t\t\t" + dsquare);

        System.out.println("CF VALUES :");

        for(int i =0 ; i< CX.size() ; i = i+2)
        {
            System.out.println( "C.F OF X = " +CX.get(i) + "=>" + CX.get(i+1) );
        }

        for(int i =0 ; i< CY.size() ; i = i+2)
        {
            System.out.println( "C.F OF Y = " +CY.get(i) + "=>" + CY.get(i+1) );
        }

        System.out.println("CORRELATION :");

        System.out.println("FORMULA : 1 - 6*(   SUM(D[I]*D[I]) + SUM(CF(X) + SUM(CF(Y)  )/(   N(N*N-1)    ) ");

        System.out.println("rak_correlation =" + rank_correlation);

    }

    public static float[] rankcal(int[] b  , ArrayList<Float> CF , ArrayList<Float> CT )
    {
        int[] a = new int[b.length];

        for(int i=0; i<b.length; i++)
        {
            a[i] = b[i];
        }

        Arrays.sort(a ) ;
        for (int i = 0; i < a.length / 2; i++) {
            int temp = a[i];
            a[i] = a[a.length - 1 - i];
            a[a.length - 1 - i] = temp;
        }

        element[] arr= new element[a[0] + 1];

        int[] uniqueArray = removeDuplicates(a);


        for (int i = 0; i < uniqueArray.length ; i++)
        {
            arr[uniqueArray[i]] = new element(uniqueArray[i]);
        }

        for (int i = 0; i <a.length ; i++)
        {
            arr[a[i]].addelement(a[i] , i+1 );
        }


        float[] rank = new float[a.length];

        for (int i = 0; i < b.length; i++) 
        {
            rank[i] = arr[b[i]].getrank();
        }

        float cf=0;

        int[] a_repeted = findRepeatedNumbers(b);

        for(int i =0 ; i< a_repeted.length ; i++)
        {
            CT.add((float) a_repeted[i]);

            int m = arr[a_repeted[i]].getcount();

            cf +=  (float)m*( (m*m) -1)/12;

            CT.add( (float)m*( (m*m) -1)/12);

        }

        CF.add(cf);

        return rank;
    }

    public static int[] removeDuplicates(int[] array) {
        // Create a HashSet to store unique elements
        Set<Integer> set = new HashSet<>();

        // Add elements from the array to the set (duplicates will automatically be removed)
        for (int num : array) {
            set.add(num);
        }

        // Convert the set back to an array
        int[] uniqueArray = new int[set.size()];
        int index = 0;
        for (int num : set) {
            uniqueArray[index++] = num;
        }

        return uniqueArray;
    }

    public static int[] findRepeatedNumbers(int[] array) {
        Map<Integer, Integer> countMap = new HashMap<>();
        List<Integer> repeatedNumbersList = new ArrayList<>();

        for (int num : array) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() > 1) {
                repeatedNumbersList.add(entry.getKey());
            }
        }

        int[] repeatedNumbersArray = new int[repeatedNumbersList.size()];
        for (int i = 0; i < repeatedNumbersList.size(); i++) {
            repeatedNumbersArray[i] = repeatedNumbersList.get(i);
        }

        return repeatedNumbersArray;
    }


}