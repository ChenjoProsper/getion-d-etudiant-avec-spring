package com.example.Etudiant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Etudiant.models.Etudiant;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long>{

}
