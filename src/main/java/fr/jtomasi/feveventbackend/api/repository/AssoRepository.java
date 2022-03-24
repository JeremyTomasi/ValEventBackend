package fr.jtomasi.feveventbackend.api.repository;

import fr.jtomasi.feveventbackend.api.model.Asso;
import org.springframework.data.repository.CrudRepository;

public interface AssoRepository extends CrudRepository<Asso,Integer> {

    public Asso findAssoById(int id);

    public Asso findAssoByEmail(String email);

}
