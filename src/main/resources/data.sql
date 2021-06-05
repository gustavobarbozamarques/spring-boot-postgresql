--categories
insert into categories (name, id) values ('Eletronics', 1);
insert into categories (name, id) values ('Fashion', 2);

--products
insert into products (category_id, description, name, price, id) values (1, 'Notebook 4GB RAM, 120GB SSD', 'Notebook Basic', 1500, 1);
insert into products (category_id, description, name, price, id) values (1, 'Notebook 8GB RAM, 240GB SSD', 'Notebook Intermediary', 2500, 2);
insert into products (category_id, description, name, price, id) values (1, 'Notebook 16GB RAM, 512GB SSD', 'Notebook Advanced', 3500, 3);

insert into products (category_id, description, name, price, id) values (1, 'Cellphone 4GB RAM, 32GB', 'Cellphone Basic', 700, 4);
insert into products (category_id, description, name, price, id) values (1, 'Cellphone 6GB RAM, 64GB', 'Cellphone Intermediary', 900, 5);
insert into products (category_id, description, name, price, id) values (1, 'Cellphone 8GB RAM, 128GB', 'Cellphone Advanced', 1200, 6);

insert into products (category_id, description, name, price, id) values (2, 'Shoes CC', 'Shoes Basic', 50, 7);
insert into products (category_id, description, name, price, id) values (2, 'Shoes BB', 'Shoes Intermediary', 70, 8);
insert into products (category_id, description, name, price, id) values (2, 'Shoes AA', 'Shoes Advanced', 90, 9);

insert into products (category_id, description, name, price, id) values (2, 'Watch Bronze', 'Watch Bronze 300g', 500, 10);
insert into products (category_id, description, name, price, id) values (2, 'Watch Silver', 'Watch Silver 400g', 700, 11);
insert into products (category_id, description, name, price, id) values (2, 'Watch Gold', 'Watch Gold 500g', 900, 12);