package com.example.test.mysql;

import java.util.Optional;
import org.locationtech.jts.geom.Point;
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


  @Query(value = "SELECT * FROM users u WHERE " +
      "ST_CONTAINS(ST_Buffer(ST_PointFromText(:point, 4326), :buffer), u.location) " +
      "AND u.user_name LIKE :namePattern  ",
      nativeQuery = true)
  Page<User> findUsersWithinDistance(@Param("point") String point,
      @Param("buffer") int buffer,
      @Param("namePattern") String namePattern,
      Pageable pageable);

}
