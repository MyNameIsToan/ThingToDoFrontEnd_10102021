package com.springsecurity01.service;

import java.util.List;

import com.springsecurity01.dto.NoteDTO;

public interface INoteService {
	List<NoteDTO> GetAll();
	void Save(NoteDTO note);
	void Update(NoteDTO note);
	void Delete(Long id);
}
