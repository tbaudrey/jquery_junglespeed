/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jquery.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author admin
 */
@Entity
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nom;
    private long nbrCartes;
    private String couleurCarteActuelle;

    public String getCouleurCarteActuelle() {
        return couleurCarteActuelle;
    }

    public void setCouleurCarteActuelle(String couleurCarteActuelle) {
        this.couleurCarteActuelle = couleurCarteActuelle;
    }

    @ManyToOne
    @JoinColumn(name = "Partie_ID")
    private Partie partie;

    public Partie getPartie() {
        return partie;
    }

    public void setPartie(Partie partie) {
        this.partie = partie;
    }
    
    public Utilisateur() {
    }

    public Utilisateur(Long id, String nom, Long nbrCartes) {
        this.id = id;
        this.nom = nom;
        this.nbrCartes = nbrCartes;
    }

    public Utilisateur(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public long getNbrCartes() {
        return nbrCartes;
    }

    public void setNbrCartes(Long nbrCartes) {
        this.nbrCartes = nbrCartes;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jquery.entity.Utilisateur[ id=" + id + " ]";
    }
    
}
