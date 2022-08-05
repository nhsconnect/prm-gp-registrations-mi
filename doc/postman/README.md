The MI API endpoints are exported into a Postman Collection (v2.1)

There are 4 environment variables that need to be set:
* `apikey` generated through the [NHS developer portal](https://portal.developer.nhs.uk/my-applications) - Application name: *GP Registrations Management Information API (Integration Testing Environment)*
* `mi_api_url` which can be found in the [NHS API catalogue: GP Registrations Management Information API](https://digital.nhs.uk/developer/api-catalogue/gp-registrations-management-information#api-description__environments-and-testing)
* `conversation_id` which will populate the conversationId field that will match different events together
* `a_datetime` which will populate the eventGeneratedDateTime and transferEventDateTime