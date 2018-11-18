INSERT INTO public.person (person_id, address, date_of_birth, first_name, last_name) VALUES ('70102010000', '2', '2001-02-01', 'Lian', 'Sow');
INSERT INTO public.person (person_id, address, date_of_birth, first_name, last_name) VALUES ('71210260007', '1', '2012-10-26', 'Mark', 'Smith');
INSERT INTO public.person (person_id, address, date_of_birth, first_name, last_name) VALUES ('81010260002', '30', '2010-10-26', 'Emilia', 'Evans');
INSERT INTO public.person (person_id, address, date_of_birth, first_name, last_name) VALUES ('80510260026', '2', '2005-10-26', 'Celine', 'Wright');
INSERT INTO public.person (person_id, address, date_of_birth, first_name, last_name) VALUES ('71108310000', '2', '2011-08-31', 'Edwin', 'Roberts');
INSERT INTO public.person (person_id, address, date_of_birth, first_name, last_name) VALUES ('71108310011', '2', '2011-08-31', 'Gregory', 'Roberts');
INSERT INTO public.person (person_id, address, date_of_birth, first_name, last_name) VALUES ('81803280018', '7', '2018-03-28', 'Matvei', 'Matveev');
INSERT INTO public.person (person_id, address, date_of_birth, first_name, last_name) VALUES ('81803280007', '7', '2018-03-28', 'Malcolm', 'Green');
INSERT INTO public.person (person_id, address, date_of_birth, first_name, last_name) VALUES ('81811030009', '1', '2018-11-03', 'Maali', 'Maasikas');

INSERT INTO public.hearing (id, case_number, end_time, judge, start_time) VALUES (1, '134-CIVI-2018', '2018-11-18 15:06:03.217000', 'Judge Judy', '2018-11-18 15:06:03.217000');
INSERT INTO public.hearing_participants (hearing_id, participants_person_id) VALUES (1, '81010260002');