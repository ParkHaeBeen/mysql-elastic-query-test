package com.example.test.cache;

import com.example.test.mysql.User;
import com.example.test.mysql.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class TestService {

  private final UserRepository userRepository;

  @Cacheable(cacheNames = "walkerList",key = "#id",unless ="#result ==null")
  public List <UserDto> getUser(Long id) {
    User user = userRepository.findById(id).get();
    double x = user.getLocation().getX();
    double y = user.getLocation().getY();
    System.out.println(x+" "+y);
    String point = "POINT("+y+" "+x+")";
    List <User> usersWithinDistance = userRepository.findUsersWithinDistance(point, 500 , "test999%" ,
        PageRequest.of(0 , 10)).getContent();
    System.out.println(usersWithinDistance.size());
  return usersWithinDistance.stream()
        .map(nowUser -> UserDto.from(nowUser))
        .collect(Collectors.toList());
  }

  public List <UserDto> getUserNoCache(Long id) {
    User user = userRepository.findById(id).get();
    double x = user.getLocation().getX();
    double y = user.getLocation().getY();
    System.out.println(x+" "+y);
    String point = "POINT("+y+" "+x+")";
    List <User> usersWithinDistance = userRepository.findUsersWithinDistance(point, 500 , "test999%" ,
        PageRequest.of(0 , 10)).getContent();
    System.out.println(usersWithinDistance.size());

    return usersWithinDistance.stream()
        .map(nowUser -> UserDto.from(nowUser))
        .collect(Collectors.toList());
  }

}
