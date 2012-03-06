package no.niths.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

	// private String roleName = "ROLE_USER";
	private List<String> roleNames = new ArrayList<String>();
	private String userName;
//	private String sessionToken;

	public User() {
		this("Not provided");
	}

	public User(String userName) {
		this.userName = userName;
	}

	//TODO: FIX DRY!
	@SuppressWarnings("serial")
	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		if (roleNames.isEmpty()) {
			GrantedAuthority grantedAuthority = new GrantedAuthority() {
				public String getAuthority() {
					return "ROLE_USER";
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
		// this.roleName = roleName;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

//	public String getSessionToken() {
//		return sessionToken;
//	}
//
//	public void setSessionToken(String googleToken) {
//		this.sessionToken = googleToken;
//	}

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
