import React from "react";

const TodoInput = ({myText, onTextChange, onAdd}) => {
    return (
        <div className="input-group mb-3">
            <input className="form-control" type="text" value={myText} onChange={onTextChange} />
            <button className="btn btn-primary" onClick={onAdd}>Add</button>
        </div>
    )
}

export default TodoInput;