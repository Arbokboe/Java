<!DOCTYPE html>
<html>
<head>
    <title>Крестики-нолики</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
            background: linear-gradient(135deg, #667eea, #764ba2);
            overflow: auto;
        }

        .board {
            display: grid;
            grid-template-columns: repeat(3, 100px);
            grid-gap: 10px;
            background-color: rgba(255, 255, 255, 0.9);
            padding: 20px;
            border-radius: 15px;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
            margin-bottom: 30px; /* Увеличим отступ */
        }

        .cell {
            width: 100px;
            height: 100px;
            border: none;
            display: flex;
            justify-content: center;
            align-items: center;
            background-color: rgba(240, 240, 240, 0.8);
            border-radius: 10px;
            transition: background-color 0.3s ease, transform 0.3s ease;
        }

        .cell:hover {
            background-color: rgba(220, 220, 220, 0.8);
            transform: scale(1.05);
        }

        .cell-button {
            width: 80px;
            height: 80px;
            font-size: 2.5em;
            border: none;
            background-color: #fff;
            border-radius: 50%;
            outline: none;
            cursor: pointer;
            transition: color 0.3s ease, background-color 0.3s ease;
        }

        .cell-button:hover {
            color: #007bff;
            background-color: #e0e0e0;
        }

        .back-button-container {
            position: absolute;
            bottom: 35px;
            left: 60px;
        }

        .back-button {
            padding: 20px 40px;
            font-size: 1.2em;
            background: linear-gradient(to right, #6a11cb, #2575fc);
            color: white;
            border: none;
            border-radius: 30px;
            cursor: pointer;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            outline: none;
            /*float: left;*/
            /*margin-left: 20px;*/
        }

        .back-button:hover {
            transform: translateY(-5px);
            box-shadow: 0 5px 12px rgba(0, 0, 0, 0.3);
        }

        .game-over-message {
            font-size: 1.5em;
            font-weight: bold;
            color: #4a148c;
            margin-bottom: 20px;
            text-align: center;
        }

        .new-game-button {
            padding: 20px 40px;
            font-size: 1.2em;
            background: linear-gradient(to right, #6a11cb, #2575fc);
            color: white;
            border: none;
            border-radius: 30px;
            cursor: pointer;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            outline: none;
        }

        .new-game-button:hover {
            transform: translateY(-5px);
            box-shadow: 0 5px 12px rgba(0, 0, 0, 0.3);
        }

        .new-game-button-container {
            position: absolute;
            bottom: 100px;
            left: 50%; /* Сдвигаем левый край контейнера на 50% ширины родителя */
            transform: translateX(-50%); /* Сдвигаем контейнер обратно на половину его собственной ширины */
        }


        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: translateY(20px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }
    </style>
</head>
<body>
<#if Win?? && Win>
    <div class="game-over-message">Вы победили!</div>
</#if>

<#if GameOver?? && GameOver>
    <div class="game-over-message">Компьютер победил!</div>
</#if>

<#if Draw?? && Draw>
    <div class="game-over-message">Ничья!</div>
</#if>

<form action="/gameBot/${gameId}" method="post">
    <div class="board" id="board">
        <#list field as row>
            <#list row as cell>
                <div class="cell">
                    <button class="cell-button" type="submit" value="${(row?index * 3) + cell?index}" name="index"
                            <#if Win?? || GameOver?? || Draw?? || cell != -1>disabled</#if>>
                        <#if cell == 1>
                            <img src="/images/X.png" alt="X" style="width: 60px;">
                        <#elseif cell == 0>
                            <img src="/images/O.png" alt="O" style="width: 50px">
                        </#if>
                    </button>
                </div>
            </#list>
        </#list>
    </div>
</form>

<div class="back-button-container">
    <a href="/">
        <button class="back-button">Назад</button>
    </a>
</div>

<div class="new-game-button-container">
    <form action="/NewGameWithBot" method="post"> <button class="new-game-button" type="submit">Новая игра</button>
    </form>
</div>


</body>
</html>