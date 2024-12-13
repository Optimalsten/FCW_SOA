ФИО: Штейнмиллер Олег Адольфович  
Дата сдачи: 12.12.2024  
Номер группы: 686

# Команды в Linux

### Создание, файл Pets.txt
```sh
cat > Pets.txt
Собаки
Кошки
Хомяки

### Создание, файл PackAnimals.txt
```sh
cat > PackAnimals.txt
Лошади
Верблюды
Ослы
```

### Объединение, файлы Pets.txt и PackAnimals.txt - в HumanFriends.txt
```sh
cat Pets.txt PackAnimals.txt > HumanFriends.txt
```

### Проверка результата на наличия файла в директории
```sh
ls
```

### Просмотр, файл HumanFriends.txt
```sh
cat HumanFriends.txt
```

### Переименование, файл HumanFriends.txt в файл HumanFriends_new (и обратно)
```sh
mv HumanFriends.txt HumanFriends_new
ls
mv HumanFriends_new HumanFriends.txt
```

### Создание новой директории
```sh
mkdir Final_Control_Work
```

### Перемещение в другую директорию (Final_Control_Work), файл HumanFriends.txt
```sh
mv HumanFriends.txt Final_Control_Work/
```

### Проверка результата
```sh
ls Final_Control_Work/
```
# MySQL в Linux

### Установка MySQL и установка пакета из дополнительного репозитория
```sh
   99  sudo wget -c https://dev.mysql.com/get/mysql-apt-config_0.8.33-1_all.deb
  100  ll
  101  sudo dpkg - mysql-apt-config_0.8.33_1_all.deb
  102  sudo dpkg -i mysql-apt-config_0.8.33_1_all.deb
  103* 
  104  clear
  105  sudo apt update
  106  sudo su
  107  sudo apt update
  108  apt list --upgradable
  109  clear
  110  ll
  111  sudo su
  112  history
  113  clear
  114  sudo su
  115  sudo apt install mysql
  116  sudo apt install mysql-community-server
```

# Управление deb-пакетами

### Установка deb-пакета, проверка, удаление, полное удаление, проверка
```sh
  123  sudo apt-get install curl
  124  dpkg -l | grep curl
  125  sudo dpkg -r curl
  126  sudo dpkg -P curl
  127  dpkg -l | grep curl
```
# История команд в терминале Ubuntu

    42  clear
    43  ls
    44  cat > Pets.txt
    45  ls
    46  cat Pets.txt
    47  cat > PackAnimals.txt
    48  ls
    49  cat PackAnimals.txt
    50  cat Pets.txt PackAnimals.txt > HumanFriends.txt
    51  ls
    52  cat HumanFriends
    53  cat HumanFriends.txt
    54  mv HumanFriens.txt HumanFriens_new
    55  mv HumanFriends.txt HumanFriends_new
    56  ls
    57  mv HumanFriends-new HumanFriends.txt
    58  mv HumanFriends_new HumanFriends.txt
    59  ls
    60  history
    61  clear
    62  mkdir Final_Control_Work
    63  ls
    64  mv HumanFriends.txt Final_Control_Work/
    65  ls
    66  ls Final_Control_Work/
    67  History
    68  history

    99  sudo wget -c https://dev.mysql.com/get/mysql-apt-config_0.8.33-1_all.deb
    100  ll
    101  sudo dpkg - mysql-apt-config_0.8.33_1_all.deb
    102  sudo dpkg -i mysql-apt-config_0.8.33_1_all.deb
    103*
    104  clear
    105  sudo apt update
    106  sudo su
    107  sudo apt update
    108  apt list --upgradable
    109  clear
    110  ll
    111  sudo su
    112  history
    113  clear
    114  sudo su
    115  sudo apt install mysql
    116  sudo apt install mysql-community-server

    122  clear
    123  sudo apt-get install curl
    124  dpkg -l | grep curl
    125  sudo dpkg -r curl
    126  sudo dpkg -P curl
    127  dpkg -l | grep curl

    1  cd ~
    2  cd /etc/apt
    3  cd sources.list.d/
    4  ll
    5  remove archive_uri-http_repo_mysql_com_apt_ubuntu_focal-jammy.list
    6  ll
    7  rm archive_uri-http_repo_mysql_com_apt_ubuntu_focal-jammy.list
    8  ll
    9  exit
    10  cd ~
    11  cd /etc/apt
    12  cd sources.list.d/
    13  ll
    14  cat mysql.list
    15  history

    113  clear
    114  sudo su
    115  sudo apt install mysql
    116  sudo apt install mysql-community-server

    122  clear
    123  sudo apt-get install curl
    124  dpkg -l | grep curl
    125  sudo dpkg -r curl
    126  sudo dpkg -P curl
    127  dpkg -l | grep curl
    128  history

