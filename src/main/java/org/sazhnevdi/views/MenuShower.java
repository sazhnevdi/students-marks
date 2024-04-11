package org.sazhnevdi.views;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class MenuShower {
    private static MenuShower menuShower;

    public static MenuShower createInstance() {
        if (menuShower == null) {
            menuShower = new MenuShower();
        }
        return menuShower;
    }

    public void showMainMenuItems() {
        var mainMenu = """
                ----------------------------------------------------
                Выберите необходимое действие
                a: Загрузить данные из файла
                b: Создать новый файл
                c: Переход в меню просмотра и редактирования данных
                z: Выход
                """;
        System.out.println(mainMenu);
    }

    public void showEditorMenuItems() {
        var editorMenu = """
                ------------------------------------------
                a: Добавить нового студента
                b: Удалить студента
                c: Изменить оценку студента
                d: Просмотр оценок всех студентов
                е: Просмотр оценок конкретного студента
                f: Добавить оценку студенту
                z: Выход
                """;
        System.out.println(editorMenu);
    }
}
