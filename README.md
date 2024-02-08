# Итоговая контрольная работа

1. Используя команду cat в терминале операционной системы Linux, создать два файла Домашние животные (заполнив файл собаками, кошками, хомяками) и Вьючные животными заполнив файл (Лошадьми, верблюдами и ослы), а затем объединить их. Просмотреть содержимое созданного файла. Переименовать файл, дав ему новое имя (Друзья человека).

```
vika@vika-2004:~$ cat > pets
dog
cat
hamster               
vika@vika-2004:~$ cat > pack_animals
horse
camel
donkey
vika@vika-2004:~$ cat pets pack_animals
dog
cat
hamster
horse
camel
donkey
vika@vika-2004:~$ cat pets pack_animals > animals
vika@vika-2004:~$ cat animals
dog
cat
hamster
horse
camel
donkey
vika@vika-2004:~$ mv animals human_friends
```

2. Создать директорию, переместить файл туда.

```
vika@vika-2004:~$ mkdir folder1 && mv human_friends ./folder1
vika@vika-2004:~$ cd folder1
vika@vika-2004:~/folder1$ ls
human_friends
```

3. Подключить дополнительный репозиторий MySQL. Установить любой пакет из этого репозитория

```
vika@vika-2004:~$ wget https://repo.mysql.com//mysql-apt-config_0.8.25-1_all.deb
--2024-02-06 22:02:16--  https://repo.mysql.com//mysql-apt-config_0.8.25-1_all.deb
Resolving repo.mysql.com (repo.mysql.com)... 23.210.253.161, 2a02:26f0:9500:b8a::1d68, 2a02:26f0:9500:b83::1d68
Connecting to repo.mysql.com (repo.mysql.com)|23.210.253.161|:443... connected.
HTTP request sent, awaiting response... 200 OK
Length: 18120 (18K) [application/x-debian-package]
Saving to: ‘mysql-apt-config_0.8.25-1_all.deb’

mysql-apt-config_0.8.25-1_all.de 100%[=========================================================>]  17,70K  --.-KB/s    in 0s      

2024-02-06 22:02:16 (71,1 MB/s) - ‘mysql-apt-config_0.8.25-1_all.deb’ saved [18120/18120]

vika@vika-2004:~$ sudo dpkg -i mysql-apt-config_0.8.25-1_all.deb
[sudo] password for vika: 
Selecting previously unselected package mysql-apt-config.
(Reading database ... 174288 files and directories currently installed.)
Preparing to unpack mysql-apt-config_0.8.25-1_all.deb ...
Unpacking mysql-apt-config (0.8.25-1) ...
Setting up mysql-apt-config (0.8.25-1) ...
vika@vika-2004:~$ sudo apt install mysql-client
```
4. Установить и удалить deb-пакет с помощью dpkg.

sudo dpkg -i *package_name*

sudo dpkg -r *package_name*

sudo dpkg -P *package_name*

5. Выложить историю команд в терминале ubuntu

```
vika@vika-2004:~$ history | tail
 1054  cd folder1
 1055  ls
 1056  cd ..
 1057  wget https://repo.mysql.com//mysql-apt-config_0.8.25-1_all.deb
 1058  sudo dpkg -i mysql-apt-config_0.8.25-1_all.deb
 1059  sudo apt install mysql-client
 1060  sudo dpkg -P mysql-apt-config_0.8.25-1_all.deb
 1061  sudo dpkg -P mysql-server
 1062  sudo dpkg -P mysql-client
 1063  history | tail
```

6. Нарисовать диаграмму, в которой есть класс родительский класс, домашние животные и вьючные животные, в составы которых в случае домашних животных войдут классы: собаки, кошки, хомяки, а в класс вьючные животные войдут: Лошади, верблюды и ослы.

![animals_diagram](animals.png)

7. В подключенном MySQL репозитории создать базу данных “Друзья человека”

```
CREATE DATABASE mans_friends;

USE mans_friends;
```
8. Создать таблицы с иерархией из диаграммы в БД

