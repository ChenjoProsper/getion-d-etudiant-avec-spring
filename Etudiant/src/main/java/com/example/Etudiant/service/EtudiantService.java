package com.example.Etudiant.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Etudiant.models.Etudiant;
import com.example.Etudiant.models.Note;
import com.example.Etudiant.repository.EtudiantRepository;

@Service
public class EtudiantService {
	@Autowired
	NoteService noteService;

	@Autowired
    private EtudiantRepository etudiantRepository;

    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }

    public void createEtudiant(Etudiant etudiant) {
        etudiantRepository.save(etudiant);
    }
    
    public void supprimerEtudiant(Long id) {
        try {
            if (etudiantRepository.existsById(id)) {
            	noteService.supprimerNoteByEtudiantId(id);
                etudiantRepository.deleteById(id);
                System.out.println("Étudiant supprimé avec succès.");
            } else {
                System.out.println("Étudiant avec l'ID " + id + " n'existe pas.");
            }
        } catch (Exception e) {
            System.out.println("Un problème est survenu lors de la suppression de l'étudiant : " + e.getMessage());
        }
    }

    public Optional<Etudiant> rechercherEtudiant(Long id) {
    	try {
    		if (etudiantRepository.existsById(id)) {
    			return etudiantRepository.findById(id);
    		} else {
                System.out.println("Étudiant avec l'ID " + id + " n'existe pas.");
                return null;
    		}
    	}catch(Exception e) {
    		System.out.println("Un problème est survenu lors de la recherche de l'étudiant : " + e.getMessage());
    		return null;
    	}
    }
    
    public Etudiant modifierEtudiant(Long id, String nom, String prenom, int age, int niveau){
    	Optional<Etudiant> etudiantOptional = etudiantRepository.findById(id);
		if(etudiantOptional.isPresent()) {
			Etudiant etudiant = etudiantOptional.get();
			etudiant.setNom(nom);
			etudiant.setPrenom(prenom);
			etudiant.setAge(age);
			etudiant.setNiveau(niveau);
			return etudiantRepository.save(etudiant);
		} else {
            System.out.println("Étudiant avec l'ID " + id + " n'existe pas.");
            return null;
        }
    }

    public Etudiant monterDeNiveau(Long id) {
    	try {
			if(noteService.calculerMoyenne(id)>=10) {
    			Etudiant etud = rechercherEtudiant(id).get();
    			int niveau =(int)etud.getNiveau() + 1;
    			System.out.println(etud.getNom()+" "+etud.getPrenom()+" va passer au niveau "+niveau);
    			etud.setNiveau(etud.getNiveau()+1);
    			
    			noteService.supprimerNoteByEtudiantId(id);
    			return etudiantRepository.save(etud);
    		}else {
    			System.out.println("L'etudiant ne peut pas passer au niveau superieur");
    			return null;
    		}
    	}catch(Exception e) {
    		System.out.println(e);
    		return null;
    	}
    }
    
    public void Orienter(Long etudiantId) {
        List<Note> notes = noteService.getNotesParEtudiant(etudiantId);
        if (!notes.isEmpty()) {
        	Note max = new Note();
        	max.setNote(0);
            for(Note n:notes) {
            	if(n.getNote()>max.getNote()) {
            		max = n;
            	}
            }
            System.out.println("Vous devrier aller faire "+max.getMatiere().getNom());
        }else {
        	System.out.println("Nous avons besoin de plus de donnees concernant vos notes");
        }
    }
}
