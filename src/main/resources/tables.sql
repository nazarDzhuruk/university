CREATE TABLE IF NOT EXISTS teacher
(
    id SERIAL PRIMARY KEY,
    name VARCHAR,
    surname VARCHAR
);

CREATE TABLE IF NOT EXISTS student
(
    id SERIAL PRIMARY KEY,
    name VARCHAR,
    surname VARCHAR
);

CREATE TABLE IF NOT EXISTS course
(
    courseid SERIAL PRIMARY KEY,
    coursename VARCHAR
);

CREATE TABLE IF NOT EXISTS course_student
(
    student_id INT,
    course_id INT,
    FOREIGN KEY (student_id) REFERENCES students (id) ON DELETE CASCADE,
    FOREIGN KEY (group_id) REFERENCES course (courseid) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS lecture
(
    id SERIAL PRIMARY KEY,
    lecturename VARCHAR,
    starttime TIMESTAMP
);
CREATE TABLE IF NOT EXISTS teacher_lecture
(
    teacher_id INTEGER,
    lecture_id INTEGER,
    FOREIGN KEY (teacher_id) REFERENCES teacher (id) ON DELETE CASCADE,
    FOREIGN KEY (lecture_id) REFERENCES lecture (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS course_lecture
(
    course_id INTEGER,
    lecture_id INTEGER,
    FOREIGN KEY (course_id) REFERENCES course (courseid) ON DELETE CASCADE,
    FOREIGN KEY (lecture_id) REFERENCES lecture (id) ON DELETE CASCADE
);
