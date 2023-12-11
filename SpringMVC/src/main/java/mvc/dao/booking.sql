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
