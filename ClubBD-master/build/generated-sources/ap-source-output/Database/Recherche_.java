package Database;

import Database.Createurrecherche;
import Database.Genrerecherche;
import Database.Serie;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-16T20:39:51")
@StaticMetamodel(Recherche.class)
public class Recherche_ { 

    public static volatile SingularAttribute<Recherche, String> cote;
    public static volatile SingularAttribute<Recherche, Integer> numero;
    public static volatile SingularAttribute<Recherche, Integer> idRecherche;
    public static volatile SingularAttribute<Recherche, Serie> idSerie;
    public static volatile SingularAttribute<Recherche, String> titre;
    public static volatile CollectionAttribute<Recherche, Createurrecherche> createurrechercheCollection;
    public static volatile CollectionAttribute<Recherche, Genrerecherche> genrerechercheCollection;

}