import { useState } from "react";

 const ScoreList = () => {
    // 成績陣列
    const scores = [
        {id: 1, name: 'Alice', score: 100},
        {id: 2, name: 'Bob', score: 55},
        {id: 3, name: 'John', score: 80},
        {id: 4, name: 'Mary', score: 40},
        {id: 5, name: 'cha', score: 70}
    ];

    return(
        <>
            <h1>Score List</h1>
            <ul>
                {
                    scores.map((score) => {
                        return <li key={score.id}>姓名: {score.name} 分數: {score.score}</li>
                    })
                }
            </ul>
            <h1>Score Filter</h1>
            <button>顯示及格分數</button>
            <button>顯示不及格分數</button>
            <ul>
                
            </ul>
        </>
    )

 };

export default ScoreList;
