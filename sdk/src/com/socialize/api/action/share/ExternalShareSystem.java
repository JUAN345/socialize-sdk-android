package com.socialize.api.action.share;

import com.socialize.api.action.ShareType;
import com.socialize.entity.Entity;
import com.socialize.entity.Share;

/**
 * @author Jason Polites
 */
public interface ExternalShareSystem {
    void reportShare(Entity entity, ShareType shareType, Share share) throws Exception;
}
