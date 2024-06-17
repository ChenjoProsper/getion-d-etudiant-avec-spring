package com.example.Etudiant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Etudiant.models.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
	
}
