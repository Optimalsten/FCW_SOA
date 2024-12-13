### Общие данные
// Штейнмиллер Олег Адольфович

// Дата сдачи: 12.122024 

// Группа № 6866


### Создание базы данных

_Запуск mysql в терминале на Linux_
```sh
fcw@fcw-ubuntu:~$ ls
Desktop    Final_Control_Work                 mysql-apt-config_0.8.33-1_all.deb    PackAnimals.txt  Public     Videos
Documents  Music                              mysql-apt-config_0.8.33-1_all.deb.1  Pets.txt         snap
Downloads  mysql-apt-config_0.8.15-1_all.deb  mysql-pwd                            Pictures         Templates
fcw@fcw-ubuntu:~$ sudo systemctl stop mysql
fcw@fcw-ubuntu:~$ sudo mysqld -init-file=~/mysql-pwd
2024-12-09T23:01:00.546292Z 0 [System] [MY-015015] [Server] MySQL Server - start.
2024-12-09T23:01:00.735904Z 0 [System] [MY-010116] [Server] /usr/sbin/mysqld (mysqld 8.4.3) starting as process 51126
2024-12-09T23:01:00.739763Z 0 [ERROR] [MY-010123] [Server] Fatal error: Please read "Security" section of the manual to find out how to run mysqld as root!
2024-12-09T23:01:00.739932Z 0 [ERROR] [MY-010119] [Server] Aborting
2024-12-09T23:01:00.740330Z 0 [System] [MY-010910] [Server] /usr/sbin/mysqld: Shutdown complete (mysqld 8.4.3)  MySQL Community Server - GPL.
2024-12-09T23:01:00.740333Z 0 [System] [MY-015016] [Server] MySQL Server - end.
fcw@fcw-ubuntu:~$ sudo systemctl start mysql
fcw@fcw-ubuntu:~$ mysql -u root -p
Enter password: 
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 8
Server version: 8.4.3 MySQL Community Server - GPL

Copyright (c) 2000, 2024, Oracle and/or its affiliates.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql>
```

_Создание БД HumanFriends_
```sh
mysql> CREATE DATABASE HumanFriends;
mysql> USE Human_Friends;
Database changed
```

### Создание таблиц

_Создание Таблицы Pets_
```sh
mysql> CREATE TABLE Pets (
-> ID INT AUTO_INCREMENT PRIMARY KEY,
-> Name VARCHAR(45),
-> Type VARCHAR(45),
-> BirthDay DATE,
-> Commands TEXT
-> );
Query OK, 0 rows affected (0,01 sec)
```

_Создание Таблицы PackAnimals_
```sh
mysql> CREATE TABLE PackAnimals
-> ID INT AUTO_INCREMENT PRIMARY KEY,
-> Name VARCHAR(45),
-> Type VARCHAR(45),
-> BirthDay DATE,
-> Commands TEXT
-> );Query OK, 0 rows affected (0,01 sec)
```

### Заполнение Таблиц данными о животных

_Заполнение Таблицы Pets_
```sh
mysql> INSERT INTO Pets (Name, Type, BirthDay, Commands) VALUES
-> ('Fido', 'Dog', '2020-01-01', 'Sit, Stay, Fetch'),
-> ('Whiskers', 'Cat', '2019-05-15', 'Sit, Pounce'),
-> ('Hammy', 'Hamster', '2021-03-10', 'Roll, Hide'),
-> ('Buddy', 'Dog', '2018-12-10', 'Sit, Paw, Bark'),
-> ('Smudge', 'Cat', '2020-02-20', 'Sit, Pounce, Scratch'),
-> ('Peanut', 'Hamster', '2021-08-01', 'Roll, Spin'),
-> ('Bella', 'Dog', '2019-11-11', 'Sit, Stay, Roll'),
-> ('Oliver', 'Cat', '2020-06-30', 'Meow, Scratch, Jump');
Query OK, 8 rows affected (0,01 sec)
Records: 8  Duplicates: 0  Warnings: 0
```

_Заполнение Таблицы PackAnimals_
```sh
mysql> INSERT INTO PackAnimals (Name, Type, BirthDay, Commands) VALUES
-> ('Thunder', 'Horse', '2015-07-21', 'Trot, Canter, Gallop'),
-> ('Sandy', 'Camel', '2016-11-03', 'Walk, Carry Load'),
-> ('Eeyore', 'Donkey', '2017-09-18', 'Walk, Carry Load, Bray'),
-> ('Storm', 'Horse', '2014-05-05', 'Trot, Canter'),
-> ('Dune', 'Camel', '2018-12-12', 'Walk, Sit'),
-> ('Burro', 'Donkey', '2019-01-23', 'Walk, Bray, Kick'),
-> ('Blaze', 'Horse', '2016-02-29', 'Trot, Jump, Gallop'),
-> ('Sahara', 'Camel', '2015-08-14', 'Walk, Run');
Query OK, 8 rows affected (0,00 sec)
Records: 8  Duplicates: 0  Warnings: 0
```

