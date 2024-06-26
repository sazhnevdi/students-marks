Сборка и запуск проекта: в корне проекта запустить скрипт start.sh (Unix-like OS)



Задача: Отслеживание оценок учащихся

Создайте Java-программу, которая позволяет пользователю вводить и сохранять оценки для списка учащихся. Программа должна позволять пользователю добавлять, удалять и обновлять оценки для каждого учащегося, а также просматривать оценки для всех учащихся или для конкретного учащегося.

Требования:

Используйте коллекцию из Java Collection Framework для хранения данных об оценках учащихся.

У каждого ученика должно быть имя и список оценок, связанных с ним.

Программа должна иметь интерфейс на основе меню, который позволяет пользователю выбрать нужную операцию.

Программа должна проверять вводимые пользователем данные и корректно обрабатывать ошибки.

Программа должна иметь возможность сохранять данные об оценках учащихся в файл и загружать их из файла.

Примерный программный поток:

При запуске программы пользователю должно быть предложено загрузить существующий файл данных или создать новый.

После выбора опции пользователю должно быть представлено меню:

a. Добавьте нового ученика

b. Удалите ученика

c. Обновите оценку ученика

d. Просмотр оценок всех учащихся

e. Просмотр оценок конкретного учащегося

Когда пользователь выбирает опцию, программа должна запросить необходимые входные данные (например, имя учащегося, оценку и т.д.) и выполнить желаемую операцию.

Примечание: Вы вольны выбрать конкретную коллекцию из Java Collection Framework, которую вы хотите использовать для этой задачи (например, ArrayList, LinkedList и т.д.).