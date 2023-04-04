package co.prod.vo;

import lombok.Data;

@Data
public class EmpVO {
	private int employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private int salary;
	private String hireDate;
	private String jobId;
}
