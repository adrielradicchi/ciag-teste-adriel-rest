package br.com.ciagtesteadriel.http;

//Importando a classe para realizar a conversao de Objeto Java para XML
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {

	private int code;
	
	private String name;
	
	private String email;
	
	private String password;
	
	private String sex;
	
	public User(){
		
	}
	
	public User(int code, String name, String email, String password, String sex){
		super();
		this.code = code;
		this.name = name;
		this.email = email; 
		this.password = password; 
		this.sex = sex;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
}