_Проверка результатов создания Таблиц и их заполнения_
```sh
mysql> show tables;
+-------------------------+
| Tables_in_Human_Friends |
+-------------------------+
| PackAnimals             |
| Pets                    |
+-------------------------+
2 rows in set (0,01 sec)

mysql> describe Pets;
+----------+-------------+------+-----+---------+----------------+
| Field    | Type        | Null | Key | Default | Extra          |
+----------+-------------+------+-----+---------+----------------+
| ID       | int         | NO   | PRI | NULL    | auto_increment |
| Name     | varchar(45) | YES  |     | NULL    |                |
| Type     | varchar(45) | YES  |     | NULL    |                |
| BirthDay | date        | YES  |     | NULL    |                |
| Commands | text        | YES  |     | NULL    |                |
+----------+-------------+------+-----+---------+----------------+
5 rows in set (0,04 sec)

mysql> describe PackAnimals;
+----------+-------------+------+-----+---------+----------------+
| Field    | Type        | Null | Key | Default | Extra          |
+----------+-------------+------+-----+---------+----------------+
| ID       | int         | NO   | PRI | NULL    | auto_increment |
| Name     | varchar(45) | YES  |     | NULL    |                |
| Type     | varchar(45) | YES  |     | NULL    |                |
| BirthDay | date        | YES  |     | NULL    |                |
| Commands | text        | YES  |     | NULL    |                |
+----------+-------------+------+-----+---------+----------------+
5 rows in set (0,00 sec)

mysql> SELECT * FROM Pets;
+----+----------+---------+------------+----------------------+
| ID | Name     | Type    | BirthDay   | Commands             |
+----+----------+---------+------------+----------------------+
|  1 | Fido     | Dog     | 2020-01-01 | Sit, Stay, Fetch     |
|  2 | Whiskers | Cat     | 2019-05-15 | Sit, Pounce          |
|  3 | Hammy    | Hamster | 2021-03-10 | Roll, Hide           |
|  4 | Buddy    | Dog     | 2018-12-10 | Sit, Paw, Bark       |
|  5 | Smudge   | Cat     | 2020-02-20 | Sit, Pounce, Scratch |
|  6 | Peanut   | Hamster | 2021-08-01 | Roll, Spin           |
|  7 | Bella    | Dog     | 2019-11-11 | Sit, Stay, Roll      |
|  8 | Oliver   | Cat     | 2020-06-30 | Meow, Scratch, Jump  |
+----+----------+---------+------------+----------------------+
8 rows in set (0,00 sec)

mysql> SELECT * FROM PackAnimals;
+----+---------+--------+------------+------------------------+
| ID | Name    | Type   | BirthDay   | Commands               |
+----+---------+--------+------------+------------------------+
|  1 | Thunder | Horse  | 2015-07-21 | Trot, Canter, Gallop   |
|  2 | Sandy   | Camel  | 2016-11-03 | Walk, Carry Load       |
|  3 | Eeyore  | Donkey | 2017-09-18 | Walk, Carry Load, Bray |
|  4 | Storm   | Horse  | 2014-05-05 | Trot, Canter           |
|  5 | Dune    | Camel  | 2018-12-12 | Walk, Sit              |
|  6 | Burro   | Donkey | 2019-01-23 | Walk, Bray, Kick       |
|  7 | Blaze   | Horse  | 2016-02-29 | Trot, Jump, Gallop     |
|  8 | Sahara  | Camel  | 2015-08-14 | Walk, Run              |
+----+---------+--------+------------+------------------------+
8 rows in set (0,00 sec)
```

### Удаление записей в Таблице PackAnimals о верблюдах и объединение Таблиц лошадей и ослов

_Удаление записей о верблюдах в Таблице PackAnimals_
```sh
mysql> DELETE FROM PackAnimals WHERE Type = 'Camel';
Query OK, 3 rows affected (0,01 sec)

mysql> SELECT * FROM PackAnimals;
+----+---------+--------+------------+------------------------+
| ID | Name    | Type   | BirthDay   | Commands               |
+----+---------+--------+------------+------------------------+
|  1 | Thunder | Horse  | 2015-07-21 | Trot, Canter, Gallop   |
|  3 | Eeyore  | Donkey | 2017-09-18 | Walk, Carry Load, Bray |
|  4 | Storm   | Horse  | 2014-05-05 | Trot, Canter           |
|  6 | Burro   | Donkey | 2019-01-23 | Walk, Bray, Kick       |
|  7 | Blaze   | Horse  | 2016-02-29 | Trot, Jump, Gallop     |
+----+---------+--------+------------+------------------------+
5 rows in set (0,00 sec)
```

