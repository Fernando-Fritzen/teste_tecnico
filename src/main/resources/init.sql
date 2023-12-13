create table course(
    id number primary key auto_increment,
    description varchar(255) not null
);

create table student(
    id number primary key auto_increment,
    name varchar(255) not null
);

create table registration(
    id number primary key auto_increment,
    id_student number,
    id_course number,
    FOREIGN KEY (id_student) REFERENCES student(id),
    FOREIGN KEY (id_course) REFERENCES course(id)
);

insert into course (id, description) values (1, 'Engenharia de Software');
insert into course (id, description) values (2, 'Medicina');
insert into course (id, description) values (3, 'Ciencias Contábeis');

insert into student (id, name) values (1, 'Ricardo Santos');
insert into student (id, name) values (2, 'Juliana Matos');
insert into student (id, name) values (3, 'Marcelo da Silva');

insert into registration (id, id_student, id_course) values (1, 1, 1);
insert into registration (id, id_student, id_course) values (2, 2, 2);
insert into registration (id, id_student, id_course) values (3, 3, 2);