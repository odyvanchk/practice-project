CREATE TABLE public.waiting_list
(
    id_lesson bigint NOT NULL,
    id_student integer NOT NULL,
    PRIMARY KEY (id_lesson, id_student),
    CONSTRAINT fk_wait_lesson FOREIGN KEY (id_lesson, id_student)
        REFERENCES public.lessons (id_lesson_from_schedule, id_student) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_wait_student FOREIGN KEY (id_student)
        REFERENCES public.users (id_user) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

ALTER TABLE IF EXISTS public.waiting_list
    OWNER to root1;

ALTER TABLE IF EXISTS public.lessons
    ADD COLUMN max_students_count smallint NOT NULL DEFAULT 1;


-- ALTER TABLE IF EXISTS public.schedules
--     ADD COLUMN current_students_count smallint NOT NULL;

-- ALTER TABLE IF EXISTS public.schedules
--     ADD COLUMN price money;
--
-- ALTER TABLE IF EXISTS public.schedules
--     ALTER COLUMN price SET DEFAULT 10;
--
-- ALTER TABLE IF EXISTS public.schedules
--     ALTER COLUMN price SET NOT NULL;

ALTER TABLE IF EXISTS public.teachers_schedules DROP COLUMN IF EXISTS price;

-- CREATE TABLE public.lessons
-- (
--     id_lesson_from_schedule bigint NOT NULL,
--     id_student integer NOT NULL,
--     status smallint NOT NULL,
--     PRIMARY KEY (id_lesson_from_schedule, id_student),
--     CONSTRAINT fk_schedule_lesson FOREIGN KEY (id_lesson_from_schedule)
--         REFERENCES public.schedules (id_lesson) MATCH SIMPLE
--         ON UPDATE NO ACTION
--         ON DELETE NO ACTION,
--     CONSTRAINT fk_lesson_student FOREIGN KEY (id_student)
--         REFERENCES public.users (id_user) MATCH SIMPLE
--         ON UPDATE NO ACTION
--         ON DELETE NO ACTION,
--     CONSTRAINT fk_lesson_status FOREIGN KEY (status)
--         REFERENCES public.lessons_statuses (id_lesson_status) MATCH SIMPLE
--         ON UPDATE NO ACTION
--         ON DELETE NO ACTION
-- );

ALTER TABLE IF EXISTS public.lessons
    OWNER to root1;

CREATE TABLE public.teachers_one_time_preferences
(
    id_preference bigint NOT NULL,
    id_teacher integer NOT NULL,
    date_time_start timestamp without time zone NOT NULL,
    date_time_finish timestamp without time zone NOT NULL,
    PRIMARY KEY (id_preference),
    CONSTRAINT fk_one_time_user FOREIGN KEY (id_teacher)
        REFERENCES public.users (id_user) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

ALTER TABLE IF EXISTS public.teachers_one_time_preferences
    OWNER to root1;

-- ALTER TABLE IF EXISTS public.lesson_reviews
--     ADD COLUMN mark real;

-- CREATE FUNCTION evaluate_avg_mark() RETURNS trigger AS $evaluate_avg_mark$
-- BEGIN
--     UPDATE public.teachers_descriptions
--     SET mark = (SELECT AVG(mark) FROM public.lesson_reviews WHERE id_tutor = NEW.id_tutor)
--     WHERE NEW.id_tutor = id_teacher;
--     RETURN NEW;
-- END;
-- $evaluate_avg_mark$ LANGUAGE plpgsql;
--
-- CREATE TRIGGER tr_evaluate_mark AFTER INSERT OR UPDATE ON public.lesson_reviews
--     FOR EACH ROW EXECUTE FUNCTION evaluate_avg_mark();

--test trigger
-- insert into schedules (id_teacher, date_time, status, current_students_count)
-- values(3, NOW(), 0,0);
-- select * from schedules;
-- insert into lessons_statuses values(0,'created');
-- insert into lesson_reviews  (id_lesson,  id_tutor, id_student, mark)
-- values(2, 3, 1,4);
-- insert into lesson_reviews  (id_lesson,  id_tutor, id_student, mark)
-- values(2, 3, 6,3);
-- insert into teachers_descriptions
-- values(3, null, null,null);
-- select * from teachers_descriptions;

--
-- CREATE FUNCTION increase_student_count() RETURNS trigger AS $increase_student_count$
-- BEGIN
--     UPDATE public.schedules
--     SET current_students_count = ((SELECT current_students_count FROM public.schedules WHERE id_lesson = NEW.id_lesson_from_schedule) + 1)
--     WHERE id_lesson = NEW.id_lesson_from_schedule;
--     RETURN NEW;
-- END;
-- $increase_student_count$ LANGUAGE plpgsql;

-- CREATE TRIGGER tr_increase_student_count AFTER INSERT ON public.lessons
--     FOR EACH ROW EXECUTE FUNCTION increase_student_count();



-- select * from schedules;

-- CREATE FUNCTION decrease_student_count() RETURNS trigger AS $decrease_student_count$
-- BEGIN
--     IF NEW.status = 2 THEN -- 2 - lesson is cancelled by student
--         UPDATE public.schedules
--         SET current_students_count = ((SELECT current_students_count FROM public.schedules WHERE id_lesson = NEW.id_lesson_from_schedule) - 1)
--         WHERE id_lesson = NEW.id_lesson_from_schedule;
--     END IF;
--     RETURN NEW;
-- END;
-- $decrease_student_count$ LANGUAGE plpgsql;
--
-- CREATE TRIGGER tr_decrease_student_count AFTER UPDATE ON public.lessons
--     FOR EACH ROW EXECUTE FUNCTION decrease_student_count();




-- CREATE FUNCTION cancel_lesson_student_by_teacher() RETURNS trigger AS $cancel_lesson_student_by_teacher$
-- BEGIN
--     IF NEW.status = 3 THEN -- 3 - lesson is cancelled by teacher
--         UPDATE public.lessons
--         SET status = 3
--         WHERE NEW.id_lesson = id_lesson_from_schedule;
--     END IF;
--     RETURN NEW;
-- END;
-- $cancel_lesson_student_by_teacher$ LANGUAGE plpgsql;
--
-- CREATE TRIGGER tr_cancel_lesson_student_by_teacher AFTER UPDATE ON public.schedules
--     FOR EACH ROW EXECUTE FUNCTION cancel_lesson_student_by_teacher();

--test
-- insert into lessons values(2, 3, 0);
-- update schedules set status = 3 WHERE id_lesson = 2;
-- select * from lessons;




-- ALTER TABLE IF EXISTS public.lesson_reviews DROP COLUMN IF EXISTS id_review;
-- ALTER TABLE IF EXISTS public.lesson_reviews
--     ADD CONSTRAINT pk_reviews PRIMARY KEY (id_lesson, id_student);

-- CREATE PROCEDURE book_lesson(teacher integer, start_time timestamp without time zone, student integer) AS $book_lesson$
-- declare
--     id_search bigint := -1;
--     default_params record;
-- BEGIN
--     id_search = (SELECT id_lesson from schedules
--                  where id_teacher = teacher and date_time_start = start_time);
--     raise notice 'Value: %', id_search;
--     if (id_search is not NULL) then
--         if (is_empty_place_at_lesson(id_search) and (select status from schedules where id_lesson = id_search) = 4) then
--             insert into lessons values (id_search, student, 0);
--         else
--             insert into waiting_lists values (id_search, student);
--         end if;
--     else
--         SELECT default_student_count, default_price INTO default_params
--         FROM teachers_descriptions
--         WHERE id_teacher = teacher;
--         INSERT INTO schedules (id_teacher, date_time_start, status, max_students_count, price)
--         VALUES (teacher, start_time, 4, default_params.default_student_count, default_params.default_price)
--         RETURNING id_lesson into id_search;
--         insert into lessons values (id_search, student, 0);
--     end if;
-- END;
-- $book_lesson$ LANGUAGE plpgsql;


--
-- CREATE FUNCTION is_empty_place_at_lesson(lesson bigint) RETURNS boolean AS $is_empty_place_at_lesson$
-- declare
--     curr_student integer :=0;
--     max_student integer:=0;
-- BEGIN
--     SELECT current_students_count, max_students_count
--     INTO curr_student, max_student
--     FROM schedules
--     WHERE id_lesson = lesson;
--     IF curr_student < max_student THEN
--         RETURN true;
--     ELSE
--         RETURN false;
--     END iF;
-- END;
-- $is_empty_place_at_lesson$ LANGUAGE plpgsql;
--
--
-- CREATE FUNCTION get_all_teacher_lessons(teacher integer, days_count integer)
--     RETURNS setof schedules AS $get_all_teacher_lessons$
-- SELECT * from schedules
-- where id_teacher = teacher and
--         date_time_start >= now() and
--         date_time_start <= make_interval(days => days_count) + now();
-- $get_all_teacher_lessons$ LANGUAGE sql;