_Объединение Таблиц лошадей и ослов_
```sh
mysql> CREATE TABLE CombinePackAnimals AS
-> SELECT ID, Name, Type, BirthDay, Commands FROM PackAnimals
-> WHERE Type IN ('Horse', 'Donkey');
Query OK, 5 rows affected (0,02 sec)
Records: 5  Duplicates: 0  Warnings: 0

mysql> SELECT * FROM CombinePackAnimals;
+----+---------+--------+------------+------------------------+
| ID | Name    | Type   | BirthDay   | Commands               |
+----+---------+--------+------------+------------------------+
|  1 | Thunder | Horse  | 2015-07-21 | Trot, Canter, Gallop   |
|  3 | Eeyore  | Donkey | 2017-09-18 | Walk, Carry Load, Bray |
|  4 | Storm   | Horse  | 2014-05-05 | Trot, Canter           |
|  6 | Burro   | Donkey | 2019-01-23 | Walk, Bray, Kick       |
|  7 | Blaze   | Horse  | 2016-02-29 | Trot, Jump, Gallop     |
+----+---------+--------+------------+------------------------+
5 rows in set (0,00 sec)
```

### Создание Таблицы для животных в возрасте от 1 до 3 лет и вычисление их возраста

```sh
mysql> CREATE TABLE YoungAnimals AS
-> SELECT ID, Name, Type, BirthDay, Commands,TIMESTAMPDIFF(MONTH, BirthDay, CURDATE()) AS AgeMonths
-> FROM PETS
-> WHERE TIMESTAMPDIFF(YEAR, BirthDay, CURDATE()) BETWEEN 1 AND 3;
ERROR 1146 (42S02): Table 'Human_Friends.PETS' doesn't exist
mysql> CREATE TABLE YoungAnimals AS SELECT ID, Name, Type, BirthDay, Commands, TIMESTAMPDIFF(MONTH, BirthDay, CURDATE()) AS NumberMonths FROM Pets WHERE TIMESTAMPDIFF(YEAR, BirthDay, CURDATE()) BETWEEN 1 AND 3;
Query OK, 2 rows affected (0,02 sec)
Records: 2  Duplicates: 0  Warnings: 0

mysql> SELECT * FROM YoungAnimals;
+----+--------+---------+------------+------------+--------------+
| ID | Name   | Type    | BirthDay   | Commands   | NumberMonths |
+----+--------+---------+------------+------------+--------------+
|  3 | Hammy  | Hamster | 2021-03-10 | Roll, Hide |           45 |
|  6 | Peanut | Hamster | 2021-08-01 | Roll, Spin |           40 |
+----+--------+---------+------------+------------+--------------+
2 rows in set (0,00 sec)
```

### Объединение всех созданных Таблиц в одну, сохраняя информацию об исходной Таблице

```sh
mysql> CREATE TABLE AnimalsTogether AS
-> SELECT ID, Name, Type, BirthDay, Commands, 'Pets' AS Origin
-> FROM Pets
-> UNION ALL
-> SELECT ID, Name, Type, BirthDay, Commands, 'PackAnimals' AS Origin
-> FROM PackAnimals;
Query OK, 13 rows affected (0,02 sec)
Records: 13  Duplicates: 0  Warnings: 0

mysql> SELECT * FROM AnimalsTogether;
+----+----------+---------+------------+------------------------+-------------+
| ID | Name     | Type    | BirthDay   | Commands               | Origin      |
+----+----------+---------+------------+------------------------+-------------+
|  1 | Fido     | Dog     | 2020-01-01 | Sit, Stay, Fetch       | Pets        |
|  2 | Whiskers | Cat     | 2019-05-15 | Sit, Pounce            | Pets        |
|  3 | Hammy    | Hamster | 2021-03-10 | Roll, Hide             | Pets        |
|  4 | Buddy    | Dog     | 2018-12-10 | Sit, Paw, Bark         | Pets        |
|  5 | Smudge   | Cat     | 2020-02-20 | Sit, Pounce, Scratch   | Pets        |
|  6 | Peanut   | Hamster | 2021-08-01 | Roll, Spin             | Pets        |
|  7 | Bella    | Dog     | 2019-11-11 | Sit, Stay, Roll        | Pets        |
|  8 | Oliver   | Cat     | 2020-06-30 | Meow, Scratch, Jump    | Pets        |
|  1 | Thunder  | Horse   | 2015-07-21 | Trot, Canter, Gallop   | PackAnimals |
|  3 | Eeyore   | Donkey  | 2017-09-18 | Walk, Carry Load, Bray | PackAnimals |
|  4 | Storm    | Horse   | 2014-05-05 | Trot, Canter           | PackAnimals |
|  6 | Burro    | Donkey  | 2019-01-23 | Walk, Bray, Kick       | PackAnimals |
|  7 | Blaze    | Horse   | 2016-02-29 | Trot, Jump, Gallop     | PackAnimals |
+----+----------+---------+------------+------------------------+-------------+
13 rows in set (0,00 sec)
```

### Завершение работы с программой MySQL

```sh
mysql> exit
```
