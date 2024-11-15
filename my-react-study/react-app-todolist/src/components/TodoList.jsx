import React from "react";
import TodoItem from "./TodoItem";

const TodoList = (todos, toggleCompletion) => {

    return (
        <ul>
        {
          todos.map((todo) => {
            <TodoItem todo={todo} toggleCompletion={toggleCompletion} />
          })
        }
      </ul>

    )
    
}

export default TodoList;