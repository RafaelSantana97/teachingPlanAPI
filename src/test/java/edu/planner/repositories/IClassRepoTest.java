package edu.planner.repositories;

import edu.planner.models.Class;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Tag("integration-test")
class IClassRepoTest {

    @Autowired
    private IClassRepo classRepo;

    @Test
    void findBySubjectNameContaining() {
        String search = "Process";
        List<Class> classes = classRepo.findBySubjectNameContaining(Pageable.unpaged(), search)
                .stream()
                .collect(Collectors.toList());

        assertTrue(classes.size() == 2, "It should contain elements");
    }
}