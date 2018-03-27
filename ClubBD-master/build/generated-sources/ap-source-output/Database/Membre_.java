package Database;

import Database.Blacklist;
import Database.Connect;
import Database.Emprunt;
import Database.Statut;
import Database.Vote;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-28T01:02:00")
@StaticMetamodel(Membre.class)
public class Membre_ { 

    public static volatile SingularAttribute<Membre, Statut> idStatut;
    public static volatile CollectionAttribute<Membre, Vote> voteCollection;
    public static volatile SingularAttribute<Membre, String> mdp;
    public static volatile CollectionAttribute<Membre, Emprunt> empruntCollection1;
    public static volatile SingularAttribute<Membre, Integer> idMembre;
    public static volatile CollectionAttribute<Membre, Connect> connectCollection;
    public static volatile SingularAttribute<Membre, String> nom;
    public static volatile SingularAttribute<Membre, String> prenom;
    public static volatile SingularAttribute<Membre, String> email;
    public static volatile CollectionAttribute<Membre, Emprunt> empruntCollection;
    public static volatile CollectionAttribute<Membre, Blacklist> blacklistCollection;

}