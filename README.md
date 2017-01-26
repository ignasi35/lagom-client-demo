# lagom-client-demo

Code demo-ing how Lagom Descriptor interfaces can help consume 3rd party REST APIs.

To run this app you will nee `sbt` and internet connectivity. Run the code via:

```(bash)
git clone git@github.com:ignasi35/lagom-client-demo.git
cd lagom-client-demo
sbt lagom-client-main/run
```

## Contents

**httpbin-api** contains the API specification of the http://httpbin.org API

**lagom-client-main** contains a main class that uses *httpbin-api* to invoke the remote endpoint.
