package com.sundy.service.security.inteceptor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;




import java.util.concurrent.CopyOnWriteArraySet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import com.sundy.core.User;

public class CustomerSessionRegistryImpl implements SessionRegistry,ApplicationListener<SessionDestroyedEvent>{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	 private static final String SESSIONIDS = "sessionIds";
	 private static final String PRINCIPALS = "principals";
	 @Autowired
	 private RedisTemplate redisTemplate;
	 
	 
	 public CustomerSessionRegistryImpl(){
		 
	 }
	 
	@Override
	public void onApplicationEvent(SessionDestroyedEvent event) {
		     String sessionId = event.getId();
	        this.removeSessionInformation(sessionId);
		
	}

	@Override
	public List<Object> getAllPrincipals() {
		return new ArrayList(this.getPrincipalsKeySet());
	}
	
	public Set<String> getPrincipalsKeySet() {
        BoundHashOperations<String, String, Set<String>> hashOperations = redisTemplate.boundHashOps(PRINCIPALS);
        return hashOperations.keys();
    }

	@Override
	public List<SessionInformation> getAllSessions(Object principal,boolean includeExpiredSessions) {
		  Set<String> sessionsUsedByPrincipal =  this.getPrincipals(((User)principal).toString());
	        if (sessionsUsedByPrincipal == null) {
	            return Collections.emptyList();
	        } else {
	            List<SessionInformation> list = new ArrayList(sessionsUsedByPrincipal.size());
	            Iterator var5 = sessionsUsedByPrincipal.iterator();

	            while (true) {
	                SessionInformation sessionInformation;
	                do {
	                    do {
	                        if (!var5.hasNext()) {
	                            return list;
	                        }

	                        String sessionId = (String) var5.next();
	                        sessionInformation = this.getSessionInformation(sessionId);
	                    } while (sessionInformation == null);
	                } while (!includeExpiredSessions && sessionInformation.isExpired());

	                list.add(sessionInformation);
	            }
	        }
	}
	
	 public Set<String> getPrincipals(final String key) {
	        BoundHashOperations<String, String, Set<String>> hashOperations = redisTemplate.boundHashOps(PRINCIPALS);
	        return hashOperations.get(key);
	    }

	@Override
	public SessionInformation getSessionInformation(String sessionId) {
		Assert.hasText(sessionId, "SessionId required as per interface contract");
        return (SessionInformation) this.getSessionInfo(sessionId);
	}
	
	public SessionInformation getSessionInfo(final String sessionId) {
        BoundHashOperations<String, String, SessionInformation> hashOperations = redisTemplate.boundHashOps(SESSIONIDS);
        return hashOperations.get(sessionId);
    }

	@Override
	public void refreshLastRequest(String sessionId) {
        Assert.hasText(sessionId, "SessionId required as per interface contract");
        SessionInformation info = this.getSessionInformation(sessionId);
        if (info != null) {
            info.refreshLastRequest();
        }
	}

	@Override
	public void registerNewSession(String sessionId, Object principal) {
		  Assert.hasText(sessionId, "SessionId required as per interface contract");
	        Assert.notNull(principal, "Principal required as per interface contract");
	        if (this.logger.isDebugEnabled()) {
	            this.logger.debug("Registering session " + sessionId + ", for principal " + principal);
	        }

	        if (this.getSessionInformation(sessionId) != null) {
	            this.removeSessionInformation(sessionId);
	        }

	        this.addSessionInfo(sessionId, new SessionInformation(principal, sessionId, new Date()));

	        Set<String> sessionsUsedByPrincipal = (Set) this.getPrincipals(principal.toString());
	        if (sessionsUsedByPrincipal == null) {
	            sessionsUsedByPrincipal = new CopyOnWriteArraySet();
	            Set<String> prevSessionsUsedByPrincipal = (Set) this.putIfAbsentPrincipals(principal.toString(), sessionsUsedByPrincipal);
	            if (prevSessionsUsedByPrincipal != null) {
	                sessionsUsedByPrincipal = prevSessionsUsedByPrincipal;
	            }
	        }

	        ((Set) sessionsUsedByPrincipal).add(sessionId);
	        this.putPrincipals(principal.toString(), sessionsUsedByPrincipal);
	        if (this.logger.isTraceEnabled()) {
	            this.logger.trace("Sessions used by '" + principal + "' : " + sessionsUsedByPrincipal);
	        }
	}

	@Override
	public void removeSessionInformation(String sessionId) {
		
		 Assert.hasText(sessionId, "SessionId required as per interface contract");
	        SessionInformation info = this.getSessionInformation(sessionId);
	        if (info != null) {
	            if (this.logger.isTraceEnabled()) {
	                this.logger.debug("Removing session " + sessionId + " from set of registered sessions");
	            }
	            this.removeSessionInfo(sessionId);
	            Set<String> sessionsUsedByPrincipal = (Set) this.getPrincipals(((User)info.getPrincipal()).toString());
	            if (sessionsUsedByPrincipal != null) {
	                if (this.logger.isDebugEnabled()) {
	                    this.logger.debug("Removing session " + sessionId + " from principal's set of registered sessions");
	                }

	                sessionsUsedByPrincipal.remove(sessionId);
	                if (sessionsUsedByPrincipal.isEmpty()) {
	                    if (this.logger.isDebugEnabled()) {
	                        this.logger.debug("Removing principal " + info.getPrincipal() + " from registry");
	                    }

	                    this.removePrincipal(((User)info.getPrincipal()).toString());
	                }

	                if (this.logger.isTraceEnabled()) {
	                    this.logger.trace("Sessions used by '" + info.getPrincipal() + "' : " + sessionsUsedByPrincipal);
	                }

	            }
	        }
		
	}
	
	 public void putPrincipals(final String key, final Set<String> set) {
	        BoundHashOperations<String, String, Set<String>> hashOperations = redisTemplate.boundHashOps(PRINCIPALS);
	        hashOperations.put(key,set);
	    }
	
	public void removeSessionInfo(final String sessionId) {
        BoundHashOperations<String, String, SessionInformation> hashOperations = redisTemplate.boundHashOps(SESSIONIDS);
        hashOperations.delete(sessionId);
    }
	
	 public void removePrincipal(final String key) {
	        BoundHashOperations<String, String, Set<String>> hashOperations = redisTemplate.boundHashOps(PRINCIPALS);
	        hashOperations.delete(key);
	    }
	 
	 public Set<String> putIfAbsentPrincipals(final String key, final Set<String> set) {
	        BoundHashOperations<String, String, Set<String>> hashOperations = redisTemplate.boundHashOps(PRINCIPALS);
	        hashOperations.putIfAbsent(key, set);
	        return hashOperations.get(key);
	    }
	 
	 public void addSessionInfo(final String sessionId, final SessionInformation sessionInformation) {
	        BoundHashOperations<String, String, SessionInformation> hashOperations = redisTemplate.boundHashOps(SESSIONIDS);
	        hashOperations.put(sessionId, sessionInformation);
	    }


}
