// 選取 HTML DOM 元素
const roomList = document.getElementById('roomList'); // 房間列表
const resultMessage = document.getElementById('resultMessage'); // 回應訊息

// 取得所有房間的資料並顯示在列表中 
const fetchRooms = async() => {
	const response = await fetch('http://localhost:8080/booking/rest/rooms');
	const apiResponse = await response.json();
	console.log(apiResponse);
	resultMessage.innerHTML = apiResponse.message
	displayRooms(apiResponse.data)
};

// 顯示房間列表, 並為每個會議室增加[刪除]與[修改]按鈕
const displayRooms = (rooms) => {
	console.log(rooms);
	//roomList.innerHTML = JSON.stringify(rooms);
	rooms.forEach(room => {
		// 建立子元素
		const listItem = document.createElement('li');
		listItem.textContent = `編號: ${room.roomId} 名稱: ${room.roomName} 人數: ${room.roomSize}`;
		
		// 建立刪除按鈕
		const deleteButton = document.createElement('button');
		deleteButton.textContent = '刪除';
		listItem.appendChild(deleteButton); // 在 listItem 後增加一個 button
		
		// 建立修改按鈕
		const updateButton = document.createElement('button');
		updateButton.textContent = '修改';
		listItem.appendChild(updateButton); // 在 listItem 後增加一個 button
		
		// 新增子元素
		roomList.appendChild(listItem);
	});
};

// 調用 fetchRooms
fetchRooms();