package com.example.Etudiant.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Etudiant.models.Note;
import com.example.Etudiant.repository.NoteRepository;

@Service
public class NoteService {

	@Autowired
	NoteRepository noteRepository;
	
	public Note ajouterNote(Note note) {
		try {
			return noteRepository.save(note);			
		}catch(Exception e) {
			System.out.println(e);
			return null;
		}
    }
	
	public List<Note> getNotesParEtudiant(Long etudiantId) {
		try {
			List<Note> notes = new ArrayList<>();
			for(Note n:noteRepository.findAll()) {
				if(n.getEtudiant().getId() == etudiantId) {
					notes.add(n);
				}
			}
			return notes;
		}catch(Exception e) {
			return null;
		}
    }
	
	public List<Note> getNotesParMatiere(Long matiereId) {
		try {
			List<Note> notes = new ArrayList<>();
			for(Note n:noteRepository.findAll()) {
				if(n.getMatiere().getId() == matiereId) {
					notes.add(n);
				}
			}
			return notes;
		}catch(Exception e) {
			return null;
		}
    }
	
	public Note getNoteParIdEtudiantEtMatiere(Long etudiantId,Long matiereId) {
		for(Note n:noteRepository.findAll()) {
			if(n.getEtudiant().getId() == etudiantId && n.getMatiere().getId() == matiereId) {
				return n;
			}
		}
		return null;
	}

    public double calculerMoyenne(Long etudiantId) {
        List<Note> notes = getNotesParEtudiant(etudiantId);
        if (!notes.isEmpty()) {
        	int credits = 0;
        	double moyenne = 0;
        	for(Note note:notes) {
        		credits += note.getMatiere().getCredit();
        		moyenne += note.getNote()*note.getMatiere().getCredit();
        	}
        	moyenne /= credits;
            return moyenne;
        } else {
            return 0.0;
        }
    }
	
    public Note modifierNote(Long etudiantId,Long matiereId, double Nnote) {
        Note note = getNoteParIdEtudiantEtMatiere(etudiantId,matiereId);
        if (note != null) {
            note.setNote(Nnote);
            return noteRepository.save(note);
        } else {
        	System.out.println("Une erreur est survenu");
			return null;
        }
    }

    public void supprimerNote(Long etudiantId,Long matiereId) {
    	try {
    		Note note = getNoteParIdEtudiantEtMatiere(etudiantId,matiereId);
    		noteRepository.deleteById(note.getId());
    		System.out.println("Note spprimer");
    	}catch(Exception e) {
    		System.out.println("Une erreur est survenu");
    	}
    }
	
    public void supprimerNoteByEtudiantId(Long etudiantId) {
    	try {
    		List<Note> notes = getNotesParEtudiant(etudiantId);
    		for(Note note:notes) {
    			noteRepository.deleteById(note.getId());
    			System.out.println("Note spprimer");    			
    		}
    	}catch(Exception e) {
    		System.out.println("Une erreur est survenu");
    	}
    }

    public void supprimerNoteByMatiereId(Long matiereId) {
    	try {
    		List<Note> notes = getNotesParMatiere(matiereId);
    		for(Note note:notes) {
    			noteRepository.deleteById(note.getId());
    			System.out.println("Note spprimer");    			
    		}
    	}catch(Exception e) {
    		System.out.println("Une erreur est survenu");
    	}
    }
    
    public List<Note> getAll(){
    	return noteRepository.findAll();
    }
}
