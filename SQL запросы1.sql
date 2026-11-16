Список всех неактивных клиенов, отсортированных по городу
select first_name, last_name, city, is_active
from customers
where is_active=false
order by city;
У скольки клиентов не указан телефон
select count (*) as cust_count
from customers
where phone is null;
Найти все товары с ценой дороже 5000 (включительно), отсортированных по цене по возрастанию
select *
from products
where price>=5000
order by price;
Вывести все товары с ценой от 1000 до 5000  рублей включительно, у которых количестов на складе больше 10
select *
from products
where price between 1000 and 5000
and stock_quantity > 10;
Подсчитайте количество заказов в каждом статусе. Выведите статус и количество заказов. отсортируйте по убыванию количества.
select status, count (status) as order_count
from orders
group by status
order by order_count desc;
Найдите всех клиентов, у которых фамилия начинается на букву "Ф" и состоит из 5-10 букв. Выведите:фамилию, имя, город.
select last_name, first_name, city
from customers
where last_name like 'Ф%'
and length (last_name) between 5 and 10; 
Найдите всех поставщиков, чьи фамилии заканчиваются на "ов". Выведите: имя, фамилию, город.
select first_name, last_name, city
from vendors
where trim (last_name) like '%ов';
Найдите среднюю цену товаров, цена которых выше 3000 рублей. Значение округлите до 2 знаков.
select round (avg (price)::numeric,2) as average_price
from products
where price > 3000;
Найдите товары, в названии которых есть слово "Смарт" или "Ноутбук". Выведите название и цену.
select name, price
from products
where name like '%Смарт%' or name like '%Ноутбук%';
Разейте товары на ценовые категории. Для каждой категории посчитайте количество товаров на складе. Отсортируйте по ценовой категории.
select 
case
when price < 1000 then 'Бюджетный'
when price between 1000 and 5000 then 'Средний'
when price > 5000 then 'Премиум'
end as category,
count (*) as price_count   
from products
group by 
case
when price < 1000 then 'Бюджетный'
when price between 1000 and 5000 then 'Средний'
when price > 5000 then 'Премиум'
end
order by avg(price);