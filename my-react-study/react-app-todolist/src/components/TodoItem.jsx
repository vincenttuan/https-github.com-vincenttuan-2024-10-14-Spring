import React from "react";

const TodoItem = ({todo, toggleCompletion, deleteTodo}) => {

    return (
        <li className="list-group-item d-flex justify-content-between align-items-center">
            <div style={{
                textDecoration: todo.completed ? 'line-through' : 'none',
            }}>
                {todo.id} -
                {todo.text}
            </div>
            <input type="checkbox" 
                    checked={todo.completed} 
                    onChange={() => toggleCompletion(todo.id)} />
            <button type="button" className="btn btn-secondary" onClick={() => deleteTodo(todo.id)}>刪除</button>        
         </li>
    )

}

export default TodoItem;