Вариант 4. Система Кассовый Аппарат. Кассир может открыть чек, добавить
выбранные товары по коду из базы данных (петрушка = 234, хлеб = 222) или
по названию товара, указать кол-во товаров или вес. Закрыть чек. Старший
кассир может отменить чек, отменить один товар в чеке и вернуть деньги
покупателю. Сделать X и Z отчеты. Товаровед может создавать товары и
указывать их кол-во на складе. 

Логика:
1. Авторизация
Касир: 
1. Показать товары на складе.
2. Показать форму для открития чека.
3. Отправить форму для открития чека.
4. Показать форму для добавления товаров в чек.
5. Отправить форму для добавления товаров в чек.
Старший касир:
1. Показать все чеки.
2. Показать форму для отмены товара/чека.
3. Отправить форму для отмены товара/чека.
4. Показать форму для отчетов.
5. Отправить форму для отчетров.
Грузчик:
1. Показать товары на складе.
2. Показать форму для добавления товара на склад.
3. Отправить форму для добавления товара на склад.


1.Запуск через tomcat -> настроить tomcat.
3.Иметь в tomcat .keystore для SSH.
4.Добавить в tomcat\conf\server.xml connector:
	<Connector port="8443" protocol="org.apache.coyote.http11.Http11AprProtocol"
               maxThreads="150" SSLEnabled="true" scheme="https" secure="true" 
               clientAuth="false" sslProtocol="TLS"
               keystoreFile="C:\Program Files\Java\apache-tomcat-9.0.27\.keystore" keystorePass="localhost"/>
5.

	
