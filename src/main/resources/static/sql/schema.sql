CREATE TABLE IF NOT EXISTS principal (
    username VARCHAR(100) PRIMARY KEY,
    password VARCHAR(40) NOT NULL
    );

CREATE TABLE IF NOT EXISTS author (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE REFERENCES principal(username) ON DELETE CASCADE ON UPDATE CASCADE
    );

CREATE TABLE IF NOT EXISTS country (
    id VARCHAR(2) PRIMARY KEY,
    value VARCHAR(64) NOT NULL
    );

CREATE TABLE IF NOT EXISTS interviewee (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE REFERENCES principal(username) ON DELETE CASCADE ON UPDATE CASCADE,
    age INT NOT NULL,
    gender gender_t NOT NULL,
    country VARCHAR(2),
    marital_status marital_status_t NOT NULL,
    CONSTRAINT country_fk FOREIGN KEY (country) REFERENCES country(id)
                                                                         ON DELETE SET NULL
                                                                         ON UPDATE CASCADE
    );

CREATE TABLE IF NOT EXISTS questionnaire (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    question VARCHAR(300) NOT NULL,
    author_id UUID,
    multiple BOOLEAN NOT NULL,
    CONSTRAINT author_fk FOREIGN KEY (author_id) REFERENCES author(id)
    ON DELETE SET NULL
    ON UPDATE CASCADE
    );

CREATE TABLE IF NOT EXISTS option (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    text VARCHAR(75) NOT NULL,
    questionnaire_id UUID NOT NULL,
    CONSTRAINT questionnaire_fk FOREIGN KEY (questionnaire_id) REFERENCES questionnaire(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    );

CREATE TABLE IF NOT EXISTS choice (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    interviewee_id UUID NOT NULL REFERENCES interviewee(id) ON DELETE CASCADE ON UPDATE CASCADE,
    questionnaire_id UUID NOT NULL REFERENCES questionnaire(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT unique_interviewee_questionnaire UNIQUE (interviewee_id, questionnaire_id)
    );

CREATE TABLE IF NOT EXISTS option_choice (
    option_id UUID NOT NULL REFERENCES option(id) ON DELETE CASCADE ON UPDATE CASCADE,
    choice_id UUID NOT NULL REFERENCES choice(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT option_choice_pk PRIMARY KEY (option_id, choice_id)
    );