create table if not exists BaseData(
	groupName varchar(50),
    itemId int,
    itemName varchar(50)
);

insert into BaseData(groupName, itemId, itemName) values('Education', 1, '小學');
insert into BaseData(groupName, itemId, itemName) values('Education', 2, '國中');
insert into BaseData(groupName, itemId, itemName) values('Education', 3, '高中');
insert into BaseData(groupName, itemId, itemName) values('Education', 4, '大學');
insert into BaseData(groupName, itemId, itemName) values('Education', 5, '研究所');
insert into BaseData(groupName, itemId, itemName) values('Sex', 1, '男');
insert into BaseData(groupName, itemId, itemName) values('Sex', 2, '女');
insert into BaseData(groupName, itemId, itemName) values('Interest', 1, '爬山 🏕');
insert into BaseData(groupName, itemId, itemName) values('Interest', 2, '音樂 🎶');
insert into BaseData(groupName, itemId, itemName) values('Interest', 3, '看書');
insert into BaseData(groupName, itemId, itemName) values('Interest', 4, '刺繡');
insert into BaseData(groupName, itemId, itemName) values('Interest', 5, '國畫');
insert into BaseData(groupName, itemId, itemName) values('Interest', 6, 'FPV ✈');

create table if not exists user(
	id int auto_increment primary key,
    name varchar(50),
    age int,
    birth timestamp,
    resume varchar(50),
    educationId int,
    sexId int
);

create table if not exists user_interest(
	userId int,
    interestId int,
    FOREIGN KEY (userId) REFERENCES user(id)
);

