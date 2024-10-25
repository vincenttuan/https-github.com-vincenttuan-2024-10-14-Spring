-- 建立 Room 資料表
create table if not exists room (
	roomId int primary key,
    roomName varchar(50) not null,
    roomSize int not null
);