# 4. API versioning will be designated within request URLs

Date: 2021-11-22

## Status

Accepted

## Context

In the future, it may be necessary to implement breaking changes to some of our API's. This could include changes to payloads, responses or a number of other things.
Therefore, we need a process to version control our API's.

## Options considered

There are multiple techniques available to version our APIs:

- URI path
- Custom request headers
- Accept header

## Decision

We will designate API versions by including a version number within the API URI. Example:
registration/v1/gp2gpRegistrationStarted
registration/v2/gp2gpRegistrationStarted etc...

This was chosen as there are some drawbacks to using headers:

- Less accessible when exploring the API via a browser or documentation
- More difficult and time-consuming to implement as a producer and consumer
- Less obvious to determine when consumers have upgraded to newer versions and older versions can be deleted

## Consequences

This means the team can easily roll out new versions of an API and consumers can migrate over to the new versions over a period of time.
Once it has been confirmed that all consumers are using the newer version of an API, the older version can be removed. 
