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
        System.out.println("----------------------------------------------------");
        System.out.println("Выберите необходимое действие");
        System.out.println("a: Загрузить данные из файла");
        System.out.println("b: Создать новый файл");
        System.out.println("c: Переход в меню просмотра и редактирования данных");
        System.out.println("z: Выход");
    }

    public void showEditorMenuItems() {
        System.out.println("------------------------------------------");
        System.out.println("Выберите необходимое действие");
        System.out.println("a: Добавить нового студента");
        System.out.println("b: Удалить студента");
        System.out.println("c: Изменить оценку студента");
        System.out.println("d: Просмотр оценок всех студентов");
        System.out.println("е: Просмотр оценок конкретного студента");
        System.out.println("f: Добавить оценку студенту");
        System.out.println("z: Выход");
    }
}
