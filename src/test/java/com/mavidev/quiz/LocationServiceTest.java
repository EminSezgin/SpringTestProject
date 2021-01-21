package com.mavidev.quiz;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@WebMvcTest(QuizApplication.class)
class LocationServiceTest {

    @MockBean
    private LocationRepository repo;

    @InjectMocks
    private LocationService service;

    @Autowired
    private MockMvc mvc;

    @Test
    void listAll() {
        Location location = new Location();
        List<Location> locations = new ArrayList<Location>();

        location.setSehir("_Test_");
        location.setIlce("_Test_");
        location.setId((long) 1);
        locations.add(location);

        location.setSehir("_Test_2");
        location.setIlce("_Test_2");
        location.setId((long) 2);
        locations.add(location);


        Mockito.when(repo.findAll()).thenReturn(locations);

        List<Location> listAll = repo.findAll();
        List<Location> result = service.listAll();

        assertSame(listAll.get(0).getIlce(), result.get(0).getIlce());
        assertSame(listAll.get(0).getSehir(), result.get(0).getSehir());
    }

    @Test
    void get() {
        Location location = new Location();

        location.setSehir("_Test_");
        location.setIlce("_Test_");
        location.setId((long) 1);


        Mockito.when(repo.getOne(location.getId())).thenReturn(location);
        Location result = service.get(location.getId());

        assertEquals(location.getSehir(), result.getSehir());
        assertEquals(location.getIlce(), result.getIlce());
    }
}