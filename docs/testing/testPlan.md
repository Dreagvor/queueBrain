# Тест-план

## Введение

Тестирование является неотъемлеой частью цикла разработки ПО и требует не меньшего внимания, чем реализация и составление требований. Данный документ содержит информацию о различных аспектах процесса тестирования проекта 'QueueBrain'. 

В рамках плана предполагается выполенение проверки функциональных и нефункциональных требова 

Требования к тестировщику:

* ​

## Объект тестирования

Объектом тестир

## Риски

## Аспекты тестирования

## Подходы к тестированию

## Представление результатов

## Выводы

- Должна быть предусмотрена авторизация с подтверждением по почте
- Должно быть реализовано REST API, позволяющее полноценно работать с приложением, в том числе от лица администратора. 

### Интерфейс пользователя

- Главный экран приложения
  ![Main](mockups/Main.png)
- Кабинет пользователя
  ![PersonalPage](mockups/Personal.png)
- Страница с очередью
  ![QueuePage](mockups/QueuePage.png)

### Характеристики пользователей

QueueBrain хорошо подойдет для огранизации нерегулярных очередей с несложными процессами обработки. Интерфейс и идея программы достаточно просты и привычны для среднего Интернет-пользователя.

## Системные требования:

#### Управление пользователями

- Зарегестрированные пользователи должны иметь возможность:
  - Вставать в очереди
  - Создавать очереди
  - Проталкивать очереди (разрешено администратору очереди)

#### Управление очередями

- Доступ к управлению очередью должен иметь только администратор. 

- При создании очереди должны быть доступны следующие настройки:

  - Название очереди

    ![](mockups/createQueuePopup.png)

- Администратор должен иметь возможность управлять продвижением очереди:
  - Перейти к следующему пользователю

- На иформационной странице очереди должна быть доступна информация:
  - QR-код ссылки на очередь
  - Последний обработанный пользователь и количество пользователей

### Нефункциональные требования

Приложение должно быть разбито на две независимо работающие части — серверную и клиентскую. Серверная часть должна быть релизована на языке java с использованием фреймворка **Spring**; клиентская часть должна быть реализована на языке typescript/javascript с использованием фреймворка **Angular**. Взаимодействие клиентской части с серверной должно вестись только через публичный API. 

## Аналоги

Основное отличие от большинства аналогов -- способ предоставления сервиса. QueueBrain является веб-приложением, это значительно упрощает процесс использования, а публичное API позволяет легко интегрировать его с другими системами. 
Все перечисленные аналоги требуют предварительной установки и настройки.

- https://led-displays.ru/programma_suo.html
  - Платная
  - Сервер очереди должен находиться локальной сети
  - Windows only
- http://www.kkc.by/katalog/spec-sistemy/sistema-upravleniya-ocheredyu
  - Платная
- http://apertum.com.ua/
  - Сервер очереди должен находиться локальной сети
