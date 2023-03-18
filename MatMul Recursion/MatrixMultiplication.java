import java.util.Arrays;
import java.util.HashMap;

public class MatrixMultiplication
{
//    to find the determinant of A
    public int findDeterminant(int [][] A) {
        if ((A[0].length % 2 != 0) || (A.length % 2 != 0))
        {
            return -1;
        }
        int result = 0;
        for (int i = 0; i < A[0].length; i++)
        {
            for (int j = 0; j < A[0].length; j++)
            {
                if (checkIfNewSubMatrix(i, j))
                {
                    result += findDeterminant(A, i, j);
                }
            }
        }
        return result;
    }
    private static int findDeterminant(int[][] mat, int row, int col)
    {
        return (mat[row][col] * mat[row + 1][col + 1] - (mat[row][col + 1] * mat[row + 1][col]));
    }

    private static boolean checkIfNewSubMatrix(int row, int col)
    {
        if (row % 2 == 0 && col % 2 == 0)
        {
            return true;
        }
        return false;
    }

//iterative algorithm
    public int[][] iterativeMatrixMultiplication(int[][]A, int[][]B)
    {
        int[][] C2 = new int[A[0].length][B.length];
        for(int row = 0; row<A[0].length; row++)
        {
            for(int column = 0; column<B.length; column++)
            {
                C2[row][column] = 0;
                for(int k=0; k<A.length; k++)
                {
                    C2[row][column] += A[row][k] * B[k][column];
                }
            }
        }
        return C2;
    }
//    recursion algorithm
    public int[][] recursiveMatrixMultiplication(int[][]A, int[][]B)
    {
        int size = A[0].length;
        int C[][] = new int[size][size];

        if(size == 1)
        {
            C[0][0] = A[0][0] * B[0][0];
            return C;
        }
        if(size ==2){
            C[0][0] = A[0][0] * B[0][0] + A[0][1]*B[1][0];
            C[0][1] = A[0][0] * B[0][1] + A[0][1]*B[1][1];
            C[1][0] = A[1][0] * B[0][0] + A[1][1]*B[1][0];
            C[1][1] = A[1][0] * B[0][1] + A[1][1]*B[1][1];
        }
        else
        {
            HashMap<Integer, int[][]> list1 = partition(A);
            HashMap<Integer, int[][]> list2 = partition(B);

            int[][] C11 = addMatrix(recursiveMatrixMultiplication(list1.get(0), list2.get(0)), recursiveMatrixMultiplication(list1.get(1), list2.get(2)));
            int[][] C12 = addMatrix(recursiveMatrixMultiplication(list1.get(0), list2.get(1)) , recursiveMatrixMultiplication(list1.get(1), list2.get(3)));
            int[][] C21 = addMatrix(recursiveMatrixMultiplication(list1.get(2), list2.get(0)), recursiveMatrixMultiplication(list1.get(3),list2.get(2)));
            int[][] C22 = addMatrix(recursiveMatrixMultiplication(list1.get(2), list2.get(1)), recursiveMatrixMultiplication(list1.get(3),list2.get(3)));
            C=combineMatrices(C, C11,C12,C21,C22);
        }
        return C;
    }

    private int[][] combineMatrices(int[][] C, int[][] C11,int[][] C12,int[][] C21,int[][] C22){
        int[][] res = new int[4][4];
        res[0][0] = C11[0][0];
        res[0][1] = C11[0][1];
        res[0][2] = C12[0][0];
        res[0][3] = C12[0][1];
        res[1][0] = C11[1][0];
        res[1][1] = C11[1][1];
        res[1][2] = C12[1][0];
        res[1][3] = C12[1][1];
        res[2][0] = C21[0][0];
        res[2][1] = C21[0][1];
        res[2][2] = C22[0][0];
        res[2][3] = C22[0][1];
        res[3][0] = C21[1][0];
        res[3][1] = C21[1][1];
        res[3][2] = C22[1][0];
        res[3][3] = C22[1][1];
        return res;
    }



    private int[][] addMatrix(int[][] A, int[][] B) {
        int[][] C = new int[A[0].length][A[0].length];
        int n = A[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }

    private HashMap<Integer, int[][]> partition(int[][] a){
        HashMap<Integer, int[][]> hM= new HashMap<>();
        int counter = 0;
        for(int i=0;i<a[0].length;i++){
            for(int j=0;j<a[0].length;j++) {
                if(i%2 == 0 && j%2==0){
                    int [][] temp=new int[2][2];
                    temp[0][0] = a[i][j];
                    temp[0][1] = a[i][j+1];
                    temp[1][0] = a[i+1][j];
                    temp[1][1] = a[i+1][j+1];
                    hM.put(counter,temp);
                    counter++;
                }

            }
        }
        return hM;
    }
//    compares the recursion and iterative algorithm
    public boolean matrixEqual(int[][]A, int[][]B)
    {
        for(int i=0;i<A[0].length;i++)
        {
            for(int j=0;j<B.length;j++)
            {
                if(A[i][j]!=B[i][j])
                {
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args)
    {
        int[][] A = {{1, 2, 3, 4}, {5, 6, 7, 8}, {-8, -7, -6, -5}, {-4, -3, -2, -1}};
        int[][] B = {{10, 11, 14, 15}, {12, 13, 16, 17}, {-18, -17, -16, -15}, {-14, -13, -12, -11}};
        MatrixMultiplication mat = new MatrixMultiplication();
        System.out.println(mat.findDeterminant(A));
        System.out.println(Arrays.deepToString(mat.recursiveMatrixMultiplication(A,B)));
        System.out.println(Arrays.deepToString(mat.iterativeMatrixMultiplication(A,B)));
        System.out.println(mat.matrixEqual((mat.recursiveMatrixMultiplication(A,B)),(mat.iterativeMatrixMultiplication(A,B))));
    }
}
