import { useState } from 'react'
import MessageForm from './components/MessageForm'
import MessageList from './components/MessageList'

import './App.css'

function App() {
  const [messages, setMessages] = useState([]);

  const addMessage = (newMessage) => {
    setMessages([...messages, newMessage]);
  };

  const deleteMessage = (indexToDelete) => {
    console.log(indexToDelete);
    setMessages(messages.filter((_, index) => index !== indexToDelete));
    /*
    setMessages(messages.filter((msg, index) => {
      console.log('刪除:', msg);
      return index !== indexToDelete
    }));
    */
  };

  return (
    <>
      <MessageForm onAddMessage={addMessage} />
      <MessageList messages={messages} onDeleteMessage={deleteMessage} />
    </>
  )
}

export default App
