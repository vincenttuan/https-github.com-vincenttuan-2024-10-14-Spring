import { useState } from "react";

 const ScoreList = () => {
    // 紀錄及格與不及格的狀態
    const [passing, setPassing] = useState(true);

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
            <button onClick={() => setPassing(true)}>顯示及格分數</button>
            <button onClick={() => setPassing(false)}>顯示不及格分數</button>
            <ul>
                {
                    scores.filter((score) => (passing ? score.score >= 60 : score.score < 60))
                        .map((score) => {
                        return <li key={score.id}>姓名: {score.name} 分數: {score.score}</li>
                    })
                }    
            </ul>
        </>
    )

 };

export default ScoreList;
