import './App.css'

function App() {

  const todos = [
    {id:1, text:'吃早餐', completed:true}, 
    {id:2, text:'做運動', completed:false}, 
    {id:3, text:'寫程式', completed:false}, 
    {id:4, text:'Debug', completed:true}, 
  ];

  return (
    <> 
      <h1>My Todo List 3</h1>
      <div>
        <input type="text" />
        <button>Add</button>
      </div>
      <ul>
        {
          todos.map((todo) => {
            return (
              <li key={todo.id}>
                {todo.text}
                <input type="checkbox" checked={todo.completed} />
              </li>
              )
          })
        }
      </ul>
    </>
  )
}

export default App
