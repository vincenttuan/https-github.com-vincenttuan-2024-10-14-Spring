import { useState } from 'react'
import MessageForm from './components/MessageForm'
import MessageList from './components/MessageList'

import './App.css'

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
