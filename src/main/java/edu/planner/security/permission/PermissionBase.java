package edu.planner.security.permission;

import edu.planner.enums.Profile;
import edu.planner.interfaces.IModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
	private List<Permission> permissions = new ArrayList<>();

	public Profile getProfile() {
		return Profile.toEnum(profile);
	}
}
