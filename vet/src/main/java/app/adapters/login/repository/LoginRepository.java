package app.adapters.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.adapters.login.entity.LoginEntity;


public interface LoginRepository  extends JpaRepository<LoginEntity, Long>{
  // Recordar escribir bien los nombres :(
  public LoginEntity findByUserName( String username);
}
