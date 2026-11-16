Найдите все товары, цена которых выше средней цены всех товаров. Выведите название, цену и количество на складе. Отсортируйте по цене по убыванию. 
select name, price, stock_quantity
from products
where price >
(
select avg (price) 
from products
)
order by price desc;
Найдите всех клиентов, которые сделали хотя бы один заказ. Выведите фамилию, имя и город. Отсортируйте по фамилии.
select last_name, first_name, city
from customers
where cust_id in 
(
select cust_id 
from orders 
where cust_id is not null
)
order by last_name;
 Выведите всех поставщиков (имя, фамилию) и общую стоимость их товаров на складе (цена * количество).
select first_name, last_name, 
( 
select
sum (price*stock_quantity) as total_summa 
from products
where products.vend_id=vendors.vend_id
)
from vendors;
Выведите номер заказа, дату и общее количество товаров (сумму) в этом заказе.
 select order_id, order_date,
(
select sum (quantity)
from order_items
where order_items.order_id=orders.order_id
)
from orders;
найдите поставщиков, у которых есть хотя бы один товар дороже 5000 руб. Выведите фамилию, имя и город. 
select last_name, first_name, city
from vendors
where vend_id in 
(select vend_id 
from products 
where price > 5000
);
Подсчитайте кол-во клиентов, которые не делали заказов.
select  count (*) as cust_count
from customers
where cust_id not in 
(
select cust_id 
from orders
where cust_id is not null
);
Подсчитайте кол-во поставщиков, у которых нет товаров.
select  count (*) as vend_count
from vendors
where vend_id not in 
(
select vend_id 
from products 
where vend_id is not null
);
Найдите клиентов, которые сделали хотя бы один заказ в 2024 году. Выведите фамилию, имя, телефон
select last_name, first_name, phone
from customers
where cust_id in 
(
select cust_id 
from orders
where order_date>= '2024-01-01' and order_date< '2025-01-01'
);
Найдите клиентов, у которых есть хотя бы один отмененный заказ. Выведите фамилию, имя, город.
select last_name, first_name, city
from customers
where cust_id in 
(
select cust_id 
from orders
where status ='cancelled'
);
Найдите товары, которые никогда не были в заказах. Выведите название, цену  кол-во на складе. Отсортируйте по названию.
select name, price, stock_quantity
from products
where product_id not in
(
select product_id
from order_items
)
order by name;

