/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jquery.service;

import java.util.List;
import jquery.entity.Partie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * @author admin
 */
@Service
public class ActualisationService {
    
    @Autowired
    PartieCrudService partieCrudService;
    
    
    @Scheduled(fixedDelay = 10000)
    public void MAJPartie(){
        System.out.println("==============================================================================");
        List<Partie> listeParties = (List<Partie>) partieCrudService.findAll();
        
        for(Partie p : listeParties){
            if(p.getEtatPartie()==false){
                commencerPartie(p);
            }
        }
        
    }
    
    public void commencerPartie(Partie p){
        if(p.getListeUtilisateurs().size()==2){
            p.setEtatPartie(true);
        }
    }
}
