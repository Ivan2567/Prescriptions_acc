package com.example.springserver.reps;

import com.example.springserver.entity.PreparatRecept;
import com.example.springserver.entity.Recept;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreparatReceptRep extends CrudRepository<PreparatRecept,Long> {
    List<PreparatRecept> findPreparatReceptsByReceptId (Long receptId);

}
