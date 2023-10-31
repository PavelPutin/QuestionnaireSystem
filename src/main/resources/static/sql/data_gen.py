import random
import uuid
import requests
import psycopg2


authors_amount_max = 10
questionnaires_amount_max = 6
options_amount_max = 8
interviewees_max = 1000
choiced_questionnaries_max = 3


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


def insert(table_name, to_insert, values):
    query = f"INSERT INTO {table_name} ({to_insert}) VALUES ({values});"
    conn.cursor().execute(query)
    print(query)
    conn.commit()


countries = []
def data_countries():
    with open("countries.txt", "r", encoding="utf8") as c_file:
        for line in c_file:
            conn.cursor().execute(line)
            countries.append(line[45:47])
    conn.commit()


questionnaires = []


def insert_questionnairy(user_id):
    table_name = "questionnaire"
    to_insert = "id, name, question, author_id, multiple"

    questionnaire_id = uuid.uuid4()
    name = get_title()[:100]
    question = get_question()[:300]
    multiple = random.choice((True, False))

    values = f"'{questionnaire_id}', '{name}', '{question}', '{user_id}', {multiple}"
    insert(table_name, to_insert, values)

    options_amount = random.randint(2, options_amount_max)
    options = []
    for _o in range(options_amount):
        table_name = "option"
        to_insert = "id, text, questionnaire_id"

        option_id = uuid.uuid4()
        text = get_option()[:75]

        options.append(option_id)
        values = f"'{option_id}', '{text}', '{questionnaire_id}'"
        insert(table_name, to_insert, values)
    
    questionnaires.append({
        "id": questionnaire_id,
        "multiple": multiple,
        "options": options
    })

author_counter = 0
users = []

def data_users():
    global author_counter
    print("- data_users")
    for _ in range(interviewees_max):
        table_name = "user_table"
        to_insert = "id, username, password, age, gender, country, marital_status"

        user_id = uuid.uuid4()
        username = ("author" if author_counter < 10 else "user") + str(_)
        password = "qwerty"
        age = random.randint(14, 70)
        gender = random.choice(('male', 'female'))
        country = random.choice(countries)
        marital_status = random.choice(('married', 'divorced', 'was_not_married'))

        values = f"'{user_id}', '{username}', '{password}', {age}, '{gender}', '{country}', '{marital_status}'"
        insert(table_name, to_insert, values)

        users.append({
            "id": user_id,
            "age": age,
            "gender": gender,
            "country": country,
            "marital_status": marital_status
        })

        if author_counter < 10:
            author_counter += 1

            questionnaire_amount = random.randint(0, questionnaires_amount_max)
            for _q in range(questionnaire_amount):
                insert_questionnairy(user_id)
    print("+ data_interviewees")


def make_choices():
    for user in users:
        choiced_questionnarie = random.sample(questionnaires, random.randint(1, choiced_questionnaries_max))
        for q in choiced_questionnarie:

            talbe_name = "answered"
            to_insert = "user_id, questionnaire_id"

            values = f"'{user['id']}', '{q['id']}'"
            insert(talbe_name, to_insert, values)

            selected_options_amount = random.randint(1, len(q["options"]) if q["multiple"] else 1)
            selected_options = random.sample(q["options"], k=selected_options_amount)
            for sel_option in selected_options:
                table_name = "choice"
                to_insert = "id, option_id, age, gender, country, marital_status"

                choice_id = uuid.uuid4()
                
                values = f"'{choice_id}', '{sel_option}', {user['age']}, '{user['gender']}', '{user['country']}', '{user['marital_status']}'"
                insert(table_name, to_insert, values)


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
    conn.commit()

    data_countries()
    data_users()
    make_choices()
except psycopg2.Error as e:
    print("Не удалось подключиться к базе данных:", e)
finally:
    if conn is not None:
        conn.close()
        print("Соединение закрыто")