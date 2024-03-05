package com.example.test.cache;


import com.example.test.mysql.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

  private String name;
  private String email;
  private double lat;
  private double lnt;
  private Long id;

  public static UserDto from(User user) {
    return UserDto.builder()
        .id(user.getUserId())
        .email(user.getUserEmail())
        .name(user.getUserName())
        .lat(user.getLocation().getX())
        .lnt(user.getLocation().getY())
        .build();

  }
}