```
CCREATE TABLE animals(
	id_animal INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    animal_type VARCHAR(20) NOT NULL
);

CREATE TABLE pets (
	id_pets INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    animal_subtype VARCHAR(20) NOT NULL,
    animal_type INT NOT NULL,
    FOREIGN KEY (animal_type) REFERENCES animal_type (id_animal)
);

CREATE TABLE pack_animals (
	id_pack_animals INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    animal_subtype VARCHAR(20) NOT NULL,
    animal_type INT NOT NULL,
    FOREIGN KEY (animal_type) REFERENCES animal_type (id_animal)
);

CREATE TABLE dogs (
	id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name VARCHAR(30) NOT NULL,
    command VARCHAR(100),
    birthday DATE,
    animal_subtype INT,
    FOREIGN KEY (animal_subtype) REFERENCES pets(id_pets)
);

CREATE TABLE cats (
	id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name VARCHAR(30) NOT NULL,
    command VARCHAR(100),
    birthday DATE,
	animal_subtype INT,
    FOREIGN KEY (animal_subtype) REFERENCES pets(id_pets)
);

CREATE TABLE hamsters (
	id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name VARCHAR(30) NOT NULL,
    ommand VARCHAR(100),
    birthday DATE,
    animal_subtype INT,
    FOREIGN KEY (animal_subtype) REFERENCES pets(id_pets)
);

CREATE TABLE horses (
	id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name VARCHAR(30) NOT NULL,
    command VARCHAR(100),
    birthday DATE,
    animal_subtype INT,
    FOREIGN KEY (animal_subtype) REFERENCES pack_animals(id_pack_animal)
);

CREATE TABLE donkeys (
	id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name VARCHAR(30) NOT NULL,
    command VARCHAR(100),
    birthday DATE,
    animal_subtype INT,
    FOREIGN KEY (animal_subtype) REFERENCES pack_animals(id_pack_animal)
);

CREATE TABLE camels (
	id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name VARCHAR(30) NOT NULL,
    command VARCHAR(100),
    birthday DATE,
    animal_subtype INT,
    FOREIGN KEY (animal_subtype) REFERENCES pack_animals (id_pack_animal)
);

```
9. Заполнить низкоуровневые таблицы именами(животных), командами, которые они выполняют и датами рождения

```
INSERT INTO animal_type (animal_type) VALUES 
	("Pets"),
    ("Pack animals")
;
INSERT INTO pets(animal_subtype, animal_type) VALUES 
	("Dogs", (SELECT id_animal FROM animal_type WHERE animal_type = "Pets")),
    ("Cats", (SELECT id_animal FROM animal_type WHERE animal_type = "Pets")),
    ("Hamsters", (SELECT id_animal FROM animal_type WHERE animal_type = "Pets"))
;
INSERT INTO pack_animals (animal_subtype, animal_type) VALUES 
	("Horses", (SELECT id_animal FROM animal_type WHERE animal_type = "Pack animals")),
    ("Camels", (SELECT id_animal FROM animal_type WHERE animal_type = "Pack animals")),
    ("Donkeys", (SELECT id_animal FROM animal_type WHERE animal_type = "Pack animals"))
;

INSERT INTO dogs (name, command, birthday, animal_subtype) VALUES 
	("Rem", "sitdown, voice", "2010-02-07", (SELECT id_pets FROM pets WHERE animal_subtype = "Dogs")),
    ("Sharik, "sitdown, voice, turn round", "2016-08-01", (SELECT id_pets FROM pets WHERE animal_subtype = "Dogs"))
;
INSERT INTO cats (name, command, birthday, animal_subtype) VALUES 
	("Vasiliy", "sitdown, say Myau", "2001-08-06", (SELECT id_pets FROM pets WHERE animal_subtype = "Cats")),
    ("Redy", "sitdown, go to me, say Myau", "2002-05-26", (SELECT id_pets FROM pets WHERE animal_subtype = "Cats"))
;
INSERT INTO hamsters (name, command, birthday, animal_subtype) VALUES 
	("Richie", "run", "2002-08-15", (SELECT id_pets FROM pets WHERE animal_subtype = "Hamster")),
    ("Mary", "run, turn round", "2015-08-14", (SELECT id_pets FROM pets WHERE animal_subtype = "Hamster"))
;
INSERT INTO horses (name, command, birthday, animal_subtype) VALUES 
	("Shram", "follow me, stop", "2022-08-16", (SELECT id_pack_animal FROM pack_animals WHERE animal_subtype = "Horses")),
    ("Blackoe", "follow me, voice, sit", "2014-05-15", (SELECT id_pack_animal FROM pack_animals WHERE animal_subtype = "Horses"))
;
INSERT INTO donkeys (name, command, birthday, animal_subtype) VALUES 
	("Study", "follow me, voice", "2022-08-07", (SELECT id_pack_animal FROM pack_animals WHERE animal_subtype = "Donkeys")),
    ("Boris", "follow me, lei, eat", "2014-07-23", (SELECT id_pack_animal FROM id_pack_animals WHERE animal_subtype = "Donkeys"))
;
INSERT INTO camels (name, command, birthday, animal_subtype) VALUES 
	("Chapa", "sitdown, sit, voice", "2021-01-08", (SELECT id_pack_animal FROM pack_animals WHERE animal_subtype = "Camels")),
    ("Marusiya", "sitdown, turn around, drink", "2017-08-05", (SELECT id_pack_animal FROM pack_animals WHERE animal_subtype = "Camels"));
```
10. Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой питомник на зимовку. Объединить таблицы лошади, и ослы в одну таблицу

