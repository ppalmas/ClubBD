package Database;

import Database.Document;
import Database.Membre;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-30T10:26:01")
@StaticMetamodel(Emprunt.class)
public class Emprunt_ { 

    public static volatile SingularAttribute<Emprunt, Date> dateReserve;
    public static volatile SingularAttribute<Emprunt, Integer> idEmprunt;
    public static volatile SingularAttribute<Emprunt, Date> dateEmprunt;
    public static volatile SingularAttribute<Emprunt, Date> dateRetour;
    public static volatile SingularAttribute<Emprunt, Document> idDocument;
    public static volatile SingularAttribute<Emprunt, Membre> idMembre;
    public static volatile SingularAttribute<Emprunt, Date> dateRetourne;
    public static volatile SingularAttribute<Emprunt, Membre> idStaff;

}