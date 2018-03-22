/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

/**
 *
 * @author centrale
 * Permet de calculer la distance entre 2 chaines de caractères
 */
public class Levenshtein {
    public static String[] inutiles = {"LE", "LA", "LES", "DE", "L", "DES", "DU", " "};
    
    /**
     * permet de calculer la distance entre 2 chaines de caractères
     * @param mot1
     * @param mot2
     * @return 
     */
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
    
    
    /**
     * permet de claculer le minimum entre 3 entiers
     * @param x1
     * @param x2
     * @param x3
     * @return 
     */
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
    
    /**
     * permet d'enlever les espaces, les apostrophes et les mots jugés inutiles d'une chaine de caractères
     * @param mot
     * @return 
     */
    public static String condense(String mot)
    {
      String[] mot_split=mot.split("'| ");
      boolean inut;
      int j;
      for (int i = 0; i<mot_split.length; i++)
      {
          inut = false;
          j = 0;
          while (!inut && j<inutiles.length)
          {
              if (mot_split[i].equals(inutiles[j]))
              {
                  inut=true;
              }
              j++;
          }
          if (inut)
          {
              mot_split[i]="";
          }
      }
      String motSansInutiles="";
      for (String c : mot_split)
      {
          motSansInutiles+=c;
      }
      return motSansInutiles;
    };
    
}
