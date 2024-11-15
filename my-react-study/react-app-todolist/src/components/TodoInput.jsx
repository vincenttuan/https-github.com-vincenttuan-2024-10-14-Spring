import React from "react";

const TodoInput = ({myText, onTextChange, onAdd}) => {
    return (
        <div>
            <input type="text" value={myText} onChange={onTextChange} />
            <button onClick={onAdd}>Add</button>
        </div>
    )
}

export default TodoInput;