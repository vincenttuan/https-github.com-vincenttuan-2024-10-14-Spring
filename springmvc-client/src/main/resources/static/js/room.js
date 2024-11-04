// 選取 HTML DOM 元素
const roomList = document.getElementById('roomList'); // 房間列表
const resultMessage = document.getElementById('resultMessage'); // 回應訊息
// 新增房間 HTML DOM 元素
const roomIdInput = document.getElementById('roomId'); // roomId
const roomNameInput = document.getElementById('roomName'); // roomName
const roomSizeInput = document.getElementById('roomSize'); // roomSize

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

// 新增房間
const addRoom = async() => {
	// 取得新增會議室表單的輸入內容
	const roomId = roomIdInput.value;
	const roomName = roomNameInput.value;
	const roomSize = roomSizeInput.value;
	
	// 檢查資料
	if(!roomId || !roomName || !roomSize) {
		resultMessage.textContent = '請輸入會議室 ID, 名稱與人數';
		return;
	}
	
	// 組合成一個 roomDto 物件
	const roomDto = {roomId, roomName, roomSize};
	console.log(roomDto);
	
	try {
		// 將 roomDto 傳送到後端
		const response = await fetch('http://localhost:8080/booking/rest/room', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(roomDto) // 要將 Dto 物件轉字串形式
		});
		
		const apiResponse = await response.json();
		console.log(apiResponse);
		resultMessage.textContent = apiResponse.message;
	} catch(e) {
		resultMessage.textContent = e;
	}
};

// 調用 fetchRooms
fetchRooms();