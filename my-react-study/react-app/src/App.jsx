import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import MyHelloComp5 from './components/MyHelloComp5';


function MyHelloComp() {  // 組件:舊版的寫法
  return <><h1>Hello React2</h1></>
}

const MyHelloComp2 = () => { // 組件:箭頭函式
  return <><h1>Hello React3</h1></>
};

const MyHelloComp3 = () => { // 組件:箭頭函式
  const text = 'react'
  return <h1>{text} {text.toUpperCase()}</h1>
};

const MyHelloComp4 = (props) => {
  const {x, y} = props;
  return <h1>x = {x} y = {y}</h1>
};

function App() {

  return (
    <>
      <h1>Hello React1</h1>
      <MyHelloComp />
      <MyHelloComp2 />
      <MyHelloComp3 />
      <MyHelloComp4 x="1" y="2" />
      <MyHelloComp5 />
    </>
  )
}

export default App
