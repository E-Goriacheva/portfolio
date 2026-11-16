Создайте триггер, который запрещает установку отрицательного значения stock_quantity в таблице product.
create or replace function check_stock_quantity ()
returns trigger as $$
begin
  if new.stock_quantity<0 then
  raise exception 'Остаток товара на складе не может быть отрицательный %', new.stock_quantity;
  end if;
  return new;
end;
$$ language plpgsql;

create trigger tr_check_stock_quantity
before insert or update of stock_quantity on products
for each row
execute function check_stock_quantity ();

Создайте триггер для проверки остатка товара на складе при добавлении позиции в заказ.
create or replace function check_stock ()
returns trigger as $$
declare
   current_stock integer;
   product_name character varying (200);
begin
   select stock_quantity, name  
   into current_stock, product_name
   from products
   where product_id=new.product_id;
  if current_stock<new.quantity
  then raise exception 'Недостаточно товара на складе %. Доступно %. Заказано %',product_name, current_stock, new.quantity;
  end if;
  return new;
end;
$$ language plpgsql;

create trigger tr_check_stock
before insert on order_items
for each row
execute function check_stock ();

Создайте триггер, который при добавлении, изменении или удалении позиции в order_items автоматически пересчитывает и обновляет поле total_amount в таблице orders. 
Create or replace function update_total_amount () 
returns trigger as $$
begin
    update orders
    set total_amount = (
        select sum(quantity * price)
        from order_items
        where order_id = new.order_id
    )
    where order_id = new.order_id;
    return new;
end;
$$ language plpgsql;

create trigger tr_update_total_amount
after insert or update or delete on order_items
for each row
execute function update_total_amount ();