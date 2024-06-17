package com.example.Etudiant.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Matiere {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column
    private String nom;
	@Column
	private int credit;

    @ManyToMany(mappedBy = "matieres")
    private Set<Etudiant> etudiants = new HashSet<>();

    
    public Long getId() {
    	return this.id;
    }
    
    public String getNom() {
    	return this.nom;
    }
    
    public void setNom(String nom) {
    	this.nom = nom;
    }
    
    public int getCredit() {
    	return this.credit;
    }
    
    public void setCredit(int credit) {
    	this.credit = credit;
    }
    
    public String toString() {
        System.out.println("-------------------------------");
    	System.out.println("Id: "+this.id);
    	System.out.println("Nom: "+this.nom);
    	System.out.println("Prenom: "+this.credit);
        System.out.println("-------------------------------");
		return nom;
    }
    
}
