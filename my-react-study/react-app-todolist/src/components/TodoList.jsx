import React from "react";
import TodoItem from "./TodoItem";

const TodoList = ({todos, toggleCompletion, handleDeleteTodo}) => {

    return (
        <ul className="list-group">
        {
          todos.map((todo) => {
            return <TodoItem key={todo.id} // 要加入 key 屬性
                             todo={todo} 
                            toggleCompletion={toggleCompletion}
                            handleDeleteTodo={handleDeleteTodo} />
          })
        }
      </ul>

    )
    
}

export default TodoList;