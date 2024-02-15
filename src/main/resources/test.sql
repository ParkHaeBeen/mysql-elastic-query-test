select count(*) from users;
use testQuery;
select count(*) from users where 3000 >= ST_Distance_Sphere(Point(127.0678821948147,37.295851313815355)
    ,Point(users.user_lnt,users.user_lat))
                             and users.user_name like '%test%';

explain select user_lat, user_lnt,ST_Distance_Sphere(Point(127.0678821948147,37.295851313815355)
    ,Point(users.user_lnt,users.user_lat))  as distance from users where ST_Distance_Sphere(Point(127.0678821948147,37.295851313815355)
                                                                             ,Point(users.user_lnt,users.user_lat)) <= 3000 and users.user_name like '%test12%'

        order by distance;


select count(*)  as distance from users where ST_Distance_Sphere(Point(127.0678821948147,37.295851313815355)
                                                  ,Point(users.user_lnt,users.user_lat)) <= 3000
                                          and users.user_name like '%test%'
order by distance;

SELECT @@profiling;SET profiling=1;

SHOW profiles;

select * from users where  users.user_name like '%test10%';


select user_lat, user_lnt,ST_Distance_Sphere(Point(127.0598821948147,37.305851313815355)
    ,Point(users.user_lnt,users.user_lat))  as distance from users where ST_Distance_Sphere(Point(127.0598821948147,37.305851313815355)
                                                                             ,Point(users.user_lnt,users.user_lat))  <= 3000;

select ST_Distance_Sphere(Point(127.0637948147,37.3232)
           ,Point(users.user_lnt,users.user_lat)) as distance from users where ST_Distance_Sphere(Point(127.0637948147,37.3232)
                                                                                   ,Point(users.user_lnt,users.user_lat))  <= 3000;

select users.user_name,ST_Distance_Sphere(Point(127.0637948147,37.3232)
    ,Point(users.user_lnt,users.user_lat)) as distance from users
where ST_Distance_Sphere(Point(127.0637948147,37.3232) ,Point(users.user_lnt,users.user_lat))  <= 3000
  and users.user_name like '%test8%'