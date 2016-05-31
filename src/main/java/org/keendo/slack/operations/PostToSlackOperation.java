/*
 * (C) Copyright 2015 Keendo SAS (http://keendo.net/).
 *
 * Contributors:
 *     Nicolas Chaillot <nicolas.chaillot@keendo.fr>
 */

package org.keendo.slack.operations;

import flowctrl.integration.slack.SlackClientFactory;
import flowctrl.integration.slack.webhook.SlackWebhookClient;
import flowctrl.integration.slack.type.Payload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.ecm.automation.core.Constants;
import org.nuxeo.ecm.automation.core.annotations.Operation;
import org.nuxeo.ecm.automation.core.annotations.OperationMethod;
import org.nuxeo.ecm.automation.core.annotations.Param;
import org.nuxeo.ecm.automation.core.collectors.DocumentModelCollector;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.NuxeoException;

/**
 * Operation to post a message to Slack.
 *
 * @author nc
 * @sinceupdate
 */
@Operation(id = PostToSlackOperation.ID, category = Constants.CAT_DOCUMENT, label = "Post message to Slack", description = "Post a message du Slack.")
public class PostToSlackOperation {

    public static final String         ID          = "Document.PostToSlackOperation";

    private static final Log           log         = LogFactory.getLog(PostToSlackOperation.class);

    public static final String         ID_CHANNEL  = "idChannel";
    public static final String         WEBHOOK_URL = "webhookUrl";
    public static final String         CUSTOM_NAME = "customName";
    public static final String         CUSTOM_ICON_URL = "customIconUrl";

    private String                     outputState = "";


    /** Webhook URL. */
    @Param(name = "webhookUrl", required = true)
    private String                     webhookUrl;

    /** Post To Channel */
    @Param(name = "channel", required = false)
    private String                     idChannel;

    /** Custom name. */
    @Param(name = "customName", required = false)
    private String                     customName;

    /** Custom icon. */
    @Param(name = "customIconUrl", required = false)
    private String                     customIconUrl;

    /** The message to post. */
    @Param(name = "message", required = true)
    private String                     message;

    private Boolean                    isTest;


    private String defaultWebhookUrl = "https://hooks.slack.com/services/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";
    //private String defaultCustomIconUrl = "https://slack.com/img/icons/app-57.png";

    private SlackWebhookClient webhookClient;

    public PostToSlackOperation(final String idChannel, final String webhookUrl, final String customName, final String customIconUrl, final String message) {
        this.idChannel = idChannel;
        this.webhookUrl = webhookUrl;
        this.customIconUrl = customIconUrl;
        this.customName = customName;
        this.message = message;
        isTest = Boolean.TRUE;
    }

    /**
     * Main method.
     *
     * @param document the document whose life cycle has to be reinitialized.
     * @return the input document.
     * @throws NuxeoException
     */
    @OperationMethod(collector = DocumentModelCollector.class)
    public DocumentModel run(DocumentModel document) throws NuxeoException {

        if (document != null) {

            sendMessageToSlack();

        }

        return document;
    }

    public void sendMessageToSlack() {

        if (webhookUrl.isEmpty()) {
            webhookUrl = defaultWebhookUrl;
        }
        webhookClient = SlackClientFactory.createWebhookClient(webhookUrl);

        Payload payload = new Payload();
        payload.setText(message);
        payload.setChannel(idChannel);

        if (!customIconUrl.isEmpty()) {
            //customIconUrl = defaultCustomIconUrl;
            payload.setIcon_url(customIconUrl);
        }


        if (!customName.isEmpty()) {
            payload.setUsername(customName);
        }

        webhookClient.post(payload);

    }

}
