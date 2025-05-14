<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Крестики-нолики</title>
    <style>
        body {
            font-family: 'Arial', sans-serif; /* Оставляем нормальный шрифт */
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background: linear-gradient(135deg, #667eea, #764ba2);
            overflow: hidden; /* Чтобы градиент занимал всю страницу без прокрутки */
        }

        .div-container {
            text-align: center;
            background-color: rgba(255, 255, 255, 0.95);
            padding: 40px 60px;
            border-radius: 15px;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.3);
            /* Убираем анимацию fadeIn */
            min-width: 300px;
        }

        h1 {
            color: #4a148c;
            margin-bottom: 30px;
            font-size: 2.5em;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.1);
        }

        .div-container form {
            margin-bottom: 20px;
        }

        .div-container form:last-child {
            margin-bottom: 0;
        }

        button {
            display: block;
            width: 100%;
            padding: 15px 20px;
            font-size: 1.1em;
            background: linear-gradient(to right, #6a11cb, #2575fc); /* Оставляем основной градиент */
            color: white;
            border: none;
            border-radius: 25px;
            cursor: pointer;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            /* Убираем переход для background */
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            /* Убираем стилизацию текста */
            text-transform: none; /* Возвращаем стандартный регистр */
            letter-spacing: normal; /* Возвращаем стандартный интервал */
            font-family: inherit; /* Наследуем шрифт от body (Arial) */
        }

        button:hover {
            /* Убираем изменение градиента при наведении - остается основной градиент */
            transform: translateY(-3px);
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.35);
        }

        button:active {
            transform: translateY(0);
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
        }

        /* Убираем правило @keyframes fadeIn, так как оно больше не используется */

    </style>
</head>
<body>
<div class="div-container">
    <h1>Добро пожаловать, <br>${userLogin}!</h1>
    <form action="/profile" method="get">
        <button type="submit">Мой профиль</button>
    </form>

    <form action="/NewGameWithBot" method="post">
        <button type="submit">Тренировка с компьютером</button>
    </form>
    <form action="/NewGameWithHuman" method="post">
        <button type="submit">Создать онлайн игру</button>
    </form>
    <form action="/waitingGames" method="get">
        <button type="submit">Присоединиться к игре</button>
    </form>
    <form action="/logout" method="post">
        <button type="submit">Выход</button>
    </form>
</div>
</body>
</html>