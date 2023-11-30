package util;

import java.util.Set;

public class UserSession {
	private static UserSession Instance;
	
	private String userId;
	private Set<String> priv;
	
	
	public UserSession(String userId, Set<String> priv) {
		this.userId = userId;
		this.priv = priv;
	}
	
	
//	public static UserSession getInstace(String userId,Set<String> priv )
	
	
	
}
