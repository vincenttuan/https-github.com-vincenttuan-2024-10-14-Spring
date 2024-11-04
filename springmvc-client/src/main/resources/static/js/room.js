// 取得所有房間的資料並顯示在列表中 
const fetchRooms = async() => {
	const response = await fetch('http://localhost:8080/booking/rest/rooms');
	const apiResponse = await response.json();
	console.log(apiResponse);
	displayRooms(apiResponse.data)
};

// 顯示房間列表
const displayRooms = (rooms) => {
	console.log(rooms);
};

// 調用 fetchRooms
fetchRooms();