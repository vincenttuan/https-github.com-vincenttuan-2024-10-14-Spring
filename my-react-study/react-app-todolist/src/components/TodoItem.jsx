import React from "react";

const TodoItem = ({todo, toggleCompletion}) => {

    return (
        <li key={todo.id}>
            {todo.id} -
            {todo.text}
            <input type="checkbox" 
                    checked={todo.completed} 
                    onChange={() => toggleCompletion(todo.id)} />
         </li>
    )

}

export default TodoItem;