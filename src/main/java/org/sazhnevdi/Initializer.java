package org.sazhnevdi;

import lombok.Getter;
import org.sazhnevdi.repositories.StorageService;
import org.sazhnevdi.services.EditorMenuHandler;
import org.sazhnevdi.services.MainMenuHandler;
import org.sazhnevdi.views.MenuShower;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Properties;

final class Initializer {
    private static final String defaultPath = "./student_marks.csv";
    private static boolean alreadyInit = false;
    @Getter
    private static StorageService storageService;
    @Getter
    private static MainMenuHandler mainMenuHandler;
    @Getter
    private static EditorMenuHandler editorMenuHandler;
    @Getter
    private static MenuShower menuShower;

    private Initializer() {
    }

    public static void init() {
        if (alreadyInit) {
            return;
        }
        var props = new Properties();
        try {
            props.load((new InputStreamReader(Objects.requireNonNull(Initializer.class.getClassLoader()
                    .getResourceAsStream("default.properties")), StandardCharsets.UTF_8)));
        } catch (Exception e) {
            System.out.println("Ошибка инициализации!Не найден файл default.properties, программа будет завершена");
            System.exit(1);
        }

        if (storageService == null) {
            if (props.get("storage.path") == null) {
                storageService = StorageService.createInstance(defaultPath);
            } else {
                storageService = StorageService.createInstance((String) props.get("storage.path"));
            }

        }

        if (menuShower == null) {
            menuShower = MenuShower.createInstance();
        }

        if (editorMenuHandler == null) {
            editorMenuHandler = EditorMenuHandler.createInstance(storageService, menuShower);
        }

        if (mainMenuHandler == null) {
            mainMenuHandler = MainMenuHandler.createInstance(storageService, editorMenuHandler, menuShower);
        }

        alreadyInit = true;
    }
}
