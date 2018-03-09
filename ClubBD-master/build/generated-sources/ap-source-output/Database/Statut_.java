package Database;

import Database.Membre;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-09T20:04:23")
@StaticMetamodel(Statut.class)
public class Statut_ { 

    public static volatile SingularAttribute<Statut, Integer> idStatut;
    public static volatile SingularAttribute<Statut, String> nomStatut;
    public static volatile CollectionAttribute<Statut, Membre> membreCollection;

}