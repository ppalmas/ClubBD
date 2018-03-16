package Database;

import Database.Genredocument;
import Database.Genrerecherche;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-16T20:39:51")
@StaticMetamodel(Genre.class)
public class Genre_ { 

    public static volatile CollectionAttribute<Genre, Genredocument> genredocumentCollection;
    public static volatile SingularAttribute<Genre, Integer> idGenre;
    public static volatile SingularAttribute<Genre, String> nomGenre;
    public static volatile CollectionAttribute<Genre, Genrerecherche> genrerechercheCollection;

}