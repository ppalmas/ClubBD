/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

import Database.Createur;
import Database.Createurdocument;
import Database.Document;
import Database.Etat;
import Database.Genre;
import Database.Genredocument;
import Database.Serie;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Victouf
 */
public class DocumentManagerImpl implements DocumentManager {

    private EntityManagerFactory emf;
    private static DocumentManagerImpl theDocumentManager;

    private DocumentManagerImpl() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("ClubBDPU");
        }
    }

    public static DocumentManager getInstance() {
        if (theDocumentManager == null) {
            theDocumentManager = new DocumentManagerImpl();
        }
        return theDocumentManager;
    }

    /**
     * Recherche des documents par un mot clé
     *
     * @param mot
     * @return liste de documents
     */
    @Override
    public List<Document> findDocumentBy1WordTitle(String mot) {
        EntityManager em = emf.createEntityManager();
        mot = "%" + mot + "%";
        Query q = em.createQuery("SELECT d FROM Document d WHERE  UPPER(d.titre) LIKE UPPER(:mot)");
        q.setParameter("mot", mot);
        List l = q.getResultList();
        return l;
    }

    /**
     * Recherche un document suivant un liste de critères au format string :
     * dans cette liste, le premier élément est le titre, le second l'auteur, le
     * troisième la cote
     *
     * @param criteres
     * @return
     */
    @Override
    public List<Document> findDocumentCr(ArrayList<String> criteres) {

        //recherche un document selon les criteres spécifiés
        EntityManager em = emf.createEntityManager();
        List l = null;
        Query query;
        //structure de la liste critere : (0)titre (1)serie (2)cote
        if (criteres.get(0) != "" || criteres.get(1) != "" || criteres.get(2) != "") {
        
        if (criteres.get(1) != "") {
                query = em.createQuery("SELECT d FROM Document d WHERE UPPER(d.titre) LIKE UPPER(:titre) AND UPPER(d.idSerie.nomSerie) LIKE UPPER(:serie) AND UPPER(d.cote) LIKE UPPER(:cote) ");
        System.out.println("serie non null");
        } else {
                query = em.createQuery("SELECT d FROM Document d WHERE UPPER(d.titre) LIKE UPPER(:titre) AND UPPER(d.cote) LIKE UPPER(:cote) ");
        System.out.println("serie null");
        }
        
        //si titre
        if (criteres.get(0) != "") {
        //on recherche un document dont le titre comporte le critère
        
                query.setParameter("titre", "%" + criteres.get(0) + "%");
        } else {
                query.setParameter("titre", "%");
        }
        //si serie
        if (criteres.get(1) != "") {
                query.setParameter("serie", "%" + criteres.get(1) + "%");
        }
        
        //si cote
        if (criteres.get(2) != "") {
                query.setParameter("cote", "%" + criteres.get(2) + "%");
        } else {
                query.setParameter("cote", "%");
        }
        
        try {
        
                l = query.getResultList();
        } catch (Exception e) {
                System.out.println("erreur syntaxe requete // " + query.toString());
        }

        }
        return l;
    }

    /**
     * Renvoie l'id max des documents de la bdd
     *
     * @return id maximum des documents de la base de données
     */
    @Override
    public int getMaxId() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createNamedQuery("Document.findAll", Document.class);
        List l = q.getResultList();
        return (l.size() + 1);
    }

    /**
     * Retrouver un document par son id
     *
     * @param id id du document
     * @return Document correspondant
     */
    @Override
    public Document findDocument(int id) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT d FROM Document d WHERE  d.idDocument=:id");
        q.setParameter("id", id);
        List l = q.getResultList();
        return l.isEmpty() ? null : (Document) l.get(0);
    }

    /**
     * Insertion d'un document dans la bdd
     *
     * @param titre
     * @param cote
     * @param etat
     * @param serie
     * @param numero
     * @param desc
     * @param comm
     * @param img
     */
    @Override
    public void insert(String titre, String cote, String etat, String serie, String numero, String desc, String comm, String img, String cnp0, String cnp1, String cnp2, String cnp3, String cnp4, String genre) {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Document d = new Document();
        d.setTitre(titre);
        d.setCote(cote);

        d.setCommentaire(comm);
        d.setDescription(desc);
        try {
            d.setNumero(Integer.parseInt(numero));
        } catch (Exception e) {
        }
        d.setImageDocument(img);

        //pour l'etat
        Query q = em.createQuery("SELECT e FROM Etat e WHERE  e.idEtat=:etat");
        q.setParameter("etat", Integer.parseInt(etat));

        List l = q.getResultList();

        d.setIdEtat((Etat) l.get(0));
        //pour la serie

        try {
            Query q2 = em.createQuery("SELECT s FROM Serie s WHERE  s.nomSerie=:serie");
            q2.setParameter("serie", serie);
            List l2 = q2.getResultList();
            d.setIdSerie((Serie) l2.get(0));
        } catch (Exception e) {
            Query q2 = em.createNamedQuery("Serie.findByIdSerie", Serie.class);
            q2.setParameter("idSerie", 1);
            List l2 = q2.getResultList();
            d.setIdSerie((Serie) l2.get(0));
        }
        //Insertion
        em.persist(d);

        //pour les créateurs
        Query q3 = em.createQuery("SELECT c FROM Createur c"); //on recup tous les createurs et on compare avec la concatenation nom-prenom
        List<Createur> l3 = q3.getResultList();
        //comparaison
        Boolean endcnp0 = true;
        Boolean endcnp1 = true;
        Boolean endcnp2 = true;
        Boolean endcnp3 = true;
        Boolean endcnp4 = true;

        for (int i = 0; i < l3.size(); i++) {
            try {
                if (endcnp0 && (l3.get(i).getNomCreateur() + " " + l3.get(i).getPrenomCreateur()).equals(cnp0.split(",")[0])) {

                    Createurdocument cd = new Createurdocument();
                    cd.setIdCreateur(l3.get(i));
                    cd.setIdDocument(d);
                    try {
                        cd.setPoste(cnp0.split(",")[1]);
                    } catch (Exception e) {
                        cd.setPoste("");
                    }
                    em.persist(cd);
                    endcnp0 = false;

                }
            } catch (Exception e) {
            }
            try {
                if (endcnp1 && (l3.get(i).getNomCreateur() + " " + l3.get(i).getPrenomCreateur()).equals(cnp1.split(",")[0])) {
                    Createurdocument cd = new Createurdocument();
                    cd.setIdCreateur(l3.get(i));
                    cd.setIdDocument(d);

                    //poste
                    try {
                        cd.setPoste(cnp1.split(",")[1]);
                    } catch (Exception e) {
                        cd.setPoste("");
                    }
                    em.persist(cd);

                    endcnp1 = false;

                }
            } catch (Exception e) {
            }
            try {
                if (endcnp2 && (l3.get(i).getNomCreateur() + " " + l3.get(i).getPrenomCreateur()).equals(cnp2.split(",")[0])) {
                    Createurdocument cd = new Createurdocument();
                    cd.setIdCreateur(l3.get(i));
                    cd.setIdDocument(d);
                    try {
                        cd.setPoste(cnp2.split(",")[1]);
                    } catch (Exception e) {
                        cd.setPoste("");
                    }
                    em.persist(cd);

                    endcnp2 = false;

                }
            } catch (Exception e) {
            }
            try {
                if (endcnp3 && (l3.get(i).getNomCreateur() + " " + l3.get(i).getPrenomCreateur()).equals(cnp3.split(",")[0])) {
                    Createurdocument cd = new Createurdocument();
                    cd.setIdCreateur(l3.get(i));
                    cd.setIdDocument(d);
                    try {
                        cd.setPoste(cnp3.split(",")[1]);
                    } catch (Exception e) {
                        cd.setPoste("");
                    }
                    em.persist(cd);

                    endcnp3 = false;

                }
            } catch (Exception e) {
            }
            try {
                if (endcnp4 && (l3.get(i).getNomCreateur() + " " + l3.get(i).getPrenomCreateur()).equals(cnp4.split(",")[0])) {
                    Createurdocument cd = new Createurdocument();
                    cd.setIdCreateur(l3.get(i));
                    cd.setIdDocument(d);
                    try {
                        cd.setPoste(cnp4.split(",")[1]);
                    } catch (Exception e) {
                        cd.setPoste("");
                    }
                    em.persist(cd);

                    endcnp4 = false;

                }
            } catch (Exception e) {
            }
        }

        //pour genre
        //on fait une liste en parsant genre
        genre = genre.replace(" ", "");
        String[] lgenre = genre.split(",");

        //oncheck si chaque mot existe deja, sinon on le rajoute
        for (int i = 0; i < lgenre.length; i++) {
            try {
                Query q5 = em.createQuery("SELECT g FROM Genre g WHERE g.nomGenre=:nom");
                q5.setParameter("nom", lgenre[i]);

                //on créé genredoc
                Genredocument gd = new Genredocument();
                gd.setIdDocument(d);
                gd.setIdGenre((Genre) q5.getResultList().get(0));
                em.persist(gd);

            } catch (Exception e) {//on trouve pas le genre, on le crée
                Genre g = new Genre();
                g.setNomGenre(lgenre[i]);
                em.persist(g);

                //on créé genredoc
                Genredocument gd = new Genredocument();
                gd.setIdDocument(d);
                gd.setIdGenre(g);
                em.persist(gd);

            }

        }

        em.getTransaction().commit();

    }

    
    
    @Override
    public Boolean exist(String cote){
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT d FROM Document d WHERE UPPER(d.cote) LIKE UPPER(:cote)");
        q.setParameter("cote", cote);
        System.out.println("parametre ok");
        System.out.println(q.getResultList().size());
        if (q.getResultList().size() != 0){
            return true;
        }
        else {return false;}
        
    }
    
    /**
     * Update des infos d'un livre
     *
     * @param iddoc
     * @param titre
     * @param cote
     * @param etat
     * @param serie
     * @param numero
     * @param desc
     * @param comm
     * @param img
     */
    @Override
    public void update(String iddoc, String titre, String cote, String etat, String serie, String numero, String desc, String comm, String img, String cnp0, String cnp1, String cnp2, String cnp3, String cnp4, String genre) {

        //on recupere le document avec l'id
        EntityManager em = emf.createEntityManager();

        Document d = em.find(Document.class, Integer.parseInt(iddoc));

        em.getTransaction().begin();

        d.setTitre(titre);
        d.setCote(cote);
        d.setCommentaire(comm);
        d.setDescription(desc);

        try {
            d.setNumero(Integer.parseInt(numero));
        } catch (Exception e) {
        }
        d.setImageDocument(img);

        //pour l'etat
        Query q = em.createQuery("SELECT e FROM Etat e WHERE  e.idEtat=:etat");
        q.setParameter("etat", Integer.parseInt(etat));
        List l = q.getResultList();

        d.setIdEtat((Etat) l.get(0));
        //pour la serie

        try {
            Query q2 = em.createQuery("SELECT s FROM Serie s WHERE  s.nomSerie=:serie");
            q2.setParameter("serie", serie);
            List l2 = q2.getResultList();

            d.setIdSerie((Serie) l2.get(0));
        } catch (Exception e) {

            Query q2 = em.createNamedQuery("Serie.findByIdSerie", Serie.class);
            q2.setParameter("idSerie", 1);
            List l2 = q2.getResultList();
            d.setIdSerie((Serie) l2.get(0));
        }

        //pour les createurs
        Query q3 = em.createQuery("SELECT c FROM Createur c"); //on recup tous les createurs et on compare avec la concatenation nom-prenom
        List<Createur> l3 = q3.getResultList();

        //comparaison
        Boolean endcnp0 = true;
        Boolean endcnp1 = true;
        Boolean endcnp2 = true;
        Boolean endcnp3 = true;
        Boolean endcnp4 = true;

        //solution simple pas tres opti : on remove tous les createursdocument de la collection et on les recreer...
        //getcreateurdoccollectionmarche pas visiblement (probleme de persistence ?
        //solution requete de tous
        Query q4 = em.createQuery("SELECT cd FROM Createurdocument cd WHERE cd.idDocument=:doc");
        q4.setParameter("doc", d);
        List<Createurdocument> lcrea = q4.getResultList();

        for (int j = 0; j < lcrea.size(); j++) {
            em.remove(lcrea.get(j));
        }

        for (int i = 0; i < l3.size(); i++) {
            try {
                if (endcnp0 && (l3.get(i).getNomCreateur() + " " + l3.get(i).getPrenomCreateur()).equals(cnp0.split(",")[0])) {

                    Createurdocument cd = new Createurdocument();
                    cd.setIdCreateur(l3.get(i));
                    cd.setIdDocument(d);
                    try {
                        cd.setPoste(cnp0.split(",")[1]);
                    } catch (Exception e) {
                        cd.setPoste("");
                    }
                    em.persist(cd);
                    endcnp0 = false;

                }
            } catch (Exception e) {
            }
            try {
                if (endcnp1 && (l3.get(i).getNomCreateur() + " " + l3.get(i).getPrenomCreateur()).equals(cnp1.split(",")[0])) {
                    Createurdocument cd = new Createurdocument();
                    cd.setIdCreateur(l3.get(i));
                    cd.setIdDocument(d);
                    try {
                        cd.setPoste(cnp1.split(",")[1]);
                    } catch (Exception e) {
                        cd.setPoste("");
                    }
                    em.persist(cd);

                    endcnp1 = false;

                }
            } catch (Exception e) {
            }
            try {
                if (endcnp2 && (l3.get(i).getNomCreateur() + " " + l3.get(i).getPrenomCreateur()).equals(cnp2.split(",")[0])) {
                    Createurdocument cd = new Createurdocument();
                    cd.setIdCreateur(l3.get(i));
                    cd.setIdDocument(d);
                    try {
                        cd.setPoste(cnp2.split(",")[1]);
                    } catch (Exception e) {
                        cd.setPoste("");
                    }
                    em.persist(cd);

                    endcnp2 = false;

                }
            } catch (Exception e) {
            }
            try {
                if (endcnp3 && (l3.get(i).getNomCreateur() + " " + l3.get(i).getPrenomCreateur()).equals(cnp3.split(",")[0])) {
                    Createurdocument cd = new Createurdocument();
                    cd.setIdCreateur(l3.get(i));
                    cd.setIdDocument(d);
                    try {
                        cd.setPoste(cnp3.split(",")[1]);
                    } catch (Exception e) {
                        cd.setPoste("");
                    }
                    em.persist(cd);

                    endcnp3 = false;

                }
            } catch (Exception e) {
            }
            try {
                if (endcnp4 && (l3.get(i).getNomCreateur() + " " + l3.get(i).getPrenomCreateur()).equals(cnp4.split(",")[0])) {
                    Createurdocument cd = new Createurdocument();
                    cd.setIdCreateur(l3.get(i));
                    cd.setIdDocument(d);
                    try {
                        cd.setPoste(cnp4.split(",")[1]);
                    } catch (Exception e) {
                        cd.setPoste("");
                    }
                    em.persist(cd);

                    endcnp4 = false;

                }
            } catch (Exception e) {
            }
        }

        //pour genre
        //on fait une liste en parsant genre
        genre = genre.replace(" ", "");
        String[] lgenre = genre.split(",");

        //on suppr tous les genredocuments associés
        List<Genredocument> lg = new ArrayList(d.getGenredocumentCollection());
        for (int i = 0; i < lg.size(); i++) {
            em.remove(lg.get(i));
        }

        //oncheck si chaque mot existe deja, sinon on le rajoute
        for (int i = 0; i < lgenre.length; i++) {
            try {
                Query q5 = em.createQuery("SELECT g FROM Genre g WHERE g.nomGenre=:nom");
                q5.setParameter("nom", lgenre[i]);

                //on créé genredoc
                Genredocument gd = new Genredocument();
                gd.setIdDocument(d);
                gd.setIdGenre((Genre) q5.getResultList().get(0));
                em.persist(gd);

            } catch (Exception e) {//on trouve pas le genre, on le crée
                Genre g = new Genre();
                g.setNomGenre(lgenre[i]);
                em.persist(g);

                //on créé genredoc
                Genredocument gd = new Genredocument();
                gd.setIdDocument(d);
                gd.setIdGenre(g);
                em.persist(gd);

            }
        }

        em.getTransaction().commit();

    }

    /**
     * Retourne la liste des créateurs associés à un document
     *
     * @param id du document
     * @return
     */
    public ArrayList<Createurdocument> findCreateur(int id) {
        ArrayList<Createurdocument> cr = new ArrayList();
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT cd FROM Createurdocument cd INNER JOIN Document d ON "
                + "(cd.idDocument.idDocument = d.idDocument) WHERE d.idDocument=:id");
        q.setParameter("id", id);
        List l = q.getResultList();
        for (Object o : l) {
            cr.add((Createurdocument) o);
        }
        return cr;
    }

    /**
     * Retourne un booléen ; vrai si le document en paramètre est disponible,
     * faux sinon : le champ dateRetourne est nul donc le document est emprunté,
     * ou le champ date réservation est non nul et alors le document est réservé
     *
     * @param id du document
     * @return
     */
    @Override
    public boolean isAvailable(int id) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT e FROM Emprunt e INNER JOIN Document d ON (e.idDocument.idDocument = d.idDocument)"
                + " WHERE  (d.idDocument=:id AND e.dateRetourne IS NULL) ");
