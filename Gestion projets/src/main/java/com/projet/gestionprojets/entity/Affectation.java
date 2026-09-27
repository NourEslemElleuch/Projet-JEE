package com.projet.gestionprojets.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "affectation")
public class Affectation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employe_id", nullable = false)
    private Utilisateur employe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projet_id", nullable = false)
    private Projet projet;

    @Column(name = "date_debut", nullable = false)
    private LocalDate dateDebut;

    @Column(name = "date_fin", nullable = false)
    private LocalDate dateFin;

    public Affectation() {
    }

    public Affectation(Utilisateur employe, Projet projet, LocalDate dateDebut, LocalDate dateFin) {
        this.employe = employe;
        this.projet = projet;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Long getId() {
        return id;
    }

    public Utilisateur getEmploye() {
        return employe;
    }

    public Projet getProjet() {
        return projet;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmploye(Utilisateur employe) {
        this.employe = employe;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }
}