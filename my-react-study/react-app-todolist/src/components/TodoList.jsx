import React from "react";
import TodoItem from "./TodoItem";

const TodoList = ({todos, toggleCompletion}) => {

    return (
        <ul className="list-group">
        {
          todos.map((todo) => {
            return <TodoItem todo={todo} toggleCompletion={toggleCompletion} />
          })
        }
      </ul>

    )
    
}

export default TodoList;