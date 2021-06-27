# NBSurveyApp

To run the application, we need to add key to local.property
OAUTH_CLIENT_ID="{key}"
OAUTH_CLIENT_SECRET="{key}"

### Technical problem faced
- Json serialize and deserialize
- Dagger Hilt and Retrofit doesn't support moshi-jsonapi well
- Testing hot flow: SharedFlow