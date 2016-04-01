/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jquery.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author admin
 */
@Entity
public class Partie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Boolean etatPartie;
    private CouleurCarte couleurCarte;
    
    @OneToMany (mappedBy = "partie")
    private List<Utilisateur> listeUtilisateurs = new ArrayList<>();

    public List<Utilisateur> getListeUtilisateurs() {
        return listeUtilisateurs;
    }

    public void setListeUtilisateurs(List<Utilisateur> listeUtilisateurs) {
        this.listeUtilisateurs = listeUtilisateurs;
    }

    public Partie() {
    }

    public Partie(Boolean etatPartie) {
        this.etatPartie = etatPartie;
    }
    
    public Partie(Long id, Boolean etatPartie, CouleurCarte couleurCarte) {
        this.id = id;
        this.etatPartie = etatPartie;
        this.couleurCarte = couleurCarte;
    }

    public Boolean getEtatPartie() {
        return etatPartie;
    }

    public void setEtatPartie(Boolean etatPartie) {
        this.etatPartie = etatPartie;
    }

    public CouleurCarte getCouleurCarte() {
        return couleurCarte;
    }

    public void setCouleurCarte(CouleurCarte couleurCarte) {
        this.couleurCarte = couleurCarte;
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
        if (!(object instanceof Partie)) {
            return false;
        }
        Partie other = (Partie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jquery.entity.Partie[ id=" + id + " ]";
    }
    
}
