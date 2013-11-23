/*
 * Copyright Mazaffaka Project (c) 2013.
 */

package l2p.gameserver.network.serverpackets;

import l2p.gameserver.model.Player;

public class ExBasicActionList extends L2GameServerPacket {
    private static final int[] BasicActions =
            {
                    0, // Переключатель Сесть/Встать. (/sit, /stand)
                    1, // Переключатель Ходьба/Бег. (/walk, /run)
                    2, // Атака выбранной цели (целей). Щелкните с зажатой клавишей Ctrl, чтобы принудительно атаковать. (/attack, /attackforce)
                    3, // Запрос торговли с выбранным игроком. (/trade)
                    4, // Выбор ближайшей цели для атаки. (/targetnext)
                    5, // Подобрать предметы, расположенные рядом. (/pickup)
                    6, // Переключиться на цель выбранного игрока. (/assist)
                    7, // Пригласить выбранного игрока в вашу группу. (/invite)
                    8, // Покинуть группу. (/leave)
                    9, // Если вы лидер группы, исключить выбранного игрока (игроков) из группы. (/dismiss)
                    10, // Настроить личный магазин для продажи предметов.(/vendor)
                    11, // Отобразить окно "Подбор Группы" для поиска групп или членов для вашей группы. (/partymatching)
                    12, // Эмоция: Поприветствовать окружающих. (/socialhello)
                    13, // Эмоция: Показать, что вы или кто-то еще одержал победу!(/socialvictory)
                    14, // Эмоция: Вдохновить ваших союзников (/socialcharge)
                    15, // Ваш питомец либо следует за вами, либо остается на месте.
                    16, // Атаковать цель.
                    17, // Прервать текущее действие.
                    18, // Подобрать находящиеся рядом предметы.
                    19, // Убирает Питомца в инвентарь.
                    20, // Использовать особое умение.
                    21, // Ваши Миньоны либо следуют за вами, либо остаются на месте.
                    22, // Атаковать цель.
                    23, // Прервать текущее действие.
                    24, // Эмоция: Ответить утвердительно. (/socialyes)
                    25, // Эмоция: Ответить отрицательно. (/socialno)
                    26, // Эмоция: Поклон, в знак уважения. (/socialbow)
                    27, // Использовать особое умение.
                    28, // Настроить личный магазин для покупки предметов. (/buy)
                    29, // Эмоция: Я не понимаю, что происходит. (/socialunaware)
                    30, // Эмоция: Я жду... (/socialwaiting)
                    31, // Эмоция: От души посмеяться. (/sociallaugh)
                    32, // Переключение между режимами атаки/движения.
                    33, // Эмоция: Аплодисменты. (/socialapplause)
                    34, // Эмоция: Покажите всем ваш лучший танец. (/socialdance)
                    35, // Эмоция: Мне грустно. (/socialsad)
                    36, // Ядовитая Газовая Атака.
                    37, // Настроить личную мастерскую для создания предметов с помощью рецептов Гномов за вознаграждение. (/dwarvenmanufacture)
                    38, // Переключатель оседлать/спешиться, когда вы находитесь рядом с Питомцем, которого можно оседлать. (/mount, /dismount, /mountdismount)
                    39, // Атака взрывающимися трупами.
                    40, // Увеличивает оценку цели (/evaluate)
                    41, // Атаковать врата замка, стены или штабы выстрелом из пушки.
                    42, // Возвращает урон обратно врагу.
                    43, // Атаковать врага, создав бурлящий водоворот.
                    44, // Атаковать врага мощным взрывом.
                    45, // Восстанавливает MP призывателя.
                    46, // Атаковать врага, призвав разрушительный шторм.
                    47, // Одновременно повреждает врага и лечит слугу.
                    48, // Атака врага выстрелом из пушки.
                    49, // Атака в приступе ярости.
                    50, // Выбранный член группы становится ее лидером.(/changepartyleader)
                    51, // Создать предмет, используя обычный рецепт за вознаграждение.(/generalmanufacture)
                    52, // Снимает узы с миньона и освобождает его.
                    53, // Двигаться к цели.
                    54, // Двигаться к цели.
                    55, // Переключатель записи и остановки записи повторов. (/start_videorecording, /end_videorecording, /startend_videorecording)
                    56, // Пригласить выбранную цель в канал команды. (/channelinvite)
                    57, // Высвечивает сообщения личного магазина и личной мастерской, содержащие искомое слово. (/findprivatestore)
                    58, // Вызвать другого игрока на дуэль. (/duel)
                    59, // Отмена дуэли означает проигрыш. (/withdraw)
                    60, // Вызвать другую группу на дуэль. (/partyduel)
                    61, // Открывает личный магазин для продажи упаковок (/packagesale)
                    62, // Обаятельная поза(/charm)
                    63, // Запускает забавную и простую мини-игру, в которую можно поиграть в любое время. (команда: /minigame)
                    64, // Открывает окно свободного телепорта, которое позволяет свободно перемещаться между локациями с телепортами. (команда: /teleportbookmark)
                    65, // Сообщает о подозрительном поведении объекта, чьи действия позволяют предположить использование бот-программы.
                    66, // Поза "Смущение"  (команда: /shyness)
                    67, // Управление кораблем
                    68, // Прекращение управления кораблем
                    69, // Отправление корабля
                    70, // Спуск с корабля
                    71, // Поклон
                    72, // Дай Пять
                    73, // Танец Вдвоем
                    74, // Вкл/Выкл данные о состоянии
                    76, // Приглашение друга
                    77, // Вкл/Выкл. Запись
                    78, // Использование Знака 1
                    79, // Использование Знака 2
                    80, // Использование Знака 3
                    81, // Использование Знака 4
                    82, // Автоприцел Знаком 1
                    83, // Автоприцел Знаком 2
                    84, // Автоприцел Знаком 3
                    85, // Автоприцел Знаком 4
                    86, // Начать/прервать автоматический поиск группы
                    87, // Propose
                    88, // Provoke
                    1000, // Осадный Молот
                    1001, // Предельный Ускоритель
                    1002, // Враждебность
                    1003, // Дикое Оглушение
                    1004, // Дикая Защита
                    1005, // Яркая Вспышка
                    1006, // Исцеляющий Свет
                    1007, // Благословение Королевы
                    1008, // Дар Королевы
                    1009, // Исцеление Королевы
                    1010, // Благословение Серафима
                    1011, // Дар Серафима
                    1012, // Исцеление Серафима
                    1013, // Проклятие Тени
                    1014, // Массовое Проклятие Тени
                    1015, // Жертва Тени
                    1016, // Проклятый Импульс
                    1017, // Проклятый Удар
                    1018, // Проклятие Поглощения Энергии
                    1019, // Умение Кэт 2
                    1020, // Умение Мяу 2
                    1021, // Умение Кая 2
                    1022, // Умение Юпитера 2
                    1023, // Умение Миража 2
                    1024, // Умение Бекара 2
                    1025, // Теневое Умение 1
                    1026, // Теневое Умение 2
                    1027, // Умение Гекаты
                    1028, // Умение Воскрешенного 1
                    1029, // Умение Воскрешенного 2
                    1030, // Умение Порочного 2
                    1031, // Рассечение
                    1032, // Режущий Вихрь
                    1033, // Кошачья Хватка
                    1034, // Кнут
                    1035, // Приливная Волна
                    1036, // Взрыв Трупа
                    1037, // Случайная Смерть
                    1038, // Сила Проклятия
                    1039, // Пушечное Мясо
                    1040, // Большой Бум
                    1041, // Укус
                    1042, // Кувалда
                    1043, // Волчий Рык
                    1044, // Пробуждение
                    1045, // Волчий Вой
                    1046, // Рев Ездового Дракона
                    1047, // Укус Божественного Зверя
                    1048, // Оглушительная Атака Божественного Зверя
                    1049, // Огненное Дыхание Божественного Зверя
                    1050, // Рев Божественного Зверя
                    1051, // Благословение Тела
                    1052, // Благословение Духа
                    1053, // Ускорение
                    1054, // Проницательность
                    1055, // Чистота
                    1056, // Воодушевление
                    1057, // Дикая Магия
                    1058, // Шепот Смерти
                    1059, // Фокусировка
                    1060, // Наведение
                    1061, // Удар Смерти
                    1062, // Двойная Атака
                    1063, // Вихревая Атака
                    1064, // Метеоритный дождь
                    1065, // Пробуждение
                    1066, // Удар Молнии
                    1067, // Молния
                    1068, // Световая Волна
                    1069, // Вспышка
                    1070, // Контроль Эффекта
                    1071, // Мощный удар
                    1072, // Проникающая Атака
                    1073, // Яростный Ветер
                    1074, // Удар Копьем
                    1075, // Боевой Клич
                    1076, // Мощное Сокрушение
                    1077, // Шаровая Молния
                    1078, // Шоковая Волна
                    1079, // Вой
                    1080, // Прилив Феникса
                    1081, // Очищение Феникса
                    1082, // Пылающее Перо Феникса
                    1083, // Пылающий Клюв Феникса
                    1084, // Смена Режима
                    1086, // Натиск Пантеры
                    1087, // Темный Коготь Пантеры
                    1088, // Смертоносный Коготь Пантеры
                    1089, // Хвост
                    1090, // Укус Ездового Дракона
                    1091, // Устрашение Ездового Дракона
                    1092, // Рывок Ездового Дракона
                    1093, // Удар Магвена
                    1094, // Легкая Походка Магвена
                    1095, // Мощный Удар Магвена
                    1096, // Легкая Походка Элитного Магвена
                    1097, // Возвращение Магвена
                    1098, // Групповое Возвращение Магвена
                    1099, // Атака
                    1100, // Перемещение
                    1101, // Прекращение
                    1102, // Отмена  призыва
                    1103, // Пассивность
                    1104, // Защита
                    1106, // Коготь Медведя
                    1107, // Топот Медведя
                    1108, // Укус Кугуара
                    1109, // Прыжок Кугуара
                    1110, // Прикосновение Потрошителя
                    1111, // Сила Потрошителя
                    1113, // Львиный Рев
                    1114, // Львиный Коготь
                    1115, // Львиный Бросок
                    1116, // Львиное Пламя
                    1117, // Полет Громового Змея
                    1118, // Очищение Громового Змея
                    1120, // Стрельба Перьями Громового Змея
                    1121, // Острые Когти Громового Змея
                    1122, // Благословение Жизни
                    1123, // Осадный Удар
                    5000, // Погладить
                    5001, // Искушение Света Розы
                    5002, // Запредельное Искушение
                    5003, // Удар Молнии
                    5004, // Молния
                    5005, // Световая волна
                    5006, // Контроль Эффекта
                    5007, // Проникающая Атака
                    5008, // Вихревая Атака
                    5009, // Сокрушение
                    5010, // Боевой Клич
                    5011, // Мощное Сокрушение
                    5012, // Шаровая Молния
                    5013, // Шоковая Волна
                    5014, // Воспламенение
                    5015, // Смена Режима
                    5016, // Усиление Кота-Рейнджера
            };

