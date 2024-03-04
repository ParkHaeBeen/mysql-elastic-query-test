package com.example.test.mysql;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.locationtech.jts.geom.Point;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "users",
      uniqueConstraints = {@UniqueConstraint(name = "users_email_unique",columnNames = {"user_email"})})
public class User extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long userId;

  @Column(name = "user_email",nullable = false)
  private String userEmail;

  @Column(name = "user_phone_number",nullable = false)
  private String userPhoneNumber;

  @Column(name = "location",nullable = false)
  private Point location;

  @Builder.Default
  @Enumerated(EnumType.STRING)
  @Column(name = "user_role",nullable = false)
  private Role userRole=Role.USER;

  @Column(name = "user_name",nullable = false)
  private String userName;

  public void modifyUserRole(final Role role){
    this.userRole = role;
  }
}
