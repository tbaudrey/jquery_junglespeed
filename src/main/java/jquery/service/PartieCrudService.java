/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jquery.service;

import org.springframework.data.repository.CrudRepository;
import jquery.entity.Partie;

/**
 *
 * @author tom
 */
public interface PartieCrudService extends CrudRepository<Partie, Long>{
    
}
