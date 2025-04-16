Проект SearchEngine представляет собой систему поиска предложенных фраз на упрощенном сайте (группа сайтов). Программа индексирует страницы сайтов и сохраняет в собственности владельцев на их страницах леммы. На вкладке статистики можно увидеть, сколько проиндексировано сайтов и страниц, а также общее количество лемм.

После этого при проведении текстового запроса пользователь получает список страниц, содержащих составляющие его слова, начиная с наиболее релевантных (где предложенных в запросе слов больше всего). В результате завершения числа страниц, соответствующий запрос.

По желанию пользователь может проиндексировать отдельную страницу, дополняющую одну из указанных в конфигурационном файле сайтов. Это можно сделать как до полной индексации сайта, так и после обновления данных.

Также имеется возможность изучить поиск как по полному списку проиндексированных сайтов, так и по конкретному сайту.

В проекте задействованы технологии Java, Spring, Hibernate, MySQL, JavaScript. Перед запуском проекта

Следует создать на локальном сервере ресурсы SQL с именем search_engine, задав набор символов utf8mb4 и параметры сортировки utf8mb4_0900_as_ci,
в файле application.yaml указаны данные для доступа к серверу, имя пользователя: пароль root: qwer1234 URL: jdbc:mysql://localhost:3306
а также адреса и имена сайтов для индексации и поиска:

по желанию можно изменить размер фрагмента (фрагмент текста, который будет представлен в рамках проверки)

Для начала работы с приложением необходимо запустить файл SearchEngine.jar, например, с командной строкой: java -jar SearchEngine.jar После этого интерфейс программы будет доступен в браузере по адресу http://localhost:8080/ Программа откроется на вкладке статистики: DASHBOARD Разумеется, если база данных search_engine пуста, на всех кнопках Google будет нулевое количество.

Нажав на имя любого из сайтов можно получить более подробную информацию:

На вкладке УПРАВЛЕНИЕ можно запустить индексацию всех указанных в конфигурационном файле сайтов, нажав на кнопку НАЧАТЬ ИНДЕКСИРОВАНИЕ.

Или введите адрес отдельной страницы, относящейся к одному из этих сайтов, и нажмите «ДОБАВИТЬ/ОБНОВИТЬ».

В случае, если страница недоступна или не существует, разразится сообщение. Аналогично манускрипту и сообщениям об ошибках ввода, индексации или поиска.

Для поиска по проиндексированным страницам нужно перейти на вкладку ПОИСК Искать можно как сразу по всем сайтам.

так и по выбранному в выпадающем списке.
