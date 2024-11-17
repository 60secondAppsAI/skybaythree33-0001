import http from "../http-common"; 

class AirportService {
  getAllAirports(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/airport/airports`, searchDTO);
  }

  get(airportId) {
    return this.getRequest(`/airport/${airportId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/airport?field=${matchData}`, null);
  }

  addAirport(data) {
    return http.post("/airport/addAirport", data);
  }

  update(data) {
  	return http.post("/airport/updateAirport", data);
  }
  
  uploadImage(data,airportId) {
  	return http.postForm("/airport/uploadImage/"+airportId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new AirportService();
