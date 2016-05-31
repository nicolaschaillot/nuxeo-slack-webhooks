/*
 * Copyright (c) 2015 by Keendo
 * Project : plim-channels-core-pim
 * File : UpdateChannelByUidTest.java
 * Created on 24 feb 2015 by pep
 */
package org.keendo.test.slack.operations;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.nuxeo.runtime.test.runner.Deploy;
import org.nuxeo.runtime.test.runner.Features;

import org.nuxeo.ecm.core.api.CoreSession;

import com.google.inject.Inject;

import fr.vif.plim.common.PlimCoreFeature;
import org.nuxeo.runtime.test.runner.FeaturesRunner;

import org.keendo.slack.operations.PostToSlackOperation;

/**
 * Class testing PostToSlackOperation.java
 *
 * @author pep
 */
@RunWith(FeaturesRunner.class)
@Features({ PlimCoreFeature.class })
@Deploy({ "org.keendo.slack.webhooks" })
public class PostToSlackOperationTest {

    @Inject
    protected CoreSession              documentManager;

    /**
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {

    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testSendMessageToSlack() throws Exception {

        String message = "This an automatic message sent by a jUnit test in keendo-slack-webhooks";

        PostToSlackOperation ptso = new PostToSlackOperation("#testnc", "", "", "", message);
        ptso.sendMessageToSlack();

    }

}