//La condition date retourné nul suffit : si le document est réservé, il n'y a pas de date indiquée,
// si le document est emprunté, il n'y a pas de date non plus
        q.setParameter("id", id);
        List l = q.getResultList();
        return l.isEmpty();

    }

    /**
     * Recherche un document suivant un liste de critères au format string :
     *
     *
     * @param criteres
     * @param b si b vaut true, l'utilisateur a complété le champ toute
     * recherche (pas de critères)
     * @return
     */
    @Override
    public List<Document> findDocumentSearch(ArrayList<String> criteres, Boolean b) {
        //recherche un document selon les criteres spécifiés
        EntityManager em = emf.createEntityManager();
        String SQL = "SELECT DISTINCT d FROM Document d";
        String SQL_part = "";
        String SQL2 = "";
        String titre = "";
        String serie = "";
        String sujet = "";
        String auteur = "";
        List l = null;
        //structure de la liste critere : (0)titre (1)sujet (2)serie (3)auteur OU (0) all

        if (b) {
            //renvoi de la liste suivant tous les critères
            String all = criteres.get(0);
            SQL_part = " (LOWER(d.titre) LIKE LOWER(:all) OR"
                    + " LOWER(d.description) LIKE LOWER(:all) "
                    + " OR LOWER(cd.idCreateur.prenomCreateur) LIKE (:all) OR LOWER(cd.idCreateur.nomCreateur) LIKE (:all)"
                    + " OR LOWER(d.idSerie.nomSerie) LIKE LOWER(:all))";
            SQL = SQL + " INNER JOIN Createurdocument cd ON (d.idDocument = cd.idDocument.idDocument) " + "WHERE" + SQL_part;
            try {
                Query q = em.createQuery(SQL);
                q.setParameter("all", "%" + all + "%");
                l = q.getResultList();
            } catch (Exception e) {
                l = null;
            }

        } else if (criteres.size() >= 1) {
            SQL_part = "";
            //renvoi suivant tous les critères
            for (int i = 0; i < criteres.size(); i++) {
                //On récupère le critère i
                String[] list = criteres.get(i).split(":");

// Si la 2eme case du critère est vide ou n'existe pas, alors le champ input était vide
//D'où le test if (list.length >=2 pour chaque item
                //Pour le titre (i=0)
                if (i == 0) {
                    if (list.length >= 2) {
                        titre = list[1];
                    } else {
                        titre = "";
                    }
                    SQL_part = " LOWER(d.titre) LIKE LOWER(:titre)";
                    //Si le titre n'est pas indiqué, on met quand même un bout de requête
                    //pour ne pas avoir de problème avec les autres critères et le 'AND'

                    //Pour le sujet/description (id =1)
                } else if (i == 1) {
                    if (list.length >= 2) {
                        sujet = list[1];
                    } else {
                        sujet = "";
                    }
                    SQL_part = SQL_part + " AND LOWER(d.description) LIKE LOWER(:sujet)";
                    //Pour la série (id=2)
                } else if (i == 2) {
                    if (list.length >= 2) {
                        serie = list[1];
                    } else {
                        serie = "";
                    }
                    //On reprend la query sql de base
                    ///!\ SI PAS d'IDSERIE RENSEIGNE, L'ouvrage n'apparait pas dans la liste
                    SQL_part = SQL_part + " AND (LOWER(d.idSerie.nomSerie) LIKE LOWER(:serie))";

                    //Pour les auteurs (id=3)
                } else if (i == 3) {
                    if (list.length >= 2) {
                        auteur = list[1];
                        System.out.println("auteur:" + auteur);
                    } else {
                        auteur = "";
                        System.out.println("auteur:" + auteur);
                    }
                    SQL2 = SQL2
                            + " JOIN Createurdocument cd ON (d.idDocument = cd.idDocument.idDocument)";
                    //SQL2 = SQL2
                    //        + " (JOIN Createurdocument cd ON (d.idDocument = cd.idDocument.idDocument)" + " (JOIN Createur c ON "
                    //        + "(cd.idCreateur.idCreateur = c.idCreateur)))";
                    SQL_part = SQL_part + " AND (LOWER(cd.idCreateur.nomCreateur) LIKE LOWER(:auteur) OR LOWER(cd.idCreateur.prenomCreateur) LIKE LOWER(:auteur))";

                }

            }
            SQL = SQL + SQL2 + " WHERE" + SQL_part;
            try {
                Query q = em.createQuery(SQL);
                q.setParameter("titre", "%" + titre + "%");
                q.setParameter("sujet", "%" + sujet + "%");
                q.setParameter("serie", "%" + serie + "%");
                q.setParameter("auteur", "%" + auteur + "%");
                l = q.getResultList();
                System.out.println(SQL);
                System.out.println(q);
                System.out.println("titre = " + titre);
                System.out.println("serie= " + serie);
                System.out.println("auteur= " + auteur);
                System.out.println(l);
            } catch (Exception e) {
                System.out.println("erreur syntaxe requete 2 // " + SQL);
                System.out.println(e);
                l = null;
            }
        } else { //Renvoi de tous les documents de la bdd
            try {
                Query q = em.createQuery(SQL);
                l = q.getResultList();
            } catch (Exception e) {
                l = null;
                System.out.println("erreur pour renvoyer TOUS les docs de la bdd//" + SQL);
            }
        }

        return l;
    }
    
    /**
     * Retourne la liste des genres associés à un document
     *
     * @param id du document
     * @return
     */
    @Override
    public ArrayList<Genredocument> findGenre(int id) {
        ArrayList<Genredocument> gr = new ArrayList();
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT gd FROM Genredocument gd INNER JOIN Document d ON "
                + "(gd.idDocument.idDocument = d.idDocument) WHERE d.idDocument=:id");
        q.setParameter("id", id);
        List l = q.getResultList();
        for (Object o : l) {
            gr.add((Genredocument) o);
        }
        return gr;
    }
    
}
