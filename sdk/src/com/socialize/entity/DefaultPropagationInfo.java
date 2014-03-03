package com.socialize.entity;

import java.io.Serializable;


public class DefaultPropagationInfo implements Serializable, PropagationInfo {

	private static final long serialVersionUID = -2383065650286877555L;
	
	private String appUrl;
	private String entityUrl;
	
	/* (non-Javadoc)
	 * @see com.socialize.entity.PropagationInfo#getAppUrl()
	 */
	@Override
	public String getAppUrl() {
		return appUrl;
	}
	
	@Override
    public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}
	
	/* (non-Javadoc)
	 * @see com.socialize.entity.PropagationInfo#getEntityUrl()
	 */
	@Override
	public String getEntityUrl() {
		return entityUrl;
	}
	
	@Override
    public void setEntityUrl(String entityUrl) {
		this.entityUrl = entityUrl;
	}
}
