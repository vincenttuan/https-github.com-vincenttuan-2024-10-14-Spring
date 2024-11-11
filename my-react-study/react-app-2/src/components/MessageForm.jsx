import { useState } from 'react'

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

  export default MessageForm;