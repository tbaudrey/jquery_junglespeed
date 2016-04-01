/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jquery.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import jquery.entity.Partie;
import jquery.entity.Utilisateur;
import jquery.service.NombreAleatoireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import jquery.service.PartieCrudService;
import jquery.service.UtilisateurCrudService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author tom
 */
@Controller
@RequestMapping("/partie")
public class PartieController {
    
    @Autowired
    private PartieCrudService partieCrudService;
    
    @Autowired
    private UtilisateurCrudService utilisateurCrudService;
    
    @Autowired
    private NombreAleatoireService nombreAleatoireService;
    
    
    @RequestMapping(value = "lister", method = RequestMethod.GET)
    public String lister(Model model){
        
//        Partie partie1 = new Partie();
//        partie1.setId(1L);
//        
//        Utilisateur utilisateur=new Utilisateur(1L, "thomas", 20L);
//        utilisateur.setPartie(partie1);
//        utilisateurCrudService.save(utilisateur);
//        
//        partie1.getListeUtilisateurs().add(utilisateur);

//        partieCrudService.save(partie1);
//        
//        Partie partie2 = new Partie();
//        partie2.setId(2L);
//        partieCrudService.save(partie2);
//        Partie partie3 = new Partie();
//        partie3.setId(3L);
//        partieCrudService.save(partie3);
//        Partie partie4 = new Partie();
//        partie4.setId(4L);
//        partieCrudService.save(partie4);
        
        model.addAttribute("parties", partieCrudService.findAll());
        return "/lister";
    }
    
    @RequestMapping(value = "rejoindre/{idPartie}", method = RequestMethod.GET)
    public String rejoindre(@PathVariable(value = "idPartie") long monId, Model model){
        
        Partie partie = new Partie();
        partie=partieCrudService.findOne(monId);
        
        model.addAttribute("partie",partie);
        model.addAttribute("utilisateur", new Utilisateur());
        
        return "rejoindre";
    }
    
    @RequestMapping(value = "rejoindre/{idPartie}", method = RequestMethod.POST)
    public String rejoindrePOST(@ModelAttribute("utilisateur") Utilisateur utilisateur, @PathVariable(value = "idPartie") long monId, Model model, HttpSession httpSession){
        
        Partie partie = partieCrudService.findOne(monId);
        utilisateur.setPartie(partie);
        utilisateur.setNbrCartes(20L);
        utilisateurCrudService.save(utilisateur);
        partie.getListeUtilisateurs().add(utilisateur);
        partieCrudService.save(partie);
        
        model.addAttribute("partie",partie);
        model.addAttribute("utilisateur",utilisateur);
        List<Utilisateur> listeUtilisateurs = new ArrayList();
        listeUtilisateurs=partie.getListeUtilisateurs();
        model.addAttribute("listeUtilisateurs",listeUtilisateurs);
        
        httpSession.setAttribute("joueur",utilisateur);
        
        return "/plateau";
    }
    
    @RequestMapping(value = "jouer/{idPartie}", method = RequestMethod.GET)
    public String jouer(@PathVariable(value = "idPartie") long monId, Model model, HttpSession httpSession){
        
        Utilisateur utilisateur= new Utilisateur();
        utilisateur=(Utilisateur) httpSession.getAttribute("joueur");
        
        Partie partie = partieCrudService.findOne(monId);
        List<Utilisateur> listeUtilisateurs = new ArrayList();
        listeUtilisateurs=partie.getListeUtilisateurs();
        
        List<String> listeCouleurs = new ArrayList();
        listeCouleurs.add(0, "yellow");
        listeCouleurs.add(1, "green");
        listeCouleurs.add(2, "black");
        listeCouleurs.add(3, "red");
        listeCouleurs.add(4, "blue");
        
        for(Utilisateur u : listeUtilisateurs){
            if(u.getNbrCartes()!=0){
                Long nbrCartes=u.getNbrCartes();
                u.setNbrCartes(nbrCartes-1L);
                int r = nombreAleatoireService.nombreAleatoire(0,4);
                System.out.println(r);
                u.setCouleurCarteActuelle(listeCouleurs.get(r));
                utilisateurCrudService.save(u);
            }
        }
        
        model.addAttribute("utilisateur",utilisateurCrudService.findOne(utilisateur.getId()));
        model.addAttribute("partie",partie);
        model.addAttribute("listeUtilisateurs",utilisateurCrudService.findAll());
        
        httpSession.setAttribute("joueur",utilisateurCrudService.findOne(utilisateur.getId()));

        return "/plateau";
    }
    
    @RequestMapping(value = "totem/{idPartie}", method = RequestMethod.GET)
    public String totem(@PathVariable(value = "idPartie") long monId, Model model, HttpSession httpSession){
        
        Utilisateur utilisateur= new Utilisateur();
        utilisateur=(Utilisateur) httpSession.getAttribute("joueur");
        
        Partie partie = partieCrudService.findOne(monId);
        List<Utilisateur> listeUtilisateurs = new ArrayList();
        listeUtilisateurs=partie.getListeUtilisateurs();
        long nbrJoueurs=listeUtilisateurs.size();
        long nbrCartesPartie =20*(listeUtilisateurs.size());
        long nbrCartesUtilisateur=utilisateur.getNbrCartes();
        long nbrCartesJoues=0L;
        
        Boolean gagne=false;
        for(Utilisateur u : listeUtilisateurs){
            nbrCartesJoues=nbrCartesJoues+u.getNbrCartes();
            if(u.getId()!=utilisateur.getId()){
                if(u.getCouleurCarteActuelle().equals(utilisateur.getCouleurCarteActuelle())){
                    gagne=true;
                    
                    if(nbrCartesUtilisateur==0L){
                        return "/gagne";
                    }
                    long nbrCartesU = u.getNbrCartes();

                    u.setNbrCartes(nbrCartesU+(nbrCartesPartie-(nbrCartesUtilisateur+nbrCartesU)));
                }
                u.setCouleurCarteActuelle("");
                utilisateurCrudService.save(u);
            }
        }
        utilisateur.setCouleurCarteActuelle("");
        
        if(gagne==false){
            utilisateur.setNbrCartes(nbrCartesUtilisateur+nbrCartesPartie-nbrCartesJoues);
        }
        utilisateurCrudService.save(utilisateur);
        if(utilisateur.getNbrCartes()==40L){
                        return "/perdu";
        }
        
        model.addAttribute("utilisateur",utilisateur);
        model.addAttribute("partie",partie);
        model.addAttribute("listeUtilisateurs",utilisateurCrudService.findAll());
        return "/plateau";
    }
}
