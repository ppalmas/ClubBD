package Database;

import Database.Document;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-30T10:26:01")
@StaticMetamodel(Serie.class)
public class Serie_ { 

    public static volatile SingularAttribute<Serie, Integer> idSerie;
    public static volatile SingularAttribute<Serie, String> nomSerie;
    public static volatile CollectionAttribute<Serie, Document> documentCollection;
    public static volatile SingularAttribute<Serie, Boolean> complet;
    public static volatile SingularAttribute<Serie, String> serieDescription;

}