import groovyx.net.http.RESTClient
import org.joda.time.format.ISODateTimeFormat

class Client {

  def load_provider_json() {
    def date = new Date()
    def http = new RESTClient("http://localhost:8081/")
    http.get(path: 'provider.json', query: ['valid_date': date]) { response, json ->
      if (response.success) {
        json
      }
    }
  }

  def process_data() {
    def data = this.load_provider_json()
    println data.toString(2)
    def value = data.count / 100
    def fmt = ISODateTimeFormat.dateTimeNoMillis()
    def date = fmt.parseDateTime(data.valid_date)
    println value
    println date
    [value, date]
  }

}


