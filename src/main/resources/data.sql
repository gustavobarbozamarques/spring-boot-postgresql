--categories
insert into categories (name, id) values ('Eletronics', nextval('category_seq'));
insert into categories (name, id) values ('Fashion', nextval('category_seq'));

--products
insert into products (category_id, description, name, price, id) values (1, 'Notebook 4GB RAM, 120GB SSD', 'Notebook Basic', 1500, nextval('product_seq'));
insert into products (category_id, description, name, price, id) values (1, 'Notebook 8GB RAM, 240GB SSD', 'Notebook Intermediary', 2500, nextval('product_seq'));
insert into products (category_id, description, name, price, id) values (1, 'Notebook 16GB RAM, 512GB SSD', 'Notebook Advanced', 3500, nextval('product_seq'));

insert into products (category_id, description, name, price, id) values (2, 'Watch Bronze', 'Watch Bronze 300g', 500, nextval('product_seq'));
insert into products (category_id, description, name, price, id) values (2, 'Watch Silver', 'Watch Silver 400g', 700, nextval('product_seq'));
insert into products (category_id, description, name, price, id) values (2, 'Watch Gold', 'Watch Gold 500g', 900, nextval('product_seq'));

--users
insert into users (name, email, password, id) values ('John Doe', 'john.doe@example.com', 'password123', nextval('user_seq'));
insert into users (name, email, password, id) values ('Jane Smith', 'jane.smith@example.com', 'securepass', nextval('user_seq'));
insert into users (name, email, password, id) values ('Peter Jones', 'peter.jones@example.com', 'mysecret', nextval('user_seq'));