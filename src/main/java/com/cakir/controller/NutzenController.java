package com.cakir.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cakir.entity.Nutzen;
import com.cakir.repository.NutzenRepository;
import com.cakir.service.NutzenService;
import com.cakir.util.RestPreconditions;

@RestController
@RequestMapping(value = "api/nutzen")
public class NutzenController {

	@Autowired
	private NutzenService nutzenService;

	@Autowired
	private NutzenRepository nutzenRepository;

	@GetMapping
	public ResponseEntity<Page<Nutzen>> getAllNutzen(
			@RequestParam Integer pageSize, @RequestParam Integer page) {

		Pageable pageable = PageRequest.of(page, pageSize);

		RestPreconditions.checkFound(pageable);

		return ResponseEntity.status(HttpStatus.OK).body(
				nutzenRepository.findAll(pageable));
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Nutzen> getNutzenById(@PathVariable("id") long id) {

		return ResponseEntity.status(HttpStatus.FOUND).body(
				RestPreconditions.checkFound(nutzenService.getNutzenById(id)));
	}

	@PostMapping(value = "/")
	public ResponseEntity<Nutzen> createNutzen(@Valid @RequestBody Nutzen nutzen) {

		return ResponseEntity.status(HttpStatus.CREATED).body(
				nutzenService.createNutzen(nutzen));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Nutzen> updateNutzen(@PathVariable("id") long id,
			@Valid @RequestBody Nutzen nutzen) {

		RestPreconditions.checkFound(nutzenService.getNutzenById(id));

		return new ResponseEntity<Nutzen>(nutzenService.updateNutzen(nutzen),
				HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public void deleteNutzen(@PathVariable("id") long id) {
		RestPreconditions.checkFound(nutzenService.getNutzenById(id));
		nutzenService.deleteNutzen(id);
	}

}
