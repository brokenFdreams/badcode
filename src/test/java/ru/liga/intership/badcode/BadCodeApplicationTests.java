package ru.liga.intership.badcode;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.liga.intership.badcode.domain.Person;
import ru.liga.intership.badcode.service.PersonService;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BadCodeApplicationTests {
	private PersonService personService;

	@BeforeClass
	public void beforeClass() {
		personService = new PersonService();
	}

	@Test
	public void testPersonServiceBMI() {
		assertEquals(51.54841992198541, personService.getAdultMaleUsersAverageBMI());
	}

	@Test
	public void testPersonBMI() {
		Person person = new Person(Long.valueOf(1), "male", "Vasya", Long.valueOf(80), Long.valueOf(190), Long.valueOf(25));
		long weight = 190;
		double heightInMeters = 25 * 100d;
		double bmi = weight / (double) (heightInMeters * heightInMeters);
		assertEquals(bmi, person.getBmi());
	}

}
