package io.javabrains.springsecurityjwt.rest;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Employee {

	private String name;

	private List<Employee> workers;

	public static void main(String[] args) {
		List<Employee> emps = new ArrayList<>();

		Employee Manger1 = Employee.builder().name("Manger1").workers(
				List.of(new Employee("worker1", null), new Employee("worker2", null), new Employee("worker3", null)))
				.build();
		Employee Manger2 = Employee.builder().name("Manger2").workers(
				List.of(new Employee("worker4", null), new Employee("worker5", null), new Employee("worker6", null)))
				.build();
		emps.add(Manger1);
		emps.add(Manger2);
		
		
		emps.stream().flatMap(t -> t.workers.stream()).forEach(System.out::println);
		
		
	}

}
