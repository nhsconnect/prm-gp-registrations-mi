# 2. Use registration ID in payload, not the path

Date: 2021-11-19

## Status

Accepted

## Context

For all of our MI events there was a discussion around whether we would include the registrationId within the URL path or the event payload, or both.  

## Decision

After a discussion around the implementation of our API we realised that we are not creating resources. Therefore, including the registrationId within the path was inappropriate, 
and we have decided to only include the registrationId within the event payload. 

## Consequences

This makes the implementation of the API easier for consumers with less repetition.
