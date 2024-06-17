package com.example.Etudiant.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Note {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "etudiant_id")
    private Etudiant etudiant;

    @ManyToOne
    @JoinColumn(name = "matiere_id")
    private Matiere matiere;
    @Column
    private double note;
    
    public Long getId() {
    	return this.id;
    }
    
    public double getNote() {
    	return this.note;
    }
    
    public void setNote(double note) {
    	this.note = note;
    }
    
    public Etudiant getEtudiant() {
    	return this.etudiant;
    }
    
    public void setEtudiant(Etudiant etudiant) {
    	this.etudiant = etudiant;
    }
    
    public Matiere getMatiere() {
    	return this.matiere;
    }
    
    public void setMatiere(Matiere maMatiere) {
    	this.matiere = maMatiere;
    }
    
    
    public String toString() {
        System.out.println("-------------------------------");
    	System.out.println("Etudiant: "+this.etudiant.getNom()+" "+this.etudiant.getPrenom());
    	System.out.println("Matiere: "+this.matiere.getNom());
    	System.out.println("Note: "+this.note);
        System.out.println("-------------------------------");
		return null;
    	
    }
}
