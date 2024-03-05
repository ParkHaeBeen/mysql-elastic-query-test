package com.example.test.cache;

import com.example.test.mysql.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

  private final TestService testService;

  @GetMapping("/cache/{id}")
  public List <UserDto> getCache(@PathVariable Long id) {
    return testService.getUser(id);
  }

  @GetMapping("/noCache/{id}")
  public List <UserDto> getNoCache(@PathVariable Long id) {
    return testService.getUserNoCache(id);
  }
}
