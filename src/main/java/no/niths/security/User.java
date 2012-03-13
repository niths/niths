package no.niths.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import no.niths.common.AppConstants;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Simple wrapper class for authenticated user
 * 
 * Holds the users authorities (roles), user name and google token
 * 
 */
public class User implements UserDetails {

	private static final long serialVersionUID = -4668876556049860936L;

	private List<String> roleNames = new ArrayList<String>();
	private String userName;
	private Long studentId;


	public User() {
		this("Not provided");
	}

	public User(String userName) {
		this.userName = userName;
	}
	
	public Long getStudentId() {
		return studentId;
	}
	
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	//TODO: FIX DRY!
	@SuppressWarnings("serial")
	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		if (roleNames.isEmpty()) {
			GrantedAuthority grantedAuthority = new GrantedAuthority() {
				public String getAuthority() {
					return AppConstants.R_ANONYMOUS;
				}
			};
			grantedAuthorities.add(grantedAuthority);
			
		} else {
			
			for (final String role : roleNames) {
				GrantedAuthority grantedAuthority = new GrantedAuthority() {
					public String getAuthority() {
						return role;
					}
				};
				grantedAuthorities.add(grantedAuthority);
			}
		}
		return grantedAuthorities;
	}

	public void addRoleName(String roleName) {
		roleNames.add(roleName);
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		if (userName == null) {
			userName = "Not provided";
		}
		this.userName = userName;
	}

}
