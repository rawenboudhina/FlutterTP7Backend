package org.isetn.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codClass;
    private String nomClass;
    private int nbreEtud;

    @OneToMany(mappedBy = "classe", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Etudiant> etudiants = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "ClassMat",
        joinColumns = @JoinColumn(name = "codClass"),
        inverseJoinColumns = @JoinColumn(name = "codMat")
    )
    private List<Matiere> matieres = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "classe", cascade = CascadeType.ALL)
    private List<ClassMat> classMats = new ArrayList<>();
}
