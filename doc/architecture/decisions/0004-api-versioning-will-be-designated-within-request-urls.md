# 4. API versioning will be designated within request URLs

Date: 2021-11-22

## Status

Accepted

## Context

In the future, it may be necessary to implement breaking changes to some of our API's. This could include changes to payloads, responses or a number of other things.
Therefore, we need a process to version control our API's.

## Decision

We will designate API versions by including a version number within the API URL. Example:
registration/v1/gp2gpRegistrationStarted
registration/v2/gp2gpRegistrationStarted etc...

## Consequences

This means the team can roll out new versions of an API and consumers can migrate over to the new versions over a period of time.
Once it has been confirmed that all consumers are using the newer version of an API, the older version can be removed. 
