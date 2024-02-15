package com.example.test.mysql;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

  Optional<User> findByUserEmail(String userEmail);

  Optional<User> findByUserEmailAndUserRole(String userEmail,Role role);

  Optional<User> findByUserIdAndUserRole(Long userId,Role role);

  @Query(value = "SELECT user_lat, user_lnt, ST_Distance_Sphere(Point(:pointLng, :pointLat), Point(u.user_lnt, u.user_lat)) AS distance " +
      "FROM users u " +
      "WHERE ST_Distance_Sphere(Point(:pointLng, :pointLat), Point(u.user_lnt, u.user_lat)) <= :distance "
      + "AND u.user_name = :name " +
      "ORDER BY distance",
      nativeQuery = true)
  Page <Object[]> findUsersWithinDistance(@Param("pointLat") double pointLat,
      @Param("pointLng") double pointLng,
      @Param("distance") double distance,
      @Param("name") String name,
      Pageable pageable);

}
