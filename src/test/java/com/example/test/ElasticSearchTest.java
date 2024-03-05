package com.example.test;


import com.example.test.elasticsearch.WalkerDocument;
import com.example.test.elasticsearch.WalkerInfoRequest;
import com.example.test.elasticsearch.WalkerSearchRepository;
import com.example.test.mysql.Role;
import com.example.test.mysql.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;




@SpringBootTest
class ElasticSearchTest {

  @Autowired
  private WalkerSearchRepository walkerSearchRepository;

  @Test
  void elasticInsert(){
    //이전 데이터 영향받지 않기 위해 삭제후 테스트 진행
    walkerSearchRepository.deleteAll();

    //given
    for(int i=1;i<=6000;i++){
      GeometryFactory geometryFactory = new GeometryFactory();
      Point point = geometryFactory.createPoint(
          new Coordinate(127.0678821948147 + i * 0.00000001 , 37.295851313815355 + i * 0.000001 ));
      point.setSRID(4326);
      User user = User.builder()
          .userId((long)i)
          .location(point)
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
      GeometryFactory geometryFactory = new GeometryFactory();
      Point point = geometryFactory.createPoint(
          new Coordinate(127.0678821948147 + i * 0.00000001 , 37.295851313815355 + i * 0.000001 ));
      User user = User.builder()
          .userId((long)i)
          .userEmail("test"+i+"@gmail.com")
          .userRole(Role.WALKER)
          .userName("test"+i)
          .userPhoneNumber("010-1555-5555")
          .location(point)
          .build();
      walkerSearchRepository.save(WalkerDocument.of(user));
    }

    for(int i=1001;i<=2000;i++){
      GeometryFactory geometryFactory = new GeometryFactory();
      Point point = geometryFactory.createPoint(
          new Coordinate(127.0678821948147 + i * 0.00000001 , 37.295851313815355 + i * 0.000001 ));
      User user = User.builder()
          .userId((long)i)
          .userEmail("test"+i+"@gmail.com")
          .userRole(Role.WALKER)
          .userName("test"+i)
          .userPhoneNumber("010-1555-5555")
          .location(point)
          .build();
      walkerSearchRepository.save(WalkerDocument.of(user));
    }

    for(int i=2001;i<=3000;i++){
      GeometryFactory geometryFactory = new GeometryFactory();
      Point point = geometryFactory.createPoint(
          new Coordinate(127.0678821948147 + i * 0.00000001 , 37.295851313815355 + i * 0.000001 ));
      User user = User.builder()
          .userId((long)i)
          .userEmail("test"+i+"@gmail.com")
          .userRole(Role.WALKER)
          .userName("test"+i)
          .userPhoneNumber("010-1555-5555")
          .location(point)
          .build();
      walkerSearchRepository.save(WalkerDocument.of(user));
    }

    for(int i=3001;i<100000;i++){
      GeometryFactory geometryFactory = new GeometryFactory();
      Point point = geometryFactory.createPoint(
          new Coordinate(127.0678821948147 + i * 0.00000001 , 37.295851313815355 + i * 0.000001 ));
      User user = User.builder()
          .userId((long)i)
          .userEmail("test"+i+"@gmail.com")
          .userRole(Role.WALKER)
          .userName("test"+i)
          .userPhoneNumber("010-1555-5555")
          .location(point)
          .build();
      walkerSearchRepository.save(WalkerDocument.of(user));
    }
  }

  @Test
  void delete() {
    walkerSearchRepository.deleteAll();
  }

  @Test
  void test2(){
    WalkerInfoRequest searchCond= WalkerInfoRequest.builder()
        .name("test1")
        .lat(37.295851313815355)
        .lnt(127.0678821948147 )
        .build();

    Pageable pageable = PageRequest.of(0,10);
    long startTime = System.currentTimeMillis();

    Page <WalkerDocument> walkerDocuments = walkerSearchRepository.searchByName(searchCond,pageable);
    long endTime = System.currentTimeMillis();
    long elapsedTime = endTime - startTime;
    System.out.println("쿼리 실행 시간: " + elapsedTime + " 밀리초");
    System.out.println("walkerDocuments = " + walkerDocuments.getSize());

    Assertions.assertThat(walkerDocuments.getTotalElements()).isEqualTo(10000);

  }

}
