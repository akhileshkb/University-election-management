drop table votes;
drop table winner;
drop table candidate;
drop table voter;
drop table admin;
drop table student;
drop table club;
drop table election;

create table student
	(s_id			varchar(15), 
	 name			varchar(20) not null, 
	 pass			varchar(20) not null,
	 primary key (s_id)
	);

create table club
	(c_id		varchar(15),
	 name		varchar(20) not null,
	 primary key (c_id)
	);

create table election
	(e_id			varchar(8),
	 status			varchar(20)
	  check (status in ('not_started', 'started', 'ended')),
	 start 			timestamp not null,
	 end_t 			timestamp check (end_t> start + interval '1 hour'),
	 guideline		text not null,
	 primary key (e_id)
	);

create table voter
	(v_id			varchar(15),
	 c_id			varchar(15),
	 primary key (v_id, c_id),
	 foreign key (v_id) references student (s_id)
		on delete cascade,
	 foreign key (c_id) references club
	 	on delete cascade
	);

create table candidate
	(s_id			varchar(15), 
	 c_id			varchar(15) , 
	 vote_count		int check (vote_count >= 0),
	 achieve		text not null,
	 primary key (s_id, c_id),
	 foreign key (s_id) references student
		on delete cascade,
	 foreign key (c_id) references club
	 	on delete cascade
	 
	);

create table votes
	(Voter		varchar(15),
	 voter_club	varchar(15),
     Candidate  varchar(15),
     cand_club	varchar(15) check (cand_club = voter_club),
	 primary key (Voter, Candidate, voter_club),
	 foreign key (Voter, voter_club) references voter(v_id, c_id)
		on delete cascade,
	 foreign key (Candidate, cand_club) references candidate(s_id, c_id) 
		on delete cascade
	);

create table winner
	(s_id			varchar(15), 
	 c_id			varchar(15),
	 margin			int ,
	 primary key (c_id),
	 foreign key (s_id, c_id) references candidate
		on delete set null
	);

create table admin
	(a_id			varchar(15), 
	 e_id			varchar(8),
	 primary key (a_id),
	 foreign key (a_id) references student (s_id)
		on delete cascade,
	 foreign key (e_id) references election
		on delete cascade
	);
