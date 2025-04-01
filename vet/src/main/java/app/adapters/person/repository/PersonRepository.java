package app.adapters.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.adapters.person.entity.PersonEntity;

public interface PersonRepository extends JpaRepository<PersonEntity, Long>{
  public boolean existsByDocument(long document);
  public PersonEntity findByDocument(long document);
} 
