/*

1. 商品
Pack(盒), Box(箱), Bottle(瓶), Bag(包), Dozen(打)
+-----------+-------------+-------+--------+----------+
| productId | productName | price |  unit  | isLaunch |
+-----------+-------------+-------+--------+----------+
|    501    |   Coffee    |  300  |  Pack  |   true   |
|    502    |  Green Tea  |  150  |  Box   |   false  |
|    503    |   Honey     |  200  | Bottle |   false  |
|    504    |   Sugar     |  100  |  Bag   |   true   |
|    505    |   Milk      |  450  |  Dozen |   true   |
+-----------+-------------+-------+--------+----------+

2. 使用者
level: 1(一般會員-進行團購), 2(後臺維運人員-進行團購+上架商品)
+--------+----------+----------+-------+
| userId | username | password | level |
+--------+----------+----------+-------+
|  101   | user123  | pass123  |   1   |
|  102   | user456  | pass456  |   2   |
|  103   | user789  | pass789  |   1   |
+--------+----------+----------+-------+

3. 購物車主檔(Master)
+--------+----------+-----------+------------+--------------+
| cartId |  userId  | cartItems | isCheckout | checkoutTime |
+--------+----------+-----------+------------+--------------+
|  201   |   101    | [1, 2]    |    true    | /-/-/  0:0:0 |
|  202   |   102    | [3]       |    false   |              |
|  203   |   103    | [4, 5]    |    true    | /-/-/  0:0:0 |
|  204   |   103    | []        |    false   |              |
|  205   |   101    | [6]       |    true    | /-/-/  0:0:0 |
+--------+----------+-----------+------------+--------------+

ps: cartItems 一對多關聯

4. 購物車明細檔(Detail)
+--------+----------+-----------+------------+
| itemId |  cartId  | productId |  quantity  |
+--------+----------+-----------+------------+
|   1    |   201    |    501    |     10     |
|   2    |   201    |    502    |     8      |
|   3    |   202    |    503    |     5      |
|   4    |   203    |    502    |     8      |
|   5    |   203    |    504    |     20     |
|   6    |   205    |    505    |     15     |
+--------+----------+-----------+------------+

資料庫的建立: CREATE SCHEMA `group_buy` DEFAULT CHARACTER SET utf8mb4 ;

 * */
-- 授權資料表
drop table if exists level_ref_service;
drop table if exists service;
drop table if exists level;

drop table if exists cartitem;
drop table if exists cart;
drop table if exists user;
drop table if exists product;

-- 建立 Service table
create table if not exists service(
    serviceId int primary key,
    serviceLocation varchar(50),
    serviceName varchar(50),
    serviceUrl varchar(50)
);

-- 建立 Level
create table if not exists level(
    levelId int primary key,
    levelName varchar(50)
);

-- 建立 Service 與 Level 之間的關聯表 table
create table if not exists level_ref_service(
    levelId int not null,
    serviceId int not null,
    sort int default 1,
    foreign key (levelId) references level(levelId),
    foreign key (serviceId) references service(serviceId),
    constraint unique_sid_and_aid UNIQUE(levelId, serviceId)
);


-- 建立 Product
create table if not exists product(
	productId int auto_increment primary key,
    productName varchar(50) not null,
    price decimal(10, 2),
    unit varchar(10),
    isLaunch boolean
);
-- 設置 AUTO_INCREMENT = 501
alter table product auto_increment = 501;

-- 建立 User
create table if not exists user(
	userId int auto_increment primary key,
    username varchar(50) not null,
    password varchar(50) not null,
    level int
);
-- 設置 AUTO_INCREMENT = 101
alter table user auto_increment = 101;

-- 建立 Cart 購物車主檔
create table if not exists cart(
	cartId int auto_increment primary key,
    userId int not null, 
    isCheckout boolean default false,
    checkoutTime datetime default current_timestamp,
    foreign key (userId) references user(userId)
);
-- 設置 AUTO_INCREMENT = 201
alter table cart auto_increment = 201;

-- 建立 CartItem 購物車明細檔
create table if not exists cartitem(
	itemId int auto_increment primary key,
    cartId int not null,
    productId int not null,
    quantity int default 0,
    foreign key (cartId) references cart(cartId),
    foreign key (productId) references product(productId)
);
-- 設置 AUTO_INCREMENT = 1
alter table cartitem auto_increment = 1;

-- 預設資料
insert into service (serviceId, serviceLocation, serviceName, serviceUrl) values(1, 'frontend', '團購首頁', '/mvc/group_buy/frontend/main');
insert into service (serviceId, serviceLocation, serviceName, serviceUrl) values(2, 'frontend', '🛒 購物車', '/mvc/group_buy/frontend/cart');
insert into service (serviceId, serviceLocation, serviceName, serviceUrl) values(3, 'frontend', '🔞 登出', '/mvc/group_buy/logout');
insert into service (serviceId, serviceLocation, serviceName, serviceUrl) values(4, 'frontend', '👼 Profile', '/mvc/group_buy/frontend/profile');
insert into service (serviceId, serviceLocation, serviceName, serviceUrl) values(51, 'backend', '後台報告', '/mvc/group_buy/backend/report');

insert into level(levelId, levelName) values(1, '一般客戶');
insert into level(levelId, levelName) values(2, '內部員工');

insert into level_ref_service(levelId, serviceId, sort) values(1, 1, 1);
insert into level_ref_service(levelId, serviceId, sort) values(1, 2, 2);
insert into level_ref_service(levelId, serviceId, sort) values(1, 3, 4);
insert into level_ref_service(levelId, serviceId, sort) values(1, 4, 3);
insert into level_ref_service(levelId, serviceId, sort) values(2, 1, 1);
insert into level_ref_service(levelId, serviceId, sort) values(2, 2, 2);
insert into level_ref_service(levelId, serviceId, sort) values(2, 3, 5);
insert into level_ref_service(levelId, serviceId, sort) values(2, 4, 4);
insert into level_ref_service(levelId, serviceId, sort) values(2, 51, 3);

INSERT INTO product (productId, productName, price, unit, isLaunch) VALUES
(501, 'Coffee', 300.00, 'Pack', true),
(502, 'Green Tea', 150.00, 'Box', false),
(503, 'Honey', 200.00, 'Bottle', false),
(504, 'Sugar', 100.00, 'Bag', true),
(505, 'Milk', 450.00, 'Dozen', true);


INSERT INTO user (userId, username, password, level) VALUES
(101, 'user123', 'pass123', 1),
(102, 'user456', 'pass456', 2),
(103, 'user789', 'pass789', 1);

INSERT INTO cart (cartId, userId, isCheckout, checkoutTime) VALUES
(201, 101, true, current_timestamp),
(202, 102, false, NULL),
(203, 103, true, current_timestamp),
(204, 103, false, NULL),
(205, 101, true, current_timestamp);

INSERT INTO cartitem (itemId, cartId, productId, quantity) VALUES
(1, 201, 501, 10),
(2, 201, 502, 8),
(3, 202, 503, 5),
(4, 203, 502, 8),
(5, 203, 504, 20),
(6, 205, 505, 15);

-- 每個使用者的總消費金額
-- 建立View
/*
create view UserTotalAmountView as
select u.userId, u.username,coalesce(sum(p.price*ci.quantity),0) as total
from user u
left join cart c on u.userId = c.userId
left join cartitem ci on c.cartId= ci.cartId
left join product p on ci.productId = p.productId
where c.isCheckout = true
group by u.userId, u.username
*/