    private static final int[] TransformationActions =
            {
                    1, // Переключатель Ходьба/Бег. (/walk, /run)
                    2, // Атака выбранной цели (целей). Щелкните с зажатой клавишей Ctrl, чтобы принудительно атаковать. (/attack, /attackforce)
                    3, // Запрос торговли с выбранным игроком. (/trade)
                    4, // Выбор ближайшей цели для атаки. (/targetnext)
                    5, // Подобрать предметы, расположенные рядом. (/pickup)
                    6, // Переключиться на цель выбранного игрока. (/assist)
                    7, // Пригласить выбранного игрока в вашу группу. (/invite)
                    8, // Покинуть группу. (/leave)
                    9, // Если вы лидер группы, исключить выбранного игрока (игроков) из группы. (/dismiss)
                    11, // Отобразить окно "Подбор Группы" для поиска групп или членов для вашей группы. (/partymatching)
                    15, // Ваш питомец либо следует за вами, либо остается на месте.
                    16, // Атаковать цель.
                    17, // Прервать текущее действие.
                    18, // Подобрать находящиеся рядом предметы.
                    19, // Убирает Питомца в инвентарь.
                    21, // Ваши Миньоны либо следуют за вами, либо остаются на месте.
                    22, // Атаковать цель.
                    23, // Прервать текущее действие.
                    40, // Увеличивает оценку цели (/evaluate)
                    50, // Выбранный член группы становится ее лидером.(/changepartyleader)
                    52, // Снимает узы с миньона и освобождает его.
                    53, // Двигаться к цели.
                    54, // Двигаться к цели.
                    55, // Переключатель записи и остановки записи повторов. (/start_videorecording, /end_videorecording, /startend_videorecording)
                    56, // Пригласить выбранную цель в канал команды. (/channelinvite)
                    57, // Высвечивает сообщения личного магазина и личной мастерской, содержащие искомое слово. (/findprivatestore)
                    63, // Запускает забавную и простую мини-игру, в которую можно поиграть в любое время. (команда: /minigame)
                    64, // Открывает окно свободного телепорта, которое позволяет свободно перемещаться между локациями с телепортами. (команда: /freeteleport)
                    65, // Сообщает о подозрительном поведении объекта, чьи действия позволяют предположить использование BOT-программы.
                    67, // Управление кораблем
                    68, // Прекращение управления кораблем
                    69, // Отправление корабля
                    70, // Спуск с корабля
                    74, // Вкл/Выкл данные о состоянии
                    76, // Приглашение друга
                    77, // Вкл/Выкл. Запись
                    78, // Использование Знака 1
                    79, // Использование Знака 2
                    80, // Использование Знака 3
                    81, // Использование Знака 4
                    82, // Автоприцел Знаком 1
                    83, // Автоприцел Знаком 2
                    84, // Автоприцел Знаком 3
                    85, // Автоприцел Знаком 4
                    86, // Начать/прервать автоматический поиск группы
                    1000, // Осадный Молот
                    1001, // Предельный Ускоритель
                    1002, // Враждебность
                    1003, // Дикое Оглушение
                    1004, // Дикая Защита
                    1005, // Яркая Вспышка
                    1006, // Исцеляющий Свет
                    1007, // Благословение Королевы
                    1008, // Дар Королевы
                    1009, // Исцеление Королевы
                    1010, // Благословение Серафима
                    1011, // Дар Серафима
                    1012, // Исцеление Серафима
                    1013, // Проклятие Тени
                    1014, // Массовое Проклятие Тени
                    1015, // Жертва Тени
                    1016, // Проклятый Импульс
                    1017, // Проклятый Удар
                    1018, // Проклятие Поглощения Энергии
                    1019, // Умение Кэт 2
                    1020, // Умение Мяу 2
                    1021, // Умение Кая 2
                    1022, // Умение Юпитера 2
                    1023, // Умение Миража 2
                    1024, // Умение Бекара 2
                    1025, // Теневое Умение 1
                    1026, // Теневое Умение 2
                    1027, // Умение Гекаты
                    1028, // Умение Воскрешенного 1
                    1029, // Умение Воскрешенного 2
                    1030, // Умение Порочного 2
                    1031, // Рассечение
                    1032, // Режущий Вихрь
                    1033, // Кошачья Хватка
                    1034, // Кнут
                    1035, // Приливная Волна
                    1036, // Взрыв Трупа
                    1037, // Случайная Смерть
                    1038, // Сила Проклятия
                    1039, // Пушечное Мясо
                    1040, // Большой Бум
                    1041, // Укус
                    1042, // Кувалда
                    1043, // Волчий Рык
                    1044, // Пробуждение
                    1045, // Волчий Вой
                    1046, // Рев Ездового Дракона
                    1047, // Укус Божественного Зверя
                    1048, // Оглушительная Атака Божественного Зверя
                    1049, // Огненное Дыхание Божественного Зверя
                    1050, // Рев Божественного Зверя
                    1051, // Благословение Тела
                    1052, // Благословение Духа
                    1053, // Ускорение
                    1054, // Проницательность
                    1055, // Чистота
                    1056, // Воодушевление
                    1057, // Дикая Магия
                    1058, // Шепот Смерти
                    1059, // Фокусировка
                    1060, // Наведение
                    1061, // Удар Смерти
                    1062, // Двойная Атака
                    1063, // Вихревая Атака
                    1064, // Метеоритный дождь
                    1065, // Пробуждение
                    1066, // Удар Молнии
                    1067, // Молния
                    1068, // Световая Волна
                    1069, // Вспышка
                    1070, // Контроль Эффекта
                    1071, // Мощный удар
                    1072, // Проникающая Атака
                    1073, // Яростный Ветер
                    1074, // Удар Копьем
                    1075, // Боевой Клич
                    1076, // Мощное Сокрушение
                    1077, // Шаровая Молния
                    1078, // Шоковая Волна
                    1079, // Вой
                    1080, // Прилив Феникса
                    1081, // Очищение Феникса
                    1082, // Пылающее Перо Феникса
                    1083, // Пылающий Клюв Феникса
                    1084, // Смена Режима
                    1086, // Натиск Пантеры
                    1087, // Темный Коготь Пантеры
                    1088, // Смертоносный Коготь Пантеры
                    1089, // Хвост
                    1090, // Укус Ездового Дракона
                    1091, // Устрашение Ездового Дракона
                    1092, // Рывок Ездового Дракона
                    1093, // Удар Магвена
                    1094, // Легкая Походка Магвена
                    1095, // Мощный Удар Магвена
                    1096, // Легкая Походка Элитного Магвена
                    1097, // Возвращение Магвена
                    1098, // Групповое Возвращение Магвена
                    1099, // Атака
                    1100, // Перемещение
                    1101, // Прекращение
                    1102, // Отмена  призыва
                    1103, // Пассивность
                    1104, // Защита
                    1106, // Коготь Медведя
                    1107, // Топот Медведя
                    1108, // Укус Кугуара
                    1109, // Прыжок Кугуара
                    1110, // Прикосновение Потрошителя
                    1111, // Сила Потрошителя
                    1113, // Львиный Рев
                    1114, // Львиный Коготь
                    1115, // Львиный Бросок
                    1116, // Львиное Пламя
                    1117, // Полет Громового Змея
                    1118, // Очищение Громового Змея
                    1120, // Стрельба Перьями Громового Змея
                    1121, // Острые Когти Громового Змея
                    1122, // Благословение Жизни
                    1123, // Осадный Удар
                    5000, // Погладить
                    5001, // Искушение Света Розы
                    5002, // Запредельное Искушение
                    5003, // Удар Молнии
                    5004, // Молния
                    5005, // Световая волна
                    5006, // Контроль Эффекта
                    5007, // Проникающая Атака
                    5008, // Вихревая Атака
                    5009, // Сокрушение
                    5010, // Боевой Клич
                    5011, // Мощное Сокрушение
                    5012, // Шаровая Молния
                    5013, // Шоковая Волна
                    5014, // Воспламенение
                    5015, // Смена Режима
                    5016, // Усиление Кота-Рейнджера
            };

    private final int[] actions;

    public ExBasicActionList(Player activeChar) {
        actions = activeChar.getTransformation() == 0 ? BasicActions : TransformationActions;
    }

    @Override
    protected void writeImpl() {
        writeEx(0x60);
        writeDD(actions, true);
    }
}