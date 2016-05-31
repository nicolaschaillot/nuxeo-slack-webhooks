#Nuxeo Slack WebHooks

## General information and motivation
This plugin contains a operation usable in an automation chain.
It allows sending webhooks to Slack. For example, to notify users of events in Nuxeo platform.

### Getting Started

#### Params
* Webhook URL. : @Param(name = "webhookUrl", required = true)
* Post To Channel : @Param(name = "channel", required = false)
* Custom name. : @Param(name = "customName", required = false)
* Custom icon. : @Param(name = "customIconUrl", required = false)
* The message to post. : @Param(name = "message", required = true)

### Plugin Configuration

### Slack
* https://slack.com/
* https://api.slack.com/incoming-webhooks

### Java client
* https://github.com/allbegray/slack-api
