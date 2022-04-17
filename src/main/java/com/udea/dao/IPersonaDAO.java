package com.udea.dao;

import com.udea.model.Persona;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Antonio
 */
@Repository
public interface IPersonaDAO extends CrudRepository<Persona, Long>{
}
 

   