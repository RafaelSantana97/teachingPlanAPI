package edu.planner.security.permission;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import edu.planner.enums.Profile;
import edu.planner.interfaces.IModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PermissionBase implements Serializable, IModel {

	private static final long serialVersionUID = -1995900791554126203L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Short profile;

	@OneToMany
	@JoinColumn(name = "permission_permissions", foreignKey = @ForeignKey(name = "FK_PERMISSION_BASE"))
	private List<Permission> permissions = new ArrayList<Permission>();

	public Profile getProfile() {
		return Profile.toEnum(profile);
	}
}
