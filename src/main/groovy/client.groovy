import groovyx.net.http.RESTClient


def load_provider_json() {
  def date = new Date()
  def http = new RESTClient("http://localhost:8081/")
  http.get(path: 'provider.json', query: ['valid_date': date]) { response, json ->
    if (response.success) {
      json
    }
  }
}

println load_provider_json().toString(2)

