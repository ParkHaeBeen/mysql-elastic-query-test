select count(*) from users;
use testQuery;
select count(*) from users where 3000 >= ST_Distance_Sphere(Point(127.0678821948147,37.295851313815355)
    ,Point(users.user_lnt,users.user_lat))
                             and users.user_name like '%test%';

explain select ST_Distance_Sphere(Point(127.0678821948147,37.295851313815355)
    ,location)  as distance from users where ST_Distance_Sphere(Point(127.0678821948147,37.295851313815355)
                                                                             ,location) <= 3000 and users.user_name like '%test12%'

        order by distance;

create spatial index idx_coordinated on users(location);


select count(*)  as distance from users where ST_CONTAINS((ST_Buffer(ST_PointFromText('Point(37.295851313815355 127.0678821948147)',4326)
            ,500)),users.location);
                                          and users.user_name like '%test%'
order by distance;

SELECT @@profiling;SET profiling=1;

SHOW profiles;

select * from users where  users.user_name like '%test10%';


select user_lat, user_lnt,ST_Distance_Sphere(Point(127.0598821948147,37.305851313815355)
    ,Point(users.user_lnt,users.user_lat))  as distance from users where ST_Distance_Sphere(Point(127.0598821948147,37.305851313815355)
                                                                             ,Point(users.user_lnt,users.user_lat))  <= 3000;

select ST_Distance_Sphere(Point(127.0637948147,37.3232)
           ,location) as distance from users where ST_Distance_Sphere(Point(127.0637948147,37.3232)
                                                                                   ,location)  <= 10000;

EXPLAIN select users.user_name,ST_Distance_Sphere(Point(127.0678821948147,37.295851313815355)
    ,users.location) as distance from users
where ST_Distance_Sphere(Point(127.0678821948147,37.295851313815355 ) ,users.location)  <= 100
  and users.user_name like '%test8%';


EXPLAIN select users.user_name,ST_CONTAINS((ST_Buffer(ST_PointFromText('Point(37.295851313815355 127.0678821948147)',4326)
    ,0),200),users.location) as distance from users
        where ST_CONTAINS((ST_Buffer(ST_PointFromText('Point(37.295851313815355 127.0678821948147)',4326)
            ,0),200),users.location);
          and users.user_name like '%test8%';

select count(*) from users where ST_CONTAINS(ST_Buffer(ST_PointFromText('Point(37.295851313815355 127.0678821948147)',4326)
    ,100),users.location) limit 0 ,10;

select st_srid(users.location) from users;
show index from users;
create spatial index idx_coordinated on users (location);

ALTER table users modify column location POINT SRID 4326;

EXPLAIN SELECT user_name,
       location
        FROM users
        WHERE ST_CONTAINS ((ST_Buffer(ST_PointFromText('POINT(37.30594819955 127.06908991948)', 4326), 100)), users.location)
and user_name like 'test999%';

EXPLAIN SELECT
            ST_CONTAINS ((ST_Buffer(ST_PointFromText('POINT(37.30594819955 127.06708991948)', 4326), 500)), users.location)
        FROM users IGNORE INDEX(idx_coordinated)
        WHERE ST_CONTAINS ((ST_Buffer(ST_PointFromText('POINT(37.30594819955 127.06708991948)', 4326), 500)), users.location)
          and user_name like 'test999%' limit 0, 10;
show status like '%handler%read%';
SELECT user_name
FROM users
WHERE ST_CONTAINS(ST_Buffer(ST_SRID(ST_PointFromText('Point(37.295851313815355 127.067882)',4326), 0), 100), users.location);

EXPLAIN SELECT
            users.user_name, ST_CONTAINS( (ST_Buffer(ST_PointFromText( 'POINT (37.30514819955 127.06808991948)', 4326), 00)), users.location) as distance
FROM users
WHERE ST_CONTAINS ((ST_Buffer(ST_PointFromText('POINT(37.30514819955 127.06808991948)', 4326), 00)), users.location)
ORDER BY distance;

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


