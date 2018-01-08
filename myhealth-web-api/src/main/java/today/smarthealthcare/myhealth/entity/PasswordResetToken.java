package today.smarthealthcare.myhealth.entity;



import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PASSWORD_RESET_TOKEN")
public class PasswordResetToken {
	@Id
	@GeneratedValue
	private Long id;
	private String token;
	private String email;
	@Column(name = "VALID_TILL")
	private Date validTill;
	private Date created = new Date();
	private boolean active;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getValidTill() {
		return validTill;
	}

	public void setValidTill(Date validTill) {
		this.validTill = validTill;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
