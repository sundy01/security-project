package com.sundy.service.security.inteceptor;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;

public class MyAccessDecisionManager extends AbstractAccessDecisionManager {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public MyAccessDecisionManager(
			List<AccessDecisionVoter<? extends Object>> decisionVoters) {
		super(decisionVoters);
	}

	
	
	@Override
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		
		if(configAttributes!=null && configAttributes.size()>0){
			int grant = voterProcess(authentication, object, configAttributes);
			if(grant==AccessDecisionVoter.ACCESS_DENIED || grant==AccessDecisionVoter.ACCESS_ABSTAIN){
				throw new AccessDeniedException("您无权访问当前资源");
			}
		}
	}
	
	private int voterProcess(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes) {
		
		
		int grant=AccessDecisionVoter.ACCESS_GRANTED;
		if("admin".equals(authentication.getName())){
			//判断当前登录用户是否为管理员，如果是就允许其访问所有资源，不再进行任何投票
			
			logger.info("当前认证的用户名称============"+authentication.getName());
			return grant;
		}
		
		//如果没有配置任何Voter，那么该页面就是允许访问
		for(AccessDecisionVoter voter:this.getDecisionVoters()){
			int result=voter.vote(authentication, object, configAttributes);
			if(result==AccessDecisionVoter.ACCESS_GRANTED && !(voter.getClass().isAssignableFrom(AuthenticatedVoter.class))){
				grant=AccessDecisionVoter.ACCESS_GRANTED;
				break;
			}else if(result==AccessDecisionVoter.ACCESS_DENIED){
				grant=AccessDecisionVoter.ACCESS_DENIED;
				break;
			}else if(result==AccessDecisionVoter.ACCESS_ABSTAIN){
				grant=AccessDecisionVoter.ACCESS_ABSTAIN;
			}
		}
		return grant;
	}

}
