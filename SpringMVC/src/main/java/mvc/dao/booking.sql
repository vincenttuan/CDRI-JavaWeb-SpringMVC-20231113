drop table if exists BookingRoom;
drop table if exists Room;
-- 建立 Room
create table if not exists Room(
	roomId int primary key,
    roomName varchar(50) not null unique,
    roomSize int not null default 1
);

-- 建立 BookingRoom
create table if not exists BookingRoom(
	bookingId int auto_increment primary key,
    roomId int not null,
    username varchar(50) not null,
    bookingDate varchar(50) not null,
    createDate timestamp default current_timestamp,
    foreign key (roomId) references Room(roomId),
    constraint unique_roomId_and_bookingDate unique(roomId, bookingDate)
);

-- 建立範例資料
insert into Room(roomId, roomName, roomSize) values(101, '101(S)', 10);
insert into Room(roomId, roomName, roomSize) values(102, '102(M)', 25);
insert into Room(roomId, roomName, roomSize) values(203, '203(L)', 50);
insert into Room(roomId, roomName, roomSize) values(404, '404(2L)', 100);

insert into BookingRoom(roomId, username, bookingDate) values(101, 'Tom', '2023-12-04');
insert into BookingRoom(roomId, username, bookingDate) values(102, 'Mary', '2023-12-05');
insert into BookingRoom(roomId, username, bookingDate) values(102, 'Rose', '2023-12-06');




