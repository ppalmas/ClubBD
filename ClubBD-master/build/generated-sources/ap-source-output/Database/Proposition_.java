package Database;

import Database.Vote;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-28T01:02:00")
@StaticMetamodel(Proposition.class)
public class Proposition_ { 

    public static volatile SingularAttribute<Proposition, String> nomProposition;
    public static volatile CollectionAttribute<Proposition, Vote> voteCollection;
    public static volatile SingularAttribute<Proposition, Integer> idProposition;
    public static volatile SingularAttribute<Proposition, String> commentaireProposition;

}