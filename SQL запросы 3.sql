Выведите все товары с указанием имени и фамилии поставщика. Название товара, цену, имя и фамилию поставщика. Отсортируйте по цене по убыванию.
select p.name, p.price, v.first_name, v.last_name
from products p
inner join vendors v
on p.vend_id=v.vend_id
order by price desc;
Выведите всех клиентов с информацией об их заказах. Если у клиента нет заказов, поле должно быть NULL. Включите: фамилию, имя, номер заказа, дату, сумму. Отсортируйте по фамилии.
select c.last_name, c.first_name, o.order_id, o.order_date, o.total_amount
from customers c
left join orders o
on c.cust_id=o.cust_id
order by c.last_name;
Выведите клиентов, у которых бщая сумма заказов превышает 5000 руб. Включите: фамилию, имя, общую сумму заказов, количество заказов.
select c.last_name, c.first_name, 
sum (o.total_amount) as total_amount_summa,
count (o.order_id) as orders_count
from customers c
join orders o
on c.cust_id=o.cust_id
group by c.cust_id, c.last_name, c. first_name
having sum (o.total_amount)>5000;
Выведите три товара, которые чаще всего заказывались (по количеству заказов). Включите: название товара, количество заказов.
select p.name, 
count (oi.order_id) as orders_count
from products p
join order_items oi
on p.product_id=oi.product_id
group by p.product_id, p.name
order by orders_count desc
limit 3;
Для каждого месяца выведите: месяц (в формате ГГГГ-ММ), кол-во заказов, общую сумму заказов, среднюю сумму заказов.
select
to_char(o.order_date, 'YYYY-MM') as month,
count ( o.order_id) as order_count,
sum (oi.price) as total_summa,
avg (oi.price) as average_order_price
from orders o
join order_items oi
on o.order_id=oi.order_id
group by to_char (o.order_date, 'YYYY-MM')
order by month;
Посчитайте кол-во товаров у каждого поставщика. Выведите фамилию, имя поставщика и количество товаров. Исклбчите поставщиков без товаров.
select v.last_name, v.first_name, count (p.product_id) as product_count
from vendors v
inner join products p
on v.vend_id=p.vend_id
group by v.last_name, v.first_name;
Найдите поставщика, у которого самая высокая общая выручка от продажи товаров. Выведите фамилию, имя поставщика и общую выручку.
select v.last_name, v.first_name, sum (oi.quantity*oi.price) as total_revenue
from vendors v
inner join products p on v.vend_id=p.vend_id
inner join order_items oi on p.product_id=oi.product_id
group by v.last_name, v.first_name
order by total_revenue desc
limit 1;
Найдите клиентов, у кого есть заказы со статусом "отказ" (или cancelled). Выведите ФИО, дату и номер заказа.
select c.last_name, c.first_name, o.order_date, o.order_id
from customers c
inner join orders o on c.cust_id=o.cust_id
where o.status='cancelled';
Найдите клиентов, которые покупали товары у поставщика "Кузнецова". Выведите фамилию, имя клиента и название товара.
select c.last_name, c.first_name, p.name
from customers c
inner join orders o on c.cust_id=o.cust_id
inner join order_items oi on o.order_id=oi.order_id
inner join products p on oi.product_id=p.product_id
inner join vendors v on p.vend_id=v.vend_id
where v.last_name='Кузнецов';
Найдите товары, котрые никогда не были в заказах. Выведите название, цену и количество на складе.
select p.name, p.price, p.stock_quantity
from products p
left join order_items oi on p.product_id=oi.product_id
where oi.orditems_id is null ;



