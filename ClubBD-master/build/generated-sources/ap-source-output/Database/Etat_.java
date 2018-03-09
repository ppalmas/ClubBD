package Database;

import Database.Document;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-09T20:04:23")
@StaticMetamodel(Etat.class)
public class Etat_ { 

    public static volatile SingularAttribute<Etat, Integer> idEtat;
    public static volatile CollectionAttribute<Etat, Document> documentCollection;
    public static volatile SingularAttribute<Etat, String> etat;

}