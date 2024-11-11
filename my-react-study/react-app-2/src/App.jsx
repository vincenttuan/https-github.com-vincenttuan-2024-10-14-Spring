import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

// Message form 元件, 用來輸入訊息
const MessageForm = () => {
  const [message, setMessage] = useState('');
  
  const handleSubmit = (e) => {
    e.preventDefault(); // 移除預設的行為
    console.log(message);
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
const MessageList = () => {
  return (
    <div>
      <h2>Message List</h2>
      <ul>
        <li>Hello John</li>
        <li>I'm fine</li>
        <li>Welcome jo~</li>
      </ul>
    </div>
  );
};


function App() {
  
  return (
    <>
      <MessageForm />
      <MessageList />
    </>
  )
}

export default App
