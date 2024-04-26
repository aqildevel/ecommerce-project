
## Header:
=>  Header: HTTP headers are additional pieces of information sent along with the response or request.
They provide metadata about the HTTP message, such as the content type, content length,
caching directives, and more. Headers are key-value pairs, where the key represents the header name,
and the value represents the header value. In the provided example, .header("Custom-Header", "Value")
sets a custom header named "Custom-Header" with the value "Value" in the HTTP response.
## Status Code:
    Status Code: The status code is a three-digit integer returned by the server to indicate the outcome of the request. 
    It's a standard part of an HTTP response. Status codes are categorized into five classes
    1xx: Informational - Request received, continuing process
    2xx: Success - The action was successfully received, understood, and accepted
    3xx: Redirection - Further action must be taken to complete the request
    4xx: Client Error - The request contains bad syntax or cannot be fulfilled
    5xx: Server Error - The server failed to fulfill an apparently valid request
    In the example provided, .ok() sets the status code to 200, which falls under the 2xx category,