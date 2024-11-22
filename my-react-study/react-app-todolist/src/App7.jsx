import { useEffect, useState } from 'react';
import './App.css'
import 'bootstrap/dist/css/bootstrap.min.css'
import TodoInput from './components/TodoInput';
import TodoList from './components/TodoList';
import { fetchTodos, addTodo, updateTodo, deleteTodo } from './services/todoService'

function App() {

  const [todos, setTodos] = useState([]);
  //const [todo, setTodo] = useState([]);
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

  // 新增代辦事項
  const onAdd = async (e) => {
    const text = myText;
    const completed = false;

    const todo = {
      text:text, 
      completed:completed
    };

    try {
      const addedTodo = await addTodo(todo);
      setTodos([...todos, addedTodo]);
      setMyText('');
    } catch(e) {
      console.error('error:', e)
    }

  };

  // 更新代辦事項
  const toggleCompletion = async (id) => {
    try {
      // 1. 根據 id 找到要修改的 todo
      const updatedTodo = todos.find(todo => todo.id === id);
      if(!updatedTodo) return; // 查無資料
      // 2. 變更 completed 狀態
      updatedTodo.completed = !updatedTodo.completed;
      // 3. 調用 updateTodo 方法進行更新
      await updateTodo(updatedTodo);
      // 4. 重新渲染 todos
      setTodos([...todos]);
    } catch(e) {
      console.error('error:', e)
    }

  };

  // 刪除代辦事項
  const handleDeleteTodo = (id) => {
    deleteTodo(id)
      .then((ok) => setTodos(todos.filter(todo => todo.id !== id)))
      .catch(e => console.error('error:', e));
  }

  return (
    <div className='container mt-5'>
      {console.log('diaplay ui')}
      <h1 className='text-center'>My Todo List 7</h1>
      <TodoInput myText={myText} onTextChange={onTextChange} onAdd={onAdd} />
      <TodoList todos={todos} toggleCompletion={toggleCompletion} handleDeleteTodo={handleDeleteTodo} />
      <div className='mt-4'>
        總數: {totalTodos} 已完成: {completedTodos} 未完成: {incompleteTodos}
      </div>
    </div> 
  )
}

export default App
