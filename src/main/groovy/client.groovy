import groovyx.net.http.HTTPBuilder


def load_provider_json() {
  def date = new Date()
  def http = new HTTPBuilder("http://localhost:8081/")
  http.get(path: 'provider.json', query: ['valid_date': date]) { response, json ->
    if (response.success) {
      println json.dump()
      json
    }
  }
}

println load_provider_json()

