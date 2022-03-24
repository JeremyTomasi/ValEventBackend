package fr.jtomasi.feveventbackend.repository;

import fr.jtomasi.feveventbackend.model.Asso;
import org.springframework.data.repository.CrudRepository;

public interface AssoRepository extends CrudRepository<Asso,Integer> {

    public Asso findAssoById(int id);

    public Asso findAssoByEmail(String email);

}
