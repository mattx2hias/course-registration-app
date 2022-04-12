package dev.matthias.apitests;

import dev.matthias.api.StudentPrompt;
import dev.matthias.entities.Student;
import org.junit.jupiter.api.Test;

public class StudentPromptTests {
    StudentPrompt sp = new StudentPrompt(new Student());

    @Test
    void displayCourseCatalog() {
        sp.viewCourseCatalogPrompt();
    }
}