```
DROP TABLE camels;

SELECT * FROM horses UNION SELECT * FROM donkeys;
```
11. Создать новую таблицу “молодые животные” в которую попадут все животные старше 1 года, но младше 3 лет и в отдельном столбце с точностью до месяца подсчитать возраст животных в новой таблице

```
CREATE TABLE young_animal AS (
SELECT *, TIMESTAMPDIFF(MONTH, birthday, NOW()) AS ages
FROM 
	(SELECT name, command, birthday, p.animal_subtype, a_t.animal_type FROM dogs AS d LEFT JOIN pets AS p ON d.animals = p.id_pets LEFT JOIN animal_type AS a_t ON p.animal_type = a_t.id_animal UNION

	SELECT name, command, birthday, p.animal_subtype, a_t.animal_type FROM cats AS c LEFT JOIN pets AS p ON c.animals = p.id_pets LEFT JOIN animal_type AS a_t ON p.animal_type = a_t.id_animal UNION

	SELECT name, command, birthday, p.animal_subtype a_t.animal_type FROM hamsters AS h LEFT JOIN pets AS p ON h.animals = p.id_pets LEFT JOIN animal_type AS a_t ON p.animal_type = a_t.id_animal UNION

	SELECT name, command, birthday, pa.animal_subtype, a_t.animal_type FROM horses AS h LEFT JOIN pack_animals AS pa ON h.animals = pa.id_pack_animal LEFT JOIN animal_type AS a_t ON pa.animal_type = a_t.id_animal UNION

	SELECT name, command, birthday, pa.animal_subtype, a_t.animal_type FROM donkeys AS d LEFT JOIN pack_animals AS pa ON d.animals = pa.id_pack_animal LEFT JOIN animal_type AS a_t ON pa.animal_type = a_t.id_animal)
 AS tmp
 HAVING ages BETWEEN 12 AND 36);

```
12. Объединить все таблицы в одну, при этом сохраняя поля, указывающие на прошлую принадлежность к старым таблицам

```
CREATE TABLE all_animals AS (SELECT name, command, birthday, p.animal_subtype, a_t.animal_type FROM dogs AS d LEFT JOIN pets AS p ON d.animals = p.id_pets LEFT JOIN animal_type AS a_t ON p.animal_type = a_t.id_animal UNION

SELECT name, command, birthday, p.animal_subtype, a_t.animal_type FROM cats AS c LEFT JOIN pets AS p ON c.animals = p.id_pets LEFT JOIN animal_type AS a_t ON p.animal_type = a_t.id_animal UNION

SELECT name, command, birthday, p.animal_subtype, a_t.animal_type FROM hamsters AS h LEFT JOIN pets AS p ON h.animals = p.id_pets LEFT JOIN animal_type AS a_t ON p.animal_type = a_t.id_animal UNION

SELECT name, command, birthday, pa.animal_subtype, a_t.animal_type FROM horses AS h LEFT JOIN pack_animals AS pa ON h.animals = pa.id_pack_animal LEFT JOIN animal_type AS a_t ON pa.animal_type = a_t.id_animal UNION

SELECT name, command, birthday, pa.animal_subtype, a_t.animal_type FROM donkeys AS d LEFT JOIN pack_animals AS pa ON d.animals  = pa.id_pack_animal LEFT JOIN animal_type AS a_t ON pa.animal_type = a_t.id_animal);

```

13. Создать класс с Инкапсуляцией методов и наследованием по диаграмме.
14. Написать программу, имитирующую работу реестра домашних животных.
В программе должен быть реализован следующий функционал:

* Завести новое животное
* определять животное в правильный класс
* увидеть список команд, которое выполняет животное
* обучить животное новым командам
* Реализовать навигацию по меню

15. Создайте класс Счетчик, у которого есть метод add(), увеличивающий̆
значение внутренней̆int переменной̆на 1 при нажатие “Завести новое животное” Сделайте так, чтобы с объектом такого типа можно было работать в блоке try-with-resources. Нужно бросить исключение, если работа с объектом ипа счетчик была не в ресурсном try и/или ресурс остался открыт. Значение считать в ресурсе try, если при заведения животного заполнены все поля.










