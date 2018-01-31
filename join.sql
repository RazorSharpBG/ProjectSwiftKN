

select p.name,m.title from person as p
join person_movies as pm on p.id= pm.id_person
join movies as m on pm.id_movies=m.id;