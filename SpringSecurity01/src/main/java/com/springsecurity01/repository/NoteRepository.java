package com.springsecurity01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springsecurity01.entity.NoteEntity;

public interface NoteRepository extends JpaRepository<NoteEntity, Long> {

}
