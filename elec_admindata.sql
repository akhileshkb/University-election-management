delete from candidate;
delete from voter;
delete from votes;
delete from winner;
delete from admin;
delete from election;
insert into election values('elec1', 'not_started', '2020-12-10 10:00:01', '2020-12-10 17:59:59', 'this is the guidline of the election: \n 1)- guid1\n 2)- guid2');
insert into admin values('18675', 'elec1');