package com.example.Etudiant.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Etudiant {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column
    private String nom;
	@Column
    private String prenom;
	@Column
    private int age;
	@Column
    private int niveau;
    
    @ManyToMany
    @JoinTable(
        name = "etudiant_matiere",
        joinColumns = @JoinColumn(name = "etudiant_id"),
        inverseJoinColumns = @JoinColumn(name = "matiere_id")
    )
    private Set<Matiere> matieres = new HashSet<>();
    
    public Long getId() {
    	return this.id;
    }
    
    public String getNom() {
    	return this.nom;
    }
    
    public String getPrenom() {
    	return this.prenom;
    }
    
    public int getAge() {
    	return this.age;
    }
    
    public int getNiveau() {
    	return this.niveau;
    }
    
    public void setNom(String nom) {
    	this.nom = nom;
    }
    
    public void setPrenom(String prenom) {
    	this.prenom = prenom;
    }
    
    public void setAge(int age) {
    	this.age = age;
    }
    
    public void setNiveau(int niveau) {
    	this.niveau = niveau;
    }
    
    public String toString() {
        System.out.println("-------------------------------");
    	System.out.println("Id: "+this.id);
    	System.out.println("Nom: "+this.nom);
    	System.out.println("Prenom: "+this.prenom);
    	System.out.println("Age: "+this.age);
    	System.out.println("Nivea: "+this.niveau);
        System.out.println("-------------------------------");
		return nom;
    }
}
