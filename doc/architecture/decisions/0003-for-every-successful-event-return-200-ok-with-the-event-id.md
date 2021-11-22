# 3. For every successful event return 200 OK with the event ID

Date: 2021-11-22

## Status

Accepted

## Context

All of our API's exist for us to be given gp2gp management information by foundation suppliers. They need nothing back from us.
No resources are being created that could be retrieved in the future by a consumer.

## Decision

As all of our API's are REST POST requests, for a successful request the response will have a status of 200 OK and will return the event ID within the body.

