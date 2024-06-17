package com.example.Etudiant;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.Etudiant.models.Etudiant;
import com.example.Etudiant.models.Matiere;
import com.example.Etudiant.models.Note;
import com.example.Etudiant.service.EtudiantService;
import com.example.Etudiant.service.MatiereService;
import com.example.Etudiant.service.NoteService;

@SpringBootTest
class EtudiantApplicationTests {
    
    @Autowired
    private EtudiantService etudiantService;
    @Autowired
    private MatiereService matiereService;
    @Autowired
    private NoteService noteService;
    Scanner sc = new Scanner(System.in);
    
    @Test
    void contextLoads() {
		int choix;
		boolean tr= true;
        String nom;
        String prenom;
        Long id;
        int age;
        int niveau;
        Double note;
        Long id2;
        int credit;
		
		while(tr) {
			System.out.println("\n\n\n***************MenuPrincipale***************\n\n");
			System.out.println("1) Ajouter un etudiant");
			System.out.println("2) Modifier les informations d'un etudiant");
			System.out.println("3) Spprimer un etudiant");
			System.out.println("4) Rechercher un etudiant");
			System.out.println("5) Afficher tout les etudiants");
			System.out.println("6) Ajouter une matiere");
			System.out.println("7) Modifier les informations d'une matiere");
			System.out.println("8) Rechercher une matiere");
			System.out.println("9) Supprimer une matiere");
			System.out.println("10) Afficher toutes les matieres");
			System.out.println("11) Enregistrer une nouvelle note");
			System.out.println("12) Modifier les informations d'une note");
			System.out.println("13) Supprimer une note");
			System.out.println("14) Faire monter un etudiant de niveau");
			System.out.println("15) Orienter un etudiant");
			System.out.println("0) Quitter");
			System.out.print("Choix: ");
			choix = sc.nextInt();
			
			switch(choix) {
				case 1:
					System.out.print("Nom: ");
			        nom = sc.next();
			        
			        System.out.print("Prenom: ");
			        prenom = sc.next();
			        
			        System.out.print("Age: ");
			        age = sc.nextInt();
			        
			        System.out.print("Niveau: ");
			        niveau = sc.nextInt();
			        
			        Etudiant etudiant = new Etudiant();
			        etudiant.setNom(nom);
			        etudiant.setPrenom(prenom);
			        etudiant.setAge(age);
			        etudiant.setNiveau(niveau);
			        
			        etudiantService.createEtudiant(etudiant);
			        System.out.println("Etudiant ajouter");
					break;
				case 2:
					System.out.print("Id: ");
			        id = sc.nextLong();
					if(etudiantService.rechercherEtudiant(id).isPresent()) {
						System.out.print("Nom: ");
				        nom = sc.next();
				        
				        System.out.print("Prenom: ");
				        prenom = sc.next();
				        
				        System.out.print("Age: ");
				        age = sc.nextInt();
				        
				        System.out.print("Niveau: ");
				        niveau = sc.nextInt();
				        
				        etudiantService.modifierEtudiant(id, nom, prenom, age, niveau);
				        System.out.println("Etudiant modifier");
					}else {
						System.out.println("Aucun etudiant n'a ete trouver");
					}
					break;
				case 3:
					System.out.print("Id: ");
			        id = sc.nextLong();
			        etudiantService.supprimerEtudiant(id);
					break;
				case 4:
					System.out.print("Id: ");
			        id = sc.nextLong();
			        Optional<Etudiant> etud = etudiantService.rechercherEtudiant(id);
			        try{
			        	etud.toString();
			        	System.out.println("Moyenne: "+noteService.calculerMoyenne(id));
			        }catch(Exception e) {
			        	
			        }
					break;
				case 5:
			        List<Etudiant> etudiants = etudiantService.getAllEtudiants();
			        for (Etudiant e : etudiants) {
			            e.toString();
			        }
					break;
				case 6:
					System.out.print("Nom: ");
			        nom = sc.next();
			        
			        System.out.print("Credit: ");
			        credit = sc.nextInt();
			        
			        Matiere matiere = new Matiere();
			        matiere.setNom(nom);
			        matiere.setCredit(credit);
			        
			        matiereService.ajouterMatiere(matiere);
			        
			        System.out.println("Etudiant ajouter");
					break;
				case 7:
					System.out.print("Id: ");
			        id = sc.nextLong();
			        
			        if(matiereService.rechercherMatiere(id).isPresent()) {
			        	System.out.print("Nom: ");
				        nom = sc.next();
				        
				        System.out.print("Credit: ");
				        credit = sc.nextInt();
				        matiereService.modifierMatiere(id, nom, credit);
				        System.out.println("Etudiant modifier");
			        }else {
						System.out.println("Aucun matiere n'a ete trouver");
					}
					break;
				case 8:
					System.out.print("Id: ");
			        id = sc.nextLong();
			        Optional<Matiere> mat = matiereService.rechercherMatiere(id);
			        try {
			        	mat.toString();
			        }catch(Exception e) {
			        	
			        }
					break;
				case 9:
					System.out.print("Id: ");
			        id = sc.nextLong();
			        matiereService.supprimmerMatiere(id);
					break;
				case 10:
					List<Matiere> matieres = matiereService.getAllMatiere();
					for(Matiere e : matieres) {
						e.toString();
					}
					break;
				case 11:
					System.out.print("Id de le'etudiant: ");
			        id = sc.nextLong();
			        
			        System.out.print("Id de la matiere: ");
			        id2 = sc.nextLong();
			        
			        System.out.print("Note: ");
			        note = sc.nextDouble();
			        try {
			        			        
			        	Etudiant moiMeme = etudiantService.rechercherEtudiant(id).get();
			        	Matiere maMatiere = matiereService.rechercherMatiere(id2).get();
				        Note maNote = new Note();
				        maNote.setNote(note);
				        maNote.setEtudiant(moiMeme);
				        maNote.setMatiere(maMatiere);
			        	noteService.ajouterNote(maNote);
			        	System.out.println("Note ajouter");
			        }catch(Exception e) {
			        	System.out.println(e);
			        }
			        List<Note> notes = noteService.getNotesParEtudiant(id);
			        for(Note e : notes) {
			        	e.toString();
			        }
					break;
				case 12:
					System.out.print("Id de le'etudiant: ");
			        id = sc.nextLong();
			        
			        System.out.print("Id de la matiere: ");
			        id2 = sc.nextLong();
			        
			        System.out.print("Note: ");
			        note = sc.nextDouble();
			        try {
			        	Note nNote = noteService.modifierNote(id, id2, note);
			        	nNote.toString();
			        }catch(Exception e) {
			        	System.out.println(e);
			        }
					break;
				case 13:
					System.out.print("Id de le'etudiant: ");
			        id = sc.nextLong();
			        
			        System.out.print("Id de la matiere: ");
			        id2 = sc.nextLong();
			        
			        noteService.supprimerNote(id, id2);
					break;
				case 14:
					System.out.print("Id de le'etudiant: ");
			        id = sc.nextLong();
			        
			        Etudiant etudiantRechercher = etudiantService.monterDeNiveau(id);
			        try {
			        	etudiantRechercher.toString();
			        }catch(Exception e){
			        	System.out.println(e);
			        }
					break;
				case 15:
					System.out.print("Id de le'etudiant: ");
			        id = sc.nextLong();
			        
			        etudiantService.Orienter(id);
					break;
				case 0:
					System.out.println("Au revoir !!");
					tr = false;
					break;
				default:
					System.out.println("Choix invalide");
					break;	
			}
		}
    }
}