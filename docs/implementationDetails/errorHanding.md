# QueueBrain error handling

## Backend REST API

В случе ошибок возвращается код ответа, не равный 200. Это позволит клиентам отличить ответ с данными от ответа с ошибкой.

Example:

```json
{
  "errorCode": "QB-T-001",
  "codeDescription": "Common QueueBrain service technical error",
  "errorUUID": "9b8d0aa9-4c18-42cb-82a3-8bb9dc3edec2",
  "errorMessage": "Internal service error"  
}
```

Назначение полей:

* code — позволит клиенту программно определить тип ошибки. Типы ошибок описаны в перечислении `com.zoxal.queuebrain.exceptions.ExceptionCode`.
* errorDescription — human-readable описание типа ошибки
* message — human-readable описание ошибки
* errorUUID — UUID ошибки для поиска ошибки во время поддержки

Ошибки сервиса логируются в отдельный файл `queuebrain_rest_errors_{yyyy-MM-dd}.log`.

Объект ошибки представлен классом `com.zoxal.queuebrain.exceptions.QueueBrainException`. Содержит два поля:

* message — internal description, конкретные детали проблемы
* clientMessage — public description, описание, возвращенное клиентам

## Logging

Общий подход к логированию: ошибка логируется **только** в месте возврата клиентом top-level хэндлере: `com.zoxal.queuebrain.controller.ExceptionResolvingControllerAdvice#resolveException`. Это позволит залогировать все ошибки, которые были выданы клиенту с их полным stacktrace.