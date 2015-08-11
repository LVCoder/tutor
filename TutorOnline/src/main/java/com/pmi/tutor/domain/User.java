package com.pmi.tutor.domain;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.util.concurrent.TimeUnit;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "user")
public class User {

	public User() {
		socialId = "none";
		registrationDate = new Date();

	}

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Size(min = 2, max = 35, message = "������� ���� ������� ���� �� {min} � {max}.")
	@Column(name = "name")
	private String name;

	@Length(min = 2, max = 35, message = "������� ������� ������� ���� �� {min} � {max}.")
	@Column(name = "surname")
	private String surname;

	@NotNull
	@Length(min = 10, max = 10, message = "����� ������� ������ 10 ����.")
	@Pattern(regexp = "[0-9]*", message = "{javax.validation.constraints.Pattern.message}")
	@Column(name = "phone")
	private String phone;

	@Email(message = "����������� �������� email.")
	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	
	@Column(name = "dob")
	private Date dob;

	@Length(max = 300, message = "���� ��� ����������� �� ���� ������ ����� 300 �������")
	@Column(name = "aboutUser")
	private String aboutUser;

	@NotBlank(message = "���� ���� �� ���� ���� ������")
	@NotNull(message = "���� ���� �� ���� ���� ������")
	@Size(max = 30, message = "���� ���� �� ���� ������ ���� �� 30 �������")
	@Column(name = "city")
	private String city;

	@Column(name = "taskCategories")
	private String taskCategories;

	@Column(name = "confirmCode")
	private String confirmCode;

	@Column(name = "authority")
	private String authority;

	@Column(name = "enabled")
	private boolean enabled;

	@Column(name = "isEmailConfirm")
	private boolean isEmailConfirm;

	@Column(name = "registration_date")
	private Date registrationDate;
	
	
	

	

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public int getTasksDone() {
		return tasksDone;
	}

	public void setTasksDone(int tasksDone) {
		this.tasksDone = tasksDone;
	}

	public int getTasksPaid() {
		return tasksPaid;
	}

	public void setTasksPaid(int tasksPaid) {
		this.tasksPaid = tasksPaid;
	}

	public void setEmailConfirm(boolean isEmailConfirm) {
		this.isEmailConfirm = isEmailConfirm;
	}

	@Column(name = "tasks_done")
	private int tasksDone;

	@Column(name = "tasks_paid")
	private int tasksPaid;

	@Column(name = "sex")
	@Enumerated(EnumType.STRING)
	private Gender gender;
	@Column(name = "social_id")
	private String socialId;

	@Column(name = "average_rate")
	private double averageRate;

	public double getAverageRate() {
		return averageRate;
	}

	public void setAverageRate(double averageRate) {
		this.averageRate = averageRate;
	}

	public boolean getIsEmailConfirm() {
		return isEmailConfirm;
	}

	public void setIsEmailConfirm(boolean isEmailConfirm) {
		this.isEmailConfirm = isEmailConfirm;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public enum Gender {
		Male, Female, Unknown
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getConfirmCode() {
		return confirmCode;
	}

	public void setConfirmCode(String confirmCode) {
		this.confirmCode = confirmCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getTaskCategories() {
		return taskCategories;
	}

	public void setTaskCategories(String taskCategories) {
		this.taskCategories = taskCategories;
	}

	public String getAboutUser() {
		return aboutUser;
	}

	public void setAboutUser(String aboutUser) {
		this.aboutUser = aboutUser;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSocialId() {
		return socialId;
	}

	public void setSocialId(String socialId) {
		this.socialId = socialId;
	}

	public String getFormatedDOB() {
		SimpleDateFormat simple = new SimpleDateFormat("YYYY/MM/dd");
		if(dob!=null)
		return simple.format(dob);
		else
			return ("1995/20/07");
	}

	public String getTimeOnSite() {
		
		Date now = new Date();
		long diffInMillies = now.getTime() - registrationDate.getTime();
		List<TimeUnit> units = new ArrayList<TimeUnit>(
				EnumSet.allOf(TimeUnit.class));
		Collections.reverse(units);
		long milliesRest = diffInMillies;
		for (TimeUnit unit : units) {
			long diff = unit.convert(milliesRest, TimeUnit.MILLISECONDS);
			long diffInMilliesForUnit = unit.toMillis(diff);
			milliesRest = milliesRest - diffInMilliesForUnit;
			if (diff != 0) {
				if (unit.name() == "DAYS") {
					if(diff>=1&&diff<7)
						switch((int)diff){
						case 1: return "���� ����";
						case 2: return "��� ��";
						case 3: return "��� ��";
						default: return diff  + " ���";
						}
					if(diff>=7&&diff<30)
						switch((int) diff/7){
						case 1: return "���� �������";
						default: return diff/7+" �����";
						}
					
					if ( diff >= 30 &&diff < 360) {
						switch ((int) diff / 30) {
						case 1:
							return "���� �����";
						case 2:
							return "��� �����";
						case 3:
							return "��� �����";
						case 4:
							return "������ �����";
						default:
							return diff / 30 + " ������";
						}
					}
					if(diff>=360)
						switch((int) diff/360){
						case 1: return "���� ��";
						default:return diff/360+" ����";
						}
				}
				else return "���� ����";
				break;
			}
		}
		if(registrationDate!=null)
		return "� "+registrationDate.toString();
		else
			return "�� �������";
			
	}
}
