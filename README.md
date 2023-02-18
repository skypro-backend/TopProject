# Дипломный проект
## Платформа по перепродаже вещей
***

### Проект написан командой "ЭС КАК ДОЛЛАР" курса "SkyPro Java-developer" в составе:
* Зимин Даниил ([GitHub](https://github.com/DaniilZimin))
* Ефремчиков Макс ([GitHub](https://github.com/thepro545))
* Лодочников Антон ([GitHub](https://github.com/J0ker001))
* Минайлов Роман
***

**Задачей команды было написать бекенд-часть сайта на Java для готовой фронтенд части и реализовать следующий функционал:**
* Авторизация и аутентификация пользователей. 
* Распределение ролей между пользователями: пользователь и администратор. 
* CRUD для объявлений на сайте: администратор может удалять или редактировать все объявления, а пользователи — только свои. 
* Под каждым объявлением пользователи могут оставлять отзывы.
* В заголовке сайта можно осуществлять поиск объявлений по названию.
* Показывать и сохранять картинки объявлений.

В качестве шаблона был предоставлен файл [Openapi](openapi.yaml)

### Стек технологий
***
**В проекте используются**:

* Backend:
    - Java 17
    - Maven
    - Spring Boot
    - Spring Web
    - Spring Data
    - Spring JPA
    - Spring Security
    - GIT
    - REST
    - Swagger
    - Lombok
    - Stream API
* SQL:
    - PostgreSQL
    - Liquibase
* Frontend:
    - Docker образ

### Запуск
***

**Для запуска нужно:**
- Клонировать проект в среду разработки
- Прописать properties в файле **[application.properties](src/main/resources/application.properties)**
- Запустить **[Docker](https://www.docker.com)**
- Запустить **[Docker образ](https://drive.google.com/file/d/1ZoGOJaHidywKNYlvNuz6kb0KoGPbeC_b/view)** // Нет доступа
- Запустить метод **main** в файле **[HomeworkApplication.java](src/main/java/ru/skypro/homework/HomeworkApplication.java)**

После выполнения всех действий сайт будет доступен по ссылке http://localhost:3000 и Swagger по [ссылке](http://localhost:8080/swagger-ui/index.html#).

