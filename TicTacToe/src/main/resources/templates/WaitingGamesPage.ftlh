<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Доступные Игры | Онлайн</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            display: flex; /* Включаем Flexbox */
            justify-content: center; /* Центрируем по главной оси (теперь это колонка) */
            align-items: center; /* Центрируем по поперечной оси (горизонталь) */
            flex-direction: column; /* Устанавливаем направление как колонка */
            height: 100vh;
            margin: 0;
            background: linear-gradient(135deg, #667eea, #764ba2);
            overflow: hidden;
            padding: 20px; /* Добавим небольшой padding на случай маленьких экранов */
            box-sizing: border-box; /* Учитываем padding в размере элемента */
        }

        .div-container {
            text-align: center;
            background-color: rgba(255, 255, 255, 0.95);
            padding: 40px 60px;
            border-radius: 15px;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.3);
            min-width: 300px;
            max-width: 800px; /* Ограничим максимальную ширину */
            width: 95%; /* И сделаем адаптивным */
            max-height: calc(100vh - 100px); /* Ограничиваем высоту, оставляя место для кнопки */
            overflow-y: auto;
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-bottom: 20px; /* Добавляем отступ снизу, чтобы отделить от кнопки */
        }

        h1 {
            color: #4a148c;
            margin-top: 0; /* Убираем верхний margin, т.к. padding у контейнера */
            margin-bottom: 30px;
            font-size: 2.5em;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.1);
        }

        ul {
            list-style: none;
            padding: 0;
            margin: 0 0 20px 0; /* Убираем верхний margin, добавляем нижний */
            text-align: left;
            width: 100%;
        }

        li {
            background-color: rgba(255, 255, 255, 0.7);
            margin-bottom: 10px;
            padding: 15px;
            border-radius: 8px;
            border: 1px solid rgba(74, 20, 140, 0.1);
            color: #4a148c;
            word-wrap: break-word;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            display: flex;
            flex-direction: column;
            align-items: flex-start;
        }

        /* Стили для красивых кнопок (уже были в вашем коде) */
        .styled-button {
            display: inline-block;
            padding: 12px 25px;
            font-size: 1.1em;
            background: linear-gradient(to right, #6a11cb, #2575fc);
            color: white;
            border: none;
            border-radius: 25px;
            cursor: pointer;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            text-decoration: none;
            text-align: center;
            font-family: inherit;
            margin: 0;
        }

        .styled-button:hover {
            transform: translateY(-3px);
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.35);
        }

        .styled-button:active {
            transform: translateY(0);
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
        }

        .game-details {
            margin-bottom: 10px;
            width: 100%;
            text-align: left;
        }

        .join-button-form {
            margin-top: 10px;
            width: 100%;
            text-align: center;
        }

        .join-button-form button {
            font-size: 1em;
            padding: 8px 20px;
            border-radius: 20px;
            background: linear-gradient(to right, #6a11cb, #2575fc);
            color: white;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
        }

        .join-button-form button:hover {
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
        }

        /* Стили для отдельного контейнера кнопки "На главную" */
        .main-button-container {
            margin-top: 20px; /* Отступ сверху */
            text-align: center; /* Центрирование содержимого */
            width: 100%; /* Занимает всю ширину для центрирования */
        }

        /* Форма внутри контейнера кнопки "На главную" */
        .main-button-container form {
            display: inline-block; /* Делаем форму строчно-блочной, чтобы центрировать */
        }

    </style>
</head>
<body>

<div class="div-container">
    <h1>Активные Онлайн Игры</h1>

    <ul id="waiting-games-list">

        <#if allWaitingGames?size == 0>
            <li style="font-style: italic; text-align: center; width: 100%;">Нет активных игр для присоединения.</li>
        <#else>
            <#list allWaitingGames as game>
                <li>
                    <div class="game-details">
                        ID Игры: ${game.uuid!""} <br>
                        Статус: ${game.state!""}
                    </div>

                    <form action="/joinGame" method="post" class="join-button-form">
                        <input type="hidden" name="gameId" value="${game.uuid}"/>
                        <button type="submit" class="styled-button">Присоединиться</button>
                    </form>
                </li>
            </#list>
        </#if>

    </ul>
</div>


<div class="main-button-container">
    <form action="/" method="get">
        <button type="submit" class="styled-button">На главную</button>
    </form>
</div>

</body>
</html>