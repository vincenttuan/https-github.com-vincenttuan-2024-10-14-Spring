// 取得所有房間的資料並顯示在列表中 

const fetchRooms = async() => {
	const response = await fetch('http://localhost:8080/booking/rest/rooms');
	const apiResponse = await response.json();
	console.log(apiResponse);
};

// 調用 fetchRooms
fetchRooms();