ALTER TABLE IF EXISTS foreign_courses.teachers_descriptions DROP CONSTRAINT IF EXISTS teachers_descriptions_country_fkey;

DROP TABLE foreign_courses.users_countries CASCADE;
