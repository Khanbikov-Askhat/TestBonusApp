Тестовое приложение.
Сами тесты написать не успел, подразумевалось, что один банковский аккаунт и аккаунт с бонусами будет существовать
Для этого написаны методы.
Адрес первого запроса http://localhost:8080/accounts/addAccount
Тело: 
{
    "amount": 5000
}

Адрес второго запроса http://localhost:8080/bonus/addBonus
Тело:
{
    "accountId": 1
}

Основаная логика приложения по подсчету http://localhost:8080/payment/Online/100 или http://localhost:8080/payment/Shop/100.
Тело:
{
    "accountId": 1,
    "bonusId": 1
}
