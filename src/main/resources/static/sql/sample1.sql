-- Количество выбравших по вариантам ответов
select q.name, opt.text, count(o_c.choice_id) from questionnaire q 
	left join option opt on q.id=opt.questionnaire_id
	left join option_choice o_c on o_c.option_id=opt.id
	where name='Как бы то ни было, убеждённость некоторых оппонентов расставила все точки над i'
	group by q.name, opt.text;