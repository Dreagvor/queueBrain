# Security

## Аутентификация

Используется сессионный механизм. За управление сессией отвечает пакет `com.zoxal.queuebrain.security`. За реализацию логики отвечает пакет `com.zoxa.queuebrain.api`.



`com.zoxal.queuebrain.security`:

* Предоставляет api для аутентификации и регистрации пользователя
* Реализует логику создания объекта Authentication для каждого входящего запроса

`com.zoxal.queuebrain.api`:

* Использует созданный объект Authentication для авторизации пользователя в api


### Auth endpoints

| Endpoint                           | Description                              |
| ---------------------------------- | ---------------------------------------- |
| /security/login?type=loginpass     | Вход в приложение с предоставлением логина и пароля |
| /security/registration?type=github | Регистрация в приложении через github    |

