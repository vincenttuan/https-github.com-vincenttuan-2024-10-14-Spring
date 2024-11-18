import { useEffect, useState } from 'react';
import './App.css'
import 'bootstrap/dist/css/bootstrap.min.css'
import TodoInput from './components/TodoInput';
import TodoList from './components/TodoList';

/**
App6.jsx 透過 useEffect 連接後端 
語法:
useEffect(() => {要做的事});     // 每次畫面有渲染時都會執行
useEffect(() => {要做的事}, []); // 畫面第一次渲染時才會執行
useEffect(() => {要做的事}, [data1]); // 當 data1 資料改變時才會執行
useEffect(() => {要做的事}, [data1, data2, data3]); // 當 data1, data2, data3 任一資料改變時就會執行
*/

function App() {

  useEffect(() => {
    console.log('run useEffect');
  });


  const [todos, setTodos] = useState([
    {id:1, text:'吃早餐', completed:true}, 
    {id:2, text:'做運動', completed:false}, 
    {id:3, text:'寫程式', completed:false}, 
    {id:4, text:'Debug', completed:true}, 
  ]);

  const [myText, setMyText] = useState('');

  // 計算統計數據
  const totalTodos = todos.length;
  const completedTodos = todos.filter((todo) => todo.completed).length;
  const incompleteTodos = totalTodos - completedTodos;

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

  const deleteTodo = (id) => {
    setTodos(todos.filter(todo => todo.id !== id))
  }

  return (
    <div className='container mt-5'>
      {console.log('diaplay ui')}
      <h1 className='text-center'>My Todo List 5</h1>
      <TodoInput myText={myText} onTextChange={onTextChange} onAdd={onAdd} />
      <TodoList todos={todos} toggleCompletion={toggleCompletion} deleteTodo={deleteTodo} />
      <div className='mt-4'>
        總數: {totalTodos} 已完成: {completedTodos} 未完成: {incompleteTodos}
      </div>
    </div> 
  )
}

export default App
