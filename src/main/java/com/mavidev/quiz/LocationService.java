package com.mavidev.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LocationService {

    @Autowired
    private LocationRepository repo;

    public List<Location> listAll(){
        return repo.findAll();
    }

    public boolean save(Location location){
        Location exists = repo.findBySehirAndIlce(location.getSehir(), location.getIlce());
        if (exists == null){
            location.setBolge(location.getBolge().toUpperCase());
            repo.save(location);
            return true;
        }
        else{
            return false;
        }
    }

    public Location get(long id) {
        return repo.getOne(id);
    }

    public void delete(long id) {
        repo.deleteById(id);
    }
}
