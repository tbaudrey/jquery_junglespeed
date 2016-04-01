/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jquery.service;

import org.springframework.stereotype.Service;

/**
 *
 * @author admin
 */
@Service
public class NombreAleatoireService {
    public int nombreAleatoire(int borneInf, int borneSup){
        int i=(int)(Math.random()*1000)%(borneSup+1-borneInf)+borneInf;
        return i;
    }
}
