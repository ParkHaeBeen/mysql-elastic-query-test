package com.example.test;


import com.example.test.elasticsearch.WalkerDocument;
import com.example.test.elasticsearch.WalkerInfoRequest;
import com.example.test.elasticsearch.WalkerSearchRepository;
import com.example.test.mysql.Role;
import com.example.test.mysql.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@SpringBootTest
class CustomWalkerSearchRepositoryImplTest {

  @Autowired
  private WalkerSearchRepository walkerSearchRepository;

  @Test
  @DisplayName("elastic insert 성공")
  void elastic_insert_test(){
    //이전 데이터 영향받지 않기 위해 삭제후 테스트 진행
    walkerSearchRepository.deleteAll();

    //given
    for(int i=1;i<=6000;i++){
      User user = User.builder()
          .userId((long)i)
          .userLat(37.300422)
          .userLnt(127.074458)
          .userEmail("tast"+i+"@gmail.com")
          .userName("tast"+i)
          .userRole(Role.WALKER)
          .userPhoneNumber("010-1234-123"+i)
          .build();
      walkerSearchRepository.save(WalkerDocument.of(user));
    }

    WalkerInfoRequest searchCond= WalkerInfoRequest.builder()
        .name("test")
        .lat(37.3017387)
        .lnt(127.0735513)
        .build();

    Pageable pageable = PageRequest.of(0,10);

    //when
    Page <WalkerDocument> walkerDocuments = walkerSearchRepository.searchByName(searchCond,pageable);

    //then
    Assertions.assertThat(walkerDocuments.getTotalElements()).isEqualTo(14);
    Assertions.assertThat(walkerDocuments.getSize()).isEqualTo(10);
  }

  @Test
  void test() {

    walkerSearchRepository.deleteAll();

    for(int i=0;i<=1000;i++){
      User user = User.builder()
          .userId((long)i)
          .userEmail("test"+i+"@gmail.com")
          .userRole(Role.WALKER)
          .userName("test"+i)
          .userPhoneNumber("010-1555-5555")
          .userLat(37.295851313815355+i*0.000001)
          .userLnt(127.0678821948147+i*0.00000001)
          .build();
      walkerSearchRepository.save(WalkerDocument.of(user));
    }

    for(int i=1001;i<=2000;i++){
      User user = User.builder()
          .userId((long)i)
          .userEmail("test"+i+"@gmail.com")
          .userRole(Role.WALKER)
          .userName("test"+i)
          .userPhoneNumber("010-1555-5555")
          .userLat(37.295851313815355+i*0.00000001)
          .userLnt(127.0678821948147+i*0.0000000002)
          .build();
      walkerSearchRepository.save(WalkerDocument.of(user));
    }

    for(int i=2001;i<=3000;i++){
      User user = User.builder()
          .userId((long)i)
          .userEmail("test"+i+"@gmail.com")
          .userRole(Role.WALKER)
          .userName("test"+i)
          .userPhoneNumber("010-1555-5555")
          .userLat(37.295851313815355+i*0.0000000003)
          .userLnt(127.0678821948147+i*0.0000000002)
          .build();
      walkerSearchRepository.save(WalkerDocument.of(user));
    }

    for(int i=3001;i<100000;i++){
      User user = User.builder()
          .userId((long)i)
          .userEmail("test"+i+"@gmail.com")
          .userRole(Role.WALKER)
          .userName("test"+i)
          .userPhoneNumber("010-1555-5555")
          .userLat(37.295851313815355+i*0.0000000003)
          .userLnt(127.0678821948147+i*0.0000000002)
          .build();
      walkerSearchRepository.save(WalkerDocument.of(user));
    }

    WalkerInfoRequest searchCond= WalkerInfoRequest.builder()
        .name("test")
        .lat(37.3017387)
        .lnt(127.0735513)
        .build();

    Pageable pageable = PageRequest.of(0,10);

    //when
    Page <WalkerDocument> walkerDocuments = walkerSearchRepository.searchByName(searchCond,pageable);

    //then
    Assertions.assertThat(walkerDocuments.getTotalElements()).isEqualTo(10000);
    Assertions.assertThat(walkerDocuments.getSize()).isEqualTo(10);
  }

  @Test
  void delete() {
    walkerSearchRepository.deleteAll();
  }

  @Test
  void test2(){
    WalkerInfoRequest searchCond= WalkerInfoRequest.builder()
        .name("test1")
        .lat(37.3017387)
        .lnt(127.0735513)
        .build();

    Pageable pageable = PageRequest.of(0,10);
    long startTime = System.currentTimeMillis();
// 실행할 쿼리
    //when
    Page <WalkerDocument> walkerDocuments = walkerSearchRepository.searchByName(searchCond,pageable);
    long endTime = System.currentTimeMillis();
    long elapsedTime = endTime - startTime;
    System.out.println("쿼리 실행 시간: " + elapsedTime + " 밀리초");
    System.out.println("walkerDocuments = " + walkerDocuments.getSize());
    //then
    Assertions.assertThat(walkerDocuments.getTotalElements()).isEqualTo(10000);

  }

  @Test
  void test34(){
    for(int i=10000;i<200000;i++){
      User user = User.builder()
          .userId((long)i)
          .userEmail("test"+i+"@gmail.com")
          .userRole(Role.WALKER)
          .userName("test"+i)
          .userPhoneNumber("010-1555-5555")
          .userLat(37.295851313815355+i*0.0000000003)
          .userLnt(127.0678821948147+i*0.0000000002)
          .build();
      walkerSearchRepository.save(WalkerDocument.of(user));
    }
  }
}