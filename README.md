# Currency Rate REST API using Spring Boot

An API which lets you retrieve the latest currency exchange rates for various currencies.
The REST API is built using Java and the Spring Boot framework.
The API provides the following features:

- Retrieval of latest currency exchange rates: Users can query the API to get the latest exchange rates for various currencies.

- Support for multiple base currencies: The API allows users to specify the base currency for the exchange rates.

- Integration with a currency exchange rate provider: The API integrates with a third-party currency exchange rate provider to fetch real-time exchange rate data.
#
# API Endpoints

- /rates/{base}: Retrieves +100 latest exchange rates for a specific base currency and returns the result in JSON format
- /rate/{base}/{target} :Retrieves a single exchange rate for a specific base currency and target,returns the result in JSON format

