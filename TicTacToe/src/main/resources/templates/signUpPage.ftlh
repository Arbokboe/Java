<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Регистрация</title>
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

        .register-container {
            text-align: center;
            background-color: rgba(255, 255, 255, 0.9);
            padding: 60px;
            border-radius: 15px;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
            width: 350px; /* Немного шире для доп. поля */
            animation: fadeIn 1s ease-out;
        }

        h2 { /* Изменено с h1 на h2 */
            color: #4a148c;
            margin-bottom: 40px;
            font-size: 3em;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.1);
        }

        .form-group {
            margin-bottom: 25px; /* Увеличим отступ между полями */
            text-align: left; /* Выровняем текст слева внутри form-group */
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
            color: #333;
        }

        input[type="text"],
        input[type="password"] {
            width: calc(100% - 30px); /* Учитываем padding */
            padding: 15px;
            border: 1px solid #ddd;
            border-radius: 8px;
            box-sizing: border-box;
            font-size: 1em;
            margin-bottom: 10px; /* Добавим отступ снизу для лучшего вида */
        }

        button[type="button"] { /* Указываем тип button */
            padding: 20px 40px;
            font-size: 1.2em;
            background: linear-gradient(to right, #6a11cb, #2575fc);
            color: white;
            border: none;
            border-radius: 30px;
            cursor: pointer;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            width: 100%; /* Занимает всю ширину контейнера */
            margin-top: 20px; /* Отступ сверху от полей формы */
        }

        button[type="button"]:hover { /* Указываем тип button */
            transform: translateY(-5px);
            box-shadow: 0 5px 12px rgba(0, 0, 0, 0.3);
        }

        .message { /* Общий класс для сообщений */
            position: fixed; /* Фиксированное позиционирование относительно окна браузера */
            bottom: 20px;
            right: -400px; /* Начальное положение справа за пределами экрана */
            margin-top: 15px;
            padding: 15px 20px;
            border-radius: 8px; /* Увеличим радиус для соответствия */
            display: block; /* Делаем видимым, чтобы можно было анимировать */
            opacity: 0; /* Начальная прозрачность */
            transition: right 0.3s ease-out, opacity 0.3s ease-out; /* Анимация свойства right и opacity */
            text-align: center; /* Центрируем текст сообщения */
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* Добавим небольшую тень */
        }

        .message.show { /* Класс для показа сообщения с анимацией */
            right: 20px; /* Конечное положение справа снизу */
            opacity: 1;
        }

        .error-message {
            color: #dc3545; /* Красный цвет для ошибок */
            background-color: #f8d7da;
            border: 1px solid #f5c6cb;
        }

        .success-message {
            color: #28a745; /* Зеленый цвет для успеха */
            background-color: #d4edda;
            border: 1px solid #c3e6cb;
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
<div class="register-container">
    <h2>Регистрация нового пользователя</h2>
    <form id="registerForm">
        <div class="form-group">
            <label for="login">Имя пользователя (логин):</label>
            <input type="text" id="login" name="login" required>
        </div>
        <div class="form-group">
            <label for="password">Пароль:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div class="form-group">
            <label for="confirmPassword">Повторите пароль:</label>
            <input type="password" id="confirmPassword" name="confirmPassword" required>
        </div>
        <button type="button" onclick="sendLoginRequest()">Зарегистрироваться</button>
    </form>
    <div id="message" class="message"></div>
</div>

<script>
    function sendLoginRequest() {
        const loginInput = document.getElementById("login");
        const passwordInput = document.getElementById("password");
        const confirmPasswordInput = document.getElementById("confirmPassword");
        const messageDiv = document.getElementById("message");

        const login = loginInput.value;
        const password = passwordInput.value;
        const confirmPassword = confirmPasswordInput.value;

        if (!password || !confirmPassword || !login) {
            messageDiv.textContent = 'Все поля должны быть заполнены!';
            messageDiv.className = 'message error-message';
            messageDiv.classList.add('show'); // Добавляем класс для показа сообщения
            setTimeout(() => {
                messageDiv.classList.remove('show'); // Удаляем класс через 5 секунд
            }, 3000);
        } else if (password !== confirmPassword) {
            messageDiv.textContent = 'Пароли должны совпадать';
            messageDiv.className = 'message error-message';
            messageDiv.classList.add('show'); // Добавляем класс для показа сообщения
            setTimeout(() => {
                messageDiv.classList.remove('show'); // Удаляем класс через 5 секунд
            }, 3000);
        } else {

            const dataToSend = {
                login: login,
                password: password
            }
            fetch('/signUp', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(dataToSend)
            })
                .then(response => {
                    if (response.status === 409) {
                        return response.json().then(errorData => {
                            messageDiv.textContent = errorData.message;
                            messageDiv.className = 'message error-message';
                            messageDiv.classList.add('show');
                            setTimeout(() => {
                                messageDiv.classList.remove('show');
                            }, 3000)
                        });
                    } else if (response.status === 400) {
                        return response.json().then(errorData => {
                            console.error('Ошибка 400:', errorData);
                            messageDiv.textContent = errorData.message;
                            messageDiv.className = 'message error-message';
                            messageDiv.classList.add('show');
                            setTimeout(() => {
                                messageDiv.classList.remove('show');
                            }, 3000)
                        });
                    } else if (response.status === 200) {
                        window.location.href = '/signInPage';
                    }
                })
        }
    }
</script>
</body>
</html>