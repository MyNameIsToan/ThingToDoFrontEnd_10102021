package com.springsecurity01.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity01.dto.NoteDTO;
import com.springsecurity01.service.INoteService;

@RestController
public class NoteAPI {
	@Autowired
	private INoteService noteService;
	
	@GetMapping(value="/Note")
	@PreAuthorize("hasAnyAuthority('USER_READ')")
	public List<NoteDTO> GetAll(){
		return noteService.GetAll();
	}
	
	@PostMapping(value="/Note")
	@PreAuthorize("hasAnyAuthority('USER_CREATE')")
	public void Save(@RequestBody NoteDTO model) {
		noteService.Save(model);
	}
	
	@PutMapping(value="/Note")
	@PreAuthorize("hasAnyAuthority('USER_UPDATE')")
	public void Update(@RequestBody NoteDTO model) {
		noteService.Update(model);
	}
	
	@DeleteMapping(value="/Note/{id}")
	@PreAuthorize("hasAnyAuthority('USER_DELETE')")
	public void Delete(@PathVariable("id") Long id) {
		noteService.Delete(id);
	}
}
