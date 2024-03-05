package com.example.test;


import com.example.test.mysql.Role;
import com.example.test.mysql.User;
import com.example.test.mysql.UserRepository;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class TestUser {

  @Autowired
  private UserRepository userRepository;

  @Test
   void insertData(){
      for(int i=0;i<=1000;i++){
        GeometryFactory geometryFactory = new GeometryFactory();
        Point point = geometryFactory.createPoint(
            new Coordinate(127.0678821948147 + i * 0.00000001 , 37.295851313815355 + i * 0.000001 ));
        point.setSRID(4326);
        User user = User.builder()
            .userEmail("test"+i+"@gmail.com")
            .userRole(Role.WALKER)
            .userName("test"+i)
            .userPhoneNumber("010-1555-5555")
            .location(point)
            .build();
        userRepository.save(user);
      }

      for(int i=1001;i<=2000;i++){
        GeometryFactory geometryFactory = new GeometryFactory();
        Point point = geometryFactory.createPoint(
            new Coordinate(127.0678821948147 + i * 0.00000001 , 37.295851313815355 + i * 0.000001 ));
        point.setSRID(4326);

        User user = User.builder()
            .userEmail("test"+i+"@gmail.com")
            .userRole(Role.WALKER)
            .userName("test"+i)
            .userPhoneNumber("010-1555-5555")
            .location(point)
            .build();
        userRepository.save(user);
      }

    for(int i=2001;i<=3000;i++){
      GeometryFactory geometryFactory = new GeometryFactory();
      Point point = geometryFactory.createPoint(
          new Coordinate(127.0678821948147 + i * 0.00000001 , 37.295851313815355 + i * 0.000001 ));
      point.setSRID(4326);

      User user = User.builder()
          .userEmail("test"+i+"@gmail.com")
          .userRole(Role.WALKER)
          .userName("test"+i)
          .userPhoneNumber("010-1555-5555")
          .location(point)
          .build();
      userRepository.save(user);
    }

    for(int i=3001;i<10000;i++){
      GeometryFactory geometryFactory = new GeometryFactory();
      Point point = geometryFactory.createPoint(
          new Coordinate(127.0678821948147 + i * 0.00000001 , 37.295851313815355 + i * 0.000001 ));
      point.setSRID(4326);

      User user = User.builder()
          .userEmail("test"+i+"@gmail.com")
          .userRole(Role.WALKER)
          .userName("test"+i)
          .userPhoneNumber("010-1555-5555")
          .location(point)
          .build();
      userRepository.save(user);
    }
  }

  @Test
  void insertDataMore(){

    for(int i=4001;i<10000;i++){
      GeometryFactory geometryFactory = new GeometryFactory();
      Point point = geometryFactory.createPoint(
          new Coordinate(37.295851313815355 + i * 0.000001 , 127.0678821948147 + i * 0.00000001));
      User user = User.builder()
          .userEmail("test"+i+"@gmail.com")
          .userRole(Role.WALKER)
          .userName("test"+i)
          .userPhoneNumber("010-1555-5555")
          .location(point)
          .build();
      userRepository.save(user);
    }
  }

  @Test
  void insertDataTestUser(){

    GeometryFactory geometryFactory = new GeometryFactory();
    Point point = geometryFactory.createPoint(
          new Coordinate(127.0678821948147,37.294981013815355));
    point.setSRID(4326);
    User user = User.builder()
          .userEmail("test1511551111@gmail.com")
          .userRole(Role.WALKER)
          .userName("test222")
          .userPhoneNumber("010-1555-5555")
          .location(point)

          .build();
      userRepository.save(user);

  }
}
