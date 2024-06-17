package com.example.Etudiant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Etudiant.models.Matiere;

@Repository
public interface MatiereRepository extends JpaRepository<Matiere, Long> {

}
