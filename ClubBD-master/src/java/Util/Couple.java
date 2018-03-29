/**
 * ********************************************************************
 * Class Couple
 * Définition de couples nom/valeur
 *********************************************************************
 */
package Util;

/**
 * Définition de couples nom/valeur
 * @author centrale
 */
public class Couple {
    private String name;
    private String value;

    /**
     *
     * @param name
     * @param value
     */
    public Couple(String name, String value) {
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
    public String getValue() {
        return value;
    }

    /**
     *
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }
    
    
}
