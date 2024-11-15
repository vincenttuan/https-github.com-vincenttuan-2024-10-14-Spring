import { useState } from 'react';
import './App.css'
import TodoInput from './components/TodoInput';
import TodoList from './components/TodoList';

/**
App5.jsx 切模組
src
├── components
│        ├── TodoInput.jsx
│        ├── TodoItem.jsx
│        └── TodoList.jsx
└── App5.jsx
*/

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

  const toggleCompletion = (id) => {
    setTodos(
      todos.map((todo) => todo.id == id ? {...todo, completed: !todo.completed} : todo)
    );
  };

  return (
    <> 
      <h1>My Todo List 4</h1>
      <TodoInput myText={myText} onTextChange={onTextChange} onAdd={onAdd} />
      <TodoList todos={todos} toggleCompletion={toggleCompletion} />
    </>
  )
}

export default App
