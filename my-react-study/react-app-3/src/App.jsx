import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import ScoreList from './components/ScoreList'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <ScoreList />
    </>
  )
}

export default App
