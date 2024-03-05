
select st_srid(users.location) from users;
show index from users;
create spatial index idx_coordinated on users (location);

EXPLAIN select users.user_name,ST_CONTAINS((ST_Buffer(ST_PointFromText('Point(37.295851313815355 127.0678821948147)',4326)
    ,0),200),users.location) as distance from users
        where ST_CONTAINS((ST_Buffer(ST_PointFromText('Point(37.295851313815355 127.0678821948147)',4326)
            ,0),200),users.location);
          and users.user_name like '%test8%';


CREATE TABLE users (
                       user_id BIGINT NOT NULL AUTO_INCREMENT,
                       user_email VARCHAR(255) NOT NULL,
                       user_phone_number VARCHAR(255) NOT NULL,
                       location POINT NOT NULL SRID 4326,
                       user_role VARCHAR(50) NOT NULL,
                       user_name VARCHAR(255) NOT NULL,
                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       PRIMARY KEY (user_id),
                       CONSTRAINT users_email_unique UNIQUE (user_email)
);


