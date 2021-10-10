package com.springsecurity01.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springsecurity01.dto.NoteDTO;
import com.springsecurity01.entity.NoteEntity;
import com.springsecurity01.entity.User;
import com.springsecurity01.repository.NoteRepository;
import com.springsecurity01.repository.UserRepository;
import com.springsecurity01.service.INoteService;

@Service
public class NoteService implements INoteService{
	
	@Autowired
	private NoteRepository noteRepository;
	@Autowired
	private UserRepository userRepository;
	@Override
	public List<NoteDTO> GetAll() {
		List<NoteDTO> ListOfNoteDTO = new ArrayList<>();
		List<NoteEntity> ListOfNoteEntity = noteRepository.findAll();
		for(NoteEntity item : ListOfNoteEntity) {
			NoteDTO noteDTO = new NoteDTO();
			noteDTO.setId(item.getId());
			noteDTO.setParentid(item.getParentId());
			noteDTO.setContent(item.getContent());
			noteDTO.setUsername(item.getUsers().getUsername());
			ListOfNoteDTO.add(noteDTO);
		}
		return ListOfNoteDTO;
	}

	@Override
	public void Save(NoteDTO note) {
		NoteEntity noteEntity = new NoteEntity();
		if(note.getParentid() == null) {
			noteEntity.setParentId(0L);
		}else{
			NoteEntity subNoteEntity = noteRepository.findOne(note.getParentid());
			if(subNoteEntity == null) {
				noteEntity.setParentId(0L);
			}else {
				noteEntity.setParentId(note.getParentid());
			}
		}
		noteEntity.setContent(note.getContent());
		User user = userRepository.findByUsername(note.getUsername());
		noteEntity.setUsers(user);
		noteRepository.save(noteEntity);
	}

	@Override
	public void Update(NoteDTO note) {
		NoteEntity noteEntity = noteRepository.findOne(note.getId());
		noteEntity.setContent(note.getContent());
		User user = userRepository.findByUsername(note.getUsername());
		noteEntity.setUsers(user);
		noteRepository.save(noteEntity);
	}

	@Override
	public void Delete(Long id) {
		NoteEntity noteEntity = noteRepository.findOne(id);
		if(noteEntity != null) {
			noteRepository.delete(id);
		}
	}
	
}
