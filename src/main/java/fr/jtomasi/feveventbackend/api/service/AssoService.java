package fr.jtomasi.feveventbackend.api.service;

import fr.jtomasi.feveventbackend.api.model.Asso;
import fr.jtomasi.feveventbackend.api.repository.AssoRepository;
import org.springframework.stereotype.Service;

@Service
public class AssoService {

    private final AssoRepository assoRepository;

    public AssoService(AssoRepository assoRepository) {
        this.assoRepository = assoRepository;
    }

    public Asso getAssoById(int id){
        return assoRepository.findAssoById(id);
    }

    public Asso getAssoByEmail(String email){
        return assoRepository.findAssoByEmail(email);
    }

    public Iterable<Asso> getAssos(){
        return assoRepository.findAll();
    }

    public void addAsso(Asso asso){
        assoRepository.save(asso);
    }

    public void updateAsso(int id, String name, String email, String description){
        Asso asso = assoRepository.findAssoById(id);
        if(asso != null){
            asso.setName(name);
            asso.setEmail(email);
            asso.setDescription(description);
            assoRepository.save(asso);
        }
    }
}
