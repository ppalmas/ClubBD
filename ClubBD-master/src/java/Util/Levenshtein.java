/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

/**
 *
 * @author centrale
 */
public class Levenshtein {
    public static int distance(String mot1, String mot2)
    {
        int size1 = mot1.length();
        int size2 = mot2.length();
        int[][] d = new int[size1+1][];
        for (int i = 0 ; i<size1+1; i++)
        {
            d[i]=new int[size2+1];
        }
        int sub;
        for (int i = 0; i<size1+1; i++)
        {
           d[i][0]=i; 
        }
        for (int j = 0; j<size2+1; j++)
        {
           d[0][j]=j; 
        }
        
        for (int i=0; i<size1; i++)
            for (int j=0; j<size2; j++)
            {
                if (mot1.charAt(i)==mot2.charAt(j))
                {
                    sub=0;
                }
                else
                {
                    sub=1;
                }
                d[i+1][j+1] = mini(d[i][j+1]+1,d[i+1][j]+1,d[i][j]+sub);
            }
        return d[size1][size2];   
    }
    
    public static int mini(int x1, int x2, int x3)
    {
        int result;
        if (x1<x2){
            if (x1<x3)
            {
                result=x1;
            }
            else
            {
                result=x3;
            }
        }
        else
        {
            if (x2<x3)
            {
                result=x2;
            }
            else
            {
                result=x3;
            }
        }
        return result;
    }
}
