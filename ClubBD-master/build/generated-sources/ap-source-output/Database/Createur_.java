package Database;

import Database.Createurdocument;
import Database.Createurrecherche;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-10T00:09:46")
@StaticMetamodel(Createur.class)
public class Createur_ { 

    public static volatile SingularAttribute<Createur, String> prenomCreateur;
    public static volatile SingularAttribute<Createur, Integer> idCreateur;
    public static volatile CollectionAttribute<Createur, Createurrecherche> createurrechercheCollection;
    public static volatile CollectionAttribute<Createur, Createurdocument> createurdocumentCollection;
    public static volatile SingularAttribute<Createur, String> nomCreateur;

}