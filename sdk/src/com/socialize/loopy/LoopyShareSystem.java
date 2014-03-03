package com.socialize.loopy;

import com.sharethis.loopy.sdk.Item;
import com.sharethis.loopy.sdk.Loopy;
import com.sharethis.loopy.sdk.ShareCallback;
import com.socialize.api.action.ShareType;
import com.socialize.api.action.share.ExternalShareSystem;
import com.socialize.entity.Entity;
import com.socialize.entity.PropagationInfo;
import com.socialize.entity.Share;
import com.socialize.log.SocializeLogger;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Jason Polites
 */
public class LoopyShareSystem implements ExternalShareSystem {

    private SocializeLogger logger;

    @Override
    public void reportShare(Entity entity, ShareType shareType, Share share) throws Exception {

        final PropagationInfo propagationInfo = share.getPropagationInfoResponse().getPropagationInfo(shareType);

        if(propagationInfo != null) {
            // We are in an an async block so we can block here (indeed we need to)
            final CountDownLatch latch = new CountDownLatch(1);

            Loopy.shorten(propagationInfo.getEntityUrl(), new ShareCallback() {
                @Override
                public void onResult(Item item, Throwable error) {

                    if(error != null && item.getShortlink() != null) {
                        propagationInfo.setEntityUrl(item.getShortlink());
                    } else if(error != null) {
                        logger.error("Error generating loopy shortlink", error);
                    }

                    latch.countDown();
                }

            });

            latch.await(5, TimeUnit.SECONDS);

            Item item = new Item();
            item.setUrl(propagationInfo.getEntityUrl());
            item.setTitle( entity.getDisplayName() );

            // Fire and forget
            Loopy.reportShare(item, shareType.name());
        }
    }

    public void setLogger(SocializeLogger logger) {
        this.logger = logger;
    }
}
