import random
import uuid
import requests
import psycopg2


authors_amount_max = 10
questionnaires_amount_max = 6
options_amount_max = 8
interviewees_max = 1000


fish_text_url = "https://fish-text.ru/get"
t_index = 0
titles = []
q_index = 0
questions = []
opt_index = 0
options = []
author_names = [f"author{i}" for i in range(authors_amount_max)]
file_name = "data.sql"


def get_from_fish(textType: str) -> str:
    resp = requests.get(fish_text_url, params={
        "type": textType,
        "number": 50,
    })
    return resp.json()


sep = "\\n" * 2


def init_title():
    text = get_from_fish("title")
    global titles
    titles = text["text"].split(sep)[:50]


def init_question():
    text = get_from_fish("paragraph")
    global questions
    questions = text["text"].split(sep)[:50]


def init_option():
    text = get_from_fish("title")
    global options
    options = text["text"].split(sep)[:50]


def get_title():
    global t_index
    if t_index >= len(titles):
        t_index = 0
        init_title()
    value = t_index
    t_index += 1
    return titles[value]


def get_question():
    global q_index
    if q_index >= len(questions):
        q_index = 0
        init_question()
    value = q_index
    q_index += 1
    return questions[value]


def get_option():
    global opt_index
    if opt_index >= len(options):
        opt_index = 0
        init_option()
    value = opt_index
    opt_index += 1
    return options[value]


init_title()
init_question()
init_option()


def insert(file, table_name, to_insert, values):
    query = f"INSERT INTO {table_name} ({to_insert}) VALUES ({values});"
    conn.cursor().execute(query)
    print(query)
    conn.commit()


questionnaires = []

def data_authors():
    print("- data_authors")
    with open(file_name, "a", encoding="utf8") as data_sql:
        for author in author_names:
            print(f"{author}")

            table_name = "principal"
            to_insert = "username, password"

            username = author
            password = "qwerty"

            values = f"'{username}', '{password}'"
            insert(data_sql, table_name, to_insert, values)


            table_name = "author"
            to_insert = "id, username"

            author_id = uuid.uuid4()
            username = author
            
            values = f"'{author_id}', '{username}'"
            insert(data_sql, table_name, to_insert, values)

            questionnaire_amount = random.randint(0, questionnaires_amount_max)
            for _q in range(questionnaire_amount):
                table_name = "questionnaire"
                to_insert = "id, name, question, author_id, multiple"

                questionnaire_id = uuid.uuid4()
                name = get_title()[:100]
                question = get_question()[:300]
                multiple = random.choice((True, False))

                values = f"'{questionnaire_id}', '{name}', '{question}', '{author_id}', {multiple}"
                insert(data_sql, table_name, to_insert, values)

                options_amount = random.randint(2, options_amount_max)
                options = []
                for _o in range(options_amount):
                    table_name = "option"
                    to_insert = "id, text, questionnaire_id"

                    option_id = uuid.uuid4()
                    text = get_option()[:75]

                    options.append(option_id)
                    values = f"'{option_id}', '{text}', '{questionnaire_id}'"
                    insert(data_sql, table_name, to_insert, values)
                
                questionnaires.append({
                    "id": questionnaire_id,
                    "multiple": multiple,
                    "options": options
                })
    print("+ data_authors")
    
countries = []
def data_countries():
    with open("countries.txt", "r", encoding="utf8") as c_file:
        with open(file_name, "a", encoding="utf8") as data_sql:
            for line in c_file:
                conn.cursor().execute(line)
                countries.append(line[45:47])
    conn.commit()

choiced_questionnaries_max = 3

def data_interviewees():
    print("- data_interviewees")
    with open(file_name, "a", encoding="utf8") as data_sql:
        for _ in range(interviewees_max):
            table_name = "principal"
            to_insert = "username, password"

            username = "interviewee" + str(_)
            password = "qwerty"

            values = f"'{username}', '{password}'"
            insert(data_sql, table_name, to_insert, values)

            table_name = "interviewee"
            to_insert = "id, username, age, gender, country, marital_status"

            interviewee_id = uuid.uuid4()
            username = "interviewee" + str(_)
            age = random.randint(14, 70)
            gender = random.choice(('male', 'female'))
            country = random.choice(countries)
            marital_status = random.choice(('married', 'divorced', 'was_not_married'))

            values = f"'{interviewee_id}', '{username}', {age}, '{gender}', '{country}', '{marital_status}'"
            insert(data_sql, table_name, to_insert, values)

            choiced_questionnarie = random.sample(questionnaires, random.randint(1, choiced_questionnaries_max))
            for q in choiced_questionnarie:
                table_name = "choice"
                to_insert = "id, questionnaire_id, interviewee_id"

                choice_id = uuid.uuid4()
                
                values = f"'{choice_id}', '{q['id']}', '{interviewee_id}'"
                insert(data_sql, table_name, to_insert, values)

                selected_options_amount = random.randint(1, len(q["options"]) if q["multiple"] else 1)
                selected_options = random.sample(q["options"], k=selected_options_amount)
                for sel_option in selected_options:
                    table_name = "option_choice"
                    to_insert = "option_id, choice_id"

                    values = f"'{sel_option}', '{choice_id}'"
                    insert(data_sql, table_name, to_insert, values)

    print("+ data_interviewees")


def clear_db():
    with open("drop.sql", encoding="utf8") as drop:
        query = drop.read()
        conn.cursor().execute(query)
        conn.commit()


def create_tables():
    with open("schema.sql", "r", encoding="utf8") as schema:
        query = schema.read()
        conn.cursor().execute(query)
        conn.commit()


try:
    conn = psycopg2.conn = psycopg2.connect("dbname='questionnaire' user='questionnaire' password='qwerty' host='localhost' port='5555'")
    print("connect to database")

    clear_db()
    create_tables()

    conn.cursor().execute("select * from principal;")
    conn.commit()

    data_authors()
    data_countries()
    data_interviewees()
except psycopg2.Error as e:
    print("Не удалось подключиться к базе данных:", str(e))
finally:
    if conn is not None:
        conn.close()
        print("Соединение закрыто")