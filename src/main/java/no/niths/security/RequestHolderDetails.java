package no.niths.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import no.niths.common.constants.SecurityConstants;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Simple wrapper class for authenticated user.
 * We set the current authenticated user to an instance of this class.
 * Spring security then uses the instance to determine if it has the 
 * roles and attributes needed
 * 
 * The attributes in this class is used to fine grain the security checks.
 * 
 * Ex: The student id can be used together with the role like this:
 * 
 * @PreAuthorize(hasRole('ROLE_STUDENT') and principal.studentId == #id)
 * public void anyMethod(Long id) {...}
 * 
 * principal = the authenticated user
 * 
 */
public class RequestHolderDetails implements UserDetails {

	private static final long serialVersionUID = -4668876556049860936L;

	private List<String> roleNames = new ArrayList<String>();
	private String userName;
	private Long studentId;
	private Long developerId;
	private Long appId;


	public RequestHolderDetails() {
		this("Not provided");
	}

	public RequestHolderDetails(String userName) {
		this.userName = userName;
	}
	
	public Long getStudentId() {
		return studentId;
	}
	
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	@SuppressWarnings("serial")
	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		if (roleNames.isEmpty()) {
			GrantedAuthority grantedAuthority = new GrantedAuthority() {
				public String getAuthority() {
					return SecurityConstants.R_ANONYMOUS;
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

	public Long getDeveloperId() {
		return developerId;
	}

	public void setDeveloperId(Long developerId) {
		this.developerId = developerId;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

}
