package org.sazhnevdi.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.sazhnevdi.repositories.StorageService;
import org.sazhnevdi.views.MenuShower;

import java.util.Scanner;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class MainMenuHandler {
    private static MainMenuHandler mainMenuHandler;
    private final StorageService service;
    private final EditorMenuHandler editorMenuHandler;
    private final MenuShower menuShower;

    public static MainMenuHandler createInstance(StorageService storageService
            , EditorMenuHandler editorMenuHandler, MenuShower menuShower) {

        if (mainMenuHandler == null) {
            mainMenuHandler = new MainMenuHandler(storageService, editorMenuHandler, menuShower);
        }
        return mainMenuHandler;
    }

    public void start() {
        menuShower.showMainMenuItems();
        var sc = new Scanner(System.in);
        var choise = StringUtils.EMPTY;

        while (!choise.equals("z")) {
            choise = sc.nextLine();

            switch (choise) {
                case "a":
                    if (service.loadDataFromStorage())
                        System.out.println("Данные загружены из файла");
                    menuShower.showMainMenuItems();
                    break;
                case "b":
                    if (service.createNewFileStorage()) {
                        System.out.println("Данные будут сохранены в новый файл");
                    }
                    menuShower.showMainMenuItems();
                    break;
                case "c":
                    editorMenuHandler.start(sc);
                    break;
                case "z":
                    System.out.println("Выход");
                    break;
                default:
                    System.out.println("Не корректный ввод");
                    menuShower.showMainMenuItems();
            }
        }

        service.saveDataToStorage();

    }
}
