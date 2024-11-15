import React from "react";

const TodoItem = ({todo, toggleCompletion}) => {

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
         </li>
    )

}

export default TodoItem;