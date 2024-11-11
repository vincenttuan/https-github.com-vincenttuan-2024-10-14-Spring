import { useState } from 'react'

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

export default MessageList;