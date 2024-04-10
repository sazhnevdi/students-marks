package org.sazhnevdi.repositories;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class StorageService {
    private static StorageService storageService;
    private final String storagePath;
    @Getter
    private final Map<String, List<String>> data;

    public static StorageService createInstance(String storagePath) {
        if (storageService == null) {
            storageService = new StorageService(storagePath, new HashMap<>());
        }
        return storageService;
    }

    public void saveDataToStorage() {
        try (var csvWriter = new FileWriter(storagePath, false)) {
            data.forEach((name, marks) -> {
                try {
                    csvWriter.append(name);
                    csvWriter.append(", ");
                    csvWriter.append(String.join(", ", data.get(name)));
                    csvWriter.append("\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

        } catch (Exception e) {
            System.out.println("Не удалось сохранить данные в файл");
        }
    }

    public boolean loadDataFromStorage() {
        Path path = Paths.get(storagePath);
        if (!Files.exists(path)) {
            System.out.printf("Не найден файл с данными по пути: %s\n", storagePath);
            return false;
        }
        try (Stream<String> lines = Files.lines(path)) {
            lines.filter(line -> line.contains(", "))
                    .forEach(line -> {
                        String[] keyValuePair = line.split(", ");
                        String studentName = keyValuePair[0];
                        String[] studentMarks = Arrays.copyOfRange(keyValuePair, 1, keyValuePair.length);
                        data.put(studentName, new ArrayList<>(Arrays.asList(studentMarks)));
                    });
            return true;
        } catch (IOException e) {
            System.out.println("не удалось созранить данные в файл");
            return false;
        }
    }

    public boolean createNewFileStorage() {
        try {
            Files.deleteIfExists(Path.of(storagePath));
            if (!data.isEmpty()) {
                data.clear();
            }
            return true;
        } catch (IOException e) {
            System.out.printf("Не удалось удалить файл: %s!\n", e.getMessage());
            return false;
        }
    }

    public boolean changeMarkByIndex(String name, String number, String newMark) {
        if (!isStorageContainsStudent(name)) {
            System.out.printf("Студент с таким именем: %s не найден!\n", name);
            return false;
        }

        if (data.get(name).isEmpty()) {
            System.out.printf("У студента: %s пока еще нет оценок!\n", name);
            return false;
        }

        var index = 0;
        if (isNumber(number)) {
            index = Integer.parseInt(number) - 1;
        } else {
            System.out.println("Введены не корректные данные, номер оценки должен быть числом");
            return false;
        }

        if (!isNumber(newMark) || newMark.startsWith("-")) {
            System.out.println("Введены не корректные данные, оценка дожна быть положительным числом");
            return false;
        }

        if (index < 0 || index >= data.get(name).size()) {
            System.out.printf("Введите корректный порядковый номер оценки в диапазоне от %d до %d!\n"
                    , 1, data.get(name).size());
            return false;
        }

        data.get(name).set(index, newMark);
        return true;
    }

    public boolean addNewStudent(String name) {
        if (isStorageContainsStudent(name)) {
            System.out.println("Студент с таким именем уже существует!");
            return false;
        }

        data.put(name, new ArrayList<>());
        return true;
    }

    public boolean removeStudent(String name) {
        if (!isStorageContainsStudent(name)) {
            System.out.printf("Студент с таким именем: %s не найден!\n", name);
            return false;
        }

        data.remove(name);
        return true;
    }

    public void addNewMarkToStudent(String name, String mark) {
        if (!isStorageContainsStudent(name)) {
            System.out.printf("Студент с таким именем: %s не найден!\n", name);
            return;
        }
        if (!NumberUtils.isDigits(mark) || mark.startsWith("-")) {
            System.out.println("Введены не корректные данные, оценка дожна быть положительным числом");
            return;
        }
        data.get(name).add(mark);
        System.out.println("Оценка добавлена");
    }

    public List<String> getMarkListByStudentName(String name) {
        if (!isStorageContainsStudent(name)) {
            System.out.printf("Студента с таким именем: %s не найден!\n", name);
            return null;
        }
        if (CollectionUtils.isEmpty(data.get(name))) {
            System.out.printf("У студента: %s пока еще нет оценок!\n", name);
        }
        return data.get(name);
    }

    private boolean isNumber(String str) {
        return NumberUtils.isDigits(str);
    }

    private boolean isStorageContainsStudent(String name) {
        return data.containsKey(name);
    }

}


