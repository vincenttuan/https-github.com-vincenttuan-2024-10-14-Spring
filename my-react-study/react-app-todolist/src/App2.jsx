import './App.css'

function App() {

  const todos = [
    '吃早餐', '做運動', '寫程式', 'Debug'
  ];

  return (
    <> 
      <h1>My Todo List 2</h1>
      <div>
        <input type="text" />
        <button>Add</button>
      </div>
      <ul>
        {
          todos.map((todo, index) => {
            return (<li key={index}>{todo}</li>)
          })
        }
      </ul>
    </>
  )
}

export default App
