package org.isetn.RestControllers;

import java.util.List;

import org.isetn.entities.Classe;
import org.isetn.entities.Etudiant;
import org.isetn.repository.ClasseRepository;
import org.isetn.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/etudiant")
public class EtudiantController {
	@Autowired
	private EtudiantRepository etudiantRepository;
	
	@Autowired
	private ClasseRepository classeRepository;

	@PostMapping("/add")
	public Etudiant add(@RequestBody Etudiant etudiant) {
		/*
		 * if (etudiant.getClasse() == null) { return
		 * ResponseEntity.badRequest().body("Classe cannot be null"); }
		 * 
		 * Classe classe =
		 * classeRepository.findById(etudiant.getClasse().getCodClass()).orElse(null);
		 * 
		 * if (classe == null) { return
		 * ResponseEntity.badRequest().body("Classe not found for codClass: " +
		 * etudiant.getClasse().getCodClass()); }
		 * 
		 * // Associate the existing Classe with the Etudiant
		 * etudiant.setClasse(classe);
		 */
	    return etudiantRepository.save(etudiant);

	    //return ResponseEntity.ok("Etudiant added successfully");
	}




	@GetMapping("/all")
	public List<Etudiant> getAll() {
		return etudiantRepository.findAll();
	}
	
	@DeleteMapping("/delete")
	public void delete(@Param("id") Long id)
	{
		Etudiant c =  etudiantRepository.findById(id).get();
		etudiantRepository.delete(c);
	}
	
	@PutMapping("/update")
	public Etudiant update(@RequestBody Etudiant etudiant) {
		Classe classe = classeRepository.findById(etudiant.getClasse().getCodClass()).orElse(null);

		if (classe == null) {
		    throw new IllegalArgumentException("Classe not found");
		}

		// Associate the existing Classe with the Etudiant
		etudiant.setClasse(classe);
		return etudiantRepository.save(etudiant);
	}
	
	
	@GetMapping("/byClass")
	public List<Etudiant> getStudentsByClass(@RequestParam("classeId") Long classeId) {
	    // Find the class by its ID
	    Classe classe = classeRepository.findById(classeId).orElse(null);

	    if (classe == null) {
	        throw new IllegalArgumentException("Classe not found");
	    }

	    // Fetch and return the students of that class
	    return etudiantRepository.findByClasse(classe);
	}

}
