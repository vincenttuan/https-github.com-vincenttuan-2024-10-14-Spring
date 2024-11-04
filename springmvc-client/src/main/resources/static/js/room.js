// 取得所有房間的資料並顯示在列表中 

const fetchRooms = async() => {
	const response = await response.json();
	console.log(response);
};

// 調用 fetchRooms
fetchRooms();