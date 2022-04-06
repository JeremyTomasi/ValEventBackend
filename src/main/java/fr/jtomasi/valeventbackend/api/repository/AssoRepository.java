package fr.jtomasi.valeventbackend.api.repository;

import fr.jtomasi.valeventbackend.api.model.Asso;
import org.springframework.data.repository.CrudRepository;

public interface AssoRepository extends CrudRepository<Asso,Integer> {

    public Asso findAssoById(int id);

    public Asso findAssoByEmail(String email);

}
