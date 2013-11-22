import groovyx.net.http.HTTPBuilder


def load_provider_json() {
  def http = new HTTPBuilder('http://www.google.com')
//    response = HTTParty.get(URI::encode('http://localhost:8081/producer.json?valid_date=' + Time.now.httpdate))
//    if response.success?
//      JSON.parse(response.body)
//    end
}


println load_provider_json()

