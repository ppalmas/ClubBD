package Database;

import Database.Blacklist;
import Database.Createurdocument;
import Database.Emprunt;
import Database.Etat;
import Database.Genredocument;
import Database.Serie;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-28T01:02:00")
@StaticMetamodel(Document.class)
public class Document_ { 

    public static volatile SingularAttribute<Document, Integer> numero;
    public static volatile SingularAttribute<Document, String> titre;
    public static volatile SingularAttribute<Document, Integer> idDocument;
    public static volatile SingularAttribute<Document, String> description;
    public static volatile CollectionAttribute<Document, Emprunt> empruntCollection;
    public static volatile CollectionAttribute<Document, Genredocument> genredocumentCollection;
    public static volatile SingularAttribute<Document, Etat> idEtat;
    public static volatile SingularAttribute<Document, String> cote;
    public static volatile SingularAttribute<Document, Serie> idSerie;
    public static volatile SingularAttribute<Document, String> imageDocument;
    public static volatile SingularAttribute<Document, String> commentaire;
    public static volatile CollectionAttribute<Document, Createurdocument> createurdocumentCollection;
    public static volatile CollectionAttribute<Document, Blacklist> blacklistCollection;

}