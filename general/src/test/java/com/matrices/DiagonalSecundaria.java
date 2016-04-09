package com.matrices;

public class DiagonalSecundaria {


    public static void main(String... args) {
        int N = 10;
        int[][] M = matrix(N);

// numero de elementos por encima o por debajo
        int x = ((N * N) - N) / 2;

        int d[] = new int[N];

        int[] up = new int[ x ];
        int[] bottom = new int[ x ];
        int upCount = 0;
        int bottomCount = 0;

        for (int i = 0; i<N; i++) {
            for (int j=0; j<N; j++) {
                int k = N - j - 1;
                int value = M [ i ] [ j ];

                if (i == k) {
                    d[ j ] = value;
                } else if (i < k) {
                    up[ upCount++ ] = value;
                } else {
                    bottom[ bottomCount++ ] = value;
                }
            }
        }

        print(M);
        print(d, "diagonal: " ) ;
        print(up, "arriba: " ) ;
        print(bottom, "abajo: " ) ;
    }

    public static void print(int [ ]v, String desc) {
        System.out.print(desc) ;
        for (int aV : v) {
            System.out.print(aV + " ");
        }
        System.out.println( ) ;
    }

    public static void print(int [ ][ ]M) {
        System.out.println("Matriz: " ) ;
        for (int[] aM : M) {
            print(aM, "");
        }
    }

    public static int[][] matrix(int n) {
        int[][] M = new int[ n ][ ] ;

        for (int i = 0; i < n; i++) {
            M[ i ] = new int[ n ];
            for (int j=0; j < n; j++) {
                M[ i ][ j ] = (int)(Math.random() * 10) ;
            }
        }

        return M;
    }
}
