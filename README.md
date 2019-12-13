Вариант 4. Система Кассовый Аппарат. Кассир может открыть чек, добавить
выбранные товары по коду из базы данных (петрушка = 234, хлеб = 222) или
по названию товара, указать кол-во товаров или вес. Закрыть чек. Старший
кассир может отменить чек, отменить один товар в чеке и вернуть деньги
покупателю. Сделать X и Z отчеты. Товаровед может создавать товары и
указывать их кол-во на складе. 

Требования: 
Intellij Idea, Tomcat, Java 8, MySql

Установка: 
1. Клонировать репозиторий
2. Сконфигурировать context.xml в webapp/META-INF Например: <?xml version="1.0" encoding="UTF-8"?><Context><Resource name="jdbc/finallab" auth="Container" type="javax.sql.DataSource" maxActive="100" maxIdle="30" maxWait="10000" driverClassName="com.mysql.cj.jdbc.Driver" url="jdbc:mysql://localhost:3306/finallab?serverTimezone=UTC&amp;useUnicode=true&amp;characterEncoding=UTF-8&amp;autoConnect=true" username="username" password="password"/></Context>
3. Запустить resources/finallab.sql
4. Выполнить команду mvn clean install
5. Выполнить команду mvn tomcat7:run
6. Имя пользователя грузчика: loader пароль: loader
   Имя пользователя касира: cashier пароль: cashier
   Имя пользователя старшего касира: chief пароль: chief

Логика:

Авторизация:
1. Показать форму для логина.
2. Отправить форму для логина.

Касир: 
1. Создать новый чек.
2. Показать текущий чек.
3. Показать форму для добавления товаров в чек.
4. Показать форму для закрытия чека.
5. Отправить форму для добавления товаров в чек. 
6. Отправить форму для закрытия чека.

Старший касир:
1. Показать все чеки.
2. Показать форму для отмены товара.
3. Показать форму для отмены чека.
4. Показать форму для х отчета.
5. Показать форму для z отчета.
6. Показать форму для нового х отчета.
7. Отправить форму для отмены товара.
8. Отправить форму для отмены чека.
9. Отправить форму для х отчета.
10. Отправить форму для z отчета.
11. Отправить форму для нового х отчета.

Грузчик get:
1. Показать товары на складе.
2. Показать форму для добавления товара на склад.
3. Показать форму для изменения товара на складе.
4. Отправить форму для добавления товара на склад.
5. Отправить форму для изменения товара на складе.

	
