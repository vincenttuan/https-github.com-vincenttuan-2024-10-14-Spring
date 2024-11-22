import { useEffect, useState } from 'react';
import './App.css'
import 'bootstrap/dist/css/bootstrap.min.css'
import TodoInput from './components/TodoInput';
import TodoList from './components/TodoList';
import { fetchTodos, addTodo, updateTodo, deleteTodo } from './services/todoService'

function App() {

  const [todos, setTodos] = useState([]);
  const [todo, setTodo] = useState([]);
  const [myText, setMyText] = useState('');

  useEffect(() => {
    console.log('取得遠端 todo list 資料');
    // 獲取代辦事項
    //fetchTodos().then(data => setTodos(data))
    fetchTodos().then(setTodos).catch((e) => console.error('error:', e));
  }, []);


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
      <h1 className='text-center'>My Todo List 7</h1>
      <TodoInput myText={myText} onTextChange={onTextChange} onAdd={onAdd} />
      <TodoList todos={todos} toggleCompletion={toggleCompletion} deleteTodo={deleteTodo} />
      <div className='mt-4'>
        總數: {totalTodos} 已完成: {completedTodos} 未完成: {incompleteTodos}
      </div>
    </div> 
  )
}

export default App
