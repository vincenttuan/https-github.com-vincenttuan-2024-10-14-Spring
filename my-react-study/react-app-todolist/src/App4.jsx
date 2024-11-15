import { useState } from 'react';
import './App.css'

function App() {

  const [todos, setTodos] = useState([
    {id:1, text:'吃早餐', completed:true}, 
    {id:2, text:'做運動', completed:false}, 
    {id:3, text:'寫程式', completed:false}, 
    {id:4, text:'Debug', completed:true}, 
  ]);

  const [myText, setMyText] = useState('');

  const onTextChange = (e) => {
    setMyText(e.target.value);
  };

  const onAdd = (e) => {
    const id = todos.length > 0 ? Math.max(...todos.map((t) => t.id)) + 1 : 1;
    const text = myText;
    const completed = false;

    const todo = {
      id:id, 
      text:text, 
      completed:completed
    };

    setTodos([...todos, todo]);

    console.log(todo);

  };

  return (
    <> 
      <h1>My Todo List 4</h1>
      <div>
        <input type="text" value={myText} onChange={onTextChange} />
        <button onClick={onAdd}>Add</button>
      </div>
      <ul>
        {
          todos.map((todo) => {
            return (
              <li key={todo.id}>
                {todo.id} -
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
