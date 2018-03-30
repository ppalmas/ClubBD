package Database;

import Database.Membre;
import Database.Proposition;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-30T10:26:01")
@StaticMetamodel(Vote.class)
public class Vote_ { 

    public static volatile SingularAttribute<Vote, Integer> idVote;
    public static volatile SingularAttribute<Vote, Proposition> idProposition;
    public static volatile SingularAttribute<Vote, Membre> idMembre;

}