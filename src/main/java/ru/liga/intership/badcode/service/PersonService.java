package ru.liga.intership.badcode.service;


import ru.liga.intership.badcode.domain.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonService {

	/**
	 * Возвращает средний индекс массы тела всех лиц мужского пола старше 18 лет
	 *
	 * @return
	 */
	public double getAdultMaleUsersAverageBMI() {
		double totalImt = 0.0;
		List<Person> adultPersons = getListOfMaleUsers();
		for (Person person : adultPersons) {
            totalImt += person.getBmi();
		}
		return totalImt / adultPersons.size();
	}

	private List<Person> getListOfMaleUsers() {
		List<Person> adultPersons = new ArrayList<Person>();
		try {
			Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:test", "sa", "");
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM person WHERE sex = 'male' AND age > 18");
			while (rs.next()) {
				long id = rs.getLong("id");
				String sex = rs.getString("sex");
				String name = rs.getString("name");
				long age = rs.getLong("age");
				long weight = rs.getLong("weight");
				long height = rs.getLong("height");
				Person person = new Person(id, sex, name, age, weight, height);
				adultPersons.add(person);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adultPersons;
	}

}
