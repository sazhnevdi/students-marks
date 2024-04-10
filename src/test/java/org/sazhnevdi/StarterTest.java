package org.sazhnevdi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.sazhnevdi.Initializer.getStorageService;

class StarterTest {

    @BeforeEach
    public void initial() {
        Initializer.init();
    }

    @Test
    public void initialDataStorageIsEmpty() {
        var service = getStorageService();
        Assertions.assertTrue(service.getData().isEmpty());
    }

    @Test
    public void createNewStudentInStorage() {
        var service = getStorageService();
        var student = "dmitriy";
        service.addNewStudent(student);

        Assertions.assertEquals(1, service.getData().size());
    }

    @Test
    public void removeStudentFromStorage() {
        createNewStudentInStorage();
        var service = getStorageService();
        service.removeStudent("dmitriy");
        Assertions.assertTrue(service.getData().isEmpty());
    }

    @Test
    public void addNewMarkToStudent() {
        createNewStudentInStorage();
        var service = getStorageService();
        var student = "dmitriy";
        var marks = service.getMarkListByStudentName(student);
        Assertions.assertTrue(marks.isEmpty());

        service.addNewMarkToStudent(student, "5");
        marks = service.getMarkListByStudentName(student);
        Assertions.assertEquals(1, marks.size());
    }

    @Test
    public void getMarkListByStudentName() {
        var expected = List.of("4", "5", "7");
        var service = getStorageService();
        var data = service.getData();
        data.put("dmitriy", List.of("4", "5", "7"));
        var marks = service.getMarkListByStudentName("dmitriy");

        Assertions.assertEquals(expected, marks);
    }
}