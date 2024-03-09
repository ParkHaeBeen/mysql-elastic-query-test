
select st_srid(users.location) from users;
show index from users;
create spatial index idx_coordinated on users (location);
select count(*) from users;
EXPLAIN  select users.user_name,ST_CONTAINS((ST_Buffer(ST_PointFromText('Point(37.295851313815355 127.0678821948147)',4326)
,200)),users.location) as distance from users
        where ST_CONTAINS((ST_Buffer(ST_PointFromText('Point(37.295851313815355 127.0678821948147)',4326)
            ,200)),users.location) and user_name like 'test2%';

EXPLAIN ANALYZE select users.user_name,ST_CONTAINS((ST_Buffer(ST_PointFromText('Point(37.295851313815355 127.0678821948147)',4326)
    ,200)),users.location) as distance from users
        where ST_CONTAINS((ST_Buffer(ST_PointFromText('Point(37.295851313815355 127.0678821948147)',4326)
            ,200)),users.location) and user_name like 'test%';

create index idx_name on users (user_name);


explain   select *
from (select *
     from users
      where ST_CONTAINS((ST_Buffer(ST_PointFromText('Point(37.295851313815355 127.0678821948147)',4326)
          ,200)),users.location)) temp
where temp.user_name like 'test2%';



EXPLAIN  select temp.user_name,ST_CONTAINS((ST_Buffer(ST_PointFromText('Point(37.295851313815355 127.0678821948147)',4326)
    ,200)),temp.location) as distance from (select * from users where user_name like 'test2%') temp
                where ST_CONTAINS((ST_Buffer(ST_PointFromText('Point(37.295851313815355 127.0678821948147)',4326)
                    ,200)),temp.location);
select count(*) from users
where user_name like 'test179%';

select * from (
    select * from users where user_name like 'test2%';
              ) temp



explain    select *
          from (select *
                from users
                where ST_CONTAINS((ST_Buffer(ST_PointFromText('Point(37.295851313815355 127.0678821948147)',4326)
                    ,200)),users.location)) temp
          where user_email like 'test179%';

explain select *
from users
where ST_CONTAINS((ST_Buffer(ST_PointFromText('Point(37.295851313815355 127.0678821948147)',4326)
    ,200)),users.location);

select users.user_name,ST_CONTAINS((ST_Buffer(ST_PointFromText('Point(37.295851313815355 127.0678821948147)',4326)
    ,200)),users.location) as distance from users
where ST_CONTAINS((ST_Buffer(ST_PointFromText('Point(37.295851313815355 127.0678821948147)',4326)
    ,200)),users.location);

select count(*) from users
where user_name like 'test2%';


show status like '%handler%read%';
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


