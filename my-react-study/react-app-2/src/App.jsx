import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

// Message form 元件, 用來輸入訊息
const MessageForm = ({onAddMessage}) => {
  const [message, setMessage] = useState('');
  
  const handleSubmit = (e) => {
    e.preventDefault(); // 移除預設的行為
    console.log(message);
    onAddMessage(message); // 將訊息傳給父元件<App>的 onMessage 函式
    setMessage(''); // 表單清空
  };

  return (
    <form onSubmit={handleSubmit}>
      <label htmlFor='message'>Message</label>
      <input type="text" id='message' value={message} onChange={(e) => setMessage(e.target.value)} />
      <button type="submit">Submit</button>
    </form>
  );
};

// Message list 元件, 用來顯示歷史訊息
const MessageList = ({messages}) => {
  return (
    <div>
      <h2>Message List</h2>
      <ul>
        <li>{messages}</li>
      </ul>
      <hr />
      <ul>
        {
          messages.map((msg, index) => (
            <li key={index}>{index} - {msg}</li>
          ))
        }
      </ul>
    </div>
  );
};


function App() {
  const [messages, setMessages] = useState([]);

  const addMessage = (newMessage) => {
    setMessages([...messages, newMessage]);
  };

  return (
    <>
      <MessageForm onAddMessage={addMessage} />
      <MessageList messages={messages} />
    </>
  )
}

export default App
