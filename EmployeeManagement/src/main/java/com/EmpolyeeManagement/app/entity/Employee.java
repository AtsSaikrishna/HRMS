package com.EmpolyeeManagement.app.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String firstName;
    private String lastName;
    private Long employeeId;
    
     

    public Employee(Long id, String firstName, String lastName, Long employeeId, String email, String phoneNumber,
			String gender, LocalDate dateOfBirth, String address, String jobTitle, String department, String role,
			LocalDate dateOfJoining, String status, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.employeeId = employeeId;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.jobTitle = jobTitle;
		this.department = department;
		this.role = role;
		this.dateOfJoining = dateOfJoining;
		this.status = status;
		this.password = password;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(unique = true, nullable = false)
    private String email;  // Unique constraint for email

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    private String gender;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    private String address;

    @Column(name = "job_title", nullable = false)
    private String jobTitle;

    private String department;

    private String role;

    @Column(name = "date_of_joining", nullable = false)
    private LocalDate dateOfJoining;

   // @Column(name = "employee_type", nullable = false)
   // private String employeeType; // Full-Time, Part-Time, Contract

    private String status; // Active, Inactive

   // @Column(name = "reporting_manager")
    //private String reportingManager;

    //private Double salary;

   // @Column(name = "tax_deductions")
   // private Double taxDeductions;

   // @Column(name = "pf_number", unique = true)
  //  private String pfNumber;  // Unique constraint for pfNumber

    //@Column(name = "bank_account_details")
    //private String bankAccountDetails;

   // @Column(name = "leave_balance", nullable = false)
   // private Integer leaveBalance;

    //@Column(name = "assigned_assets")
    //private String assignedAssets; // Example: Laptop, Mobile, etc.

    @JsonIgnore
    private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

   