<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Информация о пользователе</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background: linear-gradient(135deg, #667eea, #764ba2);
            overflow: hidden;
        }

        .div-container {
            text-align: center;
            background-color: rgba(255, 255, 255, 0.95);
            padding: 40px 60px;
            border-radius: 15px;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.3);
            min-width: 300px;
            display: flex; /* Используем Flexbox для основного контейнера */
            flex-direction: column; /* Располагаем элементы по вертикали */
            align-items: center; /* Центрируем элементы по горизонтали */
        }

        h1 {
            color: #4a148c;
            margin-bottom: 30px;
            font-size: 2.5em;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.1);
        }

        /* --- Стили для блока с деталями пользователя (Grid) --- */
        .user-details {
            display: grid; /* Используем Grid для выравнивания в две колонки */
            grid-template-columns: auto 1fr; /* 1я колонка по ширине контента (метки), 2я занимает остальное пространство (значения) */
            gap: 10px 20px; /* Отступы между строками и колонками */
            text-align: left; /* Выравниваем текст по левому краю внутри ячеек */
            margin-bottom: 30px; /* Отступ снизу от блока деталей */
            width: 100%; /* Занимаем всю доступную ширину в .div-container */
        }

        .user-details > div {
            padding: 5px 0; /* Небольшой внутренний отступ сверху/снизу */
            color: #333; /* Цвет текста для меток */
            font-size: 1.2em; /* Размер шрифта */
        }

        /* Стили для значений (находятся во 2-й колонке, в <td>) */
        .user-details strong {
            color: #6a11cb; /* Цвет для значений */
            font-weight: bold;
            /* margin-left: 5px; /* Убираем отступ, Grid сам управляет пространством */
        }

        /* Добавляем разделительную линию между строками */
        .user-details > div {
            border-bottom: 1px solid #eee;
            padding-bottom: 10px; /* Отступ под линией */
            margin-bottom: 0; /* Убираем стандартный margin-bottom */
        }

        /* Убираем нижнюю линию у последней строки */
        .user-details > div:nth-last-child(-n+2) { /* Выбираем последние два div (последняя строка) */
            border-bottom: none;
            padding-bottom: 5px; /* Возможно, нужен чуть меньший отступ без линии */
        }

        /* Опционально: корректируем верхний отступ у первой строки */
        .user-details > div:nth-child(-n+2) {
            padding-top: 0;
        }

        /* --- Конец стилей для блока с деталями пользователя --- */


        /* Эти стили для форм присутствуют в вашем блоке CSS,
           но не будут применены, так как на этой странице нет соответствующих элементов. */
        .div-container form {
            margin-bottom: 20px;
        }

        .div-container form:last-child {
            margin-bottom: 0;
        }


        /* Стили для ссылки "Назад", имитирующие кнопку с MainPage */
        .button-like-link {
            display: inline-block; /* Позволяет применять padding и margin */
            width: auto; /* Ширина по содержимому + padding */
            padding: 15px 20px;
            font-size: 1.1em;
            background: linear-gradient(to right, #6a11cb, #2575fc); /* Градиент как на кнопке MainPage */
            color: white;
            border: none;
            border-radius: 25px; /* Скругленные углы */
            cursor: pointer; /* Показывает, что это кликабельный элемент */
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            transition: transform 0.3s ease, box-shadow 0.3s ease, background 0.3s ease; /* Плавные переходы */
            text-decoration: none; /* Убираем подчеркивание у ссылки */
            text-align: center; /* Центрируем текст внутри ссылки */
            margin-top: 10px; /* Отступ сверху от блока деталей */
        }

        .button-like-link:hover {
            transform: translateY(-3px);
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.35);
        }

        .button-like-link:active {
            transform: translateY(0);
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
        }

        /* Сброс базовых стилей для <a>, если есть */
        a {
            /* */
        }

    </style>
</head>
<body>
<div class="div-container">
    <h1>Информация о пользователе</h1>
    <div class="user-details">
        <div>User ID:</div>
        <div><strong>${userId}</strong></div>
        <div>Login:</div>
        <div><strong>${login}</strong></div>
    </div>

    <a href="/" class="button-like-link">Назад</a>
</div>
</body>
</html>