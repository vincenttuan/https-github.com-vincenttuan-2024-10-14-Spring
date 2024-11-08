// 選取 HTML DOM 元素
const roomList = document.getElementById('roomList'); // 房間列表
const resultMessage = document.getElementById('resultMessage'); // 回應訊息
// 新增房間 HTML DOM 元素
const roomIdInput = document.getElementById('roomId'); // roomId
const roomNameInput = document.getElementById('roomName'); // roomName
const roomSizeInput = document.getElementById('roomSize'); // roomSize

// 修改房間 HTML DOM 元素
const editRoomIdInput = document.getElementById('editRoomId');
const editRoomNameInput = document.getElementById('editRoomName');
const editRoomSizeInput = document.getElementById('editRoomSize');
const editModal = document.getElementById('editModal');

// 取得所有房間的資料並顯示在列表中 
const fetchRooms = async(cleanup) => {
	if(cleanup) {
		roomList.innerHTML = '';
	}
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
		deleteButton.onclick = () => deleteRoom(room.roomId);
		listItem.appendChild(deleteButton); // 在 listItem 後增加一個 button
		
		// 建立修改按鈕
		const updateButton = document.createElement('button');
		updateButton.textContent = '修改';
		updateButton.onclick = () => openModal(room);
		listItem.appendChild(updateButton); // 在 listItem 後增加一個 button
		
		// 新增子元素
		roomList.appendChild(listItem);
	});
};

// 開啟修改房間視窗
const openModal = (room) => {
	console.log('開啟修改房間視窗, 修改前:', room);
	editRoomIdInput.value = room.roomId;
	editRoomNameInput.value = room.roomName;
	editRoomSizeInput.value = room.roomSize;
	editModal.style.display = 'flex';
};

// 關閉修改房間視窗
const closeModal = () => {
	editModal.style.display = 'none';
};

// 執行修改房間程序
const editRoom = async() => {
	const uptData = {
		roomName: editRoomNameInput.value,
		roomSize: editRoomSizeInput.value
	};
	
	try {
		const roomId = editRoomIdInput.value;
		const response = await fetch(`http://localhost:8080/booking/rest/room/${roomId}`, {
			method: 'PUT',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(uptData)
		});
		const apiResponse = await response.json();
		if(apiResponse.status == 200) {
			fetchRooms(true); // 刪除成功, 立即重新查詢房間列表
		} 
		resultMessage.textContent = apiResponse.message;
		
	} catch(e) {
		resultMessage.textContent = e;
	} finally {
		closeModal();
	}
	
	
};



// 刪除房間
const deleteRoom = async (roomId) => {
	console.log('delete roomId:', roomId);
	try {
		//const response = await fetch('http://localhost:8080/booking/rest/room/' + roomId, {method: 'DELETE'});
		const response = await fetch(`http://localhost:8080/booking/rest/room/${roomId}`, {method: 'DELETE'});
		const apiResponse = await response.json();
		console.log('apiResponse:', apiResponse);
		
		if(apiResponse.status == 200) {
			fetchRooms(true); // 刪除成功, 立即重新查詢房間列表
		}
		resultMessage.textContent = apiResponse.message;
		
	} catch(e) {
		resultMessage.textContent = e;
	}
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
		// 判斷 status 是否是 200
		if(apiResponse.status == 200) {
			// 重新查詢 rooms
			fetchRooms(true); // true 畫面要清空
			// 新增表單畫面清空
			roomIdInput.value = '';
			roomNameInput.value = '';
			roomSizeInput.value = '';
		}
		
	} catch(e) {
		resultMessage.textContent = e;
	}
};

// 調用 fetchRooms
fetchRooms();