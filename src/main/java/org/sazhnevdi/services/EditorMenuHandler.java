package org.sazhnevdi.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.sazhnevdi.repositories.StorageService;
import org.sazhnevdi.views.MenuShower;

import java.util.Scanner;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class EditorMenuHandler {
    private static EditorMenuHandler editorMenuHandler;
    private final StorageService service;
    private final MenuShower menuShower;


    public static EditorMenuHandler createInstance(StorageService storageService, MenuShower menuShower) {
        if (editorMenuHandler == null) {
            editorMenuHandler = new EditorMenuHandler(storageService, menuShower);
        }
        return editorMenuHandler;
    }

    public void start(Scanner sc) {
        menuShower.showEditorMenuItems();
        String choise = StringUtils.EMPTY;

        while (!choise.equals("z")) {
            choise = sc.nextLine();

            switch (choise) {
                case "a":
                    System.out.println("Введите имя нового студента:");
                    if (service.addNewStudent(sc.nextLine().trim().toLowerCase())) {
                        System.out.println("Новый студент добавлен");
                    }
                    menuShower.showEditorMenuItems();
                    break;
                case "b":
                    System.out.println("Введите имя удаляемого студента:");
                    if (service.removeStudent(sc.nextLine().trim().toLowerCase())) {
                        System.out.println("Студент удален");
                    }
                    menuShower.showEditorMenuItems();
                    break;
                case "c":
                    System.out.println("Введите имя студента:");
                    var name = sc.nextLine().trim().toLowerCase();
                    var marks = service.getMarkListByStudentName(name);
                    if (CollectionUtils.isNotEmpty(marks)) {
                        System.out.println(marks);
                        System.out.println("Введите порядковый номер обновляемой оценки в списке:");
                        var number = sc.nextLine().trim();
                        System.out.println("Введите оценку:");
                        var mark = sc.nextLine().trim();
                        if (service.changeMarkByIndex(name, number, mark)) {
                            System.out.println("Оценка изменена");
                        }
                    }
                    menuShower.showEditorMenuItems();
                    break;
                case "d":
                    var data = service.getData();
                    if (MapUtils.isNotEmpty(data)) {
                        data.forEach((key, value) -> System.out.printf("Студент: %s\nОценки: %s\n", key, value));
                    } else {
                        System.out.println("Данных не обнаружено");
                    }
                    menuShower.showEditorMenuItems();
                    break;
                case "e":
                    System.out.println("Введите имя студента:");
                    var sMarks = service.getMarkListByStudentName(sc.nextLine().trim().toLowerCase());
                    if (sMarks != null) {
                        System.out.println(sMarks);
                    }
                    menuShower.showEditorMenuItems();
                    break;
                case "f":
                    System.out.println("Введите имя студента:");
                    var sName = sc.nextLine().trim().toLowerCase();
                    System.out.println("Введите оценку:");
                    var mark = sc.nextLine().trim().toLowerCase();
                    service.addNewMarkToStudent(sName, mark);
                    menuShower.showEditorMenuItems();
                    break;
                case "z":
                    menuShower.showMainMenuItems();
                    break;
                default:
                    System.out.println("неверный ввод");
                    menuShower.showEditorMenuItems();
            }
        }

    }

}
