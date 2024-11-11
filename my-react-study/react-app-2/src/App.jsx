import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

// Message form 元件, 用來輸入訊息
const MessageForm = () => {
  return (
    <form>
      <label htmlFor='message'>Message</label>
      <input type="text" />
      <button type="submit">Submit</button>
    </form>
  );
};

// Message list 元件, 用來顯示歷史訊息
const MessageList = () => {

};


function App() {
  
  return (
    <>
      <MessageForm />
    </>
  )
}

export default App
