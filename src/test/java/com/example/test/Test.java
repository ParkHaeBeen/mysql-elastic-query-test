package com.example.test;


import com.example.test.mysql.Role;
import com.example.test.mysql.User;
import com.example.test.mysql.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class Test {

  @Autowired
  private UserRepository userRepository;

  @org.junit.jupiter.api.Test
   void test(){
      for(int i=0;i<=1000;i++){
        User user = User.builder()
            .userEmail("test"+i+"@gmail.com")
            .userRole(Role.WALKER)
            .userName("test"+i)
            .userPhoneNumber("010-1555-5555")
            .userLat(37.295851313815355+i*0.000001)
            .userLnt(127.0678821948147+i*0.00000001)
            .build();
        userRepository.save(user);
      }

      for(int i=1001;i<=2000;i++){
        User user = User.builder()
            .userEmail("test"+i+"@gmail.com")
            .userRole(Role.WALKER)
            .userName("test"+i)
            .userPhoneNumber("010-1555-5555")
            .userLat(37.295851313815355+i*0.00000001)
            .userLnt(127.0678821948147+i*0.0000000002)
            .build();
        userRepository.save(user);
      }

    for(int i=2001;i<=3000;i++){
      User user = User.builder()
          .userEmail("test"+i+"@gmail.com")
          .userRole(Role.WALKER)
          .userName("test"+i)
          .userPhoneNumber("010-1555-5555")
          .userLat(37.295851313815355+i*0.0000000003)
          .userLnt(127.0678821948147+i*0.0000000002)
          .build();
      userRepository.save(user);
    }

    for(int i=3001;i<10000;i++){
      User user = User.builder()
          .userEmail("test"+i+"@gmail.com")
          .userRole(Role.WALKER)
          .userName("test"+i)
          .userPhoneNumber("010-1555-5555")
          .userLat(37.295851313815355+i*0.0000000003)
          .userLnt(127.0678821948147+i*0.0000000002)
          .build();
      userRepository.save(user);
    }
  }

  @org.junit.jupiter.api.Test
  void test2(){

    for(int i=4001;i<10000;i++){
      User user = User.builder()
          .userEmail("test"+i+"@gmail.com")
          .userRole(Role.WALKER)
          .userName("test"+i)
          .userPhoneNumber("010-1555-5555")
          .userLat(37.295851313815355+i*0.0000000003)
          .userLnt(127.0678821948147+i*0.0000000002)
          .build();
      userRepository.save(user);
    }
  }

  @org.junit.jupiter.api.Test
  void test3() {

    Pageable pageable = PageRequest.of(0,10);
    long startTime = System.currentTimeMillis();
// 실행할 쿼리
    Page <Object[]> usersWithinDistance = userRepository.findUsersWithinDistance(
        37.295851313815355 , 127.0678821948147 , 3000, "test1",pageable);
    long endTime = System.currentTimeMillis();
    long elapsedTime = endTime - startTime;
    System.out.println("쿼리 실행 시간: " + elapsedTime + " 밀리초");
    System.out.println("usersWithinDistance.size() = " + usersWithinDistance.getSize());
  }

  @org.junit.jupiter.api.Test
  void tesjjt(){

    for(int i=100000;i<200000;i++){
      User user = User.builder()
          .userEmail("test"+i+"@gmail.com")
          .userRole(Role.WALKER)
          .userName("test"+i)
          .userPhoneNumber("010-1555-5555")
          .userLat(37.295851313815355+i*0.0000000003)
          .userLnt(127.0678821948147+i*0.0000000002)
          .build();
      userRepository.save(user);
    }
  }

}
