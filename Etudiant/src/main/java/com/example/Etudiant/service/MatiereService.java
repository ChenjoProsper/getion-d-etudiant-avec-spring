package com.example.Etudiant.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Etudiant.models.Matiere;
import com.example.Etudiant.repository.MatiereRepository;

@Service
public class MatiereService {
	@Autowired
	NoteService noteService;
	
	@Autowired
	MatiereRepository matiereRepository;
	
	public List<Matiere> getAllMatiere(){
		return matiereRepository.findAll();
	}
	
	public Matiere ajouterMatiere(Matiere matiere) {
		return matiereRepository.save(matiere);
	}
	
	public Matiere modifierMatiere(Long id, String nom,int credit) {
		Optional<Matiere> matiereOptional = matiereRepository.findById(id);
		if(matiereOptional.isPresent()) {
			Matiere matiere = matiereOptional.get();
			matiere.setNom(nom);
			matiere.setCredit(credit);
			return matiereRepository.save(matiere);
		}else {
			System.out.println("Une erreur est survenu");
			return null;
		}
	}

	public void supprimmerMatiere(Long id) {
		try {
			if(matiereRepository.existsById(id)) {
				noteService.supprimerNoteByMatiereId(id);
				matiereRepository.deleteById(id);
				System.out.println("Matiere supprimé avec succès.");
            } else {
                System.out.println("La Matiere avec l'ID " + id + " n'existe pas.");
            }
			
		}catch(Exception e) {
			System.out.println("Un problème est survenu lors de la suppression de la matiere : " + e.getMessage());
		}
	}
	
	public Optional<Matiere> rechercherMatiere(Long id) {
		try {
			if(matiereRepository.existsById(id)) {
				return matiereRepository.findById(id);				
			}else {
				System.out.println("La matiere avec l'ID " + id + " n'existe pas.");
				return null;
			}
				
		}catch(Exception e) {
			System.out.println("Un problème est survenu lors de la recherche de la matiere : " + e.getMessage());
    		return null;
		}
	}

}
