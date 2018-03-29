/**
 * ********************************************************************
 * Class CoupleStats
 * Définition de couples nom/valeur comparables
 *********************************************************************
 */
package Util;

/**
 * Définition de couples nom/valeur comparables
 * @author centrale
 */
public class CoupleStats implements Comparable{
    
    private String name;
    private int value;

    /**
     *
     * @param name
     * @param value
     */
    public CoupleStats(String name, int value) {
        this.name=name;
        this.value=value;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public int getValue() {
        return value;
    }

    /**
     *
     * @param value
     */
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
