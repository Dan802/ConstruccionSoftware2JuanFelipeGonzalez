package app.adapters.login.entity;

import app.adapters.person.entity.PersonEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="login")
@Getter
@Setter
public class LoginEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="login_id")
  private long loginId;
  
  @JoinColumn(name="person_id")
  @OneToOne
  private PersonEntity personId;

  @Column(name="user_name")
  private String userName;

  @Column(name="password")
  private String password;
}

