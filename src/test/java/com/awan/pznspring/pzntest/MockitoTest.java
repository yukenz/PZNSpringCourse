package com.awan.pznspring.pzntest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@DisplayNameGeneration(DisplayNameGeneratorImpl.class)
@ExtendWith({MockitoExtension.class})
public class MockitoTest {

    /* Tidak bekerja dengan Test Instance lifecycle Class */
    /* Penggunaan Mock dengan Anotasi */
    @Mock
    PersonRepository personRepository;

    PersonService personService;

    @BeforeEach
    public void setUp() {
        personService = new PersonService(personRepository);
    }

    @Test
    void testMock() {
        /* Pembuatan Mock secara programmatic */
        List listMock = Mockito.mock(List.class);
        Mockito.when(listMock.get(0)).thenReturn("Awan");

        Assertions.assertEquals("Awan", listMock.get(0));

    }

    @Test
    void testGetNotFound() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            personService.getPerson("not found");
        });
    }

    @Test
    void testFoundWithMock() {
        Mockito.when(personRepository.selectById("A")).thenReturn(new Person("A", "Awan"));
        Person person = personService.getPerson("A");
        Assertions.assertEquals("A", person.getId());
        Assertions.assertEquals("Awan", person.getName());
    }

    @Test
    void testInsertSuccessWithMock() {
        Person awan = personService.register("awan");
        Assertions.assertEquals("awan", awan.getName());
        Assertions.assertNotNull(awan.getId());

        Mockito.verify(personRepository, Mockito.times(1)).insert(new Person(awan.getId(), awan.getName()));

    }
}
