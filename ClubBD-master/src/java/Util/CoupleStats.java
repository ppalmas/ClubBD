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
public class CoupleStats implements Comparable{
    
    private String name;
    private int value;

    public CoupleStats(String name, int value) {
        this.name=name;
        this.value=value;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Object o) {
        int r;
        if (o instanceof CoupleStats)
        {
            r=((Integer) this.getValue()).compareTo(((CoupleStats) o).getValue());
        }
        else
        {
            r=0;
        }
        return r;
    }